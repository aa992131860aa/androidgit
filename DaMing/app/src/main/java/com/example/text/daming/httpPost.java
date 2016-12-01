package com.example.text.daming;

import java.io.IOException;

import org.json.JSONException;

/**
 * Created by chuliangliang on 15/8/6.
 */
public class httpPost {

//    public static final String UTF_8 = "UTF-8";
//
//    public String post2Json(String url , String body) throws JSONException{
//        String retSrc = new String();
//
//        try {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost request = new HttpPost(url);
//
//            // 绑定到请求 Entry
//            StringEntity se = new StringEntity(body,"utf-8");
//            request.setHeader("Content-Type", "application/json");
//            request.setEntity(se);
//
//            // 发送请求
////            httpclient.getParams().setContentCharset(UTF_8);
////            httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//            HttpResponse httpResponse = httpclient.execute(request);
//            // 得到应答的字符串，这也是一个 JSON 格式保存的数据
//            retSrc = EntityUtils.toString(httpResponse.getEntity());
//        } catch (IOException e) {
//            e.printStackTrace();
//            retSrc = "error";
//        }
//
//        return retSrc;
//    }
}
