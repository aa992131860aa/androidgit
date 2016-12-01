package com.daming.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.entity.Px;
import com.daming.entity.Renypx;
import com.example.daming_text.R;

public class PeixunjiluAdapter extends BaseAdapter{
	private List<Px> mList;
	private Context mContext;
   public PeixunjiluAdapter(Context context,List<Px> list){
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.peixun_item, null);
		   TextView peiitem_tv_traindate = (TextView) convertView.findViewById(R.id.peiitem_tv_traindate);
		   TextView peiitem_tv_trainbrief = (TextView) convertView.findViewById(R.id.peiitem_tv_trainbrief);
		   TextView peiitem_tv_trainteacher = (TextView) convertView.findViewById(R.id.peiitem_tv_trainteacher);
		   TextView peiitem_tv_trainresult = (TextView) convertView.findViewById(R.id.peiitem_tv_trainresult);
		 
		   peiitem_tv_traindate.setText(mList.get(arg0).getPxrq());
		   peiitem_tv_trainbrief.setText(mList.get(arg0).getPxnr());
		   peiitem_tv_trainteacher.setText(mList.get(arg0).getPxjs());
		   peiitem_tv_trainresult.setText(mList.get(arg0).getPxjg());

		}
		return convertView;
	}
    public void refresh(List<Px> list){
    	mList = list;
    	notifyDataSetChanged();
    }
}
