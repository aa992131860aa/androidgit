package com.daming.entity;

import java.io.Serializable;

public class Pccl implements Serializable{
	private String pcclid;
	private String bcid;
	private String bcsdmch;
	private String sdstart;
	private String sdend;
	private String sec;
	private String sl;
	private String wcsl;
	public String getPcclid() {
		return pcclid;
	}
	public void setPcclid(String pcclid) {
		this.pcclid = pcclid;
	}
	public String getBcid() {
		return bcid;
	}
	public void setBcid(String bcid) {
		this.bcid = bcid;
	}
	public String getBcsdmch() {
		return bcsdmch;
	}
	public void setBcsdmch(String bcsdmch) {
		this.bcsdmch = bcsdmch;
	}
	public String getSdstart() {
		return sdstart;
	}
	public void setSdstart(String sdstart) {
		this.sdstart = sdstart;
	}
	public String getSdend() {
		return sdend;
	}
	public void setSdend(String sdend) {
		this.sdend = sdend;
	}
	public String getSec() {
		return sec;
	}
	public void setSec(String sec) {
		this.sec = sec;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getWcsl() {
		return wcsl;
	}
	public void setWcsl(String wcsl) {
		this.wcsl = wcsl;
	}

}
