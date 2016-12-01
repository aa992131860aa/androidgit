package com.daming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daming.entity.Ldjl;
import com.daming.entity.Px;
import com.example.daming_text.R;

import java.util.List;

public class LaodongjilvAdapter extends BaseAdapter{
	private List<Ldjl> mList;
	private Context mContext;
   public LaodongjilvAdapter(Context context, List<Ldjl> list){
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.laodong_item, null);
		   TextView laodong_tv_date = (TextView) convertView.findViewById(R.id.laodong_tv_date);
		   TextView laodong_tv_content = (TextView) convertView.findViewById(R.id.laodong_tv_content);
		   TextView laodong_tv_method = (TextView) convertView.findViewById(R.id.laodong_tv_method);
		   TextView laodong_tv_person = (TextView) convertView.findViewById(R.id.laodong_tv_person);

			laodong_tv_date.setText(mList.get(arg0).getRq());
			laodong_tv_content.setText(mList.get(arg0).getWftl());
			laodong_tv_method.setText(mList.get(arg0).getClcs());
			laodong_tv_person.setText(mList.get(arg0).getClr());

		}
		return convertView;
	}
    public void refresh(List<Ldjl> list){
    	mList = list;
    	notifyDataSetChanged();
    }
}
