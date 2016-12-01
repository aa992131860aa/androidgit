package com.daming.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daming.entity.Pcxx;
import com.daming.entity.Px;
import com.daming.entity.Renypx;
import com.example.daming_text.R;

public class ShengchanPCAdapter extends BaseAdapter {
	private List<Pcxx> mList;
	private Context mContext;
	private String mProLineCode;
	private String scrq;

	public ShengchanPCAdapter(Context context, List<Pcxx> list) {
		mList = list;
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return mList.size();

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return mList.get(position);

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		MyHolder holder = null;
		if (convertView == null) {
			holder = new MyHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.progress_item, null);
			holder.progress_tv_classcore = (TextView) convertView
					.findViewById(R.id.progress_tv_classcore);
			holder.progress_tv_prolinecode = (TextView) convertView
					.findViewById(R.id.progress_tv_prolinecode);
			holder.progress_tv_procode = (TextView) convertView
					.findViewById(R.id.progress_tv_procode);
			holder.progress_tv_proname = (TextView) convertView
					.findViewById(R.id.progress_tv_proname);
			holder.progress_tv_sizemode = (TextView) convertView
					.findViewById(R.id.progress_tv_sizemode);
			holder.progress_tv_promethod = (TextView) convertView
					.findViewById(R.id.progress_tv_promethod);
			holder.progress_tv_plannumber1 = (TextView) convertView
					.findViewById(R.id.progress_tv_plannumber);
			
			holder.progress_tv_gxmc1 = (TextView) convertView
					.findViewById(R.id.progress_tv_gxmc);
			
			holder.progress_tv_leader = (TextView) convertView
					.findViewById(R.id.progress_tv_leader);
			holder.progress_tv_checker = (TextView) convertView
					.findViewById(R.id.progress_tv_checker);
			holder.progress_tv_worke1 = (TextView) convertView
					.findViewById(R.id.progress_tv_worke1);
			holder.progress_tv_worke2 = (TextView) convertView
					.findViewById(R.id.progress_tv_worke2);
			holder.progress_tv_worke3 = (TextView) convertView
					.findViewById(R.id.progress_tv_worke3);
			holder.progress_tv_worke4 = (TextView) convertView
					.findViewById(R.id.progress_tv_worke4);
			
			

			convertView.setTag(holder);
		} else {
			holder = (MyHolder) convertView.getTag();
		}
		if (mList.size() > 0) {
			Pcxx pcxx = mList.get(position);
//		    if(pcxx.getScgx2()!=null){
//		    	holder.progress_view_gxmc.setVisibility(View.VISIBLE);
//		    	holder.progress_view_plannumber.setVisibility(View.VISIBLE);
//		    	holder.progress_tv_gxmc2.setVisibility(View.VISIBLE);
//		    	holder.progress_tv_plannumber2.setVisibility(View.VISIBLE);
//		    	holder.progress_tv_gxmc2.setText(pcxx.getScgx2());
//		    	holder.progress_tv_plannumber2.setText(pcxx.getSl2());
//		    }
			holder.progress_tv_plannumber1.setText(pcxx.getSl());
			holder.progress_tv_gxmc1.setText(pcxx.getScgx());
			holder.progress_tv_classcore.setText(pcxx.getScrq() + " "
					+ pcxx.getBcdm());
			holder.progress_tv_prolinecode.setText(mProLineCode);
			holder.progress_tv_procode.setText(pcxx.getCpdm());
			holder.progress_tv_proname.setText(pcxx.getCpmc());
			holder.progress_tv_sizemode.setText(pcxx.getGgxh());
			holder.progress_tv_promethod.setText(pcxx.getGxfl());
			holder.progress_tv_leader.setText(pcxx.getFzr());
			holder.progress_tv_checker.setText(pcxx.getJyy());
			holder.progress_tv_worke1.setText(pcxx.getCzy1());
			holder.progress_tv_worke2.setText(pcxx.getCzy2());
			holder.progress_tv_worke3.setText(pcxx.getCzy3());
			holder.progress_tv_worke4.setText(pcxx.getCzy4());
		}
		return convertView;
	}

	private class MyHolder {
		TextView progress_tv_classcore;
		TextView progress_tv_prolinecode;
		TextView progress_tv_procode;
		TextView progress_tv_proname;
		TextView progress_tv_sizemode;
		TextView progress_tv_promethod;
		TextView progress_tv_plannumber1;
		TextView progress_tv_plannumber2;
		TextView progress_tv_gxmc1;
		TextView progress_tv_gxmc2;
		TextView progress_tv_leader;
		TextView progress_tv_checker;
		TextView progress_tv_worke1;
		TextView progress_tv_worke2;
		TextView progress_tv_worke3;
		TextView progress_tv_worke4;
		View progress_view_gxmc;
		View progress_view_plannumber;

	}

	public void refresh(List<Pcxx> list, String proLineCode, String scrq) {
		mList = list;
		mProLineCode = proLineCode;
		this.scrq = scrq;
		notifyDataSetChanged();
	}

	private List<Pcxx> resvertList(List<Pcxx> list) {
		List<Pcxx> pcxxs = new ArrayList<Pcxx>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
                    
				if (i >= 1){
					if (list.get(i - 1).getPcid()
							.equals(list.get(i).getPcid())) {
					Pcxx pcxx = 	list.get(i-1);
					pcxx.setScgx2(list.get(i).getScgx());
					pcxx.setSl2(list.get(i).getSl());
					pcxxs.remove(pcxxs.size()-1);
					pcxxs.add(pcxx);
						continue;
					}
			}
				pcxxs.add(list.get(i));
				}
		}
		return pcxxs;
	}
}
