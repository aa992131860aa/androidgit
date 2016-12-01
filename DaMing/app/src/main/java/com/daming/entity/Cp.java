package com.daming.entity;

import java.io.Serializable;
import java.util.List;

public class Cp implements Serializable{
	private String cpgxid;
	private String cpdm;
	private String cpmc;
	private String ggxh;
	private String gxfl;
	private String scgx;
	private String jp;
	private String rs;
	private String yxcc;
	private List<Pccl> pcclList;

	public String getCpgxid() {
		return cpgxid;
	}

	public void setCpgxid(String cpgxid) {
		this.cpgxid = cpgxid;
	}

	public String getCpdm() {
		return cpdm;
	}

	public void setCpdm(String cpdm) {
		this.cpdm = cpdm;
	}

	public String getCpmc() {
		return cpmc;
	}

	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}

	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	public String getGxfl() {
		return gxfl;
	}

	public void setGxfl(String gxfl) {
		this.gxfl = gxfl;
	}

	public String getScgx() {
		return scgx;
	}

	public void setScgx(String scgx) {
		this.scgx = scgx;
	}

	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getYxcc() {
		return yxcc;
	}

	public void setYxcc(String yxcc) {
		this.yxcc = yxcc;
	}

	public List<Pccl> getPcclList() {
		return pcclList;
	}

	public void setPcclList(List<Pccl> pcclList) {
		this.pcclList = pcclList;
	}

}
