package com.koitt.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;
import com.koitt.board.model.UserInfo;
import com.koitt.board.service.FileService;
import com.koitt.board.service.MovieService;
import com.koitt.board.service.UserInfoService;

@RestController
@RequestMapping("/rest")
public class MovieRestController {

	private static final String UPLOAD_FOLDER = "/poster";

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private MovieService movieService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private FileService fileService;

	// 영화 목록
	@RequestMapping(value = "/movie", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Movie>> list() throws CommonException {
		List<Movie> list = null;

		list = movieService.list();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<Movie>>(list, HttpStatus.OK);
		}

		return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
	}

	// 영화 상세보기
	@RequestMapping(value = "/movie/{mno}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Movie> detail(@PathVariable("mno") String mno, UriComponentsBuilder ucBuilder)
			throws CommonException, Exception {

		Movie movie = null;
		String filename = null;

		movie = movieService.detail(mno);

		if (movie != null && movie.getMno() > 0) {
			filename = movie.getMposter();
			if (filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename, "UTF-8");
				movie.setMposter(filename);
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/rest/movie/mposter/{mposter}").buildAndExpand(movie.getMposter()).toUri());

			return new ResponseEntity<Movie>(movie, headers, HttpStatus.OK);
		}

		return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
	}

	// 파일 내려받기
	@RequestMapping(value = "/movie/mposter/{mposter:.+}", method = RequestMethod.GET)
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

	// 영화 추가
	@RequestMapping(value = "/movie", method = RequestMethod.POST)
	public ResponseEntity<Void> newMovie(HttpServletRequest request, @PathVariable("mno") int mno, String mtitle,
			String mcontent, @RequestParam("mposter") MultipartFile mposter, UriComponentsBuilder ucBuilder)
			throws CommonException, Exception {

		Movie movie = new Movie();
		movie.setMno(mno);
		movie.setMtitle(mtitle);
		movie.setMcontent(mcontent);

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
			movie.setMposter(uploadFilename);
		}

		movieService.newMovie(movie);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/movie/{mno}").buildAndExpand(movie.getMno()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/*
	 * 글 수정 https://blog.outsider.ne.kr/1001 참고 1. Multipart/form-data 전송 시에는 POST로
	 * 전송해야 한다. 2. 만약 PUT을 이용해서 전송하고 싶다면, input type file에 대해서는 따로 POST로 전송하고, 나머지
	 * 일반 정보는 PUT으로 따로 전송한다.
	 */
	@RequestMapping(value = "/movie/{mno}", method = RequestMethod.POST)
	public ResponseEntity<Void> modify(HttpServletRequest request, @PathVariable("mno") int mno, String mtitle,
			String mcontent, @RequestParam("mposter") MultipartFile mposter, String password,
			UriComponentsBuilder ucBuilder) throws CommonException, Exception {

		// 비밀번호 비교해서 같지 않다면 오류메시지 출력
		boolean isMatched = userInfoService.isMovieMatched(mno, password);
		if (!isMatched) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

		Movie movie = new Movie();
		movie.setMno(mno);
		movie.setMtitle(mtitle);
		movie.setMcontent(mcontent);

		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		String originalName = mposter.getOriginalFilename();

		// attachment 객체를 이용하여, 파일을 서버에 전송
		if (mposter != null && !mposter.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String name = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = name + Long.toHexString(System.currentTimeMillis()) + ext;
			mposter.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			movie.setMposter(uploadFilename);
		}

		String newFilename = movieService.modify(movie);
		if (newFilename != null && !newFilename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, newFilename);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/*
	 * 글 삭제
	 * 
	 * @RequestHeader("Authorization")을 사용하면 "Basic encodeBase64(email:password)"
	 * 정보를 가져올 수 있다.
	 */
	@RequestMapping(value = "/movie/{mno}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remove(HttpServletRequest request, @PathVariable("mno") String mno,
			@RequestHeader("Authorization") String authorization) throws CommonException, UnsupportedEncodingException {

		String base64Credentials = authorization.split(" ")[1];
		String plainCredentials = new String(Base64.decodeBase64(base64Credentials));
		String password = plainCredentials.split(":")[1];

		boolean isMatched = userInfoService.isBoardMatched(Integer.parseInt(mno), password);
		if (!isMatched) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

		String filename = movieService.remove(mno);
		if (filename != null && !filename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, filename);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
