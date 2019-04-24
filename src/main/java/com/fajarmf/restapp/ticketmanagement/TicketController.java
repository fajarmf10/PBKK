package com.fajarmf.restapp.ticketmanagement;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fajarmf.aop.UserTokenRequired;
import com.fajarmf.model.User;
import com.fajarmf.service.TicketService;
import com.fajarmf.service.UserService;
import com.fajarmf.util.Util;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	TicketService ticketSevice;
	
	@Autowired
	UserService userSevice;
	
	@ResponseBody
	@RequestMapping("/my/tickets")
	public Map<String, Object> getMyTickets(
	HttpServletRequest request
	) {
		User user = userSevice.getUserByToken(request.getHeader("token"));
		if(user == null){
			return Util.getUserNotAvailableError();
		}
		return Util.getSuccessResult(ticketSevice.getMyTickets(user.getUserid()));
	}
}