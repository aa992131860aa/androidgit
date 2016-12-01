package com.daming.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.entity.Cpxx;
import com.daming.entity.Px;
import com.daming.entity.Renypx;
import com.example.daming_text.R;

public class ChanxianxinxiAdapter extends BaseAdapter{
	private List<Cpxx> mList;
	private Context mContext;
   public ChanxianxinxiAdapter(Context context,List<Cpxx> list){
	   mList = list;
	   mContext = context;
   }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
			return mList.size();
	
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
	
		return mList.get(arg0);
		
		
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		if(mList.size()>0){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.chanxijibenxinxi_item, null);
		   TextView jibenitem_tv_procore = (TextView) convertView.findViewById(R.id.jibenitem_tv_procore);
		   TextView jibenitem_tv_proname = (TextView) convertView.findViewById(R.id.jibenitem_tv_proname);
		   TextView jibenitem_tv_mode = (TextView) convertView.findViewById(R.id.jibenitem_tv_mode);
		   TextView jibenitem_tv_promode = (TextView) convertView.findViewById(R.id.jibenitem_tv_promode);
		   TextView jibenitem_tv_tap1 = (TextView) convertView.findViewById(R.id.jibenitem_tv_tap1);
		   TextView jibenitem_tv_persion1 = (TextView) convertView.findViewById(R.id.jibenitem_tv_persion1);
           TextView jibenitem_tv_name = (TextView) convertView.findViewById(R.id.jibenitem_tv_name);
		   
           Cpxx cpxx = mList.get(arg0);
           jibenitem_tv_procore.setText(cpxx.getCpdm());
           jibenitem_tv_proname.setText(cpxx.getCpmc());
           jibenitem_tv_mode.setText(cpxx.getGgxh());
           jibenitem_tv_promode.setText(cpxx.getGxfl());
           jibenitem_tv_name.setText(cpxx.getScgx());
           jibenitem_tv_tap1.setText(cpxx.getJp());
           jibenitem_tv_persion1.setText(cpxx.getRs());
		}
		return convertView;
	}
    public void refresh(List<Cpxx> list){
    	mList = list;
    	notifyDataSetChanged();
    }
}
