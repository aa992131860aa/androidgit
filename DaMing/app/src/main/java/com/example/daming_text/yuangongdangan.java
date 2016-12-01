package com.example.daming_text;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.adapter.LaodongjilvAdapter;
import com.daming.adapter.RenyuanxinxiAdapter;
import com.daming.entity.Djll;
import com.daming.entity.Jx;
import com.daming.entity.Renydjll;
import com.daming.entity.Renyjx;
import com.daming.entity.Renyldjl;
import com.daming.entity.Renyxx;
import com.daming.entity.Renyzl;
import com.daming.entity.Ryxx;
import com.daming.entity.Ryzl;
import com.daming.entity.Zlssjl;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class yuangongdangan extends Activity {
	// 返回按钮
	@ViewInject(R.id.img_fanhui3)
	private ImageView img_3;
	// 人员工号
	private String rydm;
	private String ip;
	private String mUsername;
	private String mUserpwd;
	private String scrq;
	private String month;
	private String year;
	private Renyxx renyxx;
	@ViewInject(R.id.ren_iv_photourl)
	private ImageView ren_iv_photourl;
	@ViewInject(R.id.ren_tv_name)
	private TextView ren_tv_name;
	@ViewInject(R.id.ren_tv_birthday)
	private TextView ren_tv_birthday;
	@ViewInject(R.id.ren_tv_number)
	private TextView ren_tv_number;
	@ViewInject(R.id.ren_tv_job)
	private TextView ren_tv_job;

	@ViewInject(R.id.ren_tv_assessdate1)
	private TextView ren_tv_assessdate1;
	@ViewInject(R.id.ren_tv_levelskill1)
	private TextView ren_tv_levelskill1;


	@ViewInject(R.id.ren_tv_assessdate2)
	private TextView ren_tv_assessdate2;
	@ViewInject(R.id.ren_tv_levelskill2)
	private TextView ren_tv_levelskill2;



	@ViewInject(R.id.ren_tv_assessdate3)
	private TextView ren_tv_assessdate3;
	@ViewInject(R.id.ren_tv_levelskill3)
	private TextView ren_tv_levelskill3;




	@ViewInject(R.id.ren_tv_assessdate4)
	private TextView ren_tv_assessdate4;
	@ViewInject(R.id.ren_tv_levelskill4)
	private TextView ren_tv_levelskill4;




	@ViewInject(R.id.ren_tv_assessdate5)
	private TextView ren_tv_assessdate5;
	@ViewInject(R.id.ren_tv_levelskill5)
	private TextView ren_tv_levelskill5;



	@ViewInject(R.id.ren_tv_assessdate6)
	private TextView ren_tv_assessdate6;
	@ViewInject(R.id.ren_tv_levelskill6)
	private TextView ren_tv_levelskill6;



	@ViewInject(R.id.ren_tv_assessdate7)
	private TextView ren_tv_assessdate7;
	@ViewInject(R.id.ren_tv_levelskill7)
	private TextView ren_tv_levelskill7;


	@ViewInject(R.id.ren_tv_assessdate8)
	private TextView ren_tv_assessdate8;
	@ViewInject(R.id.ren_tv_levelskill8)
	private TextView ren_tv_levelskill8;



	@ViewInject(R.id.ren_tv_assessdate9)
	private TextView ren_tv_assessdate9;
	@ViewInject(R.id.ren_tv_levelskill9)
	private TextView ren_tv_levelskill9;



	@ViewInject(R.id.ren_tv_assessdate10)
	private TextView ren_tv_assessdate10;
	@ViewInject(R.id.ren_tv_levelskill10)
	private TextView ren_tv_levelskill10;



	@ViewInject(R.id.ren_tv_assessdate11)
	private TextView ren_tv_assessdate11;
	@ViewInject(R.id.ren_tv_levelskill11)
	private TextView ren_tv_levelskill11;



	@ViewInject(R.id.ren_tv_assessdate12)
	private TextView ren_tv_assessdate12;
	@ViewInject(R.id.ren_tv_levelskill12)
	private TextView ren_tv_levelskill12;


	private List<TextView> ren_tv_assessdates = new ArrayList<>();
	private List<TextView> ren_tv_levelskills = new ArrayList<>();
	private List<TextView> ren_tv_dates = new ArrayList<>();
	private List<TextView> ren_tv_pronumbers = new ArrayList<>();
	private List<TextView> ren_tv_requests = new ArrayList<>();
	private List<TextView> ren_tv_disposes = new ArrayList<>();
	private List<TextView> ren_tv_comes = new ArrayList<>();
    private RenyuanxinxiAdapter adapter;
	private List<Jx> jxes;
    private String test;
	private MyListViewDeal myListViewDeal;
	@ViewInject(R.id.renyuanxinxi_lv_deal)
	private ListView renyuanxinxi_lv_deal;
    private Renyzl renyzl;
	@ViewInject(R.id.renyuanxinxi_lv)
	private ListView renyuanxinxi_lv;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.renyuanxinxi);
		myListViewDeal = new MyListViewDeal();
		initView();
		initData();
		new RenyxxTask().execute();
		new RenydjllTask().execute();
		new RenyzlTask().execute();
		new RenypxTask().execute();
		addListener();
		bindData();

	}
	private class MyListViewDeal extends BaseAdapter {

		@Override
		public int getCount() {
			if (renyzl != null && renyzl.getRyzls() != null) {
				return renyzl.getRyzls().size();
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
			convertView = LayoutInflater.from(yuangongdangan.this).inflate(R.layout.renyuan_item, null);

			TextView title_tv_date = (TextView) convertView.findViewById(R.id.renyuan_date);
			TextView title_tv_no= (TextView) convertView.findViewById(R.id.renyuan_pro);
			TextView title_tv_source= (TextView) convertView.findViewById(R.id.renyuan_come);
			TextView title_tv_description= (TextView) convertView.findViewById(R.id.renyuan_request);
			TextView title_tv_status= (TextView) convertView.findViewById(R.id.renyuan_deal);
			//convertView.setLayoutParams(param);
			// Toast.makeText(chanxianzhiliang.this, "gg"+chanxzlhj, 0).show();
			if (renyzl != null && renyzl.getRyzls() != null)
			{

				Ryzl zlssjl = renyzl.getRyzls().get(position);
				title_tv_date.setText(zlssjl.getRq());
				title_tv_no.setText(zlssjl.getCpdm());
				title_tv_source.setText(zlssjl.getSlly());
				title_tv_description.setText(zlssjl.getWtms());
				title_tv_status.setText(zlssjl.getJjfz());
			}
			return convertView;
		}

	}
	/**
	 * 
	 * @author Administrator
	 * 
	 */
	private class RenydjllTask extends AsyncTask<Void, Void, Renydjll> {

		@Override
		protected Renydjll doInBackground(Void... arg0) {
			return loadRenydjllData();
		}

		@Override
		protected void onPostExecute(Renydjll result) {
			super.onPostExecute(result);
			if (result != null && result.getResult().equals("1")) {
				showRenydjll(result);
			}

		}
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	private class RenyzlTask extends AsyncTask<Void, Void, Renyzl> {

		@Override
		protected Renyzl doInBackground(Void... arg0) {
			return loadRenyzlData();
		}

		@Override
		protected void onPostExecute(Renyzl result) {
			super.onPostExecute(result);
			//Toast.makeText(yuangongdangan.this, test, Toast.LENGTH_SHORT).show();
			if (result != null && result.getResult().equals("1")) {
				renyzl = result;
				renyuanxinxi_lv_deal.setAdapter(myListViewDeal);
				//showRenyzl(result);
			}

		}

		private void showRenyzl(Renyzl result) {
			List<Ryzl> ryzls = result.getRyzls();
			Collections.reverse(ryzls);
			for(int i=0;i<ryzls.size();i++){
				if(i<12){
				ren_tv_dates.get(i).setText(ryzls.get(i).getRq());
				ren_tv_pronumbers.get(i).setText(ryzls.get(i).getCpdm());
				ren_tv_requests.get(i).setText(ryzls.get(i).getWtms());
				ren_tv_disposes.get(i).setText(ryzls.get(i).getJjfz());
				ren_tv_comes.get(i).setText(ryzls.get(i).getSlly());
				}
			}

		}
	}

	/**
	 * 异步加载人员信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class RenyxxTask extends AsyncTask<Void, Void, Renyxx> {

		@Override
		protected Renyxx doInBackground(Void... arg0) {
			return loadRenyxxData();
		}

		@Override
		protected void onPostExecute(Renyxx result) {
			super.onPostExecute(result);
			if (result != null) {
				renyxx = result;
				showRenyxx(result);
			}

		}
	}

	/**
	 * 显示人员信息
	 * 
	 * @param renyxx
	 */
	private void showRenyxx(Renyxx renyxx) {
		Ryxx ryxx = renyxx.getRyxx();
		Picasso.with(this).load(ryxx.getSrc()).into(ren_iv_photourl);
		ren_tv_name.setText(ryxx.getXm());
		ren_tv_number.setText(ryxx.getRydm());
		ren_tv_job.setText(ryxx.getRylb());

	}
	/**
	 * 异步加载培訓信息
	 * @author Administrator
	 *
	 */
	private class RenypxTask extends AsyncTask<Void, Void, Renyjx> {

		@Override
		protected Renyjx doInBackground(Void... arg0) {
			return loadRenypxData();
		}
		@Override
		protected void onPostExecute(Renyjx result) {
			super.onPostExecute(result);

			if(result!=null&&result.getResult().equals("1")&&result.getJxes()!=null){
				Collections.reverse( result.getJxes());
				adapter.refresh(result.getJxes());

			}

		}
	}


	/**
	 * 加载培训内容数据
	 * @return
	 */
	private Renyjx loadRenypxData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <rydm>"
				+ rydm + "</rydm><d>201</d></param></data></root>";
		String method_name = "GetRenyjx";
		String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
				method_name, ip);
		test = data;
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseRenyjx(data);
			//Log.e("renypx", data);

		} else {
			return null;
		}
	}
	/**
	 * 显示人员等级履历
	 * 
	 * @paramrenyxx
	 */
	private void showRenydjll(Renydjll renydjll) {
		List<Djll> djlls = renydjll.getDjlls();
		for(int i=0;i<djlls.size();i++){
			if(i<12){
			ren_tv_assessdates.get(i).setText(djlls.get(i).getPdrq());
			ren_tv_levelskills.get(i).setText(djlls.get(i).getJndj());}
		}

		//Toast.makeText(this, "技能:" + year+month+rydm, 0).show();
	}

	/**
	 * 加载人员信息数据
	 * 
	 * @return
	 */
	private Renyxx loadRenyxxData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <rydm>"
				+ rydm + "</rydm></param></data></root>";
		String method_name = "GetRenyxx";
		String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd,
				param, method_name, ip);
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseRenyxx(data);

		} else {
			return null;
		}
	}

	/**
	 * 加载人员等级履历
	 * 
	 * @return
	 */
	private Renydjll loadRenydjllData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <rydm>"
				+ rydm + "</rydm></param></data></root>";
		String method_name = "GetRenydjll";
		String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd,
				param, method_name, ip);
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseRenydjll(data);

		} else {
			return null;
		}
	}
	/**
	 * 加载人员质量数据
	 * 
	 * @return
	 */
	private Renyzl loadRenyzlData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <rydm>"
				+ rydm + "</rydm><d>365</d></param></data></root>";
		String method_name = "GetRenyzl";
		String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd,
				param, method_name, ip);
		if (!data.equals("")) {
//			test = data;
//			return null;
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseRenyzl(data);

		} else {
			return null;
		}
	}

	
	private void bindData() {
		  renyuanxinxi_lv.setAdapter(adapter);
	}

	private void addListener() {

		img_3.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void initData() {
		ViewUtils.inject(this);
		jxes = new ArrayList<>();
		adapter = new RenyuanxinxiAdapter(this,jxes);
		ren_tv_assessdates.add(ren_tv_assessdate1);
		ren_tv_assessdates.add(ren_tv_assessdate2);
		ren_tv_assessdates.add(ren_tv_assessdate3);
		ren_tv_assessdates.add(ren_tv_assessdate4);
		ren_tv_assessdates.add(ren_tv_assessdate5);
		ren_tv_assessdates.add(ren_tv_assessdate6);
		ren_tv_assessdates.add(ren_tv_assessdate7);
		ren_tv_assessdates.add(ren_tv_assessdate8);
		ren_tv_assessdates.add(ren_tv_assessdate9);
		ren_tv_assessdates.add(ren_tv_assessdate10);
		ren_tv_assessdates.add(ren_tv_assessdate11);
		ren_tv_assessdates.add(ren_tv_assessdate12);

		ren_tv_levelskills.add(ren_tv_levelskill1);
		ren_tv_levelskills.add(ren_tv_levelskill2);
		ren_tv_levelskills.add(ren_tv_levelskill3);
		ren_tv_levelskills.add(ren_tv_levelskill4);
		ren_tv_levelskills.add(ren_tv_levelskill5);
		ren_tv_levelskills.add(ren_tv_levelskill6);
		ren_tv_levelskills.add(ren_tv_levelskill7);
		ren_tv_levelskills.add(ren_tv_levelskill8);
		ren_tv_levelskills.add(ren_tv_levelskill9);
		ren_tv_levelskills.add(ren_tv_levelskill10);
		ren_tv_levelskills.add(ren_tv_levelskill11);
		ren_tv_levelskills.add(ren_tv_levelskill12);



		rydm = getIntent().getStringExtra("rydm");
		ip = getIntent().getStringExtra("ip");
		mUsername = getIntent().getStringExtra("username");
		mUserpwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
		String scrqs[] = scrq.split("-");
		if (scrqs != null && scrqs.length > 1) {
			year = scrqs[0];
			month = scrqs[1];
		}

	}

	private void initView() {
		img_3 = (ImageView) findViewById(R.id.img_fanhui3);
	}
}
