package com.fajarmf.restapp.ticketmanagement;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fajarmf.aop.AdminTokenRequired;
import com.fajarmf.aop.CSRTokenRequired;
import com.fajarmf.aop.TokenRequired;
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
	@TokenRequired
	@RequestMapping("/{ticketid}")
	public <T> T getTicket(
	@PathVariable("ticketid") final Integer ticketid,
	HttpServletRequest request
	) {
		return (T) Util.getSuccessResult(ticketSevice.getTicket(ticketid));
	}

	@ResponseBody
	@RequestMapping(value = "/by/admin", method = RequestMethod.PUT)
	public <T> T updateTicketByAdmin (
	@RequestParam("ticketid") final Integer ticketid,
	@RequestParam(value="content") String content,
	@RequestParam(value="severity") Integer severity,
	@RequestParam(value="status") Integer status,
	HttpServletRequest request,
	HttpServletResponse response
	) {
		User user = userSevice.getUserByToken(request.getHeader("token"));
		if(user == null){
			return Util.getUserNotAvailableError();
		}
		ticketSevice.updateTicket(ticketid, content, severity, status);
		Map<String, String> result = new LinkedHashMap<>();
		result.put("result", "updated");
		return (T) result;
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping("/by/admin")
	public <T> T getAllTickets(
	HttpServletRequest request,
	HttpServletResponse response) {
		return (T) ticketSevice.getAllTickets();
	}
	
	@ResponseBody
	@UserTokenRequired
	@RequestMapping(value = "/{ticketid}", method = RequestMethod.DELETE)
	public <T> T deleteTicketByUser (
	@RequestParam("ticketid") final Integer ticketid,
	HttpServletRequest request
	) {
		User user = userSevice.getUserByToken(request.getHeader("token"));
		ticketSevice.deleteMyTicket(user.getUserid(), ticketid);
		return Util.getSuccessResult();
	}
	
	@ResponseBody
	@CSRTokenRequired
	@RequestMapping(value = "/by/csr", method =  RequestMethod.DELETE)
	public <T> T deleteTicketsByCSR (
			@RequestParam("ticketids") final String ticketids,
			
			HttpServletRequest request
			) {
		
		User user = userSevice.getUserByToken(request.getHeader("token"));
		ticketSevice.deleteTickets(user, ticketids);
		
		return Util.getSuccessResult(); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/{ticketid}", method = RequestMethod.PUT)
	public <T> T updateTicketByCustomer (
			@PathVariable("ticketid") final Integer ticketid,
			@RequestParam(value="content") String content,
			HttpServletRequest request,
			HttpServletResponse response
	) {
		User user = userSevice.getUserByToken(request.getHeader("token"));
		if(user == null){
			return Util.getUserNotAvailableError();
		}
		ticketSevice.updateTicket(ticketid, content, 2, 1);
		Map<String, String> result = new LinkedHashMap<>();
		result.put("result", "updated");
		return (T) result;
	}
	
	@ResponseBody
	@CSRTokenRequired
	@RequestMapping(value = "/by/csr", method = RequestMethod.PUT)
	public <T> T updateTicketByCSR (
	@RequestParam("ticketid") final Integer ticketid,
	@RequestParam(value="content") String content,
	@RequestParam(value="severity") Integer severity,
	@RequestParam(value="status") Integer status,
	HttpServletRequest request
	) {
		ticketSevice.updateTicket(ticketid, content, severity, status);
		return Util.getSuccessResult();
	}
	
	@ResponseBody
	@AdminTokenRequired
	@RequestMapping(value = "/by/admin", method = RequestMethod.DELETE)
	public <T> T deleteTicketsByAdmin (
	@RequestParam("ticketids") final String ticketids,
	HttpServletRequest request
	) {
		User user = userSevice.getUserByToken(request.getHeader("token"));
		ticketSevice.deleteTickets(user, ticketids);
		return Util.getSuccessResult();
	}
	
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