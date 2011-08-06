package com.martinanalytics.testmodule.client.app;

public class DatedMessage {
	private String msg;
	private java.util.Date timestamp;
	
	public DatedMessage(String msg_){
		timestamp = new java.util.Date();
		msg = msg_;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the timestamp
	 */
	public java.util.Date getTimestamp() {
		return timestamp;
	}

}
