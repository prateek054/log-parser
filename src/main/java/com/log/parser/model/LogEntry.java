package com.log.parser.model;

import java.sql.Timestamp;

public class LogEntry {
	
	private String id;
	private String state;
	private String type;
	private String host;
	private Long timestamp;
	
	public LogEntry(String id, String state, String type, String host, Long timestamp) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.host = host;
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "LogEntry [id=" + id + ", state=" + state + ", type=" + type + ", host=" + host + ", timestamp="
				+ timestamp + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogEntry other = (LogEntry) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

}
