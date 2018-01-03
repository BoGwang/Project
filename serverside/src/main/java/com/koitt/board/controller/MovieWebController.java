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

import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.Movie;
import com.koitt.board.model.UserInfo;
import com.koitt.board.service.FileService;
import com.koitt.board.service.MovieService;
import com.koitt.board.service.UserInfoService;

@Controller
@RequestMapping("/rest")
public class MovieWebController {

	private static final String UPLOAD_FOLDER = "/poster";

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private MovieService movieService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserInfoService userInfoService;

	// 영화 목록 화면
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list(Model model) throws CommonException {
		List<Movie> list = null;

		list = movieService.list();
		
		logger.debug(list);
		
		model.addAttribute("list", list);
		return "list";
	}

	// 글 상세 화면
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(Model model, 
			@RequestParam(value = "mno", required=true) String mno)
					throws CommonException, Exception {

		Movie movie = null;
		String filename = null;

		movie = movieService.detail(mno);
		filename = movie.getMposter();
		if (filename != null && !filename.trim().isEmpty()) {
			filename = URLDecoder.decode(filename, "UTF-8");
		}

		model.addAttribute("item", movie);
		model.addAttribute("filename", filename);

		return "detail";	// /WEB-INF/views/detail.jsp 페이지로 이동
	}

	// 영화 작성 화면
	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public String newMovie(Model model) {
		
		String email = this.getPrincipal();
		UserInfo item = userInfoService.detail(email);
		
		model.addAttribute("id", item.getId());
		model.addAttribute("email", item.getEmail());

		return "new";
	}

	// 영화 작성 후, 영화 목록 화면으로 이동
	@RequestMapping(value = "/new.do", method = RequestMethod.POST)
	public String newMovie(HttpServletRequest request,
			Integer mno,
			String mtitle,
			String mcontent,
			String director,
			@RequestParam("mposter") MultipartFile mposter)
					throws CommonException, Exception {

		Movie movie = new Movie();
		movie.setMno(mno);
		movie.setMtitle(mtitle);
		movie.setDirector(director);
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
			String uploadFilename = name
					+ Long.toHexString(System.currentTimeMillis())
					+ ext;
			mposter.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			movie.setMposter(uploadFilename);
		}

		movieService.newMovie(movie);

		return "redirect:list.do";
	}

	// 글 삭제 확인 화면
	@RequestMapping(value = "/remove.do", method = RequestMethod.GET)
	public String removeConfirm(Model model,
			@RequestParam(value = "mno", required = true) String mno) {

		model.addAttribute("mno", mno);

		return "remove-confirm";
	}

	// 글 삭제 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/remove.do", method = RequestMethod.POST)
	public String remove(HttpServletRequest request,
			@RequestParam(value = "mno", required = true) String mno,
			String password)
					throws CommonException, UnsupportedEncodingException {
		
		boolean isMatched = userInfoService.isMovieMatched(Integer.parseInt(mno), password);
		if (!isMatched) {
			return "redirect:/board/remove.do?mno=" + mno + "&action=error-password";
		}

		String filename = movieService.remove(mno);
		if (filename != null && !filename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, filename);
		}

		return "redirect:list.do";
	}

	// 글 수정하기 화면
	@RequestMapping(value = "/modify.do", method = RequestMethod.GET)
	public String modify(Model model,
			@RequestParam(value = "mno", required = true) String mno)
					throws CommonException {

		Movie item = null;

		item = movieService.detail(mno);

		model.addAttribute("item", item);

		return "modify";
	}

	// 글 수정 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public String modify(HttpServletRequest request,
			int mno,
			String mtitle,
			String mcontent,
			String director,
			@RequestParam("mposter") MultipartFile mposter,
			String password)
					throws CommonException, Exception {
		
		// 비밀번호 비교해서 같지 않다면 오류메시지 출력
		boolean isMatched = userInfoService.isMovieMatched(mno, password);
		if (!isMatched) {
			return "redirect:/board/modify.do?mno=" + mno + "&action=error-password";
		}

		Movie movie = new Movie();
		movie.setMno(mno);
		movie.setMtitle(mtitle);
		movie.setMcontent(mcontent);
		movie.setDirector(director);

		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		String originalName = mposter.getOriginalFilename();

		// attachment 객체를 이용하여, 파일을 서버에 전송
		if (mposter != null && !mposter.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String name = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = name
					+ Long.toHexString(System.currentTimeMillis())
					+ ext;
			mposter.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			movie.setMposter(uploadFilename);
		}

		String newFilename = movieService.modify(movie);
		if (newFilename != null && !newFilename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, newFilename);
		}

		return "redirect:list.do";
	}

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
