package com.fajarmf.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fajarmf.model.Ticket;
import com.fajarmf.model.User;

public class TicketServiceImpl implements TicketService {
	@Autowired
	UserService userService;
	
	public static List<Ticket> tickets;

	@Override
	public void addTicket(Integer creatorid, String content, Integer severity, Integer status) {
		Ticket ticket = new Ticket(creatorid, new Date(), content, severity, status);
		tickets.add(ticket);
	}

	@Override
	public List<Ticket> getMyTickets(Integer creatorid) {		
		
		return tickets.stream()
				.filter(x -> x.getCreatorid().intValue() == creatorid.intValue())				
				.collect(Collectors.toList())
				;
	}
	
	@Override
	public Ticket getTicket(Integer creatorid, Integer ticketid) {
		
		return tickets.stream()
				.filter(x -> x.getCreatorid().intValue()  == creatorid.intValue() && x.getTicketid().intValue() == ticketid.intValue())
				.findAny()
				.orElse(null);
	}
	
	@Override
	public Ticket getTicket(Integer ticketid) {
		
		return tickets.stream()
				.filter(x -> x.getTicketid().intValue() == ticketid.intValue())
				.findAny()
				.orElse(null);
	}
	
	@Override
	public void deleteMyTicket(Integer userid, Integer ticketid) {		
		tickets.removeIf(x -> x.getTicketid().intValue() == ticketid.intValue() && x.getCreatorid().intValue() == userid.intValue());
	}
	
	@Override
	public void deleteTickets(User user, String ticketids) {
	List<String> ticketObjList = Arrays.asList(ticketids.split(","));
	List<Integer> intList =
	ticketObjList.stream()
	.map(Integer::valueOf)
	.collect(Collectors.toList());
	tickets.removeIf(x -> intList.contains(x.getTicketid()));
	}
	
	@Override
	public List<Ticket> getAllTickets() {
		return tickets;
	}
	
	@Override
	public void updateTicket(Integer ticketid, String content, Integer severity, Integer status) {
		Ticket ticket = getTicket(ticketid);
		if(ticket == null){
			throw new RuntimeException("Ticket Not Available");
		}
		ticket.setContent(content);
		ticket.setSeverity(severity);
		ticket.setStatus(status);
	}
}
