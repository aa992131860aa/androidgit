package com.example.daming_text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class chanpinjiaohu extends Activity {
	private ImageView fanhui;
	private String mIp;
	private String mUserName;
	private String mUserPwd;
	private String scrq;
	private Button tuzhi,zuoye,jianyan,zhiliang,lishi,biaozhun;
	private String cpdm1;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shengchandaima);
		initView();
		init();
		addListener();
		bindData();
	}
	private void bindData() {
		// TODO Auto-generated method stub
		
	}

	private void init() {
		// TODO Auto-generated method stub
		mIp = getIntent().getStringExtra("ip");
		mUserName = getIntent().getStringExtra("username");
		mUserPwd = getIntent().getStringExtra("userpwd");
		scrq = getIntent().getStringExtra("scrq");
		cpdm1 = getIntent().getStringExtra("cpdm1");
	}
	private void addListener(){
		fanhui.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();	
			}
		});
		tuzhi.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chanpinjiaohu.this, tuzhiwenjian.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				intent.putExtra("cpdm1", cpdm1);
				startActivity(intent);
			}
		});
		zuoye.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chanpinjiaohu.this, zuoyezhibiao.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				intent.putExtra("cpdm1", cpdm1);
				startActivity(intent);
			}
		});
		jianyan.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chanpinjiaohu.this, JianyanjilvActivity.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				intent.putExtra("cpdm1", cpdm1);
				startActivity(intent);
			}
		});
		zhiliang.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chanpinjiaohu.this, zhiliangfenxi.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				intent.putExtra("cpdm1", cpdm1);
				startActivity(intent);
				//startActivity(new Intent(chanxiandaima.this,zhiliangfenxi.class));
			}
		});
		lishi.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chanpinjiaohu.this, lishichanliang.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				intent.putExtra("cpdm1", cpdm1);
				startActivity(intent);
			}
		});
		biaozhun.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chanpinjiaohu.this, biaozhunzuoye.class);
				intent.putExtra("username", mUserName);
				intent.putExtra("userpwd", mUserPwd);
				intent.putExtra("ip", mIp);
				intent.putExtra("scrq", scrq);
				intent.putExtra("cpdm1", cpdm1);
				startActivity(intent);
			}
		});
	}
	public void initView(){
		fanhui=(ImageView) findViewById(R.id.img_fanhui);
		tuzhi=(Button) findViewById(R.id.tuzhi);
		zuoye=(Button) findViewById(R.id.zuoye);
		jianyan=(Button) findViewById(R.id.jianyan);
		zhiliang=(Button) findViewById(R.id.zhiliang);
		lishi=(Button) findViewById(R.id.lishi);
		biaozhun=(Button) findViewById(R.id.biaozhun);
	}
}
