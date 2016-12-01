package com.daming.entity;

import java.util.List;

public class Chanxjxzb {
	private String result ;   //0-失败  1-成功
	private String   msg ;      //消息，失败原因
	private List<Jxzb>  jxzbs;		//绩效指标
	private String cxdcl;
	private String cxhgl;
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
	public List<Jxzb> getJxzbs() {
		return jxzbs;
	}
	public void setJxzbs(List<Jxzb> jxzbs) {
		this.jxzbs = jxzbs;
	}
	public String getCxdcl() {
		return cxdcl;
	}
	public void setCxdcl(String cxdcl) {
		this.cxdcl = cxdcl;
	}
	public String getCxhgl() {
		return cxhgl;
	}
	public void setCxhgl(String cxhgl) {
		this.cxhgl = cxhgl;
	}
	

}
