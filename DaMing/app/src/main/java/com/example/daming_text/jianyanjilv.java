//package com.example.daming_text;
//
//import java.io.File;
//
//import com.daming.entity.Chanpjswj;
//import com.daming.entity.Chanpjyjl;
//import com.daming.entity.Chanplscl;
//import com.daming.util.RequestDataUtil;
//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.squareup.picasso.Picasso;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//public class jianyanjilv extends Activity {
//	//private ImageView img44;
//	private String mIp;
//	private String mUserName;
//	private String mUserPwd;
//	private String scrq;
//	private String year;
//	private String month;
//	private String day;
//	private String cpdm;
//    private ImageView jianyan_iv;
//    private String test;
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.tuzhiwenjian);
//		init();
//		new ChanpjyjlTask().execute();
//		//img44 = (ImageView) findViewById(R.id.btn_img44);
////		jianyan_iv = (ImageView) findViewById(R.id.jianyan_iv);
////		img44.setOnClickListener(new OnClickListener() {
////			public void onClick(View arg0) {
////				finish();
////			}
////		});
//
//	}
//
//	private void init() {
//		// TODO Auto-generated method stub
//		mIp = getIntent().getStringExtra("ip");
//		mUserName = getIntent().getStringExtra("username");
//		mUserPwd = getIntent().getStringExtra("userpwd");
//		scrq = getIntent().getStringExtra("scrq");
//		cpdm = getIntent().getStringExtra("cpdm1");
//		String scrqs[] = scrq.split("-");
//		if (scrqs != null && scrqs.length > 2) {
//			year = scrqs[0];
//			month = scrqs[1];
//			day = scrqs[2];
//		}
//	}
//	/**
//	 * 异步加载历史信息
//	 *
//	 * @author Administrator
//	 *
//	 */
//	private class ChanpjyjlTask extends AsyncTask<Void, Void, Chanpjyjl> {
//
//		@Override
//		protected Chanpjyjl doInBackground(Void... arg0) {
//			return loadChanpjyjl();
//		}
//
//
//		@Override
//		protected void onPostExecute(Chanpjyjl result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			if(result!=null&&result.getSrc_jyjl()!=null&&!result.getSrc_jyjl().equals("")){
//			String src =	result.getSrc_jyjl();
//			int lastOf = src.lastIndexOf("/");
//			String srcPdf = src.substring(lastOf, src.length());
//            final String targe = TongZiActivity.getDiskCacheDir(jianyanjilv.this)+srcPdf;
//				new HttpUtils().download(result.getSrc_jyjl(), targe, new RequestCallBack<File>() {
//
//					@Override
//					public void onSuccess(ResponseInfo<File> arg0) {
//						// TODO Auto-generated method stub
//						//showDocument(Uri.fromFile(new File(targe)));
//						   Uri path = Uri.fromFile(new File(targe));
//				             Intent intent = new Intent(Intent.ACTION_VIEW);
//				             intent.setDataAndType(path, "application/pdf");
//				             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				                 //startActivity(intent);
//				                 startActivityForResult(intent, 100);
//					}
//
//					@Override
//					public void  onFailure(HttpException arg0, String arg1) {
//
//					}
//				});
//			}else{
//				Toast.makeText(jianyanjilv.this, "没有文件", 0).show();
//				finish();
//			}
//		}
//	}
//  @Override
//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	// TODO Auto-generated method stub
//	super.onActivityResult(requestCode, resultCode, data);
//	finish();
//}
//
//	/**
//	 * 加载培训内容数据
//	 *
//	 * @return
//	 */
//	private Chanpjyjl loadChanpjyjl() {
//		String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cpdm>"
//				+ cpdm
//				+ "</cpdm><rq>"
//				+ year+month+day //"2015-12-01"//
//				+ "</rq></param></data></root>";
//		String method_name = "GetChanpjyjl";
//		String data = RequestDataUtil.callCommonMethod(mUserName, mUserPwd,
//				param, method_name, mIp);
//		test = data;
//		if (!data.equals("")) {
//			// Log.e("chanx", data);
//			data = RequestDataUtil.splieRequestXml(data);
//			return RequestDataUtil.parseChanpjyjl(data);
//
//		} else {
//			return null;
//		}
//	}
//}
