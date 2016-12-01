package com.daming.entity;

import java.io.Serializable;
import java.util.List;

public class GetPaicList implements Serializable{
	private String data;
	private String result;
	private String msg;
	private String recordcount;
    private List<PaicList> paicLists;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<PaicList> getPaicLists() {
		return paicLists;
	}

	public void setPaicLists(List<PaicList> paicLists) {
		this.paicLists = paicLists;
	}

	public String getRecordcount() {
		return recordcount;
	}

	public void setRecordcount(String recordcount) {
		this.recordcount = recordcount;
	}
}
