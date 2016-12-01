package com.example.daming_text;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.entity.Chanxxx;
import com.daming.entity.ConfirmChanxdm;
import com.daming.entity.GetPaic;
import com.daming.entity.GetPaicList;
import com.daming.entity.GetPaichj;
import com.daming.entity.PaicList;
import com.daming.entity.Pccl;
import com.daming.entity.SaveWcsl;
import com.daming.entity.Shouqjs;
import com.daming.entity.TongZiRecord;
import com.daming.entity.TongZis;
import com.daming.util.BadgeView;
import com.daming.util.Constant;
import com.daming.util.RequestDataUtil;
import com.daming.util.ShareUtils;
import com.squareup.picasso.Picasso;

public class yemianer2 extends Activity implements OnClickListener {
    private Button chanxian, main_btn_banci;
    private ImageView btn_img1, btn_img2, btn_img3, btn_img4, btn_img5,
            btn_img_daming;
    private TextView btn_chanxian1, btn_chanxian2, main_tv_fzr, main_tv_jyy,
            main_tv_czg1, main_tv_czg2, main_tv_czg3;
    private RelativeLayout main_rel;
    private LinearLayout main_lly;
    private String mUsername;
    private String mUserpwd;
    private Button main_btn_next;
    private String test = "gg";
    private TextView main_tv_flag1, main_tv_flag2;
    private String cpdm1;
    private String cpdm2;
    // 产品生产时段1
    private TextView main_tv_period1, main_tv_period2, main_tv_period3,
            main_tv_period4, main_tv_period5, main_tv_period6;
    // 产品生产时段2
    private TextView main_tv2_period1, main_tv2_period2, main_tv2_period3,
            main_tv2_period4, main_tv2_period5, main_tv2_period6;
    // 产品计划生产1
    private TextView main_tv_planpro1, main_tv_planpro2, main_tv_planpro3,
            main_tv_planpro4, main_tv_planpro5, main_tv_planpro6;
    // 产品计划生产2
    private TextView main_tv2_planpro1, main_tv2_planpro2, main_tv2_planpro3,
            main_tv2_planpro4, main_tv2_planpro5, main_tv2_planpro6;
    // 产品实际生产1
    private TextView main_tv_realpro1, main_tv_realpro2, main_tv_realpro3,
            main_tv_realpro4, main_tv_realpro5, main_tv_realpro6;
    // 产品实际生产2
    private TextView main_tv2_realpro1, main_tv2_realpro2, main_tv2_realpro3,
            main_tv2_realpro4, main_tv2_realpro5, main_tv2_realpro6;
    // 产品完成率1
    private TextView main_tv_sumratio1, main_tv_sumratio2, main_tv_sumratio3,
            main_tv_sumratio4, main_tv_sumratio5, main_tv_sumratio6;
    // 产品完成率2
    private TextView main_tv2_sumratio1, main_tv2_sumratio2,
            main_tv2_sumratio3, main_tv2_sumratio4, main_tv2_sumratio5,
            main_tv2_sumratio6;
    private LinearLayout main_lly_quality, main_lly_debug, main_lly_equipment,
            main_lly_engineering, main_lly_materiel, main_lly_safety,
            main_lly_areamanager, main_lly_generalmanager;
    // 保存人员头像的信息
    private List<ImageView> imageList;
    // 保存人员姓名的信息
    private List<TextView> textList;
    // 保存生产时段的信息
    private List<TextView> textPeroidList1;
    // 保存生产时段的信息
    private List<TextView> textPeroidList2;
    // 保存计划生产的信息
    private List<TextView> textPlanproList1;
    // 保存计划生产的信息
    private List<TextView> textPlanproList2;
    // 保存实际生产的信息
    private List<TextView> textRealproList1;
    // 保存实际生产的信息
    private List<TextView> textRealproList2;
    // 保存总完成率的信息
    private List<TextView> textSumratioList1;
    // 保存总完成率的信息
    private List<TextView> textSumratioList2;
    // 用于标示alert
    int mSingleChoiceID = -1;
    // 产量list
    private List<PaicList> mGetPaicLists;
    // 具体产线信息
    private List<GetPaic> mGetPaics;
    // 根据产线代码获取产线信息
    private List<Chanxxx> mChanList;
    private List<String> mPaicsPcclId1;
    private List<String> mPaicsPcclId2;
    private List<ConfirmChanxdm> savePaicList;
    private List<ConfirmChanxdm> startHj;
    private List<ConfirmChanxdm> endHj;

    private String ip;

    private Button main_btn_pcmc;
    private boolean isStart1 = true;
    private boolean isStart2 = true;
    private boolean isStart3 = true;
    private boolean isStart4 = true;
    private boolean isStart5 = true;
    private boolean isStart6 = true;
    private boolean isStart7 = true;
    private boolean isStart8 = true;
    int sumNumber1;
    int sumNumber2;
    private static boolean isStartTimer = true;
    private String scrq;
    private String hjjlh1,hjjlh2,hjjlh3,hjjlh4,hjjlh5,hjjlh6,hjjlh7,hjjlh8;
    // 产量输入框
    private EditText main_edt_pro;
    // 呼叫回复的dialog
    // AlertDialog.Builder dialog;
    AlertDialog alertDialog;
    private int timeNo =-1;
    private int proNo = -1;
    private int tTimeNo =-1;
    private int tProNo = -1;
    private int pcclid = -1;
    private int proRealNumber = -1;
    List<Pccl> pcclPlanList1;
    List<Pccl> pcclPlanList2;
    int index = -1;
    private GetPaichj mGetPaichj;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
        //   getWindow().setSoftInputMode(   WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mUsername = getIntent().getStringExtra("username1");
        mUserpwd = getIntent().getStringExtra("userpwd1");

            ip = getIntent().getStringExtra("ip1");

        mSingleChoiceID = getIntent().getIntExtra("mSingleChoiceID", 0);
        mGetPaicLists = (List<PaicList>) getIntent().getSerializableExtra("mGetPaicLists");
        //mGetPaics = (List<GetPaic>) getIntent().getSerializableExtra("mGetPaics");

       // Toast.makeText(yemianer2.this,mGetPaicLists.get(mSingleChoiceID).getScrq()+mGetPaicLists.get(mSingleChoiceID).getBcdm()+"",Toast.LENGTH_SHORT).show();
         initData();

