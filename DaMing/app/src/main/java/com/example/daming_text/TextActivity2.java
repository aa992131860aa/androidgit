package com.example.daming_text;

import java.io.File;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.entity.Chanxcpxx;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class TextActivity2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		  WebView wView = (WebView)findViewById(R.id.text_wv);     
	        WebSettings wSet = wView.getSettings();     
	        wSet.setJavaScriptEnabled(true);     
	                     
	        wView.loadUrl("file:///android_asset/test.html");    
	}

	
	 /**
	  * 异步加载培訓信息
	  * @author Administrator
	  *
	  */
//   private class ChanxtjtjTask extends AsyncTask<Void, Void, Chanxtjtj>{
//
//		@Override
//		protected Chanxtjtj doInBackground(Void... arg0) {
//			return loadChanxcpxxData();
//		}
//   	@Override
//   	protected void onPostExecute(Chanxcpxx result) {
//   		super.onPostExecute(result);
//   		if(result!=null&&result.getResult().equals("1")){
//   			//Toast.makeText(chanxianxinxi.this, "result:"+result.getResult(), 0).show();
//   		    mAdapter.refresh(result.getCpxxs());
//   		}
//
//   	}
//   }
//
//
//   /**
//    * 加载培训内容数据     
//    * @return
//    */
//	private Chanxcpxx loadChanxcpxxData() {
//		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
//				+ mUserName + "</cxdm></param></data></root>";
//		String method_name = "GetChanxcpxx";
//		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd, param,
//				method_name, mIp);
//		if (!data.equals("")) {
//			//Log.e("chanx", data);
//			data = RequestDataUtil.splieRequestXml(data);
//			return RequestDataUtil.parseChanxcpxx(data);
//
//		} else {
//			return null;
//		}
//	}

}
