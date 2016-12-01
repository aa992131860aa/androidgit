package com.daming.entity;

import java.util.List;

public class Renydjll {
	private String result ;   //0-失败  1-成功
	private String    msg ;      //消息，失败原因
	private List<Djll>	  djlls;		//人员等级履历
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
	public List<Djll> getDjlls() {
		return djlls;
	}
	public void setDjlls(List<Djll> djlls) {
		this.djlls = djlls;
	}
    
   

}
