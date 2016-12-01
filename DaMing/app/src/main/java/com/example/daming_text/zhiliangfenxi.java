package com.example.daming_text;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.daming.entity.Chanplszl;
import com.daming.entity.Chanpzlss;
import com.daming.entity.ChanpzlssEx;
import com.daming.entity.Chanpzlssjl;
import com.daming.entity.Zlssjl;
import com.daming.json.TjtjJson;
import com.daming.json.ZhiliangfenxissJson;
import com.daming.json.ZhiliangfenxizlJson;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class zhiliangfenxi extends Activity {
	@ViewInject(R.id.img_fanhui000)
	private ImageView imgfanhui;
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	private String scrq;
	private String year;
	private String month;
	private String cpdm;
	@ViewInject(R.id.zhiliangfx_iv1)
	private ImageView zhiliangfx_iv1;
	@ViewInject(R.id.zhiliangfx_tv_cpdm)
	private TextView zhiliangfx_tv_cpdm;
	@ViewInject(R.id.zhiliangfx_iv2)
	private ImageView zhiliangfx_iv2;
	@ViewInject(R.id.zhiliangfx_lv_deal)
	private ListView zhiliangfx_lv_deal;
	private MyListViewDeal myListViewDeal;
	private Chanpzlssjl chanpzlssjl;
	private String test;
	private String testm;
	private WebView zhiliangfx_wv_ss;
	private WebView zhiliangfx_wv_zl;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zhiliangfenxi);
        ViewUtils.inject(this);
		init();
		addListener();
		bindData();

		zhiliangfx_wv_ss = (WebView) findViewById(R.id.zhiliangfx_wv_ss);
		zhiliangfx_wv_ss.getSettings().setAllowFileAccess(true);
		zhiliangfx_wv_ss.getSettings().setJavaScriptEnabled(true);
		zhiliangfx_wv_ss.loadUrl("file:///android_asset/echart/myechart_cpzl_ss.html");
		new ChanpzlssTask().execute();

		zhiliangfx_wv_zl = (WebView) findViewById(R.id.zhiliangfx_wv_zl);
		zhiliangfx_wv_zl.getSettings().setAllowFileAccess(true);
		zhiliangfx_wv_zl.getSettings().setJavaScriptEnabled(true);
		zhiliangfx_wv_zl.loadUrl("file:///android_asset/echart/myechart_cpzl_zl.html");
        new ChanplszlTask().execute();



		
	}
	private void bindData() {
		// TODO Auto-generated method stub
		
	}
	private void addListener() {
		// TODO Auto-generated method stub
		imgfanhui.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private ChanpzlssEx loadChanpzlssEx() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> " +
				"<cpdm>"+"30202G04"+"</cpdm><nf>"+"0"+"</nf><yf>"+"0"+"</yf></param></data></root>";
		String method_name = "GetChanpzlssEx";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		
		if (!data.equals("")) {
			// Log.e("chanx", data);
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseChanpzlssEx(data);

		} else {
			return null;
		}
	}

	/**
	 * 异步加载培訓信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class ChanplszlTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return loadChanplszl();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String title = cpdm+"历史产量("+year+"年)";
			Log.e("chanx", "zl:"+result);
			if (result != null) {

				ZhiliangfenxizlJson tjtjJson = JSON.parseObject(result, ZhiliangfenxizlJson.class);

				if(tjtjJson.getCode()==1){
					List<ZhiliangfenxizlJson.DataBean> dataBeanList = tjtjJson.getData();
					String data1 = new String();
					String data2 = new String();
					String data3 = new String();
					String data4 = new String();
					String scrq = new String();

					for(int i=0;i<dataBeanList.size();i++)
					{
						if(i==0){
							data1 += "[";
							data1 += dataBeanList.get(i).getWcsl() +",";

							data2 += "[";
							data2 += dataBeanList.get(i).getGfs() +",";

							data3 += "[";
							data3 += dataBeanList.get(i).getKhts() +",";

							data4 += "[";
							data4 += dataBeanList.get(i).getTjs() +",";

							scrq += "[";
							scrq += dataBeanList.get(i).getMonth() +",";

						}else if(i==dataBeanList.size()-1){
							data1 += dataBeanList.get(i).getWcsl() +"";
							data1 += "]";

							data2 += dataBeanList.get(i).getGfs() +"";
							data2 += "]";

							data3 += dataBeanList.get(i).getKhts() +"";
							data3 += "]";

							data4 += dataBeanList.get(i).getTjs() +"";
							data4 += "]";

							scrq += dataBeanList.get(i).getMonth() +"";
							scrq += "]";
						}
						else{
							data1 += dataBeanList.get(i).getWcsl() +",";

							data2 += dataBeanList.get(i).getGfs() +",";

							data3 += dataBeanList.get(i).getKhts() +",";

							data4 += dataBeanList.get(i).getTjs() +",";

							scrq += dataBeanList.get(i).getMonth() +",";
						}

					}

					zhiliangfx_wv_zl.loadUrl("javascript:createChart('bar',"+data1+","+data2+","+data3+","+data4+",'"+title+"');");

				}else{
					//没有数据

					String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
					String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
					zhiliangfx_wv_zl.loadUrl("javascript:createChart('bar',"+errorData+","+errorData+","+errorData+","+errorData+",'"+title+"');");
				}

			}else{

				String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
				String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
				zhiliangfx_wv_zl.loadUrl("javascript:createChart('bar',"+errorData+","+errorData+","+errorData+","+errorData+",'"+title+"');");
			}

		}
	}

	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private String loadChanplszl() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data>" +
				" <param> <cpdm>"+cpdm+"</cpdm><nf>"+year+"</nf><yf>"+month+"</yf></param></data></root>";
		String method_name = "GetChanplszlData";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		if (!data.equals("")) {

			data = RequestDataUtil.splieRequestJson(data);

			return data.substring(1,data.length());

		} else {
			return null;
		}
	}
	/**
	 * 异步加载培訓信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class ChanpzlssTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return loadChanpzlss();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String title = cpdm+"缺陷模型分析("+year+"年"+month+"月)";
			if (result != null) {

				ZhiliangfenxissJson tjtjJson = JSON.parseObject(result, ZhiliangfenxissJson.class);

				if(tjtjJson.getCode()==1){
					List<ZhiliangfenxissJson.DataBean> dataBeanList = tjtjJson.getData();
					String data1 = new String();
					for(int i=0;i<dataBeanList.size();i++)
					{
						if(i==0){
							data1 += "[";
							data1 += dataBeanList.get(i).getSl() +",";

						}else if(i==dataBeanList.size()-1){
							data1 += dataBeanList.get(i).getSl() +"";
							data1 += "]";
						}
						else{
							data1 += dataBeanList.get(i).getSl() +",";
						}

					}
                    Log.e("zhiliangfenxi",data1+title);
					zhiliangfx_wv_ss.loadUrl("javascript:createChart('bar',"+data1+",'"+title+"');");

				}else{
					//没有数据

					zhiliangfx_wv_ss.loadUrl("javascript:createChart('bar',[0,0,0,0,0,0,0],'"+title+"');");
				}

			}else{

				zhiliangfx_wv_ss.loadUrl("javascript:createChart('bar',[0,0,0,0,0,0,0],'"+title+"');");
			}

		}
	}

	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private String loadChanpzlss() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> " +
				"<param> <cpdm>"+cpdm+"</cpdm><nf>"+year+"</nf><yf>"+month+"</yf></param></data></root>";
		String method_name = "GetChanpzlssdata";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		//Log.e("zhiliangfenxi1",data);
		if (!data.equals("")) {
			// Log.e("chanx", data);
			data = RequestDataUtil.splieRequestJson(data);
			String dataJson = data.substring(1,data.length());
			Log.e("zhiliangfenxi",dataJson);
			//return RequestDataUtil.parseChanpzlss(data);
            return dataJson;
		} else {
			return null;
		}
	}
	private void init(){
		mIp = getIntent().getStringExtra("ip");
		mUserName = getIntent().getStringExtra("username");
		mUserPwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
		cpdm = getIntent().getStringExtra("cpdm1");
		zhiliangfx_tv_cpdm.setText(cpdm);
		String scrqs [] = scrq.split("-");
		if(scrqs!=null&&scrqs.length>1){
			year = scrqs[0];
			month = scrqs[1];
		}
		myListViewDeal = new MyListViewDeal();

		zhiliangfx_lv_deal.setAdapter(myListViewDeal);


		//new ChanpzlssExTask().execute();
		new ChanpzlssjlTask().execute();
	}
	 private class MyListViewDeal extends BaseAdapter{
	        
			@Override
			public int getCount() {
				if (chanpzlssjl != null && chanpzlssjl.getZlssjls() != null) {
					return chanpzlssjl.getZlssjls().size();
				}
				return 0;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {


				AbsListView.LayoutParams param = new AbsListView.LayoutParams(
						android.view.ViewGroup.LayoutParams.MATCH_PARENT,
						parent.getHeight() / 3);
				convertView = LayoutInflater.from(zhiliangfenxi.this).inflate(R.layout.zhiliang_title, null);

				TextView title_tv_date = (TextView) convertView.findViewById(R.id.title_tv_date);
				TextView title_tv_no= (TextView) convertView.findViewById(R.id.title_tv_no);
				TextView title_tv_source= (TextView) convertView.findViewById(R.id.title_tv_source);
				TextView title_tv_description= (TextView) convertView.findViewById(R.id.title_tv_description);
				TextView title_tv_status= (TextView) convertView.findViewById(R.id.title_tv_status);
				//convertView.setLayoutParams(param);
				// Toast.makeText(chanxianzhiliang.this, "gg"+chanxzlhj, 0).show();
				if (chanpzlssjl != null && chanpzlssjl.getZlssjls() != null)
				{

					Zlssjl zlssjl = chanpzlssjl.getZlssjls().get(position);
					title_tv_date.setText(zlssjl.getScrq());
					title_tv_no.setText(cpdm);
					title_tv_source.setText(zlssjl.getSjly());
					title_tv_description.setText(zlssjl.getWtms());
					title_tv_status.setText(zlssjl.getZt());
				}
				return convertView;
			}
	    	
	    }
	 /**
	  * 异步加载产线质量呼叫
	  * @author Administrator
	  *
	  */
private class ChanpzlssjlTask extends AsyncTask<Void, Void, Chanpzlssjl>{

		@Override
		protected Chanpzlssjl doInBackground(Void... arg0) {
			return loadChanxzlhjData();
		}
	@Override
	protected void onPostExecute(Chanpzlssjl result) {
		super.onPostExecute(result);
            //chanxzlhj = result;
		//.makeText(zhiliangfenxi.this, result.getZlssjls()+"", 0).show();

		if(result!=null&&result.getResult().equals("1")){
			chanpzlssjl = result;
			zhiliangfx_lv_deal.setAdapter(myListViewDeal);
			
		}

	}
}


/**
 * 加载产线质量呼叫数据     
 * @return
 */
	private Chanpzlssjl loadChanxzlhjData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> " +
				"<param> <cpdm>"+cpdm+"</cpdm></param></data></root>";

		String method_name = "GetChanpzlssjl";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd, param,
				"GetChanpzlssjl", mIp);
		test = data;
		testm = param;
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseChapzlssjl(data);

		} else {
			return null;
		}
	}
}
