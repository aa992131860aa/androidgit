package com.daming.entity;

import java.util.List;

public class Anqll {
	private String result ;  			//0-失败  1-成功
    private String msg ;     			//消息，失败原因
    private List<String> srkc_aqlls ;			//安全履历(检验记录URL,文件格式PDF)
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
	public List<String> getSrkc_aqlls() {
		return srkc_aqlls;
	}
	public void setSrkc_aqlls(List<String> srkc_aqlls) {
		this.srkc_aqlls = srkc_aqlls;
	}
    
}
