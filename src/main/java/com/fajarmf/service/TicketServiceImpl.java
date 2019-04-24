package com.fajarmf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fajarmf.model.Ticket;

public class TicketServiceImpl implements TicketService {
	@Autowired
	UserService userService;
	
	public static List<Ticket> tickets;

	@Override
	public void addTicket(Integer creatorid, String content, Integer severity, Integer status) {
		Ticket ticket = new Ticket(creatorid, new Date(), content, severity, status);
		tickets.add(ticket);
	}
}
