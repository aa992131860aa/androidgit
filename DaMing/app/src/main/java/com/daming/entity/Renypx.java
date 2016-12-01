package com.daming.entity;

import java.util.List;

public class Renypx {
	private String result; // 0-失败 1-成功
	private String msg; // 消息，失败原因
	private List<Px> pxs; // 培训
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
	public List<Px> getPxs() {
		return pxs;
	}
	public void setPxs(List<Px> pxs) {
		this.pxs = pxs;
	}

    
}
