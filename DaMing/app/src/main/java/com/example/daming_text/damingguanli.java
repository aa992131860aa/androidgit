package com.example.daming_text;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class damingguanli extends Activity {
	private ImageView fanhui;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.damingguanli);
		hu();
		
		fanhui.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	public void hu() {
		fanhui = (ImageView) findViewById(R.id.img_fanhui);
	}
}
