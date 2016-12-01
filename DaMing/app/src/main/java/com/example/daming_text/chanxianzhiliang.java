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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.entity.Chanpzlssjl;
import com.daming.entity.Chanxjxzb;
import com.daming.entity.Chanxzlhj;
import com.daming.entity.Chanxzlssjl;
import com.daming.entity.Jxzb;
import com.daming.entity.Zlhj;
import com.daming.entity.Zlssjl;
import com.daming.json.CxjxJson;
import com.daming.util.RequestDataUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

public class chanxianzhiliang extends Activity {
	@ViewInject(R.id.img_fanhui1)
	private ImageView iView;
	@ViewInject(R.id.zhiliang_gv_main)
	private GridView zhiliang_gv_main;
	@ViewInject(R.id.zhiliang_gv_month)
	private GridView zhiliang_gv_month;
	@ViewInject(R.id.zhiliang_lv_deal)
	private ListView zhiliang_lv_deal;
	@ViewInject(R.id.zhiliang_tv_cxdm)
	private TextView zhiliang_tv_cxdm;
	private String months[];
	private String weeks[];
	private MyGridMain mGridMain;
	private MyGridMonth mGridMonth;
	private MyListViewDeal myListViewDeal;
	private int dayNumber = 6;
	private Calendar calendar;

