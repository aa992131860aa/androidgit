package com.example.daming_text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class chengyuanxinxi extends Activity {
		private  ImageView fanhui;
		private Button btn_yuang1,btn_one02,btn_one03,btn_one04;
		//人员代码
		private String rydm;
		private String ip;
		private String mUsername;
		private String mUserpwd;
		private String scrq;
		private String pcid;
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.chengyuanxinxi);
			initView();
			initData();
			
			addListener();
			bindData();
		}
		
		private void initData() {
			// TODO Auto-generated method stub
			rydm = getIntent().getStringExtra("rydm");
			ip = getIntent().getStringExtra("ip");
			mUsername = getIntent().getStringExtra("username");
			mUserpwd = getIntent().getStringExtra("userpwd");
			scrq = getIntent().getStringExtra("scrq");
			pcid = getIntent().getStringExtra("pcid");
		}

		private void bindData() {
			// TODO Auto-generated method stub
			
		}

		private void addListener() {
			// TODO Auto-generated method stub
			fanhui.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					finish();
				}
			});
			btn_yuang1.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent intent = new Intent();
					intent.setClass(chengyuanxinxi.this, yuangongdangan.class);
					intent.putExtra("rydm", rydm);
					intent.putExtra("ip", ip);
					intent.putExtra("username", mUsername);
					intent.putExtra("userpwd", mUserpwd);
					intent.putExtra("scrq", scrq);
					startActivity(intent);
					
				}
			});
			btn_one02.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent intent = new Intent();
					intent.setClass(chengyuanxinxi.this, peixunjilu.class);
					intent.putExtra("rydm", rydm);
					intent.putExtra("ip", ip);
					intent.putExtra("username", mUsername);
					intent.putExtra("userpwd", mUserpwd);
					intent.putExtra("scrq", scrq);
					startActivity(intent);
				}
			});
			btn_one03.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent intent = new Intent();
					intent.setClass(chengyuanxinxi.this, AnquanActivity.class);
					intent.putExtra("rydm", rydm);
					intent.putExtra("ip", ip);
					intent.putExtra("username", mUsername);
					intent.putExtra("userpwd", mUserpwd);
					intent.putExtra("scrq", scrq);
					startActivity(intent);
				}
			});
			btn_one04.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(chengyuanxinxi.this, laodongjilv.class);
					intent.putExtra("rydm", rydm);
					intent.putExtra("ip", ip);
					intent.putExtra("username", mUsername);
					intent.putExtra("userpwd", mUserpwd);
					intent.putExtra("scrq", scrq);
					startActivity(intent);
				}
			});
		}

		public void initView(){
			fanhui=(ImageView) findViewById(R.id.img_fanhui);
			btn_yuang1=(Button) findViewById(R.id.btn_yuang1);
			btn_one02=(Button) findViewById(R.id.btn_one5);
			btn_one03=(Button) findViewById(R.id.btn_one6);
			btn_one04=(Button) findViewById(R.id.btn_one7);
		}
}
