package com.example.daming_text;

import java.io.File;

import com.daming.entity.Chanpjswj;
import com.daming.entity.Chanpjyjl;
import com.daming.entity.TongZi;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

public class tuzhiwenjian extends Activity {
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	private String scrq;
	private String cpdm;
    private String test;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tuzhiwenjian);
		ViewUtils.inject(this);
		init();
		new ChanpjswjTask().execute();
	}

	private void init() {
		// TODO Auto-generated method stub
		mIp = getIntent().getStringExtra("ip");
		mUserName = getIntent().getStringExtra("username");
		mUserPwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
		cpdm = getIntent().getStringExtra("cpdm1");
	}

	/**
	 * 异步加载历史信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class ChanpjswjTask extends AsyncTask<Void, Void, Chanpjswj> {

		@Override
		protected Chanpjswj doInBackground(Void... arg0) {
			return loadChanpjswj();
		}

		
		@Override
		protected void onPostExecute(Chanpjswj result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null&&result.getSrc_tzwj()!=null&&!result.getSrc_tzwj().equals("")){
			String src =	result.getSrc_tzwj();
			int lastOf = src.lastIndexOf("/");
			String srcPdf = src.substring(lastOf, src.length());	
            final String targe = TongZiActivity.getDiskCacheDir(tuzhiwenjian.this)+srcPdf;
				new HttpUtils().download(result.getSrc_tzwj(), targe, new RequestCallBack<File>() {
					
					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						// TODO Auto-generated method stub
						//showDocument(Uri.fromFile(new File(targe)));
						   Uri path = Uri.fromFile(new File(targe));
				             Intent intent = new Intent(Intent.ACTION_VIEW);
				             intent.setDataAndType(path, "application/pdf");
				             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				                 //startActivity(intent);
				                 startActivityForResult(intent, 100);
					}
					
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
					}
				});
			}else{
				Toast.makeText(tuzhiwenjian.this, "没有文件", 0).show();
				finish();
			}
		}
	}
  @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	finish();
}
	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private Chanpjswj loadChanpjswj() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cpdm>"
				+ cpdm
				+ "</cpdm></param></data></root>";
		String method_name = "GetChanpjswj";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		if (!data.equals("")) {
			// Log.e("chanx", data);
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseChanpjswj(data);

		} else {
			return null;
		}
	}

}
