package com.koitt.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.Schedule;
import com.koitt.board.model.Theater;
import com.koitt.board.model.Ticket;
import com.koitt.board.model.UserInfo;
import com.koitt.board.service.MovieService;
import com.koitt.board.service.ScheduleService;
import com.koitt.board.service.UserInfoService;

@Controller
@RequestMapping("/schedule")
public class ScheduleWebController {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private MovieService movieService;



	// 영화관 수정
	@RequestMapping("/theatermodify.do")
	@ResponseBody
	public int modifyTheater(@ModelAttribute Theater bas) {
		return scheduleService.modifyTheater(bas);
	}

	// 상영관 목록 화면
	@RequestMapping(value = "/theaterlist.do", method = RequestMethod.GET)
	public String listTheater(Model model) throws CommonException {
		List<Theater> thelist = null;

		thelist = scheduleService.Theaterlist();

		logger.debug(thelist);

		model.addAttribute("thelist", thelist);
		return "theater-list";
	}

	// 스케줄 목록 화면
	@RequestMapping(value = "/schedulelist.do", method = RequestMethod.GET)
	public String listSchedule(Model model) throws CommonException {
		List<Schedule> schlist = null;

		schlist = scheduleService.Schedulelist();

		logger.debug(schlist);

		model.addAttribute("schlist", schlist);
		return "scheduleList";
	}

	// 티켓 목록 화면
	@RequestMapping(value = "/ticketlist.do", method = RequestMethod.GET)
	public String listTicket(Model model) throws CommonException {
		List<Ticket> tlist = null;

		tlist = scheduleService.Ticketlist();

		logger.debug(tlist);

		model.addAttribute("tlist", tlist);
		return "ticketList";
	}

	// 영화관 상세 화면
	@RequestMapping(value = "/theaterdetail.do", method = RequestMethod.GET)
	public String detailTheater(Model model, @RequestParam(value = "theNo", required = true) String theNo)
			throws CommonException, Exception {

		Theater theater = null;

		theater = scheduleService.detailTheater(theNo);

		model.addAttribute("item", theater);

		return "theater-detail"; // /WEB-INF/views/detail.jsp 페이지로 이동
	}



	// 스케줄 상세 화면
	@RequestMapping(value = "/scheduledetail.do", method = RequestMethod.GET)
	public String detailSchedule(Model model, @RequestParam(value = "schNo", required = true) String schNo)
			throws CommonException, Exception {

		Schedule schedule = null;

		schedule = scheduleService.detailSchedule(schNo);

		model.addAttribute("item", schedule);

		return "schedule-detail";
	}
	
	// 티켓 상세 화면
	@RequestMapping(value = "/ticketdetail.do", method = RequestMethod.GET)
	public String detailTicket(Model model, @RequestParam(value = "ticNo", required = true) String ticNo)
			throws CommonException, Exception {

		Ticket ticket = null;

		ticket = scheduleService.detailTicket(ticNo);

		model.addAttribute("ticket", ticket);

		return "ticket-detail";
	}

	@RequestMapping(value = "/ticket.do", method = RequestMethod.GET)
	public String reserve(HttpSession session, Model model,
			@RequestParam(value = "movieNo", required = true) Integer movieNo) {

		if (session.getAttribute("id") != null) {
			model.addAttribute("movieNo", movieNo);
			return "ticket";
		}
		return "reservComplete";
	}

	@RequestMapping(value = "/ticket", method = RequestMethod.POST)
	public String reserve(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Integer movieNo, Integer ticNo, Integer id, Integer scNo, Integer schNo, String ticSeatno, Integer theNo,
			String scname) throws CommonException, Exception {

		String email = this.getPrincipal();
		Ticket ticket = new Ticket();
		Schedule schedule = new Schedule();

		UserInfo item = userInfoService.detail(email);
		String ticSeatno_cut[] = ticSeatno.split(",");
		for (String string : ticSeatno_cut) {
			ticket.setId(id);
			ticket.setTheNo(theNo);
			ticket.setTicSeatno(ticSeatno);
			schedule.setScNo(scNo);
			schedule.setMovieNo(movieNo);
			schedule.setScname(scname);

			scheduleService.newTicket(ticket);

		}
		return "redirect:ticketlist.do";

	}

	//스케줄 작성
	@RequestMapping(value = "/newschedule.do", method = RequestMethod.GET)
	public String schedule(Model model, Integer movieNo) throws CommonException, UnsupportedEncodingException {
		model.addAttribute("movieNo", movieNo);
		return "scheduleList";
	}

	@RequestMapping(value = "/newschedule", method = RequestMethod.POST)
	public String schedule(HttpServletRequest request, Integer movieNo, Integer theNo, Integer scNo,
			String schstarttime) throws CommonException, UnsupportedEncodingException {

		Schedule sch = new Schedule();
		StringBuilder sb = new StringBuilder();
		sb.append(schstarttime);
		sb.append(" ");
		sb.append(schstarttime);
		sch.setMovieNo(movieNo);
		sch.setScNo(scNo);
		sch.setTheNo(theNo);

		return "redirect:schedulelist.do";
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
