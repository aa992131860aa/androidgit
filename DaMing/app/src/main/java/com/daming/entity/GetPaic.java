package com.daming.entity;

import java.io.Serializable;
import java.util.List;

public class GetPaic implements Serializable{
   private String result;
   private String msg;
   private String pcid;
   private String yxcc;
   private String scrq;
   private String bcdm;
   private Fzr fzr;
   private Fzr jyy;
   private List<Fzr> cztList;
   private int count;
   private List<Cp> cpList;
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
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
public String getPcid() {
	return pcid;
}
public void setPcid(String pcid) {
	this.pcid = pcid;
}
public String getYxcc() {
	return yxcc;
}
public void setYxcc(String yxcc) {
	this.yxcc = yxcc;
}
public String getScrq() {
	return scrq;
}
public void setScrq(String scrq) {
	this.scrq = scrq;
}
public String getBcdm() {
	return bcdm;
}
public void setBcdm(String bcdm) {
	this.bcdm = bcdm;
}
public Fzr getFzr() {
	return fzr;
}
public void setFzr(Fzr fzr) {
	this.fzr = fzr;
}
public Fzr getJyy() {
	return jyy;
}
public void setJyy(Fzr jyy) {
	this.jyy = jyy;
}
public List<Fzr> getCztList() {
	return cztList;
}
public void setCztList(List<Fzr> cztList) {
	this.cztList = cztList;
}
public List<Cp> getCpList() {
	return cpList;
}
public void setCpList(List<Cp> cpList) {
	this.cpList = cpList;
}
   
}
