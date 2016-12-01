package com.daming.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/3.
 */
public class PaicList implements Serializable{
    private String recordcount;// 返回的排产数量
    private String records;
    private String record;
    private String pcid; // 排产ID
    private String scrq; // 生产日期
    private String bcdm; // 班次代码

    public String getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(String recordcount) {
        this.recordcount = recordcount;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
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
}
