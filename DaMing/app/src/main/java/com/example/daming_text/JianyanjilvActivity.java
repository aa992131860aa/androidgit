package com.example.daming_text;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daming.adapter.AnquanListAdapter;
import com.daming.adapter.JianyanListAdapter;
import com.daming.entity.Anqll;
import com.daming.entity.Chanpjyjl;
import com.daming.entity.TongZi;
import com.daming.entity.TongZis;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class JianyanjilvActivity extends Activity{
	private String mUserName;
	private String mUserPwd;
	private String mIp;
	//通知列表
	private TongZis tongZis;
	//通知信息
	private TongZi tongZi;
	private List<String> mList ;
    private JianyanListAdapter adapter;
    private ListView tongzi_lv;
	private String test;
	private String cpdm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.jianyan);
		cpdm1 = getIntent().getStringExtra("cpdm1");
    	tongzi_lv = (ListView) findViewById(R.id.jianyan_lv);
    	mList = new ArrayList<String>();
    	adapter = new JianyanListAdapter(this, mList);
    	tongzi_lv.setAdapter(adapter);
    	loadData();
		Toast.makeText(this,test,Toast.LENGTH_SHORT).show();
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
	private class TongZisTask extends AsyncTask<Void, Void, Chanpjyjl>{

		@Override
		protected Chanpjyjl doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return loadTongZisData();
		}
		@Override
		protected void onPostExecute(Chanpjyjl result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//Toast.makeText(JianyanjilvActivity.this,test,Toast.LENGTH_SHORT).show();
			if(result!=null&&result.getSrc_jyjls()!=null&&result.getSrc_jyjls().size()>0){
			  List<String> tempList = new ArrayList<>();
			  tempList = result.getSrc_jyjls();
			  for(int i=tempList.size()-1;i>=0;i--){
				  //tempList.get(i).setYd("1");
				  mList.add(tempList.get(i));
			  }
			  adapter.refresh(mList);

			  tongzi_lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, final View view,
						int arg2, long arg3) {
					// browser_pb.setVisibility(View.VISIBLE);
					arg0.setEnabled(false);
					   //mList.get(arg2).setYd("0");
					   //adapter.refresh(mList);
					ImageView browser_tv_flag = (ImageView) view.findViewById(R.id.browser_tv_flag);
                   // browser_tv_flag.setImageResource(android.R.color.transparent);
					
                    
					
					String src =	mList.get(arg2);
					int lastOf = src.lastIndexOf("/");
					String srcPdf = src.substring(lastOf, src.length());	
		            final String targe = getDiskCacheDir(JianyanjilvActivity.this)+srcPdf;
						new HttpUtils().download(src, targe, new RequestCallBack<File>() {
							
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
	public Chanpjyjl loadTongZisData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cpdm>"
				+ cpdm1
				+ "</cpdm></param></data></root>";
		String method_name = "GetChanpjyjl";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd, param,
				method_name, mIp);
		test = data;
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return  RequestDataUtil.parseChanpjyjl(data);
			
		}
		return null;
	}
	
}
