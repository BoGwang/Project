package com.koitt.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Coupon;
import com.koitt.board.model.Reservation;
import com.koitt.board.model.UserInfo;
import com.koitt.board.service.CouponService;
import com.koitt.board.service.UserInfoService;

@Controller
@RequestMapping("/rest")
public class CouponWebController {


	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CouponService couponService;

	
	@Autowired
	private UserInfoService userInfoService;

	// 쿠폰 목록 화면
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list(Model model) throws CommonException {
		List<Coupon> list = null;

		list = couponService.list();
		
		logger.debug(list);
		
		model.addAttribute("list", list);
		return "list";
	}

	// 쿠폰 상세 화면
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(Model model, 
			@RequestParam(value = "cno", required=true) String cno)
					throws CommonException, Exception {

		Coupon coupon = null;
		
		coupon = couponService.detail(cno);
		

		model.addAttribute("item", coupon);

		return "detail";	// /WEB-INF/views/detail.jsp 페이지로 이동
	}

	
	// 영화 작성 화면
		@RequestMapping(value = "/new.do", method = RequestMethod.GET)
		public String newCoupon(Model model) {
			
			String email = this.getPrincipal();
			UserInfo item = userInfoService.detail(email);
			
			model.addAttribute("id", item.getId());
			model.addAttribute("email", item.getEmail());

			return "new";
		}
		
		
		// 쿠폰 받은후, 영화 목록 화면으로 이동
		@RequestMapping(value = "/new.do", method = RequestMethod.POST)
		public String newCoupon(HttpServletRequest request, 
				Integer cno, 
				Integer id, 
				String email,
				String sale) throws CommonException, Exception {
			
			Coupon coupon = new Coupon();
			coupon.setCno(cno);
			coupon.setId(id);
			coupon.setEmail(email);
			coupon.setSale(sale);
			
			couponService.newCoupon(coupon);
			
			return "redirect:list.do";
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
