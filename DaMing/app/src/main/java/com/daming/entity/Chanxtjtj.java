package com.daming.entity;

import java.util.List;

public class Chanxtjtj {
	 private String result ;   //0-失败  1-成功
	 private String msg ;      //消息，失败原因
	 private String src;
	 private List<Tjtj>	  tjtjs;		//停机统计
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
	public List<Tjtj> getTjtjs() {
		return tjtjs;
	}
	public void setTjtjs(List<Tjtj> tjtjs) {
		this.tjtjs = tjtjs;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
    
}
