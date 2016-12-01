package com.daming.entity;

public class Chanplscl {
	private String  result ;   			//0-失败  1-成功
	private String  msg ;      			//消息，失败原因
	private String  cplscl_month ;		//产品历史产量月份(图表URL)
	private String  cplscl_year ;		//产品历史产量年度(图表URL)
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
	public String getCplscl_month() {
		return cplscl_month;
	}
	public void setCplscl_month(String cplscl_month) {
		this.cplscl_month = cplscl_month;
	}
	public String getCplscl_year() {
		return cplscl_year;
	}
	public void setCplscl_year(String cplscl_year) {
		this.cplscl_year = cplscl_year;
	}
    
}
