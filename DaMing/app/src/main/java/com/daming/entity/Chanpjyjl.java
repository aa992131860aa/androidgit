package com.daming.entity;

import java.util.List;

public class Chanpjyjl {
private String	data;
private String result ;			//0-失败  1-成功
private String msg ;			//消息，失败原因
private List<String> src_jyjls ;			//检验记录(检验记录URL,文件格式PDF)
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
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

	public List<String> getSrc_jyjls() {
		return src_jyjls;
	}

	public void setSrc_jyjls(List<String> src_jyjls) {
		this.src_jyjls = src_jyjls;
	}
}
