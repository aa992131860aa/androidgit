package com.example.daming_text;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daming.adapter.LaodongjilvAdapter;
import com.daming.adapter.PeixunjiluAdapter;
import com.daming.entity.Ldjl;
import com.daming.entity.Px;
import com.daming.entity.Renyldjl;
import com.daming.entity.Renypx;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class laodongjilv extends Activity {
	private ImageView img001;
	@ViewInject(R.id.laodong_lv_content)
	private ListView laodong_lv_content;
	// 人员工号
	private String rydm;
	private String ip;
	private String mUsername;
	private String mUserpwd;
	private List<Ldjl> mPxs;
	private LaodongjilvAdapter mAdapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.laodongjilv);
		img001=(ImageView) findViewById(R.id.img_fanhui001);

		init();
		new RenypxTask().execute();
		addListener();
		bindData();
	}
	private void bindData() {
		// TODO Auto-generated method stub
		laodong_lv_content.setAdapter(mAdapter);
	}
	private void addListener() {
		img001.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	private void init() {
		ViewUtils.inject(this);
		mPxs = new ArrayList<>();
		mAdapter = new LaodongjilvAdapter(this,mPxs);
		//Toast.makeText(this, rydm, 0).show();

		rydm = getIntent().getStringExtra("rydm");
		ip = getIntent().getStringExtra("ip");
		mUsername = getIntent().getStringExtra("username");
		mUserpwd = getIntent().getStringExtra("userpwd");
		//Toast.makeText(this,rydm,Toast.LENGTH_SHORT).show();
	}
	/**
	 * 异步加载培訓信息
	 * @author Administrator
	 *
	 */
	private class RenypxTask extends AsyncTask<Void, Void, Renyldjl> {

		@Override
		protected Renyldjl doInBackground(Void... arg0) {
			return loadRenypxData();
		}
		@Override
		protected void onPostExecute(Renyldjl result) {
			super.onPostExecute(result);
			if(result!=null&&result.getResult().equals("1")&&result.getLdjls()!=null){
				mAdapter.refresh(result.getLdjls());
				//Toast.makeText(laodongjilv.this,"r:"+result.getLdjls(),Toast.LENGTH_SHORT).show();
			}

		}
	}


	/**
	 * 加载培训内容数据
	 * @return
	 */
	private Renyldjl loadRenypxData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <rydm>"
				+ rydm + "</rydm></param></data></root>";
		String method_name = "GetRenyldjl";
		String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
				method_name, ip);
		if (!data.equals("")) {
			data = RequestDataUtil.splieRequestXml(data);
			return RequestDataUtil.parseRenyldjl(data);
			//Log.e("renypx", data);

		} else {
			return null;
		}
	}
}
