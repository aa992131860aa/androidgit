package com.daming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daming_text.R;

import java.util.List;

public class JianyanListAdapter extends BaseAdapter {
	private final Context context;
	private List<String> mList;
	public JianyanListAdapter(Context context, List<String> list) {
		this.context = context;
		mList = list;
	}

	public int getCount() {
		return mList.size();
	}

	public long getItemId(int i) {
		return i;
	}

	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(
					R.layout.anquan_list_item, viewGroup, false);
			holder.anquan_tv_tzid = (TextView) view
					.findViewById(R.id.anquani_tv_tzid);
			holder.anquan_tv_bt = (TextView) view
					.findViewById(R.id.anquani_tv_bt);
		
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		if (mList != null && mList.size() > 0) {
			String tz = mList.get(i);
			
			holder.anquan_tv_tzid.setText((i+1)+"");
			int lastOf = tz.lastIndexOf("/");
			String srcPdf = tz.substring(lastOf+1, tz.length());
			holder.anquan_tv_bt.setText(srcPdf);
			
		}

		return view;
	}

	class ViewHolder {
		TextView anquan_tv_tzid;
		TextView anquan_tv_bt;
		TextView anquan_tv_fbbm;
		TextView anquan_tv_tzsj;
		ImageView anquan_tv_flag;
	}

	public void refresh(List<String> list) {
        if(list.size()>0){
        
        }
		mList = list;
		notifyDataSetChanged();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

}
