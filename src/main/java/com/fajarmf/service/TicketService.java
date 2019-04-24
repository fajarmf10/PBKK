package com.fajarmf.service;

import java.util.List;

import com.fajarmf.model.Ticket;
import com.fajarmf.model.User;

public interface TicketService {
	List<Ticket> getMyTickets(Integer creatorid);
	void addTicket(Integer creatorid, String content, Integer severity, Integer status);
}
