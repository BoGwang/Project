package com.koitt.board.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserInfo;
import com.koitt.board.service.FileService;
import com.koitt.board.service.UserInfoService;

@Controller
public class UserWebController {

	private static final String UPLOAD_FOLDER = "/avatar";

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model) {
		
		String email = this.getPrincipal();
		
		if (email != null && !email.trim().isEmpty()) {
			UserInfo item = userInfoService.detail(email);
			model.addAttribute("userInfo", item);
		}

		return "/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "user/login";
	}

	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?action=logout";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		model.addAttribute("userInfo", this.getPrincipal());
		return "user/admin";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String accessDeniedPage(Model model) {
		model.addAttribute("email", this.getPrincipal());
		return "user/access-denied";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinPage() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String newUser(HttpServletRequest request,
			String email,
			String password,
			String name,
			@RequestParam("avatar") MultipartFile avatar)
					throws CommonException, Exception {

		UserInfo user = new UserInfo();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);

		// 최상위 경로 밑에 upload 폴더의 경로를 가져온다.
		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);

		// MultipartFile 객체에서 파일명을 가져온다.
		String originalName = avatar.getOriginalFilename();

		// upload 폴더가 없다면, upload 폴더 생성
		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdir();
		}

		// avatar 객체를 이용하여, 파일을 서버에 전송
		if (avatar != null && !avatar.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String fileName = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = fileName
					+ Long.toHexString(System.currentTimeMillis())
					+ ext;
			avatar.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			user.setAvatar(uploadFilename);
		}

		userInfoService.newUser(user);

		return "redirect:login";
	}

	@RequestMapping(value = "/user/setting" , method = RequestMethod.GET)
	public String setting() {
		return "user/setting";
	}

	@RequestMapping(value = "/user/delete" , method = RequestMethod.GET)
	public String delete() {
		return "user/delete";
	}

	@RequestMapping(value = "/user/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request, String password) throws CommonException, UnsupportedEncodingException {
		String email = this.getPrincipal();
		String filename = userInfoService.delete(email, password);
		if (filename != null && !filename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, filename);
		}

		return "redirect:/logout";
	}

	// 사용자 수정하기 화면
	@RequestMapping(value = "/user/modify", method = RequestMethod.GET)
	public String modify(Model model) throws CommonException {

		UserInfo item = null;

		String email = this.getPrincipal();
		item = userInfoService.detail(email);

		model.addAttribute("item", item);

		return "user/modify";
	}

	// 사용자 수정 후, 사용자 설정 화면으로 이동
	@RequestMapping(value = "/user/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request,
			Integer id,
			String oldPassword,
			String newPassword,
			String name,
			@RequestParam("avatar") MultipartFile avatar)
					throws CommonException,Exception {

		// 기존 비밀번호 검사 후 수정할지 결정
		boolean isMatched = userInfoService.isPasswordMatched(id, oldPassword);
		if (!isMatched) {
			return "redirect:/user/modify?action=error-password";
		}

		UserInfo user = new UserInfo();
		user.setId(id);
		user.setPassword(encoder.encode(newPassword));
		user.setName(name);

		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		String originalName = avatar.getOriginalFilename();

		// avatar 객체를 이용하여, 파일을 서버에 전송
		if (avatar != null && !avatar.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String fname = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = fname
					+ Long.toHexString(System.currentTimeMillis())
					+ ext;
			avatar.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			user.setAvatar(uploadFilename);
		}

		String oldFilename = userInfoService.modify(user);
		if (oldFilename != null && !oldFilename.trim().isEmpty()) {
			fileService.remove(request, UPLOAD_FOLDER, oldFilename);
		}

		return "redirect:/user/setting";
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
