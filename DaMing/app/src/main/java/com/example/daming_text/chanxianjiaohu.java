package com.example.daming_text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class chanxianjiaohu extends Activity {
	private ImageView img_fanhui;
	private Button btn_on1,btn_on2,btn_on3,btn_on4;
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	private String scrq;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chanxian);

		initView();
		init();
		addListener();
//		String saomiao = getIntent().getStringExtra("saomiao");
//		if(saomiao!=null&&saomiao.equals("saomiao")){
//			finish();
//			Toast.makeText(this,saomiao,Toast.LENGTH_SHORT).show();
//		}
	}
	private void init() {
		// TODO Auto-generated method stub
		mIp = getIntent().getStringExtra("ip");
		mUserName = getIntent().getStringExtra("username");
		mUserPwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
	}
	private void addListener() {
		img_fanhui.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
		btn_on1.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chanxianjiaohu.this, chanxianxinxi.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				startActivity(intent);
			}
		});
		btn_on2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(chanxianjiaohu.this, shengchanPC.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				startActivity(intent);
			}
		});
		btn_on3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(chanxianjiaohu.this, chanxianzhiliang.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				startActivity(intent);
			}
		});
		btn_on4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(chanxianjiaohu.this, chanxianjiaohuo.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				startActivity(intent);}
		});
	}
	public void initView(){
		img_fanhui=(ImageView) findViewById(R.id.img_fanhui);
		btn_on1=(Button) findViewById(R.id.btn_on1);
		btn_on2=(Button) findViewById(R.id.btn_sta);
		btn_on3=(Button) findViewById(R.id.btn_on3);
		btn_on4=(Button) findViewById(R.id.btn_on4);
	}
}
