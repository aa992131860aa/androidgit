package com.daming.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class Renyldjl {
   private String result;
    private String msg;
    private List<Ldjl> ldjls;

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

    public List<Ldjl> getLdjls() {
        return ldjls;
    }

    public void setLdjls(List<Ldjl> ldjls) {
        this.ldjls = ldjls;
    }
}
