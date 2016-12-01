package com.daming.json;

import java.util.List;

/**
 * Created by Administrator on 2016-11-20.
 */

public class LishiclJson {


    /**
     * code : 1
     * msg : 成功
     * recordcount : 30
     * data : [{"scrq":"1","cpdm":"2022201BX","wcsl":"149"},{"scrq":"2","cpdm":"2022201BX","wcsl":"149"},{"scrq":"3","cpdm":"2022201BX","wcsl":"149"},{"scrq":"4","cpdm":"2022201BX","wcsl":"230"},{"scrq":"5","cpdm":"2022201BX","wcsl":"287"},{"scrq":"6","cpdm":"2022201BX","wcsl":"216"},{"scrq":"7","cpdm":"2022201BX","wcsl":"299"},{"scrq":"8","cpdm":"2022201BX","wcsl":"291"},{"scrq":"9","cpdm":"2022201BX","wcsl":"80"},{"scrq":"14","cpdm":"2022201BX","wcsl":"215"},{"scrq":"15","cpdm":"2022201BX","wcsl":"149"},{"scrq":"16","cpdm":"2022201BX","wcsl":"127"},{"scrq":"17","cpdm":"2022201BX","wcsl":"297"},{"scrq":"18","cpdm":"2022201BX","wcsl":"299"},{"scrq":"19","cpdm":"2022201BX","wcsl":"263"},{"scrq":"20","cpdm":"2022201BX","wcsl":"226"},{"scrq":"21","cpdm":"2022201BX","wcsl":"0"},{"scrq":"23","cpdm":"2022201BX","wcsl":"0"},{"scrq":"26","cpdm":"2022201BX","wcsl":"0"},{"scrq":"29","cpdm":"2022201BX","wcsl":"0"},{"scrq":"12","cpdm":"2022201BX","wcsl":"0"},{"scrq":"27","cpdm":"2022201BX","wcsl":"0"},{"scrq":"30","cpdm":"2022201BX","wcsl":"0"},{"scrq":"10","cpdm":"2022201BX","wcsl":"0"},{"scrq":"24","cpdm":"2022201BX","wcsl":"0"},{"scrq":"22","cpdm":"2022201BX","wcsl":"0"},{"scrq":"25","cpdm":"2022201BX","wcsl":"0"},{"scrq":"13","cpdm":"2022201BX","wcsl":"0"},{"scrq":"11","cpdm":"2022201BX","wcsl":"0"},{"scrq":"28","cpdm":"2022201BX","wcsl":"0"}]
     */

    private int code;
    private String msg;
    private int recordcount;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(int recordcount) {
        this.recordcount = recordcount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * scrq : 1
         * cpdm : 2022201BX
         * wcsl : 149
         */

        private String scrq;
        private String cpdm;
        private String wcsl;

        public String getScrq() {
            return scrq;
        }

        public void setScrq(String scrq) {
            this.scrq = scrq;
        }

        public String getCpdm() {
            return cpdm;
        }

        public void setCpdm(String cpdm) {
            this.cpdm = cpdm;
        }

        public String getWcsl() {
            return wcsl;
        }

        public void setWcsl(String wcsl) {
            this.wcsl = wcsl;
        }
    }
}
