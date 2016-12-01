package com.daming.entity;

public class Renyxx {
	private String result; // 0-失败 1-成功
	private String msg; // 消息，失败原因
	private Ryxx ryxx; // 人员信息

	public Ryxx getRyxx() {
		return ryxx;
	}

	public void setRyxx(Ryxx ryxx) {
		this.ryxx = ryxx;
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

	

}
