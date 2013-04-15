package com.gdcs.subject;

import com.gdcs.utils.LogExt;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

public class MySimpleCursorAdapter extends SimpleCursorAdapter {

	public MySimpleCursorAdapter(Context context, int listviewlayout,
			Cursor cr, String[] columnNames, int[] is) {
		super(context, listviewlayout, cr, columnNames, is);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = super.getView(position, convertView, parent);

		}

		int[] colors = {Color.WHITE, Color.rgb(219, 238, 244) };// RGB颜色

		view.setBackgroundColor(colors[position % 2]);// 每隔item之间颜色不同

		return super.getView(position, view, parent);
	}

	@Override
	public Object getItem(int position) {
		LogExt.LogD(this, Thread.currentThread().getStackTrace(), "" + position);
		return super.getItem(position);
	}
	
}
