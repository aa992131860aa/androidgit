package com.example.daming_text;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daming.adapter.PeixunjiluAdapter;
import com.daming.entity.Px;
import com.daming.entity.Renypx;
import com.daming.util.RequestDataUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class peixunjilu extends Activity {
	//返回
	@ViewInject(R.id.img_fanhui4)
	private ImageView img_fanhuione;
	@ViewInject(R.id.peixun_lv_content)
	private ListView peixun_lv_content;
	// 人员工号
	private String rydm;
	private String ip;
	private String mUsername;
	private String mUserpwd;
	private List<Px> mPxs;
	private PeixunjiluAdapter mAdapter;
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.peixunjilu);
			init();
			new RenypxTask().execute();
			addListener();
			bindData();
			
		}
		private void bindData() {
			// TODO Auto-generated method stub
			peixun_lv_content.setAdapter(mAdapter);
		}
		private void addListener() {
			// TODO Auto-generated method stub
			img_fanhuione.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					finish();
				}
			});
		}
		private void init() {
           ViewUtils.inject(this);			
           mPxs = new ArrayList<Px>();
           mAdapter = new PeixunjiluAdapter(this,mPxs);
        //Toast.makeText(this, rydm, 0).show();

        rydm = getIntent().getStringExtra("rydm");
		ip = getIntent().getStringExtra("ip");
		mUsername = getIntent().getStringExtra("username");
		mUserpwd = getIntent().getStringExtra("userpwd");
		}
		 /**
		  * 异步加载培訓信息
		  * @author Administrator
		  *
		  */
	    private class RenypxTask extends AsyncTask<Void, Void, Renypx>{

			@Override
			protected Renypx doInBackground(Void... arg0) {
				return loadRenypxData();
			}
	    	@Override
	    	protected void onPostExecute(Renypx result) {
	    		super.onPostExecute(result);
	    		if(result!=null&&result.getResult().equals("1")){
	    			mAdapter.refresh(result.getPxs());
	    		}

	    	}
	    }
	 
	
	    /**
	     * 加载培训内容数据
	     * @return
	     */
		private Renypx loadRenypxData() {
			String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <rydm>"
					+ rydm + "</rydm></param></data></root>";
			String method_name = "GetRenypx";
			String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
					method_name, ip);
			if (!data.equals("")) {
				data = RequestDataUtil.splieRequestXml(data);
				return RequestDataUtil.parseRenypx(data);
				//Log.e("renypx", data);

			} else {
				return null;
			}
		}
}
