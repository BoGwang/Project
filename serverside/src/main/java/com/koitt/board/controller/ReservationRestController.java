package com.koitt.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;
import com.koitt.board.model.Reservation;
import com.koitt.board.service.FileService;
import com.koitt.board.service.ReservationService;
import com.koitt.board.service.UserInfoService;

@RestController
@RequestMapping("/rest")
public class ReservationRestController {

	private static final String UPLOAD_FOLDER = "/poster";

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private FileService fileService;

	// 영화 목록
	@RequestMapping(value = "/reservation", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Reservation>> list() throws CommonException {
		List<Reservation> list = null;

		list = reservationService.list();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<Reservation>>(list, HttpStatus.OK);
		}

		return new ResponseEntity<List<Reservation>>(HttpStatus.NO_CONTENT);
	}

	// 예매 상세보기
	@RequestMapping(value = "/reservation/{rno}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Reservation> detail(@PathVariable("rno") String rno, UriComponentsBuilder ucBuilder)
			throws CommonException, Exception {

		Reservation reservation = null;
		String filename = null;

		reservation = reservationService.detail(rno);

		if (reservation != null && reservation.getRno() > 0) {
			filename = reservation.getMposter();
			if (filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename, "UTF-8");
				reservation.setMposter(filename);
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/rest/reservation/mposter/{mposter}").buildAndExpand(reservation.getMposter()).toUri());

			return new ResponseEntity<Reservation>(reservation, headers, HttpStatus.OK);
		}

		return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
	}

	// 파일 내려받기
	@RequestMapping(value = "/reservation/mposter/{mposter:.+}", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("mposter") String filename) throws CommonException {

		int length = 0;
		byte[] buff = new byte[1024];

		// 서버에 저장된 파일 경로 불러오기
		String directory = request.getServletContext().getRealPath(UPLOAD_FOLDER);

		// 요청한 파일명으로 실제 파일을 객체화 하기
		File file = new File(directory, filename);

		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(file);

			// 다운받을 때, 한글 깨짐현상 수정
			filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename + ";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Length", Integer.toString(fis.available()));
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			/*
			 * Connection Stream: ServletOutputStream Chain Stream: BufferedOutputStream
			 */
			bos = new BufferedOutputStream(response.getOutputStream());

			// 서버에 있는 파일을 읽어서 (fis), 클라이언트에게 파일을 전송(bos)
			while ((length = fis.read(buff)) > 0) {
				bos.write(buff, 0, length);
			}

			// 변기 물내린다는 뜻, 버퍼에 남아있는 정보를 보내준다.
			bos.flush();

		} catch (Exception e) {
			throw new CommonException("E12: 파일 내려받기 실패");

		} finally {
			try {
				bos.close();
				fis.close();

			} catch (IOException e) {
				logger.debug(e.getMessage());
			}
		}
	}

	// 예매 추가
	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public ResponseEntity<Void> newReservation(HttpServletRequest request, String mscr, int mno,
			Date rdate,
			String mcontent, @RequestParam("mposter") MultipartFile mposter, UriComponentsBuilder ucBuilder)
			throws CommonException, Exception {

		Reservation reservation = new Reservation();
		reservation.setMno(mno);
		reservation.setMscr(mscr);
		reservation.setRdate(rdate);
		

		// 최상위 경로 밑에 upload 폴더의 경로를 가져온다.
		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);

		// MultipartFile 객체에서 파일명을 가져온다.
		String originalName = mposter.getOriginalFilename();

		// upload 폴더가 없다면, upload 폴더 생성
		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdir();
		}

		// attachment 객체를 이용하여, 파일을 서버에 전송
		if (mposter != null && !mposter.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String name = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = name + Long.toHexString(System.currentTimeMillis()) + ext;
			mposter.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			reservation.setMposter(uploadFilename);
		}

		reservationService.newReservation(reservation);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/reservation/{rno}").buildAndExpand(reservation.getRno()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


	/*
	 * 글 삭제
	 * 
	 * @RequestHeader("Authorization")을 사용하면 "Basic encodeBase64(email:password)"
	 * 정보를 가져올 수 있다.
	 */
	@RequestMapping(value = "/reservation/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remove(HttpServletRequest request, @PathVariable("rno") String rno,
			@RequestHeader("Authorization") String authorization) throws CommonException, UnsupportedEncodingException {

		String base64Credentials = authorization.split(" ")[1];
		String plainCredentials = new String(Base64.decodeBase64(base64Credentials));
		String password = plainCredentials.split(":")[1];

		boolean isMatched = userInfoService.isBoardMatched(Integer.parseInt(rno), password);
		if (!isMatched) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

		String filename = reservationService.remove(rno);
		if (filename != null && !filename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, filename);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
