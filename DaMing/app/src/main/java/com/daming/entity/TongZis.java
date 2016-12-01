package com.daming.entity;

import java.util.List;

public class TongZis {
   private String result;
   private String msg;
   private List<TongZiRecord> tongZiRecordList;
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
public List<TongZiRecord> getTongZiRecordList() {
	return tongZiRecordList;
}
public void setTongZiRecordList(List<TongZiRecord> tongZiRecordList) {
	this.tongZiRecordList = tongZiRecordList;
} 
   
}
