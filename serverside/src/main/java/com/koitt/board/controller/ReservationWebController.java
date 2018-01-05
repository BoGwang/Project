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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;
import com.koitt.board.model.Reservation;
import com.koitt.board.model.UserInfo;
import com.koitt.board.service.FileService;
import com.koitt.board.service.ReservationService;
import com.koitt.board.service.UserInfoService;

@Controller
@RequestMapping("/rest")
public class ReservationWebController {

	private static final String UPLOAD_FOLDER = "/poster";

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserInfoService userInfoService;

	// 예매 목록 화면
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list(Model model) throws CommonException {
		List<Reservation> list = null;

		list = reservationService.list();
		
		logger.debug(list);
		
		model.addAttribute("list", list);
		return "list";
	}

	// 예매 상세 화면
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(Model model, 
			@RequestParam(value = "rno", required=true) String rno)
					throws CommonException, Exception {

		Reservation reservation = null;
		String filename = null;

		reservation = reservationService.detail(rno);
		filename = reservation.getMposter();
		if (filename != null && !filename.trim().isEmpty()) {
			filename = URLDecoder.decode(filename, "UTF-8");
		}

		model.addAttribute("item", reservation);
		model.addAttribute("filename", filename);

		return "detail";	// /WEB-INF/views/detail.jsp 페이지로 이동
	}

	

	

	// 예매 취소 확인 화면
	@RequestMapping(value = "/remove.do", method = RequestMethod.GET)
	public String removeConfirm(Model model,
			@RequestParam(value = "rno", required = true) String rno) {

		model.addAttribute("rno", rno);

		return "remove-confirm";
	}

	// 예매 취소 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/remove.do", method = RequestMethod.POST)
	public String remove(HttpServletRequest request,
			@RequestParam(value = "rno", required = true) String rno,
			String password)
					throws CommonException, UnsupportedEncodingException {
		
		boolean isMatched = userInfoService.isReservationMatched(Integer.parseInt(rno), password);
		if (!isMatched) {
			return "redirect:/reservation/remove.do?rno=" + rno + "&action=error-password";
		}

		String filename = reservationService.remove(rno);
		if (filename != null && !filename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, filename);
		}

		return "redirect:list.do";
	}

	// 글 수정하기 화면




	// 파일 내려받기
	@RequestMapping(value = "/download.do", method = RequestMethod.GET, params="filename")
	public void download(HttpServletRequest request, 
			HttpServletResponse response, String filename)
					throws CommonException {

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
			response.setHeader("Content-Disposition", 
					"attachment; filename=" + filename + ";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Length", Integer.toString(fis.available()));
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			/*
			 * Connection Stream: ServletOutputStream
			 * Chain Stream: BufferedOutputStream
			 */
			bos = new BufferedOutputStream(response.getOutputStream());

			// 서버에 있는 파일을 읽어서 (fis), 클라이언트에게 파일을 전송(bos)
			while ( (length = fis.read(buff)) > 0) {
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

	@ExceptionHandler(CommonException.class)
	public String handleException(CommonException e) {
		logger.debug(e.getMessage());
		return "exception";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		logger.debug(e.getMessage());
		return "exception-common";
	}

	// 현재 접속한 사용자의 email 리턴
	private String getPrincipal() {
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		else {
			username = principal.toString();
		}

		return username;
	}
}
