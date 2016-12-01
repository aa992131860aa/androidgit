package com.daming.entity;

public class Chanpjswj {
private String	data;
private String	result ;			//0-失败  1-成功
private String	msg ;			//消息，失败原因
private String	src_tzwj ;				//产品图纸(产品图纸URL,文件格式PDF,如文件不存在,则为空,下同)
private String	src_zyzd ;				//作业指导(作业指导URL,文件格式PDF)
private String	src_bzzy ;				//标准作业(标准作业URL,文件格式PDF)
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
public String getSrc_tzwj() {
	return src_tzwj;
}
public void setSrc_tzwj(String src_tzwj) {
	this.src_tzwj = src_tzwj;
}
public String getSrc_zyzd() {
	return src_zyzd;
}
public void setSrc_zyzd(String src_zyzd) {
	this.src_zyzd = src_zyzd;
}
public String getSrc_bzzy() {
	return src_bzzy;
}
public void setSrc_bzzy(String src_bzzy) {
	this.src_bzzy = src_bzzy;
}

}
