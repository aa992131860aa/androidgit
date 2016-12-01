package com.daming.json;

import java.util.List;

/**
 * Created by Administrator on 2016-11-21.
 */

public class ZhiliangfenxizlJson {
    //{"code":1,"msg":"成功","recordcount":12,"data":[{"month":"1","khts":"","gfs":"","lfs":"","tjs":"","wcsl":""},{"month":"2","khts":"","gfs":"","lfs":"","tjs":"","wcsl":""},{"month":"3","khts":"","gfs":"","lfs":"","tjs":"","wcsl":""},{"month":"4","khts":"","gfs":"","lfs":"","tjs":"","wcsl":""},{"month":"5","khts":"","gfs":"","lfs":"","tjs":"","wcsl":""},{"month":"6","khts":"11","gfs":"24","lfs":"201","tjs":"11","wcsl":"11853"},{"month":"7","khts":"6","gfs":"129","lfs":"7063","tjs":"37","wcsl":"57072"},{"month":"8","khts":"8","gfs":"124","lfs":"7601","tjs":"12","wcsl":"57126"},{"month":"9","khts":"4","gfs":"640","lfs":"8209","tjs":"35","wcsl":"116530"},{"month":"10","khts":"1","gfs":"462","lfs":"6079","tjs":"153","wcsl":"140035"},{"month":"11","khts":"2","gfs":"458","lfs":"7579","tjs":"62","wcsl":"98032"},{"month":"12","khts":"","gfs":"","lfs":"","tjs":"","wcsl":""}]}
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
        //{"month":"1","khts":"","gfs":"","lfs":"","tjs":"","wcsl":""}
        private String month;
        private String khts;
        private String gfs;
        private String tjs;
        private String wcsl;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getKhts() {
            return khts;
        }

        public void setKhts(String khts) {
            this.khts = khts;
        }

        public String getGfs() {
            return gfs;
        }

        public void setGfs(String gfs) {
            this.gfs = gfs;
        }

        public String getTjs() {
            return tjs;
        }

        public void setTjs(String tjs) {
            this.tjs = tjs;
        }

        public String getWcsl() {
            return wcsl;
        }

        public void setWcsl(String wcsl) {
            this.wcsl = wcsl;
        }
    }
}
