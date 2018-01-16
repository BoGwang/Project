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

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Coupon;
import com.koitt.board.model.Movie;
import com.koitt.board.service.CouponService;
import com.koitt.board.service.UserInfoService;

@RestController
@RequestMapping("/rest")
public class CouponRestController {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CouponService couponService;

	@Autowired
	private UserInfoService userInfoService;


	// 쿠폰 목록
	@RequestMapping(value = "/coupon", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Coupon>> list() throws CommonException {
		List<Coupon> list = null;

		list = couponService.list();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<Coupon>>(list, HttpStatus.OK);
		}

		return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);
	}

	// 쿠폰 상세보기
	@RequestMapping(value = "/coupon/{cno}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Coupon> detail(@PathVariable("cno") String cno, UriComponentsBuilder ucBuilder)
			throws CommonException, Exception {

		Coupon coupon = null;

		coupon = couponService.detail(cno);

	
		return new ResponseEntity<Coupon>(HttpStatus.NOT_FOUND);
	}


	// 쿠폰 받기
	@RequestMapping(value = "/coupon", method = RequestMethod.POST)
	public ResponseEntity<Void> newCoupon(HttpServletRequest request, @PathVariable("cno") int cno, int id 
		 , String email,
			String sale, 
		UriComponentsBuilder ucBuilder)
			throws CommonException, Exception {

		Coupon coupon = new Coupon();
		coupon.setCno(cno);
		coupon.setId(id);
		coupon.setEmail(email);
		coupon.setSale(sale);
		
		

		couponService.newCoupon(coupon);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/coupon/{cno}").buildAndExpand(coupon.getCno()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
}
