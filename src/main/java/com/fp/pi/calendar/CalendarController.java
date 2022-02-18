package com.fp.pi.calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CalendarController {

	@RequestMapping(value = "/schedule.go", method = RequestMethod.GET)
	public String scheduleGo(HttpServletRequest request) {
		
		request.setAttribute("contentPage", "calendar/scheduleCalendar.jsp");
		
		return "index";
	}
	
	
	
	@RequestMapping(value = "/routine.go", method = RequestMethod.GET)
	public String routineGo(HttpServletRequest request) {
		
		 request.setAttribute("contentPage", "calendar/routineCalendar.jsp");
		
		return "index";
	}
	
	
}
