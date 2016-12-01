package com.daming.json;

import java.util.List;

/**
 * Created by Administrator on 2016-11-21.
 */

public class ZhiliangfenxissJson {

    /**
     * code : 1
     * msg : 成功
     * recordcount : 7
     * data : [{"qxlx":"材料-气孔","sl":"1","glfsl":"1","wgjcsl":"0","khtssl":"0"},{"qxlx":"材料-外观","sl":"3","glfsl":"3","wgjcsl":"0","khtssl":"0"},{"qxlx":"尺寸-设备","sl":"2","glfsl":"2","wgjcsl":"0","khtssl":"0"},{"qxlx":"尺寸-夹具","sl":"0","glfsl":"0","wgjcsl":"0","khtssl":"0"},{"qxlx":"尺寸-检具","sl":"0","glfsl":"0","wgjcsl":"0","khtssl":"0"},{"qxlx":"人为因素","sl":"0","glfsl":"0","wgjcsl":"0","khtssl":"0"},{"qxlx":"调机废品","sl":"3","glfsl":"3","wgjcsl":"0","khtssl":"0"}]
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
         * qxlx : 材料-气孔
         * sl : 1
         * glfsl : 1
         * wgjcsl : 0
         * khtssl : 0
         */

        private String qxlx;
        private String sl;
        private String glfsl;
        private String wgjcsl;
        private String khtssl;

        public String getQxlx() {
            return qxlx;
        }

        public void setQxlx(String qxlx) {
            this.qxlx = qxlx;
        }

        public String getSl() {
            return sl;
        }

        public void setSl(String sl) {
            this.sl = sl;
        }

        public String getGlfsl() {
            return glfsl;
        }

        public void setGlfsl(String glfsl) {
            this.glfsl = glfsl;
        }

        public String getWgjcsl() {
            return wgjcsl;
        }

        public void setWgjcsl(String wgjcsl) {
            this.wgjcsl = wgjcsl;
        }

        public String getKhtssl() {
            return khtssl;
        }

        public void setKhtssl(String khtssl) {
            this.khtssl = khtssl;
        }
    }
}
