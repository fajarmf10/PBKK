package com.fajarmf.service;

import java.util.List;

import com.fajarmf.model.Ticket;
import com.fajarmf.model.User;

public interface TicketService {
	Ticket getTicket(Integer creatorid, Integer ticketid);
	Ticket getTicket(Integer ticketid);
	List<Ticket> getMyTickets(Integer creatorid);
	void addTicket(Integer creatorid, String content, Integer severity, Integer status);
	void updateTicket(Integer ticketid, String content, Integer severity, Integer status);
	void deleteMyTicket(Integer userid, Integer ticketid);
	List<Ticket> getAllTickets();
	void deleteTickets(User user, String ticketids);
}
