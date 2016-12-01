package com.daming.entity;

public class TongZiRecord {
    private String tzid;
    private String bt;
    private String fbbm;
    private String tzsj;
    private String yd;
    public TongZiRecord(){
    	
    }
    
	public TongZiRecord(String tzid, String bt, String fbbm, String tzsj,
			String yd) {
		super();
		this.tzid = tzid;
		this.bt = bt;
		this.fbbm = fbbm;
		this.tzsj = tzsj;
		this.yd = yd;
	}

	public String getTzid() {
		return tzid;
	}
	public void setTzid(String tzid) {
		this.tzid = tzid;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getFbbm() {
		return fbbm;
	}
	public void setFbbm(String fbbm) {
		this.fbbm = fbbm;
	}
	public String getTzsj() {
		return tzsj;
	}
	public void setTzsj(String tzsj) {
		this.tzsj = tzsj;
	}
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
    
}
