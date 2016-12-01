package com.example.daming_text;

import com.daming.entity.Chanplscl;
import com.daming.json.CxjxJson;
import com.daming.json.LishiclJson;
import com.daming.util.RequestDataUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.x;

import java.util.List;

public class lishichanliang extends Activity {
    @ViewInject(R.id.img_fanhui001)
    private ImageView imgfanhui22;
    @ViewInject(R.id.lishicl_iv_month)
    private ImageView lishicl_iv_month;
    @ViewInject(R.id.lishicl_iv_year)
    private ImageView lishicl_iv_year;
    private String mIp;
    private String mUserName;
    private String mUserPwd;
    private String scrq;
    private String year;
    private String month;
    private String cpdm;
    private WebView lishicl_wv_year;
    private WebView lishicl_wv_month;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lishichanliang);
        ViewUtils.inject(this);
        init();
        addListener();
        bindData();

        lishicl_wv_month=(WebView)findViewById(R.id.lishicl_wv_month);
        lishicl_wv_month.getSettings().setAllowFileAccess(true);
        lishicl_wv_month.getSettings().setJavaScriptEnabled(true);
        lishicl_wv_month.loadUrl("file:///android_asset/echart/myechart_lishicl.html");

        lishicl_wv_year=(WebView)findViewById(R.id.lishicl_wv_year);
        lishicl_wv_year.getSettings().setAllowFileAccess(true);
        lishicl_wv_year.getSettings().setJavaScriptEnabled(true);
        lishicl_wv_year.loadUrl("file:///android_asset/echart/myechart_lishicl_year.html");
        new Chanxjxzb1Task().execute();
        new ChanxjxzbYearTask().execute();
    }

    /**
     * 异步加载培訓信息
     *
     * @author Administrator
     */
    private class ChanxjxzbYearTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {
            return loadChanxjxzbDataYear();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String title = cpdm + "产品历史产量统计图(" + year +  ")";
            if (result != null) {
                Gson gson = new Gson();

                LishiclJson cxjxJson = gson.fromJson(result, LishiclJson.class);
                // Log.e("GetChanxjxzbsl", cxjxJson.getCode() + ":result");

                if (cxjxJson.getCode() == 1) {
                    List<LishiclJson.DataBean> cxjxJsons = cxjxJson.getData();


                    //完成率
                    String data1 = new String();

                    String scrq = new String();
                    for (int i = 0; i < cxjxJsons.size(); i++) {
                        if (i == 0) {
                            data1 += "[";
                            data1 += cxjxJsons.get(i).getWcsl() + ",";

                            scrq += "[";
                            scrq += cxjxJsons.get(i).getScrq()+ ",";
                        } else if (i == cxjxJsons.size() - 1) {
                            data1 += cxjxJsons.get(i).getWcsl() + "";
                            data1 += "]";

                            scrq += cxjxJsons.get(i).getScrq() + "";
                            scrq += "]";
                        } else {
                            data1 += cxjxJsons.get(i).getWcsl() + ",";

                            scrq += cxjxJsons.get(i).getScrq() + ",";
                        }

                    }
                    Log.e("lishichanliang1",data1+":"+scrq);
                    lishicl_wv_year.loadUrl("javascript:createChart('line'," + data1 + ","  + scrq + ",'" + title + "');");
                } else {
                    String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
                    String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
                    lishicl_wv_year.loadUrl("javascript:createChart('line'," + errorData +  "," + errorDate + ",'" + title + "');");
                }


            } else {
                String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
                String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
                lishicl_wv_year.loadUrl("javascript:createChart('line'," + errorData + "," + errorDate + ",'" + title + "');");

            }

        }
    }

    /**
     * 加载培训内容数据
     *
     * @return
     */
    private String loadChanxjxzbDataYear() {
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cpdm>"
                + cpdm + "</cpdm><nf>" + year + "</nf><yf>0</yf></param></data></root>";
        //String method_name = "GetChanxjxzb";
        String method_name = "GetChanplsclData";
        String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
                param, method_name, mIp);

        if (!data.equals("")) {

            data = RequestDataUtil.splieRequestJson(data);
            Log.e("lishichanliang1", data.substring(1, data.length()));
            return data.substring(1, data.length());
        } else {
            return null;
        }
    }


    /**
     * 异步加载培訓信息
     *
     * @author Administrator
     */
    private class Chanxjxzb1Task extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {
            return loadChanxjxzbData1();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String title = cpdm + "产品历史产量统计图(" + year + "-" + month + ")";
            if (result != null) {
                Gson gson = new Gson();

                LishiclJson cxjxJson = gson.fromJson(result, LishiclJson.class);
               // Log.e("GetChanxjxzbsl", cxjxJson.getCode() + ":result");

                if (cxjxJson.getCode() == 1) {
                    List<LishiclJson.DataBean> cxjxJsons = cxjxJson.getData();


                    //完成率
                    String data1 = new String();

                    String scrq = new String();
                    for (int i = 0; i < cxjxJsons.size(); i++) {
                        if (i == 0) {
                            data1 += "[";
                            data1 += cxjxJsons.get(i).getWcsl() + ",";

                            scrq += "[";
                            scrq += cxjxJsons.get(i).getScrq() + ",";
                        } else if (i == cxjxJsons.size() - 1) {
                            data1 += cxjxJsons.get(i).getWcsl() + "";
                            data1 += "]";

                            scrq += cxjxJsons.get(i).getScrq() + "";
                            scrq += "]";
                        } else {
                            data1 += cxjxJsons.get(i).getWcsl() + ",";

                            scrq += cxjxJsons.get(i).getScrq() + ",";
                        }

                    }
                    lishicl_wv_month.loadUrl("javascript:createChart('line'," + data1 + ","  + scrq + ",'" + title + "');");
                } else {
                    String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
                    String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
                    lishicl_wv_month.loadUrl("javascript:createChart('line'," + errorData +  "," + errorDate + ",'" + title + "');");
                }


            } else {
                String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
                String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
                lishicl_wv_month.loadUrl("javascript:createChart('line'," + errorData + "," + errorDate + ",'" + title + "');");

            }

        }
    }

    /**
     * 加载培训内容数据
     *
     * @return
     */
    private String loadChanxjxzbData1() {
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cpdm>"
                + cpdm + "</cpdm><nf>" + year + "</nf><yf>" + month +
                "</yf></param></data></root>";
        //String method_name = "GetChanxjxzb";
        String method_name = "GetChanplsclData";
        String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
                param, method_name, mIp);

        if (!data.equals("")) {

            data = RequestDataUtil.splieRequestJson(data);
            Log.e("lishichanliang", data.substring(1, data.length()));
             return data.substring(1, data.length());
        } else {
            return null;
        }
    }

    private void bindData() {
        new ChanplsclTask().execute();
    }

    private void addListener() {
        imgfanhui22.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    /**
     * 异步加载历史信息
     *
     * @author Administrator
     */
    private class ChanplsclTask extends AsyncTask<Void, Void, Chanplscl> {

        @Override
        protected Chanplscl doInBackground(Void... arg0) {
            return loadChanplscl();
        }

        @Override
        protected void onPostExecute(Chanplscl result) {
            super.onPostExecute(result);
            //Toast.makeText(lishichanliang.this, result + "", Toast.LENGTH_LONG).show();

            if (result != null) {
                Picasso.with(lishichanliang.this)
                        .load(result.getCplscl_month()).into(lishicl_iv_month);
                //Toast.makeText(lishichanliang.this, result.getCplscl_year(), Toast.LENGTH_LONG).show();
                //Toast.makeText(lishichanliang.this, result.getCplscl_month(), Toast.LENGTH_LONG).show();
                Picasso.with(lishichanliang.this).load(result.getCplscl_year())
                        .into(lishicl_iv_year);

            }

        }
    }

    /**
     * 加载培训内容数据
     *
     * @return
     */
    private Chanplscl loadChanplscl() {
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cpdm>" + cpdm + "</cpdm><nf>" + year + "</nf><yf>" + month + "</yf></param></data></root>";
        String method_name = "GetChanplscl";
        String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
                param, method_name, mIp);
        if (!data.equals("")) {
            // Log.e("chanx", data);
            data = RequestDataUtil.splieRequestXml(data);
            return RequestDataUtil.parseChanplscl(data);

        } else {
            return null;
        }
    }

    private void init() {
        // TODO Auto-generated method stub
        mIp = getIntent().getStringExtra("ip");
        mUserName = getIntent().getStringExtra("username");
        mUserPwd = getIntent().getStringExtra("userpwd");
        cpdm = getIntent().getStringExtra("cpdm1");
        scrq = getIntent().getStringExtra("scrq");
        String scrqs[] = scrq.split("-");
        if (scrqs != null && scrqs.length > 1) {
            year = scrqs[0];
            month = scrqs[1];
        }
    }
}
