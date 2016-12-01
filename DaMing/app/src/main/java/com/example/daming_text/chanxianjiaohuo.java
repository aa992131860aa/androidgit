package com.example.daming_text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.daming.entity.Chanxjxzb;
import com.daming.entity.Chanxtjtj;
import com.daming.entity.Jxzb;
import com.daming.entity.Tjtj;
import com.daming.json.CxjxJson;
import com.daming.json.TjtjJson;
import com.daming.util.RequestDataUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

public class chanxianjiaohuo extends Activity {
	@ViewInject(R.id.img_fanhui2)
	private ImageView btn_img3;
	@ViewInject(R.id.jiaohuo_gv_main)
	private GridView jiaohuo_gv_main;
	@ViewInject(R.id.jiaohuo_gv_month)
	private GridView jiaohuo_gv_month;
	@ViewInject(R.id.jiaohuo_tv_cxdm)
	private TextView jiaohuo_tv_cxdm;
	private String months[];
	private String weeks[];
	private MyGridMain mGridMain;
	private MyGridMonth mGridMonth;
	private int dayNumber = 6;
	private Calendar calendar;
	private int month = 0;
	private int firstDay = 0;
	private int mainRow = 6;
	private int monthRow = 2;
	private List<String> monthList;
	private String test;
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	private String scrq;
	@ViewInject(R.id.jiaohuo_iv_stop)
	private ImageView jiaohuo_iv_stop;
	@ViewInject(R.id.jiaohuo_iv_standard)
	private ImageView jiaohuo_iv_standard;
	private String cx_year;
	private String cx_month;
	private String test1;
	private Chanxjxzb chanxjxzb;
    private boolean isStart = true;
    private int flag;
	private String test5;
	private String testMain;
	//停机停产图表
	private WebView chartshow_wb;
	//计划/实际达成率
	private WebView jiaohuo_wv_rate;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chanxianjiaohu);
		ViewUtils.inject(this);
		init();
		addListener();
		bindData();

	}

	private void bindData() {
		// TODO Auto-generated method stub
		mGridMain = new MyGridMain();
		mGridMonth = new MyGridMonth();
		jiaohuo_gv_month.setAdapter(mGridMonth);
		jiaohuo_gv_main.setAdapter(mGridMain);
		jiaohuo_gv_month.setEnabled(false);
		jiaohuo_gv_month.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				arg0.getChildAt(flag).setBackgroundResource(R.drawable.biankuan2);
				view.setBackgroundResource(R.drawable.biankuan_month);
				flag = position;
				cx_month = (position+1)+"";
				new ChanxtjtjTask().execute();
				new ChanxjxzbTask().execute();
				new ChanxjxzbsjTask(jiaohuo_gv_month).execute();
				jiaohuo_gv_month.setEnabled(false);
			}
		});
		new ChanxtjtjTask().execute();
		new ChanxjxzbTask().execute();
		new ChanxjxzbsjTask(jiaohuo_gv_month).execute();
	}

	private List<String> getDayList() {
		calendar = Calendar.getInstance();
		List<String> list = new ArrayList<String>();
		month = calendar.get(Calendar.MONTH);
		for (int i = 0; i < weeks.length; i++) {
			list.add(weeks[i]);
		}
		// 获取本月第一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

		for (int i = 0; i < weekDay - 1; i++) {
			list.add("");
		}
		// 获取本月最后一天
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
		// 设置日期格式
		SimpleDateFormat sf = new SimpleDateFormat("dd");
		String ss = sf.format(calendar.getTime());
		int lastDay = Integer.parseInt(ss);
		for (int i = 1; i <= lastDay; i++) {
			list.add(i + "");
		}

		if (list.size() % 7 != 0) {
			for (int i = 0; i < list.size() % 7; i++) {
				list.add("");
			}
		}
		return list;
	}

	private void init() {
		// TODO Auto-generated method stub
		// 获取ip和用户密码
		mIp = getIntent().getStringExtra("ip");
		mUserName = getIntent().getStringExtra("username");
		mUserPwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
		jiaohuo_tv_cxdm.setText(mUserName);
		String scrqs[] = scrq.split("-");
		if (scrqs != null && scrqs.length > 1) {
			cx_year = scrqs[0];
			cx_month = scrqs[1];
		}
		months = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12" };
		weeks = new String[] { "日", "一", "二", "三", "四", "五", "六", };
		monthList = getDayList();
		if (monthList.size() * 1.0 / 7 > 6.0) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, 0);
			params.weight = 7.0f;
			mainRow = 7;
			jiaohuo_gv_main.setLayoutParams(params);
		}



		jiaohuo_wv_rate = (WebView) findViewById(R.id.jiaohuo_wv_rate);
		jiaohuo_wv_rate.getSettings().setAllowFileAccess(true);
		jiaohuo_wv_rate.getSettings().setJavaScriptEnabled(true);
		jiaohuo_wv_rate.loadUrl("file:///android_asset/echart/myechart.html");

		chartshow_wb=(WebView)findViewById(R.id.chartshow_wb);
		chartshow_wb.getSettings().setAllowFileAccess(true);
		chartshow_wb.getSettings().setJavaScriptEnabled(true);
		chartshow_wb.loadUrl("file:///android_asset/echart/myechart.html");


	}

	private void addListener() {
		btn_img3.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private class MyGridMonth extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return months.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return months[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			AbsListView.LayoutParams param = new AbsListView.LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					arg2.getHeight() / monthRow);
			view = LayoutInflater.from(chanxianjiaohuo.this).inflate(
					R.layout.month_text, null);
			view.setLayoutParams(param);
			view.setBackgroundResource(R.drawable.biankuan2);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					100, 200);
			// view.setLayoutParams(params);
			ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(100,
					200);
			TextView month_tv = (TextView) view.findViewById(R.id.month_tv);
			month_tv.setText(months[position]);
			// 设置选中月份的颜色
			if(!cx_month.equals("")){
				
				if (position == (Integer.parseInt(cx_month)-1)&&isStart) {
					view.setBackgroundResource(R.drawable.biankuan_month);
				    flag= position;
				    isStart = false;
				}
			}
			return view;
		}

	}

	private class MyGridMain extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return monthList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return monthList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			AbsListView.LayoutParams param = new AbsListView.LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					arg2.getHeight() / mainRow);
			view = LayoutInflater.from(chanxianjiaohuo.this).inflate(
					R.layout.month_main_text, null);
			view.setLayoutParams(param);

			TextView month_tv = (TextView) view
					.findViewById(R.id.month_tv_main);
			month_tv.setText(monthList.get(position));
			if (position == monthList.size() - 1) {

			}
			// 设置星期所在的颜色
			if (position < 7) {
				view.setBackgroundResource(R.drawable.biankuan_week);
			} else {

				view.setBackgroundResource(R.drawable.biankuan_main);
				if (chanxjxzb != null) {
					List<Jxzb> jxzbs = chanxjxzb.getJxzbs();
					if (jxzbs != null) {
						for (int i = 0; i < jxzbs.size(); i++) {
							Jxzb jxzb = jxzbs.get(i);
							if (jxzb != null) {
								String rq = "0";
								if (jxzb.getScrq() != null) {
									rq = jxzb.getScrq();

								}
								if (rq.equals(month_tv.getText().toString())) {
									if (jxzb.getBcdcl() != null
											&& jxzb.getBcdcl().equals("")||jxzb.getJhwcl() != null
													&& jxzb.getJhwcl().equals("")) {

									} else if (jxzb.getBcdcl() != null&&jxzb.getJhwcl() != null) {
										String bcdclStr = jxzb.getBcdcl();
										double bcdcl = Double.parseDouble(bcdclStr);
										String jhwclStr = jxzb.getJhwcl();
										double jhwcl = Double.parseDouble(jhwclStr);
										if (bcdcl >= 100&&jhwcl>=100) {
											view.setBackgroundResource(R.drawable.biankuan_green);
										} else if (bcdcl >= 100) {

											view.setBackgroundResource(R.drawable.biankuan_yellow);
										} else {
											view.setBackgroundResource(R.drawable.biankuan_red);
										}
									}
									break;
								}
							}
						}
					}
				}
			}
			return view;
		}

	}

	/**
	 * 异步加载培訓信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class ChanxtjtjTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return loadChanxcpxxData();
		}

		@Override
		protected void onPostExecute(String result) {

			super.onPostExecute(result);
			//Toast.makeText(chanxianjiaohuo.this, test5+"gg", Toast.LENGTH_SHORT).show();
			if (result != null) {

			TjtjJson tjtjJson =JSON.parseObject(result, TjtjJson.class);
			String title = mUserName+"生产线月累计停机数("+cx_year+"-"+cx_month+")";
			if(tjtjJson.getCode()==1){
				List<TjtjJson.DataBean> dataBeanList = tjtjJson.getData();
				String data1 = new String();
				String data2 = new String();
				for(int i=0;i<dataBeanList.size();i++)
				{
					if(i==0){
						data1 += "[";
						data1 += dataBeanList.get(i).getTjsj() +",";

						data2 += "[";
						data2 += dataBeanList.get(i).getJcsl() +",";
					}else if(i==dataBeanList.size()-1){
						data1 += dataBeanList.get(i).getTjsj() +"";
						data1 += "]";

						data2 += dataBeanList.get(i).getJcsl() +"";
						data2 += "]";
					}
					else{
						data1 += dataBeanList.get(i).getTjsj() +",";

						data2 += dataBeanList.get(i).getJcsl() +",";
					}

				}
				String title1 = mUserName+"生产线月累计停机数("+cx_year+"-"+cx_month+")";
				chartshow_wb.loadUrl("javascript:createChart('bar',"+data1+","+data2+",'"+title1+"');");

			}else{
                //没有数据
				String title2 = mUserName+"生产线月累计停机数("+cx_year+"-"+cx_month+")";
				chartshow_wb.loadUrl("javascript:createChart('bar',[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],'"+title2+"');");
			}

			}else{
				String title2 = mUserName+"生产线月累计停机数("+cx_year+"-"+cx_month+")";
				chartshow_wb.loadUrl("javascript:createChart('bar',[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],'"+title2+"');");
			}

		}
	}

	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private String loadChanxcpxxData() {
		String param = "<?xml version=\"1.0\" encoding=\"gb2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
				+ mUserName
				+ "</cxdm><nf>"
				+ cx_year
				+ "</nf><yf>"
				+ cx_month
				+ "</yf></param></data></root>";
		String method_name = "GetChanxtjtjData";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		 if(!data.equals("")){

			 data = RequestDataUtil.splieRequestJson(data);
			 //Log.e("chan",data);
			 return data.substring(1,data.length());
		 }

		return  null;

	}

	/**
	 * 异步加载培訓信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class ChanxjxzbTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return loadChanxjxzbData();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String title = mUserName+"生产线\"计划/班产\"达成率("+cx_year+"-"+cx_month+")";
			if (result != null) {
				Gson gson = new Gson();

				CxjxJson cxjxJson = gson.fromJson(result, CxjxJson.class);
				Log.e("GetChanxjxzbsl",cxjxJson.getCode()+":result");

				if(cxjxJson.getCode()==1){
                   List<CxjxJson.DataBean> cxjxJsons = cxjxJson.getData();



					String data1 = new String();
					String data2 = new String();
					String scrq = new String();
				for(int i=0;i<cxjxJsons.size();i++){
						if(i==0){
							data1 += "[";
							data1 += cxjxJsons.get(i).getJhwcl() +",";

							data2 += "[";
							data2 += cxjxJsons.get(i).getBcdcl() +",";

							scrq += "[";
							scrq += cxjxJsons.get(i).getScrq() +",";
						}else if(i==cxjxJsons.size()-1){
							data1 += cxjxJsons.get(i).getJhwcl() +"";
							data1 += "]";

							data2 += cxjxJsons.get(i).getBcdcl() +"";
							data2 += "]";

							scrq += cxjxJsons.get(i).getScrq() +"";
							scrq += "]";
						}
						else{
							data1 += cxjxJsons.get(i).getJhwcl() +",";

							data2 += cxjxJsons.get(i).getBcdcl() +",";

							scrq += cxjxJsons.get(i).getScrq() +",";
						}

					}
					jiaohuo_wv_rate.loadUrl("javascript:createChart('line',"+data1+","+data2+","+scrq+",'"+title+"');");
				}
		      else{
					String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
					String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
					jiaohuo_wv_rate.loadUrl("javascript:createChart('line',"+errorData+","+errorData+","+errorDate+",'"+title+"');");
				}


			}else{
				String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
				String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
				jiaohuo_wv_rate.loadUrl("javascript:createChart('line',"+errorData+","+errorData+","+errorDate+",'"+title+"');");

			}

		}
	}

	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private String loadChanxjxzbData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
				+ mUserName
				+ "</cxdm><nf>"
				+ cx_year
				+ "</nf><yf>"
				+ cx_month
				+ "</yf></param></data></root>";
		//String method_name = "GetChanxjxzb";
		String method_name = "GetChanxjxzbData";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		test = data;
		if (!data.equals("")) {
			// Log.e("chanx", "jx"+data);
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
	private class ChanxjxzbsjTask extends AsyncTask<Void, Void, Chanxjxzb> {
         private GridView jiaohuo_gv_month;
		public ChanxjxzbsjTask(GridView jiaohuo_gv_month) {
			// TODO Auto-generated constructor stub
			this.jiaohuo_gv_month = jiaohuo_gv_month;
		}

		@Override
		protected Chanxjxzb doInBackground(Void... arg0) {
			return loadChanxjxzbsjData();
		}

		@Override
		protected void onPostExecute(Chanxjxzb result) {
			super.onPostExecute(result);
			jiaohuo_gv_month.setEnabled(true);
			//Log.e("GetChanxjxzbsl",testMain);
			if (result != null) {
				chanxjxzb = result;
				jiaohuo_gv_main.setAdapter(mGridMain);
			}

		}
	}

	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private Chanxjxzb loadChanxjxzbsjData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
				+ mUserName
				+ "</cxdm><nf>"
				+ cx_year
				+ "</nf><yf>"
				+ cx_month
				+ "</yf></param></data></root>";
		String method_name = "GetChanxjxzbsl";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		 testMain = data;
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseChanxjxzb(data);

		} else {
			return null;
		}
	}
}
