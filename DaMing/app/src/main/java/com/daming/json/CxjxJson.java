package com.daming.json;

import java.util.List;

/**
 * Created by Administrator on 2016-11-15.
 */

public class CxjxJson {

    /**
     * code : 1
     * msg : 成功
     * recordcount : 30
     * data : [{"scrq":"1","jhwcl":"98.03","bcdcl":"98.03","hgl":"100","gfl":"0","lfl":"0"},{"scrq":"2","jhwcl":"97.7","bcdcl":"97.7","hgl":"99.66","gfl":"0.34","lfl":"0"},{"scrq":"3","jhwcl":"98.03","bcdcl":"98.03","hgl":"100","gfl":"0","lfl":"0"},{"scrq":"4","jhwcl":"73.49","bcdcl":"73.49","hgl":"100","gfl":"0","lfl":"0"},{"scrq":"5","jhwcl":"94.05","bcdcl":"96.14","hgl":"99.49","gfl":"0.34","lfl":"0.17"},{"scrq":"6","jhwcl":"96.08","bcdcl":"98.21","hgl":"99.55","gfl":"0.45","lfl":"0"},{"scrq":"7","jhwcl":"96.19","bcdcl":"96.19","hgl":"100","gfl":"0","lfl":"0"},{"scrq":"8","jhwcl":"93.72","bcdcl":"96.3","hgl":"99.83","gfl":"0","lfl":"0.17"},{"scrq":"9","jhwcl":"96.71","bcdcl":"96.71","hgl":"100","gfl":"0","lfl":"0"},{"scrq":"10","jhwcl":"98.48","bcdcl":"99.02","hgl":"99.59","gfl":"0","lfl":"0.41"},{"scrq":"11","jhwcl":"83.05","bcdcl":"84.12","hgl":"98.81","gfl":"0.48","lfl":"0.71"},{"scrq":"12","jhwcl":"70.01","bcdcl":"72.36","hgl":"96.72","gfl":"1.09","lfl":"2.19"},{"scrq":"13","jhwcl":"82.75","bcdcl":"82.75","hgl":"100","gfl":"0","lfl":"0"},{"scrq":"14","jhwcl":"98.5","bcdcl":"99.05","hgl":"99.76","gfl":"0","lfl":"0.24"},{"scrq":"15","jhwcl":"89.02","bcdcl":"89.02","hgl":"100","gfl":"0","lfl":"0"},{"scrq":"16","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"17","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"18","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"19","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"20","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"21","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"22","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"23","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"24","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"25","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"26","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"27","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"28","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"29","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"},{"scrq":"30","jhwcl":"0","bcdcl":"0","hgl":"0","gfl":"0","lfl":"0"}]
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
         * jhwcl : 98.03
         * bcdcl : 98.03
         * hgl : 100
         * gfl : 0
         * lfl : 0
         */

        private String scrq;
        private String jhwcl;
        private String bcdcl;
        private String hgl;
        private String gfl;
        private String lfl;

        public String getScrq() {
            return scrq;
        }

        public void setScrq(String scrq) {
            this.scrq = scrq;
        }

        public String getJhwcl() {
            return jhwcl;
        }

        public void setJhwcl(String jhwcl) {
            this.jhwcl = jhwcl;
        }

        public String getBcdcl() {
            return bcdcl;
        }

        public void setBcdcl(String bcdcl) {
            this.bcdcl = bcdcl;
        }

        public String getHgl() {
            return hgl;
        }

        public void setHgl(String hgl) {
            this.hgl = hgl;
        }

        public String getGfl() {
            return gfl;
        }

        public void setGfl(String gfl) {
            this.gfl = gfl;
        }

        public String getLfl() {
            return lfl;
        }

        public void setLfl(String lfl) {
            this.lfl = lfl;
        }
    }
}