	private int month = 0;
	private int firstDay = 0;
	private int mainRow = 6;
	private int monthRow = 2;
	private List<String> monthList;
	private String test;
	private String test2;
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	private String scrq;
	@ViewInject(R.id.zhiliang_iv_standard)
	private ImageView zhiliang_iv_standard;
	private String cx_year;
	private String cx_month;
	private String test1;
	private Chanxzlhj chanxzlhj;
	private  Chanpzlssjl chanxzlssjl;
	private Chanxjxzb chanxjxzb;
	private int flag;
	private boolean isStart = true;
	//webview table image
	private WebView zhiliang_wv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chanxianzhiliang);
		ViewUtils.inject(this);
		init();
		addListener();
		bindData();
		new Chanxjxzb1Task().execute();
	}

	private void bindData() {
		// TODO Auto-generated method stub
		mGridMain = new MyGridMain();
		mGridMonth = new MyGridMonth();
		myListViewDeal = new MyListViewDeal();
		zhiliang_gv_month.setAdapter(mGridMonth);
		zhiliang_gv_main.setAdapter(mGridMain);
		zhiliang_lv_deal.setAdapter(myListViewDeal);
		zhiliang_gv_month.setEnabled(false);
		zhiliang_gv_month.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				arg0.getChildAt(flag).setBackgroundResource(
						R.drawable.biankuan2);
				view.setBackgroundResource(R.drawable.biankuan_month);
				flag = position;
				cx_month = (position + 1) + "";
				new ChanxzlssjlTask().execute();
				new ChanxjxzbTask(zhiliang_gv_month).execute();
				zhiliang_gv_month.setEnabled(false);
			}
		});
		new ChanxjxzbTask(zhiliang_gv_month).execute();
		new ChanxzlssjlTask().execute();

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

		zhiliang_wv = (WebView) findViewById(R.id.zhiliang_wv);
		zhiliang_wv.getSettings().setAllowFileAccess(true);
		zhiliang_wv.getSettings().setJavaScriptEnabled(true);
		zhiliang_wv.loadUrl("file:///android_asset/echart/myechart_cxzl.html");


		// 获取ip和用户密码
		mIp = getIntent().getStringExtra("ip");
		mUserName = getIntent().getStringExtra("username");
		mUserPwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
		zhiliang_tv_cxdm.setText(mUserName);
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
			zhiliang_gv_main.setLayoutParams(params);
		}

	}

	private void addListener() {
		iView.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private class MyListViewDeal extends BaseAdapter {

		@Override
		public int getCount() {
//			if (chanxzlssjl != null && chanxzlssjl.getZlssjls() != null) {
//				if (chanxzlssjl.getZlssjls().size() > 3) {
//					return chanxzlssjl.getZlssjls().size();
//				} else {
//					return 3;
//				}
//			} else {
//				return 3;
//			}
			if (chanxzlssjl != null && chanxzlssjl.getZlssjls() != null) {
				return chanxzlssjl.getZlssjls().size();
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
			// TODO Auto-generated method stub
			AbsListView.LayoutParams param = new AbsListView.LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					parent.getHeight() / 3);
			convertView = LayoutInflater.from(chanxianzhiliang.this).inflate(
					R.layout.zhiliang_title, null);
			TextView title_tv_date = (TextView) convertView
					.findViewById(R.id.title_tv_date);
			TextView title_tv_no = (TextView) convertView
					.findViewById(R.id.title_tv_no);
			TextView title_tv_source = (TextView) convertView
					.findViewById(R.id.title_tv_source);
			TextView title_tv_description = (TextView) convertView
					.findViewById(R.id.title_tv_description);
			TextView title_tv_status = (TextView) convertView
					.findViewById(R.id.title_tv_status);
			//convertView.setLayoutParams(param);
			// Toast.makeText(chanxianzhiliang.this, "gg"+chanxzlhj, 0).show();
			if (chanxzlssjl != null && chanxzlssjl.getZlssjls() != null)
					 {

				Zlssjl zlhj = chanxzlssjl.getZlssjls().get(position);
				title_tv_date.setText(zlhj.getScrq());
				title_tv_no.setText(zlhj.getCpdm());
				title_tv_source.setText(zlhj.getSjly());
				title_tv_description.setText(zlhj.getWtms());
				title_tv_status.setText(zlhj.getZt());
			}
			return convertView;
		}

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
			view = LayoutInflater.from(chanxianzhiliang.this).inflate(
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
			if (!cx_month.equals("")) {

				if (position == (Integer.parseInt(cx_month) - 1) && isStart) {
					view.setBackgroundResource(R.drawable.biankuan_month);
					flag = position;
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
			view = LayoutInflater.from(chanxianzhiliang.this).inflate(
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
									if (jxzb.getHgl() != null
											&& jxzb.getHgl().equals("")) {

									} else if (jxzb.getHgl() != null) {
										String hglStr = jxzb.getHgl();
										double hgl = Double.parseDouble(hglStr);
										if (hgl > 98) {
											view.setBackgroundResource(R.drawable.biankuan_green);
										} else if (hgl >= 90) {

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
	private class Chanxjxzb1Task extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return loadChanxjxzbData1();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String title = mUserName+"生产线月季合格率("+cx_year+"-"+cx_month+")";
			if (result != null) {
				Gson gson = new Gson();

				CxjxJson cxjxJson = gson.fromJson(result, CxjxJson.class);
				Log.e("GetChanxjxzbsl",cxjxJson.getCode()+":result");

				if(cxjxJson.getCode()==1){
					List<CxjxJson.DataBean> cxjxJsons = cxjxJson.getData();



					//合格率
					String data1 = new String();
					//工废率
					String data2 = new String();
					//料废率
					String data3 = new String();
					String scrq = new String();
					for(int i=0;i<cxjxJsons.size();i++){
						if(i==0){
							data1 += "[";
							data1 += cxjxJsons.get(i).getHgl() +",";

							data2 += "[";
							data2 += cxjxJsons.get(i).getGfl() +",";

							data3 += "[";
							data3 += cxjxJsons.get(i).getLfl() +",";

							scrq += "[";
							scrq += cxjxJsons.get(i).getScrq() +",";
						}else if(i==cxjxJsons.size()-1){
							data1 += cxjxJsons.get(i).getHgl() +"";
							data1 += "]";

							data2 += cxjxJsons.get(i).getGfl() +"";
							data2 += "]";

							data3 += cxjxJsons.get(i).getLfl() +"";
							data3 += "]";

							scrq += cxjxJsons.get(i).getScrq() +"";
							scrq += "]";
						}
						else{
							data1 += cxjxJsons.get(i).getHgl() +",";

							data2 += cxjxJsons.get(i).getGfl() +",";

							data3 += cxjxJsons.get(i).getLfl() +",";

							scrq += cxjxJsons.get(i).getScrq() +",";
						}

					}
					zhiliang_wv.loadUrl("javascript:createChart('bar',"+data1+","+data2+","+data3+","+scrq+",'"+title+"');");
				}
				else{
					String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
					String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
					zhiliang_wv.loadUrl("javascript:createChart('bar',"+errorData+","+errorData+","+errorData+","+errorDate+",'"+title+"');");
				}


			}else{
				String errorData = "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
				String errorDate = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]";
				zhiliang_wv.loadUrl("javascript:createChart('bar',"+errorData+","+errorData+","+errorData+","+errorDate+",'"+title+"');");

			}

		}
	}

	/**
	 * 加载培训内容数据
	 *
	 * @return
	 */
	private String loadChanxjxzbData1() {
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
	private class ChanxjxzbTask extends AsyncTask<Void, Void, Chanxjxzb> {
		private GridView zhiliang_gv_month;

		public ChanxjxzbTask(GridView zhiliang_gv_month) {
			// TODO Auto-generated constructor stub
			this.zhiliang_gv_month = zhiliang_gv_month;
		}

		@Override
		protected Chanxjxzb doInBackground(Void... arg0) {
			return loadChanxjxzbData();
		}

		@Override
		protected void onPostExecute(Chanxjxzb result) {
			super.onPostExecute(result);
			zhiliang_gv_month.setEnabled(true);

			if (result != null) {
				chanxjxzb = result;
				//Log.e("chanxianzhiliang",result.getCxhgl());
				zhiliang_gv_main.setAdapter(mGridMain);
				//Toast.makeText(chanxianzhiliang.this,result.getCxhgl()+"",Toast.LENGTH_SHORT).show();
				//Picasso.with(chanxianzhiliang.this).load(result.getCxhgl().into(zhiliang_iv_standard);


			}


		}
	}

	/**
	 * 加载培训内容数据
	 * 
	 * @return
	 */
	private Chanxjxzb loadChanxjxzbData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
				+ mUserName
				+ "</cxdm><nf>"
				+ cx_year
				+ "</nf><yf>"
				+ cx_month
				+ "</yf></param></data></root>";
		String method_name = "GetChanxjxzb";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		test2 = data;
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseChanxjxzb(data);

		} else {
			return null;
		}
	}

	/**
	 * 异步加载产线质量呼叫
	 * 
	 * @author Administrator
	 * 
	 */
	private class ChanxzlssjlTask extends AsyncTask<Void, Void, Chanpzlssjl> {

		@Override
		protected Chanpzlssjl doInBackground(Void... arg0) {
			return loadChanxzlssjl();
		}

		@Override
		protected void onPostExecute(Chanpzlssjl result) {
			super.onPostExecute(result);
			// chanxzlhj = result;
			//Toast.makeText(chanxianzhiliang.this, test, 1).show();

			if (result != null && result.getResult().equals("1")) {
				chanxzlssjl = result;
				//Toast.makeText(chanxianzhiliang.this, "gggg"+chanxzlssjl.getZlssjls().size(), 1).show();

				zhiliang_lv_deal.setAdapter(myListViewDeal);

			}

		}
	}

	/**
	 * 加载产线质量呼叫数据
	 * 
	 * @return
	 */
	private Chanpzlssjl loadChanxzlssjl() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
				+ mUserName
				+ "</cxdm><nf>"
				+ cx_year
				+ "</nf><yf>"
				+ cx_month
				+ "</yf></param></data></root>";
		String method_name = "GetChanxzlssjl";
		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
				param, method_name, mIp);
		
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			//test ="gg" + RequestDataUtil.parseChanxzlssjl(data);
			//Log.e("gg", data);
			return RequestDataUtil.parseChanxzlssjl(data);

		} else {
			return null;
		}
	}

}
