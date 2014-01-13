package com.iflytek.bus.adapter;

import java.util.List;

import iflytek.traffic.bean.BusLine;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.iflytek.bus.R;

public class Myadapter extends  BaseExpandableListAdapter{

	private static viewHoldreGroup viewGroup;
	private static ViewHolderChild viewChild;
	private Context context;
	private List<BusLine> list;
	
	public Myadapter(Context context, List<BusLine> list){
		super();
		this.context = context;
		this.list = list;
	}
	
	/**
	 * 内部类
	 * @author gjyuan
	 *
	 */
	class ViewHolderChild {
		TextView line;
		TextView pause;
	}	
	class viewHoldreGroup {
		TextView name;
		TextView busDuty;
		TextView begin;
	}	

	@Override
	public int getGroupCount() {			
		return list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return list.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return list.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView name;
		TextView busDuty;
		TextView begin;
		if (convertView == null || viewGroup==null) {
			LayoutInflater inflater = LayoutInflater
					.from(context);
			convertView = inflater.inflate(R.layout.bus_group, null);
//			viewGroup = new viewHoldreGroup();
//			viewGroup.name = (TextView) convertView.findViewById(R.id.name);
//			viewGroup.busDuty = (TextView) convertView
//					.findViewById(R.id.busDuty);
//			viewGroup.begin = (TextView) convertView.findViewById(R.id.begin);
			
		}
		BusLine bus = list.get(groupPosition);
		name = (TextView) convertView.findViewById(R.id.name);
		busDuty = (TextView) convertView
				.findViewById(R.id.busDuty);
		begin = (TextView) convertView.findViewById(R.id.begin);
		name.setText(bus.name);
		busDuty.setText(bus.busDuty);
		begin.setText(BusLine.Arr2Str(bus.begin, ","));
//		viewGroup.name.setText(bus.name);
//		viewGroup.busDuty.setText(bus.busDuty);
//		viewGroup.begin.setText(BusLine.Arr2Str(bus.begin, ","));
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null || viewChild == null) {
			LayoutInflater inflater = LayoutInflater
					.from(context);
			convertView = inflater.inflate(R.layout.bus_info, null);
//			viewChild = new ViewHolderChild();
//			viewChild.line = (TextView) convertView.findViewById(R.id.line);
//			viewChild.pause = (TextView) convertView.findViewById(R.id.pause);
		} 
		BusLine bus = list.get(groupPosition);
		TextView line;
		TextView pause;
		line = (TextView) convertView.findViewById(R.id.line);
		pause = (TextView) convertView.findViewById(R.id.pause);
		line.setText(BusLine.Arr2Str(bus.line, "->"));
		pause.setText(BusLine.Arr2Str(bus.pause, "->"));
//		viewChild.line.setText(BusLine.Arr2Str(bus.line, "->"));
//		viewChild.pause.setText(BusLine.Arr2Str(bus.pause, "->"));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}