package com.example.daming_text;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.daming.util.RequestDataUtil;

public class Anquanlvli extends Activity {
	// 人员工号
	private String rydm;
	private String ip;
	private String mUsername;
	private String mUserpwd;
	private String pcid;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.anquanlvli);
		rydm = getIntent().getStringExtra("rydm");
		ip = getIntent().getStringExtra("ip");
		mUsername = getIntent().getStringExtra("username");
		mUserpwd = getIntent().getStringExtra("userpwd");
		pcid = getIntent().getStringExtra("pcid");
		loadTongZiData();
	}
	/** 
	 * 加载通知信息
	 * @param tzid  通知id
	 * @return
	 */
	public void loadTongZiData() {
		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param><pcid>"+pcid+"</pcid></param></data></root>";
		String method_name = "GetPaichj";
		
		String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
				method_name, ip);
		Toast.makeText(Anquanlvli.this, "data:"+data, 1).show();
		if (!data.equals("")) {
			
			//data = RequestDataUtil.splieRequestXml(data);
			//tongZi = RequestDataUtil.parseTongZi(data);
			
		}
		
	}
}
