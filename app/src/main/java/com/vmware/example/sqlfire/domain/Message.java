package com.vmware.example.sqlfire.domain;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -4689545459707089241L;

	private String text;

	public Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [text=" + text + "]";
	}

}
