package com.daming.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daming.entity.TongZiRecord;
import com.example.daming_text.R;

public class TongZiListAdapter extends BaseAdapter {
	private final Context context;
	private List<TongZiRecord> mList;
	public TongZiListAdapter(Context context, List<TongZiRecord> list) {
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
					R.layout.tongzi_list_item, viewGroup, false);
			holder.browser_tv_tzid = (TextView) view
					.findViewById(R.id.browser_tv_tzid);
			holder.browser_tv_bt = (TextView) view
					.findViewById(R.id.browser_tv_bt);
			holder.browser_tv_fbbm = (TextView) view
					.findViewById(R.id.browser_tv_fbbm);
			holder.browser_tv_tzsj = (TextView) view
					.findViewById(R.id.browser_tv_tzsj);
			holder.browser_tv_flag = (ImageView) view
					.findViewById(R.id.browser_tv_flag);
			
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		if (mList != null && mList.size() > 0) {
			TongZiRecord tz = mList.get(i);
			if (tz.getYd().equals("1")) {
				holder.browser_tv_flag.setImageResource(R.drawable.dark_dot);
			}else{
				holder.browser_tv_flag.setImageResource(android.R.color.transparent);

			}
			holder.browser_tv_tzid.setText((i+1)+"");
			holder.browser_tv_bt.setText(tz.getBt());
			holder.browser_tv_fbbm.setText(tz.getFbbm());
			holder.browser_tv_tzsj.setText(tz.getTzsj());
		}

		return view;
	}

	class ViewHolder {
		TextView browser_tv_tzid;
		TextView browser_tv_bt;
		TextView browser_tv_fbbm;
		TextView browser_tv_tzsj;
		ImageView browser_tv_flag;
	}

	public void refresh(List<TongZiRecord> list) {
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
