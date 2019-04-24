package com.fajarmf.model;

import java.util.Date;

public class Ticket {
	private Integer ticketid;
	private Integer creatorid;
	private Date createdat;
	private String content;
	private Integer severity;
	private Integer status;

	@Override
	public String toString() {
		return "Ticket [ticketid=" + ticketid + ", creatorid=" + creatorid
				+ ", createdat=" + createdat + ", content=" + content
				+ ", severity=" + severity + ", status=" + status + "]";
	}

	private static Integer ticketCounter = 300;

	public Ticket(Integer creatorid, Date createdat, String content, Integer severity, Integer status){
		ticketCounter++;
		this.ticketid = ticketCounter;
		this.creatorid = creatorid;
		this.createdat = createdat;
		this.content = content;
		this.severity = severity;
		this.status = status;
	}

	public Integer getCreatorid() {
		// TODO Auto-generated method stub
		return creatorid;
	}
}
