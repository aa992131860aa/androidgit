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
import com.daming.entity.Chanxcpxx;
import com.daming.entity.Cpxx;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class chanxianxinxi extends Activity {
	@ViewInject(R.id.img_fanhui)
	private ImageView imageView;
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	@ViewInject(R.id.jibenxinxi_lv)
	private ListView jibebxinxi_lv;
	private ChanxianxinxiAdapter mAdapter;
	private List<Cpxx> cpxxs;
	private String test;
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.chanxianjibenxinxi);
			init();
			new ChanxcpxxTask().execute();
			addListener();
		
		}
		 /**
		  * 异步加载培訓信息
		  * @author Administrator
		  *
		  */
	    private class ChanxcpxxTask extends AsyncTask<Void, Void, Chanxcpxx>{
 
			@Override
			protected Chanxcpxx doInBackground(Void... arg0) {
				return loadChanxcpxxData();
			}
	    	@Override
	    	protected void onPostExecute(Chanxcpxx result) {
	    		super.onPostExecute(result);
	    		Log.e("result", test);
	    		//Toast.makeText(chanxianxinxi.this, "result:"+test, 0).show();
	    		if(result!=null&&result.getResult().equals("1")){
	    		    mAdapter.refresh(result.getCpxxs());
	    		}

	    	}
	    }
	 
	
	    /**
	     * 加载培训内容数据     
	     * @return
	     */
		private Chanxcpxx loadChanxcpxxData() {
			String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
					+ mUserName + "</cxdm></param></data></root>";
			String method_name = "GetChanxcpxx";
			String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd, param,
					method_name, mIp);
			test = data;
			if (!data.equals("")) {
				//Log.e("chanx", data);
				data = RequestDataUtil.splieRequestXml(data);
				return RequestDataUtil.parseChanxcpxx(data);

			} else {
				return null;
			}
		}
		private void addListener() {
			// TODO Auto-generated method stub
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					finish();
				}
			});
		}
		
		private void init() {
			// TODO Auto-generated method stub
			ViewUtils.inject(this);
		    cpxxs = new ArrayList<Cpxx>();
			mAdapter = new ChanxianxinxiAdapter(this, cpxxs);
			jibebxinxi_lv.setAdapter(mAdapter);
			mIp = getIntent().getStringExtra("ip");
			mUserName = getIntent().getStringExtra("username");
			mUserPwd = getIntent().getStringExtra("userpwd");
		}
}
