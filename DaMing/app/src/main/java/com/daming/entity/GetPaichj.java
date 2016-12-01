package com.daming.entity;

import java.util.List;

public class GetPaichj {
	private String  result ;   			//0-失败  1-成功
	private String  msg ;      			//消息，失败原因
	private List<String> hjmss ;		//产品历史产量月份(图表URL)

	public List<String> getHjmss() {
		return hjmss;
	}

	public void setHjmss(List<String> hjmss) {
		this.hjmss = hjmss;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
