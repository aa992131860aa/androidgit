package com.daming.entity;

import java.util.List;

public class Chanxpcxx {
	private String result;  //0-失败  1-成功
	private String    msg ;     //消息，失败原因
	private List<Pcxx> pcxxs;		//排产信息
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
	public List<Pcxx> getPcxxs() {
		return pcxxs;
	}
	public void setPcxxs(List<Pcxx> pcxxs) {
		this.pcxxs = pcxxs;
	}
	
	
}
