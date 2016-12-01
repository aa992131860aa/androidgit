package com.example.daming_text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.daming.adapter.TongZiListAdapter;
import com.daming.entity.TongZi;
import com.daming.entity.TongZiRecord;
import com.daming.entity.TongZis;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;


public class TongZiActivity extends Activity{
	private String mUserName;
	private String mUserPwd;
	private String mIp;
	//通知列表
	private TongZis tongZis;
	//通知信息
	private TongZi tongZi;
	private List<TongZiRecord> mList ;
    private TongZiListAdapter adapter;
    private ListView tongzi_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.tongzi);
    	tongzi_lv = (ListView) findViewById(R.id.tongzi_lv);
    	mList = new ArrayList<TongZiRecord>();
    	adapter = new TongZiListAdapter(this, mList);
    	tongzi_lv.setAdapter(adapter);
    	loadData();
    	
    }
   
	protected void loadData() {
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			mUserName = bundle.getString("username");
			mUserPwd = bundle.getString("userpwd");
			mIp = bundle.getString("ip");
			new TongZisTask().execute();
		}
	}
	/**
	 * 异步加载通知列表
	 * @author Administrator
	 *
	 */
	private class TongZisTask extends AsyncTask<Void, Void, TongZis>{

		@Override
		protected TongZis doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return loadTongZisData();
		}
		@Override
		protected void onPostExecute(TongZis result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null&&result.getTongZiRecordList().size()>0){
			  List<TongZiRecord> tempList = new ArrayList<TongZiRecord>();
			  tempList = result.getTongZiRecordList();
			  for(int i=tempList.size()-1;i>=0;i--){
				  //tempList.get(i).setYd("1");
				  mList.add(tempList.get(i));
			  }
			  adapter.refresh(mList);

			  tongzi_lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int arg2, long arg3) {
					// browser_pb.setVisibility(View.VISIBLE);
					arg0.setEnabled(false);
					   //mList.get(arg2).setYd("0");
					   //adapter.refresh(mList);
					ImageView browser_tv_flag = (ImageView) view.findViewById(R.id.browser_tv_flag);
                    browser_tv_flag.setImageResource(android.R.color.transparent);
					new TongZiTask(mList.get(arg2).getTzid(),arg0).execute();
                    	
				}
			});
			}
		}
	}
	/**
	 * 异步加载通知列表
	 * @author Administrator
	 *
	 */
	private class TongZiTask extends AsyncTask<Void, Void, TongZi>{
		String mZtid;
		AdapterView<?> view;
        public TongZiTask(String ztid,AdapterView<?> view){
        	mZtid = ztid;
        	this.view = view;
        }
		@Override
		protected TongZi doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return loadTongZiData(mZtid);
		}
		@Override
		protected void onPostExecute(TongZi result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
			String src =	result.getZw();
			int lastOf = src.lastIndexOf("/");
			String srcPdf = src.substring(lastOf, src.length());	
            final String targe = getDiskCacheDir(TongZiActivity.this)+srcPdf;
				new HttpUtils().download(result.getZw(), targe, new RequestCallBack<File>() {
					
					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						// TODO Auto-generated method stub
						//showDocument(Uri.fromFile(new File(targe)));
						   Uri path = Uri.fromFile(new File(targe));
				             Intent intent = new Intent(Intent.ACTION_VIEW);
				             intent.setDataAndType(path, "application/pdf");
				             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				                 startActivity(intent);
						view.setEnabled(true);
					}
					
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
					}
				});
			}
		}
	}
	/**
	 * 根据传入的uniqueName获取硬盘缓存的路径地址
	 * 
	 * @author blue
	 */
	@SuppressLint("NewApi")
	public static String getDiskCacheDir(Context context)
	{
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable())
		{
			cachePath = context.getExternalCacheDir().getAbsolutePath();
		} else
		{
			cachePath = context.getCacheDir().getAbsolutePath();
		}
		return cachePath;
	}
	/**
	 * 加载通知列表
	 * @return
	 */
	public TongZis loadTongZisData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param></param></data></root>";
		String method_name = "GetTongzList";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd, param,
				method_name, mIp);
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			tongZis = RequestDataUtil.parseTongZis(data);
			return tongZis;
		}
		return null;
	}
	/** 
	 * 加载通知信息
	 * @param tzid  通知id
	 * @return
	 */
	public TongZi loadTongZiData(String tzid) {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param><tzid>"+tzid+"</tzid></param></data></root>";
		String method_name = "GetTongz";
		
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd, param,
				method_name, mIp);
		if (!data.equals("")) {
			
			data = RequestDataUtil.splieRequestXml(data);
			tongZi = RequestDataUtil.parseTongZi(data);
			return tongZi;
		}
		return null;
	}
}
