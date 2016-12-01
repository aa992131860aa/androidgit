package com.daming.entity;

public class Chanplszl {
	private String result ;   			//0-失败  1-成功
	private String    msg ;      			//消息，失败原因
	private String    cplszl_year ;		//产品历史质量年度(图表URL)
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
	public String getCplszl_year() {
		return cplszl_year;
	}
	public void setCplszl_year(String cplszl_year) {
		this.cplszl_year = cplszl_year;
	}
    
}
