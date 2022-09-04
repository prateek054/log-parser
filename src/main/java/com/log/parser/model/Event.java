package com.log.parser.model;

public class Event {
	
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", type=" + type + ", host=" + host + ", duration=" + duration + ", alert="
				+ alert + "]";
	}
	private String eventId;
	private String type;
	private String host;
	private int duration;
	private String alert;
	
	public String getEventId() {
		return eventId;
	}
	public Event(String eventId, String type, String host, int duration, String alert) {
		super();
		this.eventId = eventId;
		this.type = type;
		this.host = host;
		this.duration = duration;
		this.alert = alert;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}

}
