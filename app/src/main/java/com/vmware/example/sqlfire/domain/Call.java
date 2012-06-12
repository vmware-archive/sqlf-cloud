package com.vmware.example.sqlfire.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Call implements Serializable {

	private static final long serialVersionUID = 8476690589642393815L;

	private String id;
	private String code;
	private String name;
	private String text;
	private Timestamp on;
	private CallStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getOn() {
		return on;
	}

	public void setOn(Timestamp on) {
		this.on = on;
	}

	public CallStatus getStatus() {
		return status;
	}

	public void setStatus(CallStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Call [id=" + id + ", code=" + code + ", name=" + name
				+ ", text=" + text + ", on=" + on + ", status=" + status + "]";
	}

}
