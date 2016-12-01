package com.example.daming_text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.entity.ConfirmChanxdm;
import com.daming.util.Constant;
import com.daming.util.RequestDataUtil;
import com.daming.util.ShareUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    private EditText username, psd;
    private List<ConfirmChanxdm> mConfirmList;
    private String name;
    private String ps;
    private String ip;

    // 登陆
    private Button daming_btn_login;
    // ip端口 60.191.233.246 103
    private EditText daming_ip;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.daming_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //initView();
        //initData();


        //name = username.getText().toString();
        //ps = psd.getText().toString();

        //ip = daming_ip.getText().toString();


        //new HttpUtils().send(HttpRequest.HttpMethod.GET, "http://218.1.125.82:8089/ControlDate/date.do", new RequestCallBack<String>() {
        //	@Override
        //	public void onSuccess(ResponseInfo<String> responseInfo) {
        //		if (responseInfo.result.equals("ok")) {

        initView();
        initData();
        //Toast.makeText(MainActivity.this, responseInfo.result, Toast.LENGTH_SHORT).show();
        TextView main_tv_test = (TextView) findViewById(R.id.main_tv_test);
        main_tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                ps = psd.getText().toString();

                ip = daming_ip.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "登入失败,用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (ip.equals("")) {
                    Toast.makeText(MainActivity.this, "ip:端口不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(MainActivity.this, "name"+name+",ps"+ps+ip, 200).show();
                    ShareUtils.setUserName(MainActivity.this, name);
                    ShareUtils.setUserPwd(MainActivity.this, ps);
                    daming_btn_login.setEnabled(false);
                    userLogin(name, ps, ip);
                }
            }
        });
        daming_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                ps = psd.getText().toString();

                ip = daming_ip.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "登入失败,用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (ip.equals("")) {
                    Toast.makeText(MainActivity.this, "ip:端口不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(MainActivity.this, "name"+name+",ps"+ps+ip, 200).show();
                    ShareUtils.setUserName(MainActivity.this, name);
                    ShareUtils.setUserPwd(MainActivity.this, ps);
                    daming_btn_login.setEnabled(false);
                    userLogin(name, ps, ip);
                }
            }
        });

    }
    //	}

//	@Override
//	public void onFailure(HttpException e, String s) {

//			}
//		});

//	}

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.REQUEST_DATA:

                    if (mConfirmList == null) {
                        Toast.makeText(MainActivity.this, "请联网后重试", Toast.LENGTH_SHORT).show();
                    } else {
                        ConfirmChanxdm comChanxdm;
                        if (mConfirmList.size() > 0) {
                            comChanxdm = mConfirmList.get(0);
                            if (comChanxdm.getResult().equals("1")) {
                                Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_SHORT)
                                        .show();
                                Intent intent = new Intent(MainActivity.this,
                                        yemianer.class);
                                intent.putExtra("username", name);
                                intent.putExtra("userpwd", ps);
                                intent.putExtra("ip", daming_ip.getText().toString());
                                startActivity(intent);
                                mConfirmList = null;
                                daming_btn_login.setEnabled(true);
                            } else if (comChanxdm.getResult().equals("0")) {
                                Toast.makeText(MainActivity.this, "登入失败,用户名或密码错误",
                                        Toast.LENGTH_SHORT).show();
                                daming_btn_login.setEnabled(true);
                            }
                        }
                    }
                    break;
                case Constant.REQUEST_ERROR:
                    daming_btn_login.setEnabled(true);
                    Toast.makeText(MainActivity.this, "登入失败,连接服务器失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    private void initData() {
        // TODO Auto-generated method stub

        ip = ShareUtils.getIp(MainActivity.this);
        String shaUserName = ShareUtils.getUserName(MainActivity.this);
        String shaUserPwd = ShareUtils.getUserPwd(MainActivity.this);
        if (!ip.equals("")) {
            daming_ip.setText(ip);
        }
        if (!shaUserName.equals("")) {
            username.setText(shaUserName);
        }
        if (!shaUserPwd.equals("")) {
            psd.setText(shaUserPwd);
        }
    }

    public void userLogin(final String username, final String userpwd, final String ip) {
        new Thread() {
            public void run() {

                String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <username>"
                        + username
                        + "</username><password>"
                        + userpwd
                        + "</password></param></data></root>";
                String method_name = "ConfirmChanxdm";

                String data = RequestDataUtil.callMethod(username, userpwd,
                        param, method_name, ip);
                if (!data.equals("")) {
                    data = RequestDataUtil.splieRequestXml(data);
                    mConfirmList = RequestDataUtil.parseConfirmXml(data);
                    mHandler.sendEmptyMessage(Constant.REQUEST_DATA);
                } else {
                    mHandler.sendEmptyMessage(Constant.REQUEST_ERROR);
                }
            }

            ;
        }.start();

    }


    public void initView() {
        username = (EditText) findViewById(R.id.username);
        psd = (EditText) findViewById(R.id.pwd);
        daming_ip = (EditText) findViewById(R.id.daming_ip);
        daming_btn_login = (Button) findViewById(R.id.daming_btn_login);

    }
}
