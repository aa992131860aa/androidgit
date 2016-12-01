package com.daming.entity;

import java.util.List;

public class Chanxzlhj {
	private String  result ;   //0-失败  1-成功
	private String    msg ;      //消息，失败原因
	private List<Zlhj>  zlhjs;		//质量呼叫
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
	public List<Zlhj> getZlhjs() {
		return zlhjs;
	}
	public void setZlhjs(List<Zlhj> zlhjs) {
		this.zlhjs = zlhjs;
	}
	
    
}
