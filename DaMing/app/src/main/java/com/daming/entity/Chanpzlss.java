package com.daming.entity;

public class Chanpzlss {
	private String result ;   			//0-失败  1-成功
	private String    msg ;      			//消息，失败原因
	private String    cpzlss ;				//产品质量损失(图表URL)
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
	public String getCpzlss() {
		return cpzlss;
	}
	public void setCpzlss(String cpzlss) {
		this.cpzlss = cpzlss;
	}
    
}
