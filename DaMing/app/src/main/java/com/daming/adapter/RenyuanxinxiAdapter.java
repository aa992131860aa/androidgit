package com.daming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daming.entity.Jx;
import com.daming.entity.Ldjl;
import com.example.daming_text.R;

import java.util.List;

public class RenyuanxinxiAdapter extends BaseAdapter {
	private List<Jx> mList;
	private Context mContext;

	public RenyuanxinxiAdapter(Context context, List<Jx> list) {
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		if (mList.size() > 0) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.renyuanxinxi_item, null);

			TextView ryxx_item_scrq = (TextView) convertView.findViewById(R.id.ryxx_item_scrq);
			TextView ryxx_item_bcdm = (TextView) convertView.findViewById(R.id.ryxx_item_bcdm);
			TextView ryxx_item_cxdm = (TextView) convertView.findViewById(R.id.ryxx_item_cxdm);
			TextView ryxx_item_cpdm = (TextView) convertView.findViewById(R.id.ryxx_item_cpdm);
			TextView ryxx_item_gxlb = (TextView) convertView.findViewById(R.id.ryxx_item_gxlb);
			TextView laodong_tv_gx = (TextView) convertView.findViewById(R.id.ryxx_item_gx);
			TextView ryxx_item_jhs = (TextView) convertView.findViewById(R.id.ryxx_item_jhs);
			TextView ryxx_item_wcs = (TextView) convertView.findViewById(R.id.ryxx_item_wcs);
			TextView ryxx_item_rwgfs = (TextView) convertView.findViewById(R.id.ryxx_item_rwgfs);
			TextView ryxx_item_qtgfs = (TextView) convertView.findViewById(R.id.ryxx_item_qtgfs);
			TextView ryxx_item_tjs = (TextView) convertView.findViewById(R.id.ryxx_item_tjs);
			TextView ryxx_item_fls = (TextView) convertView.findViewById(R.id.ryxx_item_fls);
			TextView ryxx_item_bcsj = (TextView) convertView.findViewById(R.id.ryxx_item_bcsj);
			TextView ryxx_item_txsj = (TextView) convertView.findViewById(R.id.ryxx_item_txsj);
			TextView ryxx_item_tzsl = (TextView) convertView.findViewById(R.id.ryxx_item_tzsl);
			TextView ryxx_item_jhwcl = (TextView) convertView.findViewById(R.id.ryxx_item_jhwcl);
			TextView ryxx_item_bcdcl = (TextView) convertView.findViewById(R.id.ryxx_item_bcdcl);
			TextView ryxx_item_hgl = (TextView) convertView.findViewById(R.id.ryxx_item_hgl);
			TextView ryxx_item_s = (TextView) convertView.findViewById(R.id.ryxx_item_s);
			TextView ryxx_item_czg = (TextView) convertView.findViewById(R.id.ryxx_item_czg);
			TextView ryxx_item_fzr = (TextView) convertView.findViewById(R.id.ryxx_item_fzr);
			TextView ryxx_item_jyy = (TextView) convertView.findViewById(R.id.ryxx_item_jyy);

			ryxx_item_scrq.setText(mList.get(position).getScrq());
			ryxx_item_bcdm.setText(mList.get(position).getBcdm());
			ryxx_item_cxdm.setText(mList.get(position).getCxdm());
			ryxx_item_cpdm.setText(mList.get(position).getCpdm());
			ryxx_item_gxlb.setText(mList.get(position).getGxfl());
					laodong_tv_gx.setText(mList.get(position).getScgx());
			ryxx_item_jhs.setText(mList.get(position).getJhsl());
					ryxx_item_wcs.setText(mList.get(position).getWcsl());
			ryxx_item_rwgfs.setText(mList.get(position).getRwgfs());
			ryxx_item_qtgfs.setText(mList.get(position).getQtgfs());
			ryxx_item_tjs.setText(mList.get(position).getTjs());
					ryxx_item_fls.setText(mList.get(position).getLfs());
			ryxx_item_bcsj.setText(mList.get(position).getBcsj());
			ryxx_item_txsj.setText(mList.get(position).getTxsj());
			ryxx_item_tzsl.setText(mList.get(position).getJcsl());
			ryxx_item_jhwcl.setText(mList.get(position).getJhwcl());
			ryxx_item_bcdcl.setText(mList.get(position).getBcdcl());
					ryxx_item_hgl.setText(mList.get(position).getHgl());
			ryxx_item_s.setText(mList.get(position).getPd5sdf());
					ryxx_item_czg.setText(mList.get(position).getCzgs());
			ryxx_item_fzr.setText(mList.get(position).getFzr());
					ryxx_item_jyy.setText(mList.get(position).getJyy());





		}
		return convertView;
	}

	public void refresh(List<Jx> list) {
		mList = list;
		notifyDataSetChanged();
	}
}
