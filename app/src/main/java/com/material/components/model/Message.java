package com.material.components.model;

import java.io.Serializable;

public class Message implements Serializable{
	private long id;
	private String date;
	private String content;
	private boolean fromMe;
	private boolean showTime = true;

	public Message(long id, String content, boolean fromMe, String date) {
		this.id = id;
		this.date = date;
		this.content = content;
		this.fromMe = fromMe;
	}

	public Message(long id, String content, boolean fromMe, boolean showTime, String date) {
		this.id = id;
		this.date = date;
		this.content = content;
		this.fromMe = fromMe;
		this.showTime = showTime;
	}

	public long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	public boolean isFromMe() {
		return fromMe;
	}

	public boolean isShowTime() {
		return showTime;
	}
}