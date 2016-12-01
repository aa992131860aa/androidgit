package com.example.daming_text;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daming.adapter.ChanxianxinxiAdapter;
import com.daming.adapter.ShengchanPCAdapter;
import com.daming.entity.Chanxpcxx;
import com.daming.entity.Pcxx;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class shengchanPC extends Activity {
	@ViewInject(R.id.btn_img01)
	private ImageView img01;
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	private String scrq;
	@ViewInject(R.id.shengchan_lv)
	private ListView shengchan_lv;
	private ShengchanPCAdapter mAdapter;
	private List<Pcxx> pcxxs;
	private String test;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shengchanpacheng);
		init();
		new ChanxpcxxTask().execute();
		addListener();
	}
	 /**
	  * 异步加载培訓信息
	  * @author Administrator
	  *
	  */
   private class ChanxpcxxTask extends AsyncTask<Void, Void, Chanxpcxx>{

		@Override
		protected Chanxpcxx doInBackground(Void... arg0) {
			return loadChanxpcxxData();
		}
   	@Override
   	protected void onPostExecute(Chanxpcxx result) {
   		super.onPostExecute(result);
   		//Toast.makeText(shengchanPC.this, test+"1", 0).show();
   		//Log.e("shengchan", test);
   		if(result!=null&&result.getResult().equals("1")){
   		    mAdapter.refresh(result.getPcxxs(),mUserName,scrq);
   		}

   	}
   }


   /**
    * 加载培训内容数据     
    * @return
    */
	private Chanxpcxx loadChanxpcxxData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
				+ mUserName + "</cxdm></param></data></root>";
		String method_name = "GetChanxpcxx";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd, param,
				method_name, mIp);
		test = data;
		if (!data.equals("")) {
			//Log.e("chanx", data);
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseChanxpcxx(data);

		} else {
			return null;
		}
	}
	private void addListener() {
		img01.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		}
	
	private void init() {
		// TODO Auto-generated method stub
		ViewUtils.inject(this);
	    pcxxs = new ArrayList<Pcxx>();
		mAdapter = new ShengchanPCAdapter(this, pcxxs);
		shengchan_lv.setAdapter(mAdapter);
		mIp = getIntent().getStringExtra("ip");
		mUserName = getIntent().getStringExtra("username");
		mUserPwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
	}
}
