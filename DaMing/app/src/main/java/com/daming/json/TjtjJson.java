package com.daming.json;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class TjtjJson {


    /**
     * code : 1
     * msg : 成功
     * recordcount : 8
     * data : [{"yclx":"测量延误","tjsj":"0","jcsl":"0"},{"yclx":"产品调试","tjsj":"4800","jcsl":"0"},{"yclx":"机器故障","tjsj":"0","jcsl":"0"},{"yclx":"其他原因","tjsj":"3600","jcsl":"0"},{"yclx":"缺料等待","tjsj":"0","jcsl":"0"},{"yclx":"人员调整","tjsj":"0","jcsl":"0"},{"yclx":"新员工","tjsj":"0","jcsl":"0"},{"yclx":"质量处理","tjsj":"0","jcsl":"0"}]
     */

    private int code;
    private String msg;
    private int recordcount;
    /**
     * yclx : 测量延误
     * tjsj : 0
     * jcsl : 0
     */

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
        private String yclx;
        private int tjsj;
        private int jcsl;

        public String getYclx() {
            return yclx;
        }

        public void setYclx(String yclx) {
            this.yclx = yclx;
        }

        public int getTjsj() {
            return tjsj;
        }

        public void setTjsj(int tjsj) {
            this.tjsj = tjsj;
        }

        public int getJcsl() {
            return jcsl;
        }

        public void setJcsl(int jcsl) {
            this.jcsl = jcsl;
        }
    }
}
