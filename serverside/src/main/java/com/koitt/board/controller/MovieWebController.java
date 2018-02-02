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
@RequestMapping("/movie")
public class MovieWebController {

	private static final String UPLOAD_FOLDER = "/movieposter";

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private MovieService movieService;

	@Autowired
	private FileService fileService;

	@Autowired
	private UserInfoService userInfoService;

	// 영화 목록 화면
	@RequestMapping(value = "/movielist.do", method = RequestMethod.GET)
	public String list(Model model) throws CommonException {
		List<Movie> mlist = null;

		mlist = movieService.listAll();

		logger.debug(mlist);

		model.addAttribute("mlist", mlist);
		return "movie-list";
	}

	// 글 상세 화면
	@RequestMapping(value = "/moviedetail.do", method = RequestMethod.GET)
	public String detail(Model model, @RequestParam(value = "movieNo", required = true) String movieNo)
			throws CommonException, Exception {

		Movie movie = null;
		String filename = null;

		movie = movieService.detail(movieNo);
		filename = movie.getMovieposter();
		if (filename != null && !filename.trim().isEmpty()) {
			filename = URLDecoder.decode(filename, "UTF-8");
		}

		model.addAttribute("mlist", movie);
		model.addAttribute("filename", filename);

		return "movie-detail"; // /WEB-INF/views/detail.jsp 페이지로 이동
	}

	// 영화 작성 화면
	@RequestMapping(value = "/movienew.do", method = RequestMethod.GET)
	public String newMovie(Model model) {

		String email = this.getPrincipal();
		UserInfo movie = userInfoService.detail(email);

		model.addAttribute("id", movie.getId());
		model.addAttribute("email", movie.getEmail());

		return "movie-new";
	}

	// 영화 작성 후, 영화 목록 화면으로 이동
	@RequestMapping(value = "/movienew.do", method = RequestMethod.POST)
	public String newMovie(HttpServletRequest request, Integer id, String movietitle, String moviecontent,
			String moviedirector, @RequestParam("movieposter") MultipartFile movieposter)
			throws CommonException, Exception {

		Movie movie = new Movie();
		movie.setId(id);
		movie.setMoviedirector(moviedirector);
		movie.setMovietitle(movietitle);
		movie.setMoviecontent(moviecontent);

		// 최상위 경로 밑에 upload 폴더의 경로를 가져온다.
		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);

		// MultipartFile 객체에서 파일명을 가져온다.
		String originalName = movieposter.getOriginalFilename();

		// upload 폴더가 없다면, upload 폴더 생성
		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdir();
		}

		// attachment 객체를 이용하여, 파일을 서버에 전송
		if (movieposter != null && !movieposter.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String name = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = name + Long.toHexString(System.currentTimeMillis()) + ext;
			movieposter.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			movie.setMovieposter(uploadFilename);
		}

		movieService.newMovie(movie);

		return "redirect:movielist.do";
	}

	// 글 수정하기 화면
	@RequestMapping(value = "/moviemodify.do", method = RequestMethod.GET)
	public String modify(Model model, @RequestParam(value = "movieNo", required = true) String movieNo)
			throws CommonException {

		Movie mlist = null;

		mlist = movieService.detail(movieNo);

		model.addAttribute("mlist", mlist);

		return "movie-modify";
	}

	// 글 삭제 확인 화면
	@RequestMapping(value = "/movieremove.do", method = RequestMethod.GET)
	public String removeConfirm(Model model, @RequestParam(value = "movieNo", required = true) String movieNo) {

		model.addAttribute("movieNo", movieNo);

		return "movie-remove";
	}

	// 글 삭제 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/movieremove.do", method = RequestMethod.POST)
	public String remove(HttpServletRequest request, @RequestParam(value = "movieNo", required = true) String movieNo)
			throws CommonException, UnsupportedEncodingException {

		String filename = movieService.remove(movieNo);
		if (filename != null && !filename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, filename);
		}
		return "redirect:movielist.do";
	}

	// 글 수정 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/moviemodify.do", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, 
			Integer movieNo, 
			String movietitle, 
			String moviecontent,
			String moviedirector,
			@RequestParam("movieposter") MultipartFile movieposter, String password)
			throws CommonException, Exception {

		Movie movie = new Movie();
		movie.setMovieNo(movieNo);
		movie.setMovietitle(movietitle);
		movie.setMoviecontent(moviecontent);
		movie.setMoviedirector(moviedirector);

		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		String originalName = movieposter.getOriginalFilename();

		// attachment 객체를 이용하여, 파일을 서버에 전송
		if (movieposter != null && !movieposter.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String name = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = name + Long.toHexString(System.currentTimeMillis()) + ext;
			movieposter.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			movie.setMovieposter(uploadFilename);
		}

		String newFilename = movieService.modifyMovie(movie);
		if (newFilename != null && !newFilename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, newFilename);
		}

		return "redirect:movielist.do";
	}

	// 파일 내려받기
	@RequestMapping(value = "/download.do", method = RequestMethod.GET, params = "filename")
	public void download(HttpServletRequest request, HttpServletResponse response, String filename)
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
		} else {
			username = principal.toString();
		}

		return username;
	}
}