        addListener();

    }


    private void realPro(View v,int pTimeNo,int pProNo){
        if(proNo==-1&&timeNo==-1){
            //Toast.makeText(yemianer2.this, "请选择生产时段", Toast.LENGTH_SHORT).show();
            if(pProNo==0){
                String time =  textPeroidList1.get(pTimeNo).getText().toString();
                String times [] = time.split("-");
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "HH:mm");
                Date nows = new Date();
                String nowsStr = dateFormat.format(nows);
                String startTime = times[0];
                String endTime = times[1];
                try {
                    long resStart = dateFormat.parse(nowsStr)
                            .getTime()
                            - dateFormat.parse(startTime).getTime();
                    long resEnd = dateFormat.parse(endTime)
                            .getTime()
                            - dateFormat.parse(nowsStr).getTime();
                    if(resEnd<0||(dateFormat.parse(nowsStr).getTime()<0&&dateFormat.parse(endTime).getTime()>0)){
                        //Toast.makeText(yemianer2.this, "生产时段", Toast.LENGTH_SHORT).show();
//					if(timeNo>=0){
//                         textPeroidList1.get(temp).setBackgroundColor(Color.parseColor("#ffffff"));
                        unlockPro(v, pProNo, pTimeNo);

//					}
                        //  timeNo = temp;
                        //  proNo = 0;
                        // textPeroidList1.get(temp).setBackgroundColor(Color.parseColor("#449D44"));
                    }else{
                        Toast.makeText(yemianer2.this, "还没到达排产时间", Toast.LENGTH_SHORT).show();
                    }


                } catch (ParseException e) {

                    e.printStackTrace();
                }
            }

            else if(pProNo==1){
                String time =  textPeroidList2.get(pTimeNo).getText().toString();
                String times [] = time.split("-");
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "HH:mm");
                Date nows = new Date();
                String nowsStr = dateFormat.format(nows);
                String startTime = times[0];
                String endTime = times[1];
                try {
                    long resStart = dateFormat.parse(nowsStr)
                            .getTime()
                            - dateFormat.parse(startTime).getTime();
                    long resEnd = dateFormat.parse(endTime)
                            .getTime()
                            - dateFormat.parse(nowsStr).getTime();
                    if(resEnd<0||(dateFormat.parse(nowsStr).getTime()<0&&dateFormat.parse(endTime).getTime()>0)){
                        //Toast.makeText(yemianer2.this, "生产时段", Toast.LENGTH_SHORT).show();
//					if(timeNo>=0){
//                         textPeroidList1.get(temp).setBackgroundColor(Color.parseColor("#ffffff"));
                        unlockPro(v, pProNo, pTimeNo);

//					}
                        //  timeNo = temp;
                        //  proNo = 0;
                        // textPeroidList1.get(temp).setBackgroundColor(Color.parseColor("#449D44"));
                    }else{
                        Toast.makeText(yemianer2.this, "还没到达排产时间", Toast.LENGTH_SHORT).show();
                    }


                } catch (ParseException e) {

                    e.printStackTrace();
                }
            }
        }
        //第一个产品信息
        if(proNo==0&&pProNo==0){
            if(pTimeNo==timeNo){
                realProDialog(v, pTimeNo, pProNo);
            }else {

                if(pTimeNo<timeNo){
                    //解锁
                    unlockPro(v, pProNo, pTimeNo);
                }else{
                    Toast.makeText(yemianer2.this, "还没到达排产时间", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(proNo==1&&pProNo==1){
            if(pTimeNo==timeNo){
                realProDialog(v, pTimeNo, pProNo);
            }else {

                if(pTimeNo<timeNo){
                    //解锁
                    unlockPro(v, pProNo, pTimeNo);
                }else{
                    Toast.makeText(yemianer2.this, "还没到达排产时间", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_tv_realpro1:

                realPro(v, 0, 0);
                break;
            case R.id.main_tv_realpro2:
                realPro(v, 1, 0);
                break;
            case R.id.main_tv_realpro3:
                realPro(v, 2, 0);
                break;
            case R.id.main_tv_realpro4:
                realPro(v, 3, 0);
                break;
            case R.id.main_tv_realpro5:
                realPro(v, 4, 0);
                break;

            case R.id.main_tv2_realpro1:
                realPro(v, 0, 1);
                break;
            case R.id.main_tv2_realpro2:
                realPro(v, 1, 1);
                break;
            case R.id.main_tv2_realpro3:
                realPro(v, 2, 1);
                break;
            case R.id.main_tv2_realpro4:
                realPro(v, 3, 1);
                break;
            case R.id.main_tv2_realpro5:
                realPro(v, 4, 1);
                break;

            case R.id.main_lly_quality:
                String qualityStart = "质量呼叫";
                String qualityEnd = "质量返回";
                hujiao(qualityStart, qualityEnd, v, 1,hjjlh1);
                break;
            case R.id.main_lly_debug:
                String debugStart = "调试呼叫";
                String debugEnd = "调试返回";
                hujiao(debugStart, debugEnd, v, 2,hjjlh2);
                break;
            case R.id.main_lly_equipment:
                String equipmentStart = "设备呼叫";
                String equipmentEnd = "设备返回";
                hujiao(equipmentStart, equipmentEnd, v, 3,hjjlh2);
                break;
            case R.id.main_lly_engineering:
                String engineeringStart = "工程呼叫";
                String engineeringEnd = "工程返回";
                hujiao(engineeringStart, engineeringEnd, v, 4,hjjlh4);
                break;
            case R.id.main_lly_materiel:
                String materielStart = "物料呼叫";
                String materielEnd = "物料返回";
                hujiao(materielStart, materielEnd, v, 5,hjjlh5);
                break;
            case R.id.main_lly_safety:
                String safetyStart = "安全呼叫";
                String safetyEnd = "安全返回";
                hujiao(safetyStart, safetyEnd, v, 6,hjjlh6);
                break;
            case R.id.main_lly_areamanager:
                String areaStart = "主管呼叫";
                String areaEnd = "主管返回";
                hujiao(areaStart, areaEnd, v, 7,hjjlh7);
                break;
            case R.id.main_lly_generalmanager:
                String generalStart = "经理呼叫";
                String genaralEnd = "经理返回";
                hujiao(generalStart, genaralEnd, v, 8,hjjlh8);
                break;
            case R.id.btn_img1:
                GetPaic getPaic = mGetPaics.get(0);
                String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
                Intent intent = new Intent();
                intent.setClass(yemianer2.this, chengyuanxinxi.class);
                if (getPaic != null) {
                    intent.putExtra("rydm", getPaic.getFzr().getRydm());
                    intent.putExtra("ip", ip);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("userpwd", mUserpwd);
                    intent.putExtra("scrq", scrq);
                    intent.putExtra("pcid", pcid);
                }

                startActivity(intent);
                break;
            case R.id.btn_img2:
                GetPaic getPaic2 = mGetPaics.get(0);

                Intent intent2 = new Intent();
                intent2.setClass(yemianer2.this, chengyuanxinxi.class);
                if (getPaic2 != null) {
                    intent2.putExtra("rydm", getPaic2.getJyy().getRydm());
                    intent2.putExtra("ip", ip);
                    intent2.putExtra("username", mUsername);
                    intent2.putExtra("userpwd", mUserpwd);
                    intent2.putExtra("scrq", scrq);
                }

                startActivity(intent2);
                break;
            case R.id.btn_img3:
                GetPaic getPaic3 = mGetPaics.get(0);
                // Toast.makeText(yemianer2.this, getPaic.getFzr().getRydm(),
                // Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent();
                intent3.setClass(yemianer2.this, chengyuanxinxi.class);
                if (getPaic3 != null && getPaic3.getCztList().size() > 0) {
                    intent3.putExtra("rydm", getPaic3.getCztList().get(0).getRydm());
                    intent3.putExtra("ip", ip);
                    intent3.putExtra("username", mUsername);
                    intent3.putExtra("userpwd", mUserpwd);
                    intent3.putExtra("scrq", scrq);
                }

                startActivity(intent3);
                break;
            case R.id.btn_img4:

                GetPaic getPaic4 = mGetPaics.get(0);
                // Toast.makeText(yemianer2.this, getPaic.getFzr().getRydm(),
                // Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent();
                intent4.setClass(yemianer2.this, chengyuanxinxi.class);
                if (getPaic4 != null && getPaic4.getCztList().size() > 1) {
                    intent4.putExtra("rydm", getPaic4.getCztList().get(1).getRydm());
                    intent4.putExtra("ip", ip);
                    intent4.putExtra("username", mUsername);
                    intent4.putExtra("userpwd", mUserpwd);
                    intent4.putExtra("scrq", scrq);
                }

                startActivity(intent4);
                break;
            case R.id.btn_img5:
                GetPaic getPaic5 = mGetPaics.get(0);
                // Toast.makeText(yemianer2.this, getPaic.getFzr().getRydm(),
                // Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent();
                intent5.setClass(yemianer2.this, chengyuanxinxi.class);
                if (getPaic5 != null && getPaic5.getCztList().size() > 2) {
                    intent5.putExtra("rydm", getPaic5.getCztList().get(2).getRydm());
                    intent5.putExtra("ip", ip);
                    intent5.putExtra("username", mUsername);
                    intent5.putExtra("userpwd", mUserpwd);
                    intent5.putExtra("scrq", scrq);
                }

                startActivity(intent5);
                break;
            case R.id.btn_chanxian1:

                Intent intent_cx = new Intent();
                intent_cx.setClass(yemianer2.this, chanpinjiaohu.class);
                intent_cx.putExtra("scrq", scrq);
                intent_cx.putExtra("username", mUsername);
                intent_cx.putExtra("userpwd", mUserpwd);
                intent_cx.putExtra("ip", ip);
                intent_cx.putExtra("cpdm1", cpdm1);
                startActivity(intent_cx);
                break;
            case R.id.btn_chanxian2:
                Intent intent_cx2 = new Intent();
                intent_cx2.setClass(yemianer2.this, chanpinjiaohu.class);
                intent_cx2.putExtra("scrq", scrq);
                intent_cx2.putExtra("username", mUsername);
                intent_cx2.putExtra("userpwd", mUserpwd);
                intent_cx2.putExtra("ip", ip);
                intent_cx2.putExtra("cpdm1", cpdm2);
                startActivity(intent_cx2);
                break;
            default:

                break;
        }
    }

    private void hujiao(String startName, String endName, View v, int flag,String hjjlh) {
        if (flag == 1) {
            if (isStart1) {
                isStart1 = false;
                StartTask startTask = new StartTask(startName, v, 1);
                startTask.execute();
            } else {

                isStart1 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        } else if (flag == 2) {
            if (isStart2) {
                isStart2 = false;
                StartTask startTask = new StartTask(startName, v, 2);
                startTask.execute();
            } else {

                isStart2 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        } else if (flag == 3) {
            if (isStart3) {
                isStart3 = false;
                StartTask startTask = new StartTask(startName, v, 3);
                startTask.execute();
            } else {

                isStart3 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        } else if (flag == 4) {
            if (isStart4) {
                isStart4 = false;
                StartTask startTask = new StartTask(startName, v, 4);
                startTask.execute();
            } else {

                isStart4 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        } else if (flag == 5) {
            if (isStart5) {
                isStart5 = false;
                StartTask startTask = new StartTask(startName, v, 5);
                startTask.execute();
            } else {

                isStart5 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        } else if (flag == 6) {
            if (isStart6) {
                isStart6 = false;
                StartTask startTask = new StartTask(startName, v, 6);
                startTask.execute();
            } else {

                isStart6 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        } else if (flag == 7) {
            if (isStart7) {
                isStart7 = false;
                StartTask startTask = new StartTask(startName, v, 7);
                startTask.execute();
            } else {

                isStart7 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        } else if (flag == 8) {
            if (isStart8) {
                isStart8 = false;
                StartTask startTask = new StartTask(startName, v, 8);
                startTask.execute();
            } else {

                isStart8 = true;
                realFinish(v, endName, flag,hjjlh);

            }
        }
    }

    public class StartTask extends AsyncTask<Void, Void, List<ConfirmChanxdm>> {
        private String name;
        private View view;
        private int flag;

        public StartTask(String name, View view, int flag) {
            this.name = name;
            this.view = view;
            this.flag = flag;
        }

        @Override
        protected List<ConfirmChanxdm> doInBackground(Void... arg0) {

            return loadStartHjData(name);
        }

        @Override
        protected void onPostExecute(List<ConfirmChanxdm> result) {
            super.onPostExecute(result);

            if (result != null && result.size() > 0) {
                if (result.get(0).getResult().equals("1")) {
                    view.setBackgroundColor(Color.parseColor("#ff0000"));
                    String hjjlh = result.get(0).getMsg();
                    if(flag==1) {
                        ShareUtils.setHjjlh1(yemianer2.this, hjjlh);
                    }
                    if(flag==2) {
                        ShareUtils.setHjjlh2(yemianer2.this, hjjlh);
                    }
                    if(flag==3) {
                        ShareUtils.setHjjlh3(yemianer2.this, hjjlh);
                    }
                    if(flag==4) {
                        ShareUtils.setHjjlh4(yemianer2.this, hjjlh);
                    }
                    if(flag==5) {
                        ShareUtils.setHjjlh5(yemianer2.this, hjjlh);
                    }if(flag==6) {
                        ShareUtils.setHjjlh6(yemianer2.this, hjjlh);
                    }if(flag==7) {
                        ShareUtils.setHjjlh7(yemianer2.this, hjjlh);
                    }if(flag==8) {
                        ShareUtils.setHjjlh8(yemianer2.this, hjjlh);
                    }


                } else {
                    Toast.makeText(yemianer2.this, "呼叫失败", Toast.LENGTH_SHORT).show();
                    if (flag == 1) {

                        isStart1 = true;
                    } else if (flag == 2) {
                        isStart2 = true;
                    } else if (flag == 3) {
                        isStart3 = true;
                    } else if (flag == 4) {
                        isStart4 = true;
                    } else if (flag == 5) {
                        isStart5 = true;
                    } else if (flag == 6) {
                        isStart6 = true;
                    } else if (flag == 7) {
                        isStart7 = true;
                    } else if (flag == 8) {
                        isStart8 = true;
                    }
                }
            }

        }
    }

    public class EndTask extends AsyncTask<Void, Void, List<ConfirmChanxdm>> {
        private String name;
        private View view;
        private int flag;
        private String gh;
        private String hjjlh;

        public EndTask(String name, View view, int flag, String gh,String hjjlh) {
            this.name = name;
            this.view = view;
            this.flag = flag;
            this.gh = gh;
            this.hjjlh = hjjlh;
        }

        @Override
        protected List<ConfirmChanxdm> doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            return loadEndHjData(name, gh,hjjlh);
        }

        @Override
        protected void onPostExecute(List<ConfirmChanxdm> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (result != null && result.size() > 0) {
                if (result.get(0).getResult().equals("1")) {
                    view.setBackgroundColor(Color.parseColor("#008000"));
                    Toast.makeText(yemianer2.this, "呼叫返回成功", Toast.LENGTH_SHORT).show();
                    // dialog.create().cancel();
                    if(flag==1){
                        ShareUtils.setHjjlh1(yemianer2.this,"");
                    }
                    if(flag==2){
                        ShareUtils.setHjjlh2(yemianer2.this,"");
                    }
                    if(flag==3){
                        ShareUtils.setHjjlh3(yemianer2.this,"");
                    }
                    if(flag==4){
                        ShareUtils.setHjjlh4(yemianer2.this,"");
                    }
                    if(flag==5){
                        ShareUtils.setHjjlh5(yemianer2.this,"");
                    }
                    if(flag==6){
                        ShareUtils.setHjjlh6(yemianer2.this,"");
                    }
                    if(flag==7){
                        ShareUtils.setHjjlh7(yemianer2.this,"");
                    }
                    if(flag==8){
                        ShareUtils.setHjjlh8(yemianer2.this,"");
                    }
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(yemianer2.this, "呼叫返回失败", Toast.LENGTH_SHORT).show();
                    if (flag == 1) {

                        isStart1 = false;
                    } else if (flag == 2) {
                        isStart2 = false;
                    } else if (flag == 3) {
                        isStart3 = false;
                    } else if (flag == 4) {
                        isStart4 = false;
                    } else if (flag == 5) {
                        isStart5 = false;
                    } else if (flag == 6) {
                        isStart6 = false;
                    } else if (flag == 7) {
                        isStart7 = false;
                    } else if (flag == 8) {
                        isStart8 = false;
                    }
                    // dialog.create().closeOptionsMenu();
                    alertDialog.dismiss();
                    realFinish(view, name, flag,hjjlh);
                }
            }

        }
    }
    public class ProTask extends AsyncTask<Void, Void, Shouqjs> {

        private String tm;
        private int pcclid;

        public ProTask(int pcclid, String tm) {
            this.pcclid = pcclid;
            this.tm = tm;

        }

        @Override
        protected Shouqjs doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            return loadProData1(pcclid,tm);
        }

        @Override
        protected void onPostExecute(Shouqjs result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (result != null ) {

                if (result.getResult().equals("1")) {
                    if(proNo==0){
                        String temp =	textRealproList1.get(timeNo).getText().toString();
                        int t = Integer.parseInt(temp==""?"0":temp);
                        t++;
                        final int	pro =  t;
                        SavePaicTask paicTask = new SavePaicTask(proNo, timeNo, ip, null,
                                pcclid+"", pro +"");
                        paicTask.execute();
//					new Thread() {
//						public void run() {
//							loadSavePaicWCSLData(proNo, timeNo, ip, null,
//									pcclid+"", pro +"");
//						};
//					}.start();
                        Toast.makeText(yemianer2.this, "录入成功", Toast.LENGTH_SHORT).show();
                    }
                    else if(proNo==1){
                        String temp =	textRealproList2.get(timeNo).getText().toString();
                        int t = Integer.parseInt(temp==""?"0":temp);
                        t++;
                        final int	pro =  t;
                        SavePaicTask paicTask = new SavePaicTask(proNo, timeNo, ip, null,
                                pcclid+"", pro +"");
                        paicTask.execute();
//							new Thread() {
//								public void run() {
//									loadSavePaicWCSLData(proNo, timeNo, ip, null,
//											pcclid+"", pro +"");
//								};
//							}.start();
                        Toast.makeText(yemianer2.this, "录入成功", Toast.LENGTH_SHORT).show();
                    }



                } else if(result.getResult().equals("-1")){
                    Toast.makeText(yemianer2.this, result.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(yemianer2.this, "录入失败", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public class UnlockProTask extends AsyncTask<Void, Void, Shouqjs> {
        private int pProNo;
        private View view;
        private int pTimeNo;
        private String rydm;

        public UnlockProTask(View view,int pProNo, int pTimeNo, String rydm) {
            this.pTimeNo = pTimeNo;
            this.view = view;
            this.pProNo = pProNo;
            this.rydm = rydm;
        }

        @Override
        protected Shouqjs doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            return loadUnlockProData(rydm);
        }

        @Override
        protected void onPostExecute(Shouqjs result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (result != null ) {
                if (result.getResult().equals("1")) {
                    if(proNo==-1){
                        if(pProNo==0){
                            Toast.makeText(yemianer2.this, "解锁成功", Toast.LENGTH_SHORT).show();
                            //textPeroidList1.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));
                            timeNo = pTimeNo;
                            proNo = pProNo;
                            tTimeNo = pTimeNo;
                            pcclid  = Integer.parseInt(pcclPlanList1.get(timeNo).getPcclid() != null
                                    ? pcclPlanList1.get(timeNo).getPcclid() : "0");
                            String proTemp = textRealproList1.get(timeNo).getText().toString();
                            proRealNumber  = Integer.parseInt(proTemp != ""
                                    ? proTemp : "0");
                            textPeroidList1.get(timeNo).setBackgroundColor(Color.parseColor("#449D44"));
                        }else if(pProNo==1){
                            Toast.makeText(yemianer2.this, "解锁成功", Toast.LENGTH_SHORT).show();
                            //textPeroidList2.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));
                            timeNo = pTimeNo;
                            proNo = pProNo;
                            tTimeNo = pTimeNo;

                            pcclid  = Integer.parseInt(pcclPlanList2.get(timeNo).getPcclid() != null
                                    ? pcclPlanList2.get(timeNo).getPcclid() : "0");
                            String proTemp = textRealproList2.get(timeNo).getText().toString();
                            proRealNumber  = Integer.parseInt(proTemp != ""
                                    ? proTemp : "0");
                            textPeroidList2.get(timeNo).setBackgroundColor(Color.parseColor("#449D44"));

                        }
                    }else
                    if(proNo==0){
                        Toast.makeText(yemianer2.this, "解锁成功", Toast.LENGTH_SHORT).show();
                        textPeroidList1.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));
                        timeNo = pTimeNo;
                        proNo = pProNo;
                        tTimeNo = pTimeNo;

                        pcclid  = Integer.parseInt(pcclPlanList1.get(timeNo).getPcclid() != null
                                ? pcclPlanList1.get(timeNo).getPcclid() : "0");
                        String proTemp = textRealproList1.get(timeNo).getText().toString();
                        proRealNumber  = Integer.parseInt(proTemp != ""
                                ? proTemp : "0");
                        textPeroidList1.get(timeNo).setBackgroundColor(Color.parseColor("#449D44"));
                    }else if(proNo==1){
                        Toast.makeText(yemianer2.this, "解锁成功", Toast.LENGTH_SHORT).show();
                        textPeroidList2.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));
                        timeNo = pTimeNo;
                        proNo = pProNo;
                        tTimeNo = pTimeNo;

                        pcclid  = Integer.parseInt(pcclPlanList2.get(timeNo).getPcclid() != null
                                ? pcclPlanList2.get(timeNo).getPcclid() : "0");
                        String proTemp = textRealproList2.get(timeNo).getText().toString();
                        proRealNumber  = Integer.parseInt(proTemp != ""
                                ? proTemp : "0");
                        textPeroidList2.get(timeNo).setBackgroundColor(Color.parseColor("#449D44"));

                    }
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(yemianer2.this, "解锁失败", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }


        }
    }
    private void realProDialog(final View v, final int id, final int flag) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(
                yemianer2.this);

        dialog.setTitle("请输入产品数量");
        View view = LayoutInflater.from(yemianer2.this).inflate(
                R.layout.real_pro, null);
        final EditText real_et_pro = (EditText) view
                .findViewById(R.id.real_et_pro);
        dialog.setView(view);
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                final String name = real_et_pro.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(yemianer2.this, "请输入产品数量", Toast.LENGTH_SHORT).show();

                } else {
                    //ProTask proTask = new ProTask(pcclid, real_et_pro.getText().toString());
                    //proTask.execute();
                    v.setVisibility(View.INVISIBLE);
                    String pcclId = "";
                    if (flag == 0) {
                        pcclId = mGetPaics.get(0).getCpList().get(2)
                                .getPcclList().get(id).getPcclid();
                    } else if (flag == 1) {
                        pcclId = mGetPaics.get(0).getCpList().get(3)
                                .getPcclList().get(id).getPcclid();
                    }
                    if (!pcclId.equals("")) {
                        final String pcclIdItem = pcclId;
                        new Thread() {
                            public void run() {
                                loadSavePaicWCSLData(flag, id, ip, v,
                                        pcclIdItem, name);
                            };
                        }.start();
                    }
                }
            }
        });

        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        // dialog.create().show();
        TextWatcher watcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        // real_et_pro.addTextChangedListener(watcher);

    }
    private void unlockPro(final View v, final int pProNo, final int pTimeNo) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(yemianer2.this);
        dialog.setTitle("请扫描解锁条码");


        final View view = LayoutInflater.from(yemianer2.this).inflate(
                R.layout.hujiaojilu, null);
        final EditText hjjl_edt = (EditText) view.findViewById(R.id.hjjl_edt);
        dialog.setView(view);
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                UnlockProTask proTask = new UnlockProTask(v, pProNo,pTimeNo, hjjl_edt.getText()
                        .toString());
                proTask.execute();
                // Toast.makeText(yemianer2.this, "非专业人员请勿操作", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog = dialog.create();
        alertDialog.show();

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                // Toast.makeText(yemianer2.this, "changed", Toast.LENGTH_SHORT).show();
                // dialog.create().dismiss();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
                // Toast.makeText(yemianer2.this, "before", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                if (hjjl_edt.getText().toString().length() >= 5) {
                    // Toast.makeText(yemianer2.this, "after", Toast.LENGTH_SHORT).show();
                    // dialog.create().dismiss();

                    UnlockProTask proTask = new UnlockProTask(v, pProNo,pTimeNo, hjjl_edt.getText()
                            .toString());
                    proTask.execute();

                }
            }
        };
        hjjl_edt.addTextChangedListener(textWatcher);
    }
    private void realFinish(final View v, final String name, final int flag,final String hjjlh) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(yemianer2.this);
        dialog.setTitle("请输入工号");

        final View view = LayoutInflater.from(yemianer2.this).inflate(
                R.layout.hujiaojilu, null);
        final EditText hjjl_edt = (EditText) view.findViewById(R.id.hjjl_edt);
        dialog.setView(view);
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (flag == 1) {

                    isStart1 = false;
                } else if (flag == 2) {
                    isStart2 = false;
                } else if (flag == 3) {
                    isStart3 = false;
                } else if (flag == 4) {
                    isStart4 = false;
                } else if (flag == 5) {
                    isStart5 = false;
                } else if (flag == 6) {
                    isStart6 = false;
                } else if (flag == 7) {
                    isStart7 = false;
                } else if (flag == 8) {
                    isStart8 = false;
                }
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                EndTask endTask = new EndTask(name, v, flag, hjjl_edt.getText()
                        .toString(),hjjlh);
                endTask.execute();
                // Toast.makeText(yemianer2.this, "非专业人员请勿操作", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog = dialog.create();
        alertDialog.show();

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (hjjl_edt.getText().toString().length() >= 5) {


                    EndTask endTask = new EndTask(name, v, flag, hjjl_edt
                            .getText().toString(),hjjlh);
                    endTask.execute();

                }
            }
        };
        hjjl_edt.addTextChangedListener(textWatcher);
    }
    private void dealPaic(){
        if (isStartTimer) {
            timer.start();
            isStartTimer = false;
        }
        timer2.start();
        timerSaomiao.start();
        ChanxxxTask chanxxxTask = new ChanxxxTask();
        chanxxxTask.execute();
        // Log.e("gg", mGetPaics.size() + "size");
        if (mGetPaics != null && mGetPaics.size() > 0
                &&mGetPaics.get(0)!=null&& mGetPaics.get(0).getCpList().size() > 0) {
            // 设置生产时段1
            List<Pccl> pcclList1 = mGetPaics.get(0).getCpList().get(2)
                    .getPcclList();
            String scgx = mGetPaics.get(0).getCpList().get(2).getScgx();
            if (scgx == null) {
                scgx = "";
            }
            btn_chanxian1.setText(mGetPaics.get(0).getCpList().get(2)
                    .getCpdm()
                    + "  " + scgx);
            cpdm1 = mGetPaics.get(0).getCpList().get(2).getCpdm();
            // 修改
            if(mGetPaics.get(0).getCpList().get(2).getYxcc().equals("0")){
                main_tv_flag1.setText("可超量");
                main_tv_flag1.setBackgroundColor(Color
                        .parseColor("#00ffff"));
            }else{
                main_tv_flag1.setText("不可超量");
                main_tv_flag1.setBackgroundColor(Color
                        .parseColor("#ff0000"));
            }
            btn_chanxian1.setOnClickListener(yemianer2.this);
            int real1 = 0;
            int real2 = 0;
            double sumOne1;
            double sumRatioH = 0.00;
            double sumRatioH2 = 0.00;
            // 设置计划产量1
            pcclPlanList1 = mGetPaics.get(0).getCpList()
                    .get(2).getPcclList();
            int plan1 = 0;

            if (mGetPaics.get(0).getCpList().size() >= 4) {
                // 设置生产时段1
                pcclPlanList2 = mGetPaics.get(0).getCpList()
                        .get(3).getPcclList();
                int plan2 = 0;
                if (pcclPlanList2 != null && pcclPlanList2.size() > 0) {
                    for (int i = 0; i < pcclPlanList2.size(); i++) {
                        if (i == 6) {
                            break;
                        }
                        plan2 += Integer.parseInt(pcclPlanList2.get(i)
                                .getSl());
                        if (i == pcclPlanList2.size() - 1) {
                            textPlanproList2.get(i + 1).setText(
                                    "" + plan2);
                        }
                        textPlanproList2.get(i).setText(
                                pcclPlanList2.get(i).getSl());
                    }
                }
            }
            if (pcclList1 != null && pcclList1.size() > 0) {
                mPaicsPcclId1 = new ArrayList<String>();
                for (int i = 0; i < pcclList1.size(); i++) {
                    if (i == 6) {
                        break;
                    }
                    //计划产量
                    plan1 += Integer.parseInt(pcclPlanList1.get(i)
                            .getSl());
                    textPlanproList1.get(i).setText(
                            pcclPlanList1.get(i).getSl());
                    if (i == pcclPlanList1.size() - 1) {
                        textPlanproList1.get(i + 1).setText("" + plan1);
                    }
                    //真实产量
                    real1 += Integer.parseInt(pcclList1.get(i)
                            .getWcsl() != null ? pcclList1.get(i)
                            .getWcsl() : "0");
                    final int temp = i;
                    textPeroidList1.get(i).setOnClickListener(
                            new OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    // 时间对比
                                    String time =  textPeroidList1.get(temp).getText().toString();
                                    String times [] = time.split("-");

                                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                            "HH:mm");
                                    Date nows = new Date();
                                    String nowsStr = dateFormat.format(nows);
                                    //nowsStr = "14:20";
                                    String startTime = times[0];
                                    String endTime = times[1];
                                    try {
                                        long resStart = dateFormat.parse(nowsStr)
                                                .getTime()
                                                - dateFormat.parse(startTime).getTime();

                                        long resEnd = dateFormat.parse(endTime)
                                                .getTime()
                                                - dateFormat.parse(nowsStr).getTime();
                                        if((resStart>=0&&resEnd>=0)||(dateFormat.parse(startTime).getTime()>0&&dateFormat.parse(endTime).getTime()<0)){
                                            //Toast.makeText(yemianer2.this, "生产时段", Toast.LENGTH_SHORT).show();
                                            if(timeNo>=0){
                                                textPeroidList1.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));

                                                textPeroidList2.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));


                                            }
                                            if(timeNo==-1){
                                                main_edt_pro.setText("");
                                            }
                                            timeNo = temp;
                                            proNo = 0;
                                            tTimeNo = temp;
                                            tProNo = 0;
                                            pcclid  = Integer.parseInt(pcclPlanList1.get(temp).getPcclid() != null

                                                    ? pcclPlanList1.get(temp).getPcclid() : "0");
                                            //Toast.makeText(yemianer2.this, pcclid+"", Toast.LENGTH_SHORT).show();
                                            proRealNumber  = Integer.parseInt(pcclPlanList1.get(temp).getWcsl() != null
                                                    ? pcclPlanList1.get(temp).getWcsl() : "0");
                                            textPeroidList1.get(temp).setBackgroundColor(Color.parseColor("#449D44"));
                                        }else{
                                            Toast.makeText(yemianer2.this, "请选择相应生产时段", Toast.LENGTH_SHORT).show();
                                        }


                                    } catch (ParseException e) {

                                        e.printStackTrace();
                                    }

                                }
                            });

                    textPeroidList1.get(i).setText(
                            pcclList1.get(i).getSdstart() + " - "
                                    + pcclList1.get(i).getSdend());

                    textRealproList1.get(i).setOnClickListener(
                            yemianer2.this);
                    mPaicsPcclId1.add(pcclList1.get(i).getPcclid());
                    textRealproList1.get(i).setText(
                            pcclList1.get(i).getWcsl());
                    String name = textRealproList1.get(i).getText()
                            .toString();
                    if (!name.equals("")) {

                        String palnStr = pcclList1.get(i).getSl();
                        int plan = Integer.parseInt(palnStr);
                        if (plan == 0) {
                            textSumratioList1.get(i).setText("100.00%");
                            textSumratioList1.get(i).setTextColor(
                                    Color.parseColor("#000000"));

                        } else {
                            double sumRatio = (double) (Integer
                                    .parseInt(name) * 1.0 / plan * 100);
                            if (Integer.parseInt(name) == 0) {
                                textSumratioList1.get(i).setText(
                                        "0.00%");
                                textSumratioList1.get(i).setTextColor(
                                        Color.parseColor("#FFC000"));
                            } else {
                                textSumratioList1.get(i).setText(
                                        new java.text.DecimalFormat(
                                                "#.00")
                                                .format(sumRatio)
                                                + "%");
                                if (sumRatio < 100.00) {
                                    textSumratioList1
                                            .get(i)
                                            .setTextColor(
                                                    Color.parseColor("#FFC000"));
                                } else {
                                    textSumratioList1
                                            .get(i)
                                            .setTextColor(
                                                    Color.parseColor("#000000"));

                                }
                            }
                        }
                    }
                    String sumRatioStr = textSumratioList1.get(i)
                            .getText().toString();
                    String sumRatioSub = "";

                    if (sumRatioStr.equals("")) {

                    } else {
                        sumNumber1++;
                        sumRatioSub = sumRatioStr.substring(0,
                                sumRatioStr.length() - 1);
                        sumRatioH += Double.parseDouble(sumRatioSub);
                    }
                    if (i == pcclList1.size() - 1) {
                        textPeroidList1.get(i + 1).setText("合计");
                        textRealproList1.get(i + 1).setText(real1 + "");
                        double planSum = Double
                                .parseDouble((textPlanproList1.get(
                                        i + 1).getText().toString()));

                        if (real1 == 0) {
                            textSumratioList1.get(i + 1).setText(
                                    "0.00%");
                            textSumratioList1.get(i + 1).setTextColor(
                                    Color.parseColor("#ffc000"));
                        } else {
                            textSumratioList1
                                    .get(i + 1)
                                    .setText(
                                            new java.text.DecimalFormat(
                                                    "#0.00")
                                                    .format((real1
                                                            / planSum * 100))
                                                    + "%");
                            if ((real1 / planSum) < 1.0) {
                                textSumratioList1
                                        .get(i + 1)
                                        .setTextColor(
                                                Color.parseColor("#ffc000"));

                            } else {
                                textSumratioList1
                                        .get(i + 1)
                                        .setTextColor(
                                                Color.parseColor("#000000"));
                            }
                        }
                    }

                }
            }

            if (mGetPaics.get(0).getCpList().size() >= 4) {
                // 设置生产时段2
                List<Pccl> pcclList2 = mGetPaics.get(0).getCpList()
                        .get(3).getPcclList();
                String scgx2 = mGetPaics.get(0).getCpList().get(3)
                        .getScgx();
                if (scgx2 == null) {
                    scgx2 = "";
                }

                btn_chanxian2.setText(mGetPaics.get(0).getCpList()
                        .get(3).getCpdm()
                        + "  " + scgx2);
                cpdm2 = mGetPaics.get(0).getCpList().get(3).getCpdm();
                if(mGetPaics.get(0).getCpList().get(3).getYxcc().equals("0")){
                    main_tv_flag2.setText("可超量");
                    main_tv_flag2.setBackgroundColor(Color
                            .parseColor("#00ffff"));
                }else{
                    main_tv_flag2.setText("不可超量");
                    main_tv_flag2.setBackgroundColor(Color
                            .parseColor("#ff0000"));
                }
                btn_chanxian2.setOnClickListener(yemianer2.this);
                mPaicsPcclId2 = new ArrayList<String>();

                if (pcclList2 != null && pcclList2.size() > 0) {
                    for (int i = 0; i < pcclList2.size(); i++) {
                        if (i == 6) {
                            break;
                        }
                        real2 += Integer.parseInt(pcclList2.get(i)
                                .getWcsl() != null ? pcclList2.get(i)
                                .getWcsl() : "0");
                        if (i == pcclList2.size() - 1) {
                            textPeroidList2.get(i + 1).setText("合计");
                            textRealproList2.get(i + 1).setText(
                                    real2 + "");
                        }

                        final int temp = i;
                        textPeroidList2.get(i).setOnClickListener(
                                new OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // 时间对比
                                        String time =  textPeroidList2.get(temp).getText().toString();
                                        String times [] = time.split("-");

                                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                "HH:mm");
                                        Date nows = new Date();
                                        String nowsStr = dateFormat.format(nows);
                                        //nowsStr = "14:20";
                                        String startTime =times[0];
                                        String endTime = times[1];
                                        try {
                                            long resStart = dateFormat.parse(nowsStr)
                                                    .getTime()
                                                    - dateFormat.parse(startTime).getTime();
                                            long resEnd = dateFormat.parse(endTime)
                                                    .getTime()
                                                    - dateFormat.parse(nowsStr).getTime();
                                            if((resStart>=0&&resEnd>=0)||(dateFormat.parse(startTime).getTime()>0&&dateFormat.parse(endTime).getTime()<0)){
                                                //Toast.makeText(yemianer2.this, "生产时段", Toast.LENGTH_SHORT).show();
                                                if(timeNo>=0){
                                                    textPeroidList1.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));
                                                    textPeroidList2.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));
                                                }
                                                if(timeNo==-1){
                                                    main_edt_pro.setText("");
                                                }
                                                timeNo = temp;
                                                proNo = 1;
                                                tTimeNo = temp;
                                                tProNo = 1;
                                                pcclid  = Integer.parseInt(pcclPlanList2.get(temp).getPcclid() != null
                                                        ? pcclPlanList2.get(temp).getPcclid() : "0");
                                                //Toast.makeText(yemianer2.this, pcclid+"", Toast.LENGTH_SHORT).show();
                                                proRealNumber  = Integer.parseInt(pcclPlanList2.get(temp).getWcsl() != null
                                                        ? pcclPlanList2.get(temp).getWcsl() : "0");
                                                textPeroidList2.get(temp).setBackgroundColor(Color.parseColor("#449D44"));
                                            }else{
                                                Toast.makeText(yemianer2.this, "请选择相应生产时段", Toast.LENGTH_SHORT).show();
                                            }


                                        } catch (ParseException e) {

                                            e.printStackTrace();
                                        }

                                    }
                                });

                        textPeroidList2.get(i).setText(
                                pcclList2.get(i).getSdstart() + "-"
                                        + pcclList2.get(i).getSdend());
                        textRealproList2.get(i).setOnClickListener(
                                yemianer2.this);
                        mPaicsPcclId2.add(pcclList2.get(i).getPcclid());
                        textRealproList2.get(i).setText(
                                pcclList2.get(i).getWcsl());
                        String name = textRealproList2.get(i).getText()
                                .toString();
                        if (!name.equals("")) {

                            String palnStr = pcclList2.get(i).getSl();
                            int plan = Integer.parseInt(palnStr);
                            if (plan == 0) {
                                textSumratioList2.get(i).setText(
                                        "100.00%");
                                textSumratioList2.get(i).setTextColor(
                                        Color.parseColor("#000000"));

                            } else {
                                double sumRatio = (double) (Integer
                                        .parseInt(name) * 1.0 / plan * 100);
                                if (Integer.parseInt(name) == 0) {
                                    textSumratioList2.get(i).setText(
                                            "0.00%");
                                    textSumratioList2
                                            .get(i)
                                            .setTextColor(
                                                    Color.parseColor("#ffc000"));

                                } else {
                                    textSumratioList2
                                            .get(i)
                                            .setText(
                                                    new java.text.DecimalFormat(
                                                            "#0.00")
                                                            .format(sumRatio)
                                                            + "%");
                                    if (sumRatio < 100.00) {
                                        textSumratioList2
                                                .get(i)
                                                .setTextColor(
                                                        Color.parseColor("#ffc000"));
                                    } else {
                                        textSumratioList2
                                                .get(i)
                                                .setTextColor(
                                                        Color.parseColor("#000000"));

                                    }
                                }
                            }

                        }
                        String sumRatioStr = textSumratioList2.get(i)
                                .getText().toString();
                        String sumRatioSub = "";

                        if (sumRatioStr.equals("")) {

                        } else {
                            sumNumber2++;
                            sumRatioSub = sumRatioStr.substring(0,
                                    sumRatioStr.length() - 1);
                            sumRatioH2 += Double
                                    .parseDouble(sumRatioSub);
                        }
                        if (pcclList1 != null
                                && i == pcclList1.size() - 1) {
                            textPeroidList2.get(i + 1).setText("合计");
                            textRealproList2.get(i + 1).setText(
                                    real2 + "");
                            double planSum = Double
                                    .parseDouble((textPlanproList2.get(
                                            i + 1).getText().toString()));

                            if (real2 == 0) {
                                textSumratioList2.get(i + 1).setText(
                                        "0.00%");
                                textSumratioList2
                                        .get(i + 1)
                                        .setTextColor(
                                                Color.parseColor("#ffc000"));

                            } else {
                                textSumratioList2.get(i + 1).setText(
                                        new java.text.DecimalFormat(
                                                "#.00").format((real2
                                                / planSum * 100))
                                                + "%");
                                if ((real2 / planSum) < 1.0) {
                                    textSumratioList2
                                            .get(i + 1)
                                            .setTextColor(
                                                    Color.parseColor("#ffc000"));
                                } else {
                                    textSumratioList2
                                            .get(i + 1)
                                            .setTextColor(
                                                    Color.parseColor("#000000"));

                                }
                            }
                        }
                    }
                }
            }

            // 设置头像
            if (mGetPaics.get(0).getFzr() != null
                    && !mGetPaics.get(0).getFzr().getSrc().equals("")) {
                Picasso.with(yemianer2.this)
                        .load(mGetPaics.get(0).getFzr().getSrc())
                        .into(btn_img1);
                btn_img1.setOnClickListener(yemianer2.this);
            }
            if (mGetPaics.get(0).getJyy() != null
                    && !mGetPaics.get(0).getJyy().getSrc().equals("")) {

                btn_img2.setOnClickListener(yemianer2.this);
                Picasso.with(yemianer2.this)
                        .load(mGetPaics.get(0).getJyy().getSrc())
                        .into(btn_img2);
            }
            if (mGetPaics.get(0).getCztList() != null
                    && mGetPaics.get(0).getCztList().size() > 0) {
                int size = mGetPaics.get(0).getCztList().size();
                for (int i = 0; i < size; i++) {
                    if (i == 3) {
                        return;
                    }
                    Picasso.with(yemianer2.this)
                            .load(mGetPaics.get(0).getCztList().get(i)
                                    .getSrc()).into(imageList.get(i));
                    imageList.get(i).setOnClickListener(yemianer2.this);
                    textList.get(i).setVisibility(View.VISIBLE);
                }
            }
            // 设置名字
            main_tv_fzr.setText(mGetPaics.get(0).getFzr() == null ? ""
                    : mGetPaics.get(0).getFzr().getXm());
            main_tv_jyy.setText(mGetPaics.get(0).getJyy() == null ? ""
                    : mGetPaics.get(0).getJyy().getXm());
            if (mGetPaics.get(0).getCztList() != null
                    && mGetPaics.get(0).getCztList().size() > 0) {

                for (int i = 0; i < mGetPaics.get(0).getCztList()
                        .size(); i++) {
                    if (i == 3) {
                        return;
                    }
                    textList.get(i).setText(
                            mGetPaics.get(0).getCztList().get(i)
                                    .getXm());
                }
            }
        }
    }
    private Handler mHandler = new Handler() {
        @SuppressLint("ResourceAsColor")
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case Constant.REQUEST_DATA:
                    initData();
                    break;
                case Constant.REQUEST_ERROR:
                    Toast.makeText(yemianer2.this,"服务连接错误,请重试",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                /**
                 * 产能信息处理
                 */
                case Constant.REQUEST_GETLIST_DATA:

                    break;
                case Constant.REQUEST_GETLIST_REEOR:
                    Toast.makeText(yemianer2.this, "error1" + test, Toast.LENGTH_SHORT).show();
                    break;

                case Constant.REQUEST_SAVEPAICWCSL_DATA:

                    SaveWcsl sw = (SaveWcsl) msg.obj;
                    boolean isSave = savePaicList.get(0).getResult().equals("1");
                    sw.getView().setVisibility(View.VISIBLE);
                    int number1 = 0;
                    int number2 = 0;
                    if (isSave) {
                        if (sw.getFlag() == 0) {
                            if (textRealproList1.get(sw.getId()).getText()
                                    .toString().equals("")) {
                                number1 = 0;
                            } else {
                                number1 = Integer.parseInt(textRealproList1
                                        .get(sw.getId()).getText().toString());
                            }
                            textRealproList1.get(sw.getId()).setText(
                                    sw.getNumber() + "");
                            String palnStr = textPlanproList1.get(sw.getId())
                                    .getText().toString();
                            int plan = Integer.parseInt(palnStr);

                            if (plan == 0) {
                                if (textSumratioList1.get(sw.getId()).toString()
                                        .equals("")) {
                                    sumNumber1++;
                                } else {

                                }

                                textSumratioList1.get(sw.getId())
                                        .setText("100.00%");
                                textSumratioList1.get(sw.getId()).setTextColor(
                                        Color.parseColor("#000000"));

                            } else {
                                double sumRatio = (double) (sw.getNumber() * 1.0
                                        / plan * 100);
                                if (sw.getNumber() == 0) {
                                    if (textSumratioList1.get(sw.getId())
                                            .toString().equals("")) {
                                        sumNumber1++;
                                    } else {

                                    }
                                    textSumratioList1.get(sw.getId()).setText(
                                            "0.00%");
                                    textSumratioList1.get(sw.getId()).setTextColor(
                                            Color.parseColor("#ffc000"));

                                } else {
                                    if (textSumratioList1.get(sw.getId())
                                            .toString().equals("")) {
                                        sumNumber1++;
                                    } else {

                                    }
                                    textSumratioList1.get(sw.getId()).setText(
                                            new java.text.DecimalFormat("#.00")
                                                    .format(sumRatio) + "%");
                                    if (sumRatio < 100.00) {
                                        textSumratioList1
                                                .get(sw.getId())
                                                .setTextColor(
                                                        Color.parseColor("#ffc000"));
                                    } else {
                                        textSumratioList1
                                                .get(sw.getId())
                                                .setTextColor(
                                                        Color.parseColor("#000000"));
                                    }
                                }
                            }
                            int count1 = 0;
                            for (int i = textSumratioList1.size() - 1; i >= 0; i--) {
                                String sum = textSumratioList1.get(i).getText()
                                        .toString();
                                if (!sum.equals("")) {
                                    count1 = i;
                                    break;
                                }
                            }
                            // 处理实际产量修改
                            int number = Integer.parseInt(textRealproList1
                                    .get(count1).getText().toString());
                            textRealproList1.get(count1).setText(
                                    number - number1 + sw.getNumber() + "");
                            // String sum =
                            // textSumratioList1.get(count).getText().toString();
                            // 处理产量百分比修改
                            String sumRatioStr = textSumratioList1.get(count1)
                                    .getText().toString();
                            String sumRatioSub = sumRatioStr.substring(0,
                                    sumRatioStr.length() - 1);
                            double sumRatio = Double.parseDouble(sumRatioSub);

                            double sumRatioH3 = 0.00;
                            int sumNumber3 = 0;
                            for (int i = 0; i < count1; i++) {
                                String sumRatioStr3 = textSumratioList1.get(i)
                                        .getText().toString();
                                String sumRatioSub3 = "";

                                if (sumRatioStr3.equals("")) {

                                } else {
                                    sumNumber3++;
                                    sumRatioSub3 = sumRatioStr3.substring(0,
                                            sumRatioStr3.length() - 1);
                                    sumRatioH3 += Double.parseDouble(sumRatioSub3);
                                }
                                if (i == count1 - 1) {

                                    if (sumNumber3 == 0 || sumRatioH3 == 0) {
                                        textSumratioList1.get(i + 1).setText(
                                                "0.00%");
                                        textSumratioList1.get(i + 1).setTextColor(
                                                Color.parseColor("#ffc000"));

                                    } else {
                                        int planSum = Integer
                                                .parseInt(textPlanproList1
                                                        .get(i + 1).getText()
                                                        .toString());
                                        int realSum = Integer
                                                .parseInt(textRealproList1
                                                        .get(i + 1).getText()
                                                        .toString());

                                        textSumratioList1
                                                .get(i + 1)
                                                .setText(
                                                        new java.text.DecimalFormat(
                                                                "#0.00")
                                                                .format(((realSum * 1.0 / planSum) * 100))
                                                                + "%");
                                        if ((realSum / planSum) < 1) {
                                            textSumratioList1
                                                    .get(i + 1)
                                                    .setTextColor(
                                                            Color.parseColor("#ffc000"));
                                        } else {
                                            textSumratioList1
                                                    .get(i + 1)
                                                    .setTextColor(
                                                            Color.parseColor("#000000"));

                                        }
                                    }
                                }
                            }

                        } else if (sw.getFlag() == 1) {
                            if (textRealproList2.get(sw.getId()).getText()
                                    .toString().equals("")) {
                                number2 = 0;
                            } else {
                                number2 = Integer.parseInt(textRealproList2
                                        .get(sw.getId()).getText().toString());
                            }
                            textRealproList2.get(sw.getId()).setText(
                                    sw.getNumber() + "");
                            String palnStr = textPlanproList2.get(sw.getId())
                                    .getText().toString();
                            int plan = Integer.parseInt(palnStr);

                            if (plan == 0) {
                                if (textSumratioList2.get(sw.getId()).toString()
                                        .equals("")) {
                                    sumNumber1++;
                                } else {

                                }

                                textSumratioList2.get(sw.getId())
                                        .setText("100.00%");
                                textSumratioList2.get(sw.getId()).setTextColor(
                                        Color.parseColor("#000000"));

                            } else {
                                double sumRatio = (double) (sw.getNumber() * 1.0
                                        / plan * 100);
                                if (sw.getNumber() == 0) {
                                    if (textSumratioList2.get(sw.getId())
                                            .toString().equals("")) {
                                        sumNumber1++;
                                    } else {

                                    }
                                    textSumratioList2.get(sw.getId()).setText(
                                            "0.00%");
                                    textSumratioList2.get(sw.getId()).setTextColor(
                                            Color.parseColor("#ffc000"));

                                } else {
                                    if (textSumratioList2.get(sw.getId())
                                            .toString().equals("")) {
                                        sumNumber1++;
                                    } else {

                                    }
                                    // if
                                    textSumratioList2.get(sw.getId()).setText(
                                            new java.text.DecimalFormat("#.00")
                                                    .format(sumRatio) + "%");
                                    if (sumRatio < 100.00) {
                                        textSumratioList2
                                                .get(sw.getId())
                                                .setTextColor(
                                                        Color.parseColor("#ffc000"));

                                    } else {
                                        textSumratioList2
                                                .get(sw.getId())
                                                .setTextColor(
                                                        Color.parseColor("#000000"));

                                    }
                                }
                            }
                            int count1 = 0;
                            for (int i = textSumratioList2.size() - 1; i >= 0; i--) {
                                String sum = textSumratioList2.get(i).getText()
                                        .toString();
                                if (!sum.equals("")) {
                                    count1 = i;
                                    break;
                                }
                            }
                            // 处理实际产量修改
                            int number = Integer.parseInt(textRealproList2
                                    .get(count1).getText().toString());
                            textRealproList2.get(count1).setText(
                                    number - number2 + sw.getNumber() + "");
                            // String sum =
                            // textSumratioList1.get(count).getText().toString();
                            // 处理产量百分比修改
                            String sumRatioStr = textSumratioList2.get(count1)
                                    .getText().toString();
                            String sumRatioSub = sumRatioStr.substring(0,
                                    sumRatioStr.length() - 1);
                            double sumRatio = Double.parseDouble(sumRatioSub);

                            double sumRatioH3 = 0.00;
                            int sumNumber3 = 0;
                            for (int i = 0; i < count1; i++) {
                                String sumRatioStr3 = textSumratioList2.get(i)
                                        .getText().toString();
                                String sumRatioSub3 = "";

                                if (sumRatioStr3.equals("")) {

                                } else {
                                    sumNumber3++;
                                    sumRatioSub3 = sumRatioStr3.substring(0,
                                            sumRatioStr3.length() - 1);
                                    sumRatioH3 += Double.parseDouble(sumRatioSub3);
                                }
                                if (i == count1 - 1) {

                                    if (sumNumber3 == 0) {
                                        textSumratioList2.get(i + 1).setText(
                                                "0.00%");
                                        textSumratioList2.get(i + 1).setTextColor(
                                                Color.parseColor("#ffc000"));

                                    } else {
                                        int planSum = Integer
                                                .parseInt(textPlanproList2
                                                        .get(i + 1).getText()
                                                        .toString());
                                        int realSum = Integer
                                                .parseInt(textRealproList2
                                                        .get(i + 1).getText()
                                                        .toString());

                                        textSumratioList2.get(i + 1).setText(
                                                new java.text.DecimalFormat("#.00")
                                                        .format((realSum * 1.0
                                                                / planSum * 100))
                                                        + "%");
                                        if (realSum / planSum < 1) {
                                            textSumratioList2
                                                    .get(i + 1)
                                                    .setTextColor(
                                                            Color.parseColor("#ffc000"));

                                        } else {
                                            textSumratioList2
                                                    .get(i + 1)
                                                    .setTextColor(
                                                            Color.parseColor("#000000"));

                                        }
                                    }
                                }
                            }

                        }
                    }
                    //Toast.makeText(yemianer2.this, "提交", Toast.LENGTH_SHORT).show();

                    break;
                case Constant.REQUEST_SAVEPAICWCSL_ERROR:
                    View v1 = (View) msg.obj;
                    v1.setVisibility(View.VISIBLE);
                    Toast.makeText(yemianer2.this, "提交完成产量失败", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        };
    };
    private String test1;
    private String test2;

    private double doubleRatio(String splitStr) {
        String temp = splitStr.substring(0, splitStr.length() - 1);
        return Double.parseDouble(temp);
    }
    public static List<String> sortListDesc(List<String> list) throws ParseException{
        List<String> retStr=new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<Long,String> map = new TreeMap<Long,String>();
        for(int i=0;i<list.size();i++){
            String dateStr = list.get(i);
            try {
                map.put(sdf.parse(dateStr).getTime(), dateStr);
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Collection<String> coll=map.values();
        retStr.addAll(coll);
        Collections.reverse(retStr);
        return retStr;
    }
    private void initData() {

                                scrq = mGetPaicLists.get(mSingleChoiceID).getScrq();
                                main_btn_banci.setText("当前班次(Current Shift):"
                                        + mGetPaicLists.get(mSingleChoiceID)
                                        .getScrq()
                                        + mGetPaicLists.get(mSingleChoiceID)
                                        .getBcdm() + "            ");
                                chanxian.setText("产线代码(PL Code)：" + mUsername
                                        + "        ");
                              new paicTask().execute();
//                                new Thread() {
//                                    public void run() {
//                                        /**
//                                         * 加载getPaic数据
//                                         */
//                                        loadGetPaicData();
//                                    };
//                                }.start();

    }


    public class ChanxxxTask extends AsyncTask<Void, Void, List<Chanxxx>> {

        @Override
        protected List<Chanxxx> doInBackground(Void... arg0) {
            return loadChanxxxData();
        }

        @Override
        protected void onPostExecute(List<Chanxxx> result) {

            super.onPostExecute(result);

            if (result != null && result.size() > 0) {
                if (mChanList != null && mChanList.size() > 0) {
                    main_btn_pcmc.setText("产线名称:(PL Name):"
                            + mChanList.get(0).getCxmc()
                            + "                          ");
                }
            } else {

            }
        }
    }
    public GetPaichj GetPaichjData() {
        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcid>"
                + pcid
                + "</pcid></param></data></root>";
        String method_name = "GetPaichj";
        String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
                method_name, ip);
        //Log.e("yemianer","getPaicData:"+data+",pcid:"+pcid);
        if (!data.equals("")&&data.contains("root")) {
            data = RequestDataUtil.splieRequestXml(data);
            mGetPaichj = RequestDataUtil.parseGetPaichjXml(data);
            //	Log.e("yemianer","getPaic"+data+mGetPaichj);
            return mGetPaichj;
        } else {
            return null;
        }
    }
    public class GetPaichjTask extends AsyncTask<Void, Void, GetPaichj> {


        @Override
        protected GetPaichj doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            return GetPaichjData();
        }

        @Override
        protected void onPostExecute(GetPaichj result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            //Log.e("yemianer",":1"+result);
            if (result != null) {
                for(int i=0;i<result.getHjmss().size();i++){

                    String hjms = result.getHjmss().get(i).trim();

                    if(hjms.equals("质量呼叫")){
                        main_lly_quality.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart1 = false;
                        hjjlh1 = ShareUtils.getHjjlh1(yemianer2.this);
                    }
                    if(hjms.equals("调试呼叫")){
                        main_lly_debug.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart2 = false;
                        hjjlh2 = ShareUtils.getHjjlh2(yemianer2.this);
                    }
                    if(hjms.equals("设备呼叫")){
                        main_lly_equipment.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart3 = false;
                        hjjlh3 = ShareUtils.getHjjlh3(yemianer2.this);
                    }
                    if(hjms.equals("工程呼叫")){
                        main_lly_engineering.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart4 = false;
                        hjjlh4 = ShareUtils.getHjjlh4(yemianer2.this);
                    }
                    if(hjms.equals("物料呼叫")){
                        main_lly_materiel.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart5 = false;
                        hjjlh5 = ShareUtils.getHjjlh5(yemianer2.this);
                    }
                    if(hjms.equals("安全呼叫")){
                        main_lly_safety.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart6 = false;
                        hjjlh6 = ShareUtils.getHjjlh6(yemianer2.this);
                    }
                    if(hjms.equals("主管呼叫")){
                        main_lly_areamanager.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart7 = false;
                        hjjlh7 = ShareUtils.getHjjlh7(yemianer2.this);
                    }
                    if(hjms.equals("经理呼叫")){
                        main_lly_generalmanager.setBackgroundColor(Color.parseColor("#ff0000"));
                        isStart8 = false;
                        hjjlh8 = ShareUtils.getHjjlh8(yemianer2.this);
                    }
                    //Log.e("yemianer",":"+result.getHjmss().get(i)+":");
                }
            }

        }
    }
    public List<ConfirmChanxdm> loadStartHjData(String hjms) {
        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();

        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcid>"
                + pcid
                + "</pcid><hjms>"
                + hjms
                + "</hjms></param></data></root>";
        String method_name = "StartHjjl";

        String data = RequestDataUtil.callMethod(mUsername, mUserpwd, param,
                method_name, ip);
        if (!data.equals("")&&data.contains("root")) {
            data = RequestDataUtil.splieRequestXml(data);
            startHj = RequestDataUtil.parseConfirmXml(data);
            return startHj;
        }
        return null;
    }
    public Shouqjs loadProData( int pcclid,String tm) {
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcclid>"
                + pcclid
                + "</pcclid><tm>"
                + tm
                + "</tm></param></data></root>";
        String method_name = "GetShouqjs";
        String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
                method_name, ip);
        if (!data.equals("")&&data.contains("root")) {

            data = RequestDataUtil.splieRequestXml(data);
            return RequestDataUtil.parseShouqjs(data);

        } else {
            return null;
        }
    }
    public Shouqjs loadProData1( int pcclid,String tm) {
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcclid>"
                + pcclid
                + "</pcclid><tm>"
                + tm
                + "</tm></param></data></root>";
        String method_name = "GetChanpsm";
        String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
                method_name, ip);
        if (!data.equals("")&&data.contains("root")) {

            data = RequestDataUtil.splieRequestXml(data);
            return RequestDataUtil.parseShouqjs(data);

        } else {
            return null;
        }
    }
    public Shouqjs loadUnlockProData( String rydm) {
        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
        test1 = pcid;
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcid>"
                + pcid
                + "</pcid><rydm>"
                + rydm
                + "</rydm></param></data></root>";
        String method_name = "GetShouqjs";
        String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd, param,
                method_name, ip);
        test2 = data;
        if (!data.equals("")&&data.contains("root")) {

            data = RequestDataUtil.splieRequestXml(data);
            return RequestDataUtil.parseShouqjs(data);

        } else {
            return null;
        }
    }
    public List<ConfirmChanxdm> loadEndHjData(String hjms, String gh,String hjjlh) {
        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcid>"
                + pcid
                + "</pcid><hjjlh>"
                + hjjlh
                + "</hjjlh><gh>"
                + gh
                + "</gh></param></data></root>";
        String method_name = "FinishHjjl";
        String data = RequestDataUtil.callMethod(mUsername, mUserpwd, param,
                method_name, ip);
        if (!data.equals("")&&data.contains("root")) {
            data = RequestDataUtil.splieRequestXml(data);
            endHj = RequestDataUtil.parseConfirmXml(data);
            return endHj;
        } else {
            return null;
        }
    }
    public class SavePaicTask extends AsyncTask<Void , Void, List<ConfirmChanxdm>>{
        private int flag;
        private int id;
        private String ip;
        private View v;
        private String pcclId;
        private String number;
        public SavePaicTask(int flag, int id, String ip, View v,
                            String pcclId, String number){
            this.flag = flag;
            this.id = id;
            this.ip = ip;
            this.v = v;
            this.pcclId = pcclId;
            this.number = number;
        }
        @Override
        protected List<ConfirmChanxdm> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            return loadSavePaicWCSLData1( flag,  id,  ip,  v,
                    pcclId,  number);
        }
        @Override
        protected void onPostExecute(List<ConfirmChanxdm> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(result!=null){
                SaveWcsl sw = new SaveWcsl();
                sw.setView(v);
                sw.setId(id);
                try {
                    sw.setNumber(Integer.parseInt(number));
                } catch (Exception e) {

                }
                sw.setFlag(flag);

                boolean isSave = savePaicList.get(0).getResult().equals("1");
                //Toast.makeText(yemianer2.this, isSave+"", Toast.LENGTH_SHORT).show();
                //sw.getView().setVisibility(View.VISIBLE);
                int number1 = 0;
                int number2 = 0;
                if (isSave) {
                    if (sw.getFlag() == 0) {
                        if (textRealproList1.get(sw.getId()).getText()
                                .toString().equals("")) {
                            number1 = 0;
                        } else {
                            number1 = Integer.parseInt(textRealproList1
                                    .get(sw.getId()).getText().toString());
                        }
                        textRealproList1.get(sw.getId()).setText(
                                sw.getNumber() + "");
                        String palnStr = textPlanproList1.get(sw.getId())
                                .getText().toString();
                        int plan = Integer.parseInt(palnStr);

                        if (plan == 0) {
                            if (textSumratioList1.get(sw.getId()).toString()
                                    .equals("")) {
                                sumNumber1++;
                            } else {

                            }

                            textSumratioList1.get(sw.getId())
                                    .setText("100.00%");
                            textSumratioList1.get(sw.getId()).setTextColor(
                                    Color.parseColor("#000000"));

                        } else {
                            double sumRatio = (double) (sw.getNumber() * 1.0
                                    / plan * 100);
                            if (sw.getNumber() == 0) {
                                if (textSumratioList1.get(sw.getId())
                                        .toString().equals("")) {
                                    sumNumber1++;
                                } else {

                                }
                                textSumratioList1.get(sw.getId()).setText(
                                        "0.00%");
                                textSumratioList1.get(sw.getId()).setTextColor(
                                        Color.parseColor("#ffc000"));

                            } else {
                                if (textSumratioList1.get(sw.getId())
                                        .toString().equals("")) {
                                    sumNumber1++;
                                } else {

                                }
                                textSumratioList1.get(sw.getId()).setText(
                                        new java.text.DecimalFormat("#.00")
                                                .format(sumRatio) + "%");
                                if (sumRatio < 100.00) {
                                    textSumratioList1
                                            .get(sw.getId())
                                            .setTextColor(
                                                    Color.parseColor("#ffc000"));
                                } else {
                                    textSumratioList1
                                            .get(sw.getId())
                                            .setTextColor(
                                                    Color.parseColor("#000000"));
                                }
                            }
                        }
                        int count1 = 0;
                        for (int i = textSumratioList1.size() - 1; i >= 0; i--) {
                            String sum = textSumratioList1.get(i).getText()
                                    .toString();
                            if (!sum.equals("")) {
                                count1 = i;
                                break;
                            }
                        }
                        // 处理实际产量修改
                        int number = Integer.parseInt(textRealproList1
                                .get(count1).getText().toString());
                        textRealproList1.get(count1).setText(
                                number - number1 + sw.getNumber() + "");
                        // String sum =
                        // textSumratioList1.get(count).getText().toString();
                        // 处理产量百分比修改
                        String sumRatioStr = textSumratioList1.get(count1)
                                .getText().toString();
                        String sumRatioSub = sumRatioStr.substring(0,
                                sumRatioStr.length() - 1);
                        double sumRatio = Double.parseDouble(sumRatioSub);

                        double sumRatioH3 = 0.00;
                        int sumNumber3 = 0;
                        for (int i = 0; i < count1; i++) {
                            String sumRatioStr3 = textSumratioList1.get(i)
                                    .getText().toString();
                            String sumRatioSub3 = "";

                            if (sumRatioStr3.equals("")) {

                            } else {
                                sumNumber3++;
                                sumRatioSub3 = sumRatioStr3.substring(0,
                                        sumRatioStr3.length() - 1);
                                sumRatioH3 += Double.parseDouble(sumRatioSub3);
                            }
                            if (i == count1 - 1) {

                                if (sumNumber3 == 0 || sumRatioH3 == 0) {
                                    textSumratioList1.get(i + 1).setText(
                                            "0.00%");
                                    textSumratioList1.get(i + 1).setTextColor(
                                            Color.parseColor("#ffc000"));

                                } else {
                                    int planSum = Integer
                                            .parseInt(textPlanproList1
                                                    .get(i + 1).getText()
                                                    .toString());
                                    int realSum = Integer
                                            .parseInt(textRealproList1
                                                    .get(i + 1).getText()
                                                    .toString());

                                    textSumratioList1
                                            .get(i + 1)
                                            .setText(
                                                    new java.text.DecimalFormat(
                                                            "#0.00")
                                                            .format(((realSum * 1.0 / planSum) * 100))
                                                            + "%");
                                    if ((realSum / planSum) < 1) {
                                        textSumratioList1
                                                .get(i + 1)
                                                .setTextColor(
                                                        Color.parseColor("#ffc000"));
                                    } else {
                                        textSumratioList1
                                                .get(i + 1)
                                                .setTextColor(
                                                        Color.parseColor("#000000"));

                                    }
                                }
                            }
                        }

                    } else if (sw.getFlag() == 1) {
                        if (textRealproList2.get(sw.getId()).getText()
                                .toString().equals("")) {
                            number2 = 0;
                        } else {
                            number2 = Integer.parseInt(textRealproList2
                                    .get(sw.getId()).getText().toString());
                        }
                        textRealproList2.get(sw.getId()).setText(
                                sw.getNumber() + "");
                        String palnStr = textPlanproList2.get(sw.getId())
                                .getText().toString();
                        int plan = Integer.parseInt(palnStr);

                        if (plan == 0) {
                            if (textSumratioList2.get(sw.getId()).toString()
                                    .equals("")) {
                                sumNumber1++;
                            } else {

                            }

                            textSumratioList2.get(sw.getId())
                                    .setText("100.00%");
                            textSumratioList2.get(sw.getId()).setTextColor(
                                    Color.parseColor("#000000"));

                        } else {
                            double sumRatio = (double) (sw.getNumber() * 1.0
                                    / plan * 100);
                            if (sw.getNumber() == 0) {
                                if (textSumratioList2.get(sw.getId())
                                        .toString().equals("")) {
                                    sumNumber1++;
                                } else {

                                }
                                textSumratioList2.get(sw.getId()).setText(
                                        "0.00%");
                                textSumratioList2.get(sw.getId()).setTextColor(
                                        Color.parseColor("#ffc000"));

                            } else {
                                if (textSumratioList2.get(sw.getId())
                                        .toString().equals("")) {
                                    sumNumber1++;
                                } else {

                                }
                                // if
                                textSumratioList2.get(sw.getId()).setText(
                                        new java.text.DecimalFormat("#.00")
                                                .format(sumRatio) + "%");
                                if (sumRatio < 100.00) {
                                    textSumratioList2
                                            .get(sw.getId())
                                            .setTextColor(
                                                    Color.parseColor("#ffc000"));

                                } else {
                                    textSumratioList2
                                            .get(sw.getId())
                                            .setTextColor(
                                                    Color.parseColor("#000000"));

                                }
                            }
                        }
                        int count1 = 0;
                        for (int i = textSumratioList2.size() - 1; i >= 0; i--) {
                            String sum = textSumratioList2.get(i).getText()
                                    .toString();
                            if (!sum.equals("")) {
                                count1 = i;
                                break;
                            }
                        }
                        // 处理实际产量修改
                        int number = Integer.parseInt(textRealproList2
                                .get(count1).getText().toString());
                        textRealproList2.get(count1).setText(
                                number - number2 + sw.getNumber() + "");
                        // String sum =
                        // textSumratioList1.get(count).getText().toString();
                        // 处理产量百分比修改
                        String sumRatioStr = textSumratioList2.get(count1)
                                .getText().toString();
                        String sumRatioSub = sumRatioStr.substring(0,
                                sumRatioStr.length() - 1);
                        double sumRatio = Double.parseDouble(sumRatioSub);

                        double sumRatioH3 = 0.00;
                        int sumNumber3 = 0;
                        for (int i = 0; i < count1; i++) {
                            String sumRatioStr3 = textSumratioList2.get(i)
                                    .getText().toString();
                            String sumRatioSub3 = "";

                            if (sumRatioStr3.equals("")) {

                            } else {
                                sumNumber3++;
                                sumRatioSub3 = sumRatioStr3.substring(0,
                                        sumRatioStr3.length() - 1);
                                sumRatioH3 += Double.parseDouble(sumRatioSub3);
                            }
                            if (i == count1 - 1) {

                                if (sumNumber3 == 0) {
                                    textSumratioList2.get(i + 1).setText(
                                            "0.00%");
                                    textSumratioList2.get(i + 1).setTextColor(
                                            Color.parseColor("#ffc000"));

                                } else {
                                    int planSum = Integer
                                            .parseInt(textPlanproList2
                                                    .get(i + 1).getText()
                                                    .toString());
                                    int realSum = Integer
                                            .parseInt(textRealproList2
                                                    .get(i + 1).getText()
                                                    .toString());

                                    textSumratioList2.get(i + 1).setText(
                                            new java.text.DecimalFormat("#.00")
                                                    .format((realSum * 1.0
                                                            / planSum * 100))
                                                    + "%");
                                    if (realSum / planSum < 1) {
                                        textSumratioList2
                                                .get(i + 1)
                                                .setTextColor(
                                                        Color.parseColor("#ffc000"));

                                    } else {
                                        textSumratioList2
                                                .get(i + 1)
                                                .setTextColor(
                                                        Color.parseColor("#000000"));

                                    }
                                }
                            }
                        }

                    }
                }
            }else{
                Toast.makeText(yemianer2.this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public List<ConfirmChanxdm>  loadSavePaicWCSLData1(int flag, int id, String ip, View v,
                                                       String pcclId, String number) {
        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcid>"
                + pcid
                + "</pcid><records recordcount=\"1\"><record><pcclid>"
                + pcclId
                + "</pcclid><wcsl>"
                + number
                + "</wcsl></record></records></param></data></root>";
        String method_name = "SavePaicWCSL";
        String data = RequestDataUtil.callMethod(mUsername, mUserpwd, param,
                method_name, ip);

        if (!data.equals("")&&data.contains("root")) {

            data = RequestDataUtil.splieRequestXml(data);
            savePaicList = RequestDataUtil.parseConfirmXml(data);
            return savePaicList;
//			Message msg = mHandler.obtainMessage();
//			msg.what = Constant.REQUEST_SAVEPAICWCSL_DATA;
//			SaveWcsl sw = new SaveWcsl();
//			sw.setView(v);
//			sw.setId(id);
//			try {
//				sw.setNumber(Integer.parseInt(number));
//			} catch (Exception e) {
//
//			}
//			sw.setFlag(flag);
//			msg.obj = sw;
//			mHandler.sendMessage(msg);
//		} else {
//			Message msg = mHandler.obtainMessage();
//			msg.what = Constant.REQUEST_SAVEPAICWCSL_ERROR;
//			msg.obj = v;
//			mHandler.sendMessage(msg);
        }
        return null;
    }

    public void loadSavePaicWCSLData(int flag, int id, String ip, View v,
                                     String pcclId, String number) {
        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcid>"
                + pcid
                + "</pcid><records recordcount=\"1\"><record><pcclid>"
                + pcclId
                + "</pcclid><wcsl>"
                + number
                + "</wcsl></record></records></param></data></root>";
        String method_name = "SavePaicWCSL";
        String data = RequestDataUtil.callMethod(mUsername, mUserpwd, param,
                method_name, ip);

        if (!data.equals("")&&data.contains("root")) {

            data = RequestDataUtil.splieRequestXml(data);
            savePaicList = RequestDataUtil.parseConfirmXml(data);
            Message msg = mHandler.obtainMessage();
            msg.what = Constant.REQUEST_SAVEPAICWCSL_DATA;
            SaveWcsl sw = new SaveWcsl();
            sw.setView(v);
            sw.setId(id);
            try {
                sw.setNumber(Integer.parseInt(number));
            } catch (Exception e) {

            }
            sw.setFlag(flag);
            msg.obj = sw;
            mHandler.sendMessage(msg);
        } else {
            Message msg = mHandler.obtainMessage();
            msg.what = Constant.REQUEST_SAVEPAICWCSL_ERROR;
            msg.obj = v;
            mHandler.sendMessage(msg);
        }
    }

    private List<Chanxxx> loadChanxxxData() {
        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <cxdm>"
                + mUsername + "</cxdm></param></data></root>";
        String method_name = "GetChanxxx";
        String data = RequestDataUtil.callMethod(mUsername, mUserpwd, param,
                method_name, ip);
        if (!data.equals("")&&data.contains("root")) {
            data = RequestDataUtil.splieRequestXml(data);
            try {
                 mChanList = RequestDataUtil.parseGetChanxxxXml(data);
                return mChanList;
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("yeminaer2","已经异常终结了");
                return null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
    private class paicTask extends AsyncTask<Void,Void,List<GetPaic>>{

        @Override
        protected List<GetPaic> doInBackground(Void... params) {
            return loadGetPaicData();
        }

        @Override
        protected void onPostExecute(List<GetPaic> result) {
            super.onPostExecute(result);
            if(result!=null){
                mGetPaics = result;
                Log.e("text",test);
                dealPaic();

                //排产呼叫
                new GetPaichjTask().execute();
            }

        }
    }
    private List<GetPaic> loadGetPaicData() {

        String pcid = mGetPaicLists.get(mSingleChoiceID).getPcid();
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <pcid>"
                + pcid + "</pcid></param></data></root>";
        String method_name = "GetPaic";
        String data = RequestDataUtil.callMethod(mUsername, mUserpwd, param,
                method_name, ip);


        if (!data.equals("")&&data.contains("root")) {
            data = RequestDataUtil.splieRequestXml(data);
            test = data;
            try {
                return RequestDataUtil.parseGetPaicXml(data);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
    class LoadPaicListTask extends AsyncTask<Void,Void,GetPaicList>{
        private String username;
        private String userpwd;
        public LoadPaicListTask(String username,String userpwd){
            this.username = username;
            this.userpwd = userpwd;
        }
        @Override
        protected GetPaicList doInBackground(Void... params) {
            return loadPaicListData(username,userpwd);
        }

        @Override
        protected void onPostExecute(GetPaicList result) {
            super.onPostExecute(result);
            if(result!=null&&result.getResult()!=null&&result.getResult().equals("1")){
                mGetPaicLists = result.getPaicLists();
                initData();
            }else{
                Toast.makeText(yemianer2.this,"服务连接错误,请重试",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    public GetPaicList loadPaicListData(final String username, final String userpwd) {



        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param> <username>"
                + username + "</username></param></data></root>";
        String method_name = "GetPaicList";
        String data = RequestDataUtil.callMethod(username, userpwd,
                param, method_name, ip);
        if (!data.equals("")&&data.contains("root")&&data.length()>0) {

            try {
                data = RequestDataUtil.splieRequestXml(data);
                return RequestDataUtil
                        .parseGetPaicListXml(data);
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //mHandler.sendEmptyMessage(Constant.REQUEST_DATA);
        }
//				else {
//					mHandler.sendEmptyMessage(Constant.REQUEST_ERROR);
//				}
        return null;
    }

    /**
     * 加载通知列表
     *
     * @return
     */
    private TongZis loadTongZisData() {
        TongZis tongZis;
        String param = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?><root> <data> <param></param></data></root>";
        String method_name = "GetTongzList";
        String data = RequestDataUtil.callCommonMethod(mUsername, mUserpwd,
                param, method_name, ip);
        if (!data.equals("")&&data.contains("root")) {
            data = RequestDataUtil.splieRequestXml(data);
            tongZis = RequestDataUtil.parseTongZis(data);
            return tongZis;
        }
        return null;
    }

    /**
     * 异步加载通知列表
     *
     * @author Administrator
     *
     */
    private class TongZisTask extends AsyncTask<Void, Void, TongZis> {

        @Override
        protected TongZis doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            return loadTongZisData();
        }

        @Override
        protected void onPostExecute(TongZis result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (result != null &&result.getTongZiRecordList()!=null&& result.getTongZiRecordList().size() > 0) {
                int number = 0;
                for (TongZiRecord record : result.getTongZiRecordList()) {
                    if (record.getYd()!=null&&record.getYd().equals("1")) {
                        number++;
                    }
                }
                if (number > 0) {
                    setreadFlag(number);
                } else {
                    if (readFlag != null) {
                        readFlag.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 3600000) {

        @Override
        public void onTick(long millisUntilFinished) {
            new TongZisTask().execute();
        }

        @Override
        public void onFinish() {
            // 倒计时结束
        }
    };
    //设置扫描文本框的焦点
    public CountDownTimer timerSaomiao = new CountDownTimer(Integer.MAX_VALUE,500) {
        @Override
        public void onTick(long millisUntilFinished) {
            main_edt_pro.setFocusable(true);
            main_edt_pro.setFocusableInTouchMode(true);

            main_edt_pro.requestFocus();
        }

        @Override
        public void onFinish() {

        }
    };
    //检测是否超过时间180000
    public CountDownTimer timer2 = new CountDownTimer(Integer.MAX_VALUE,180000) {

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            //Toast.makeText(yemianer2.this, tTimeNo+"gg", Toast.LENGTH_SHORT).show();

            if(tTimeNo!=-1){
                String time =  textPeroidList1.get(tTimeNo).getText().toString();
                if(!time.equals("")){
                    String times [] = time.split("-");

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "HH:mm");
                    Date nows = new Date();
                    String nowsStr = dateFormat.format(nows);
                    //nowsStr = "14:20";
                    String startTime =times[0];
                    String endTime = times[1];
                    //Toast.makeText(yemianer2.this, endTime, Toast.LENGTH_SHORT).show();
                    try {

                        long resEnd = dateFormat.parse(endTime)
                                .getTime()
                                - dateFormat.parse(nowsStr).getTime();
                        //Toast.makeText(yemianer2.this, tTimeNo+"gg"+dateFormat.parse(nowsStr).getTime()+"gg"+dateFormat.parse(endTime).getTime(), Toast.LENGTH_SHORT).show();

                        if(resEnd<0||(dateFormat.parse(nowsStr).getTime()<0&&dateFormat.parse(endTime).getTime()>0)){
                            textPeroidList1.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));
                            textPeroidList2.get(timeNo).setBackgroundColor(Color.parseColor("#ffffff"));

                            timeNo = -1;
                            proNo = -1;
                        }
                    }catch(Exception e){

                    }
                }
            }
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub

        }
    };
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        new TongZisTask().execute();

    }

    static BadgeView readFlag;

    private void setreadFlag(int number) {
        if (readFlag == null) {
            readFlag = new BadgeView(this, btn_img_daming);
        }
        readFlag.setText("" + number);
        readFlag.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        readFlag.show();
    }

    private void addListener() {
        chanxian.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(yemianer2.this, chanxianjiaohu.class);
                intent.putExtra("scrq", scrq);
                intent.putExtra("username", mUsername);
                intent.putExtra("userpwd", mUserpwd);
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        });

        btn_img_daming.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(yemianer2.this, TongZiActivity.class);
                intent.putExtra("username", mUsername);
                intent.putExtra("userpwd", mUserpwd);
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        });
        main_btn_next.setText("返回BACK");
        main_btn_next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    private void init() {
        imageList = new ArrayList<ImageView>();
        textList = new ArrayList<TextView>();
        textPeroidList1 = new ArrayList<TextView>();
        textPeroidList2 = new ArrayList<TextView>();
        textPlanproList1 = new ArrayList<TextView>();
        textPlanproList2 = new ArrayList<TextView>();
        textRealproList1 = new ArrayList<TextView>();
        textRealproList2 = new ArrayList<TextView>();
        textSumratioList1 = new ArrayList<TextView>();
        textSumratioList2 = new ArrayList<TextView>();
        main_btn_next = (Button) findViewById(R.id.main_btn_next);
        chanxian = (Button) findViewById(R.id.btn_chanxian);
        main_btn_banci = (Button) findViewById(R.id.main_btn_banci);
        btn_img1 = (ImageView) findViewById(R.id.btn_img1);
        btn_img2 = (ImageView) findViewById(R.id.btn_img2);
        btn_img3 = (ImageView) findViewById(R.id.btn_img3);
        btn_img4 = (ImageView) findViewById(R.id.btn_img4);
        btn_img5 = (ImageView) findViewById(R.id.btn_img5);
        btn_chanxian1 = (TextView) findViewById(R.id.btn_chanxian1);
        btn_chanxian2 = (TextView) findViewById(R.id.btn_chanxian2);
        btn_img_daming = (ImageView) findViewById(R.id.btn_img_daming);
        main_tv_czg1 = (TextView) findViewById(R.id.main_tv_czg1);
        main_tv_czg2 = (TextView) findViewById(R.id.main_tv_czg2);
        main_tv_czg3 = (TextView) findViewById(R.id.main_tv_czg3);
        main_tv_fzr = (TextView) findViewById(R.id.main_tv_fzr);
        main_tv_jyy = (TextView) findViewById(R.id.main_tv_jyy);

        main_rel = (RelativeLayout) findViewById(R.id.main_rel);
        main_lly = (LinearLayout) findViewById(R.id.main_lly);

        main_tv_period1 = (TextView) findViewById(R.id.main_tv_period1);
        main_tv_period2 = (TextView) findViewById(R.id.main_tv_period2);
        main_tv_period3 = (TextView) findViewById(R.id.main_tv_period3);
        main_tv_period4 = (TextView) findViewById(R.id.main_tv_period4);
        main_tv_period5 = (TextView) findViewById(R.id.main_tv_period5);
        main_tv_period6 = (TextView) findViewById(R.id.main_tv_period6);

        main_tv2_period1 = (TextView) findViewById(R.id.main_tv2_period1);
        main_tv2_period2 = (TextView) findViewById(R.id.main_tv2_period2);
        main_tv2_period3 = (TextView) findViewById(R.id.main_tv2_period3);
        main_tv2_period4 = (TextView) findViewById(R.id.main_tv2_period4);
        main_tv2_period5 = (TextView) findViewById(R.id.main_tv2_period5);
        main_tv2_period6 = (TextView) findViewById(R.id.main_tv2_period6);

        main_tv_planpro1 = (TextView) findViewById(R.id.main_tv_planpro1);
        main_tv_planpro2 = (TextView) findViewById(R.id.main_tv_planpro2);
        main_tv_planpro3 = (TextView) findViewById(R.id.main_tv_planpro3);
        main_tv_planpro4 = (TextView) findViewById(R.id.main_tv_planpro4);
        main_tv_planpro5 = (TextView) findViewById(R.id.main_tv_planpro5);
        main_tv_planpro6 = (TextView) findViewById(R.id.main_tv_planpro6);

        main_tv2_planpro1 = (TextView) findViewById(R.id.main_tv2_planpro1);
        main_tv2_planpro2 = (TextView) findViewById(R.id.main_tv2_planpro2);
        main_tv2_planpro3 = (TextView) findViewById(R.id.main_tv2_planpro3);
        main_tv2_planpro4 = (TextView) findViewById(R.id.main_tv2_planpro4);
        main_tv2_planpro5 = (TextView) findViewById(R.id.main_tv2_planpro5);
        main_tv2_planpro6 = (TextView) findViewById(R.id.main_tv2_planpro6);

        main_tv_realpro1 = (TextView) findViewById(R.id.main_tv_realpro1);
        main_tv_realpro2 = (TextView) findViewById(R.id.main_tv_realpro2);
        main_tv_realpro3 = (TextView) findViewById(R.id.main_tv_realpro3);
        main_tv_realpro4 = (TextView) findViewById(R.id.main_tv_realpro4);
        main_tv_realpro5 = (TextView) findViewById(R.id.main_tv_realpro5);
        main_tv_realpro6 = (TextView) findViewById(R.id.main_tv_realpro6);

        main_tv2_realpro1 = (TextView) findViewById(R.id.main_tv2_realpro1);
        main_tv2_realpro2 = (TextView) findViewById(R.id.main_tv2_realpro2);
        main_tv2_realpro3 = (TextView) findViewById(R.id.main_tv2_realpro3);
        main_tv2_realpro4 = (TextView) findViewById(R.id.main_tv2_realpro4);
        main_tv2_realpro5 = (TextView) findViewById(R.id.main_tv2_realpro5);
        main_tv2_realpro6 = (TextView) findViewById(R.id.main_tv2_realpro6);

        main_tv_sumratio1 = (TextView) findViewById(R.id.main_tv_sumratio1);
        main_tv_sumratio2 = (TextView) findViewById(R.id.main_tv_sumratio2);
        main_tv_sumratio3 = (TextView) findViewById(R.id.main_tv_sumratio3);
        main_tv_sumratio4 = (TextView) findViewById(R.id.main_tv_sumratio4);
        main_tv_sumratio5 = (TextView) findViewById(R.id.main_tv_sumratio5);
        main_tv_sumratio6 = (TextView) findViewById(R.id.main_tv_sumratio6);

        main_tv2_sumratio1 = (TextView) findViewById(R.id.main_tv2_sumratio1);
        main_tv2_sumratio2 = (TextView) findViewById(R.id.main_tv2_sumratio2);
        main_tv2_sumratio3 = (TextView) findViewById(R.id.main_tv2_sumratio3);
        main_tv2_sumratio4 = (TextView) findViewById(R.id.main_tv2_sumratio4);
        main_tv2_sumratio5 = (TextView) findViewById(R.id.main_tv2_sumratio5);
        main_tv2_sumratio6 = (TextView) findViewById(R.id.main_tv2_sumratio6);

        main_btn_pcmc = (Button) findViewById(R.id.main_btn_cxmc);
        main_lly_quality = (LinearLayout) findViewById(R.id.main_lly_quality);
        main_lly_debug = (LinearLayout) findViewById(R.id.main_lly_debug);
        main_lly_equipment = (LinearLayout) findViewById(R.id.main_lly_equipment);
        main_lly_engineering = (LinearLayout) findViewById(R.id.main_lly_engineering);
        main_lly_materiel = (LinearLayout) findViewById(R.id.main_lly_materiel);
        main_lly_safety = (LinearLayout) findViewById(R.id.main_lly_safety);
        main_lly_areamanager = (LinearLayout) findViewById(R.id.main_lly_areamanager);
        main_lly_generalmanager = (LinearLayout) findViewById(R.id.main_lly_generalmanager);

        main_lly_quality.setOnClickListener(this);
        main_lly_debug.setOnClickListener(this);
        main_lly_equipment.setOnClickListener(this);
        main_lly_engineering.setOnClickListener(this);
        main_lly_materiel.setOnClickListener(this);
        main_lly_safety.setOnClickListener(this);
        main_lly_areamanager.setOnClickListener(this);
        main_lly_generalmanager.setOnClickListener(this);

        imageList.add(btn_img3);
        imageList.add(btn_img4);
        imageList.add(btn_img5);

        textList.add(main_tv_czg1);
        textList.add(main_tv_czg2);
        textList.add(main_tv_czg3);

        textPeroidList1.add(main_tv_period1);
        textPeroidList1.add(main_tv_period2);
        textPeroidList1.add(main_tv_period3);
        textPeroidList1.add(main_tv_period4);
        textPeroidList1.add(main_tv_period5);
        textPeroidList1.add(main_tv_period6);

        textPeroidList2.add(main_tv2_period1);
        textPeroidList2.add(main_tv2_period2);
        textPeroidList2.add(main_tv2_period3);
        textPeroidList2.add(main_tv2_period4);
        textPeroidList2.add(main_tv2_period5);
        textPeroidList2.add(main_tv2_period6);

        textPlanproList1.add(main_tv_planpro1);
        textPlanproList1.add(main_tv_planpro2);
        textPlanproList1.add(main_tv_planpro3);
        textPlanproList1.add(main_tv_planpro4);
        textPlanproList1.add(main_tv_planpro5);
        textPlanproList1.add(main_tv_planpro6);

        textPlanproList2.add(main_tv2_planpro1);
        textPlanproList2.add(main_tv2_planpro2);
        textPlanproList2.add(main_tv2_planpro3);
        textPlanproList2.add(main_tv2_planpro4);
        textPlanproList2.add(main_tv2_planpro5);
        textPlanproList2.add(main_tv2_planpro6);

        textRealproList1.add(main_tv_realpro1);
        textRealproList1.add(main_tv_realpro2);
        textRealproList1.add(main_tv_realpro3);
        textRealproList1.add(main_tv_realpro4);
        textRealproList1.add(main_tv_realpro5);
        textRealproList1.add(main_tv_realpro6);

        textRealproList2.add(main_tv2_realpro1);
        textRealproList2.add(main_tv2_realpro2);
        textRealproList2.add(main_tv2_realpro3);
        textRealproList2.add(main_tv2_realpro4);
        textRealproList2.add(main_tv2_realpro5);
        textRealproList2.add(main_tv2_realpro6);

        textSumratioList1.add(main_tv_sumratio1);
        textSumratioList1.add(main_tv_sumratio2);
        textSumratioList1.add(main_tv_sumratio3);
        textSumratioList1.add(main_tv_sumratio4);
        textSumratioList1.add(main_tv_sumratio5);
        textSumratioList1.add(main_tv_sumratio6);

        textSumratioList2.add(main_tv2_sumratio1);
        textSumratioList2.add(main_tv2_sumratio2);
        textSumratioList2.add(main_tv2_sumratio3);
        textSumratioList2.add(main_tv2_sumratio4);
        textSumratioList2.add(main_tv2_sumratio5);
        textSumratioList2.add(main_tv2_sumratio6);

        ip = ShareUtils.getIp(yemianer2.this);

        main_tv_flag1 = (TextView) findViewById(R.id.main_tv_flag1);
        main_tv_flag2 = (TextView) findViewById(R.id.main_tv_flag2);

        main_edt_pro = (EditText) findViewById(R.id.main_edt_pro);
        main_edt_pro.setInputType(InputType.TYPE_NULL);


        TextWatcher watcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = main_edt_pro.getText().toString().trim();

                //9位数字
                if(content.length()==2){
                    char one = content.charAt(0);
                    char two = content.charAt(1);
                    boolean oneChar = Character.isDigit(one);
                    boolean twoChar = Character.isDigit(two);
                    if(oneChar){
                        index = 12;
                    }else if(twoChar){
                        index = 9;
                    }else{
                        index = 10;
                    }
                }

                if(timeNo==-1){
                    //main_edt_pro = (EditText) findViewById(R.id.main_edt_pro);
                    // Toast.makeText(yemianer2.this,"请选择生产时段",Toast.LENGTH_SHORT).show();
                    // index = 0;
                } else if (index!=-1&&content.length()==index) {
                    Toast.makeText(yemianer2.this, "现在的输入:"+content, Toast.LENGTH_SHORT).show();
                    main_edt_pro.setText("");
                    index = -1;
                    ProTask proTask = new ProTask(pcclid, content);
                    proTask.execute();



//				   main_edt_pro.setFocusable(true);
//				   main_edt_pro.setFocusableInTouchMode(true);
//
//				   main_edt_pro.requestFocus();

                }
            }
        };
        main_edt_pro.addTextChangedListener(watcher);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        if (timer2 != null) {
            timer2.cancel();
        }
        if(timerSaomiao!=null){
            timerSaomiao.cancel();
        }
        isStartTimer = true;
        readFlag = null;
    }

    public void showbagPopWindow(View parent) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View vPopWindow = inflater.inflate(R.layout.hujiaojilu, null,
                false);
        DisplayMetrics dm = new DisplayMetrics();

        // 获取屏幕信息
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int screenWidth = dm.widthPixels;

        int screenHeigh = dm.heightPixels;

        final PopupWindow popWindow = new PopupWindow(vPopWindow,
                screenWidth - 20, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //

        // 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 点击空白处时，隐藏掉pop窗口
        popWindow.setFocusable(true);
        popWindow.setBackgroundDrawable(getResources().getDrawable(
                android.R.color.transparent));
        // 设置透明度
        // backgroundAlpha(0.3f);

        // 添加pop窗口关闭事件
        popWindow.setOnDismissListener(new poponDismissListener());
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            // Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

}
