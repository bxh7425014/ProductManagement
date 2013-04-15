package com.gdcs.codeType;

import java.util.ArrayList;

public class MILEAGE_RATE {
	private String mTopTitle = MR001;
	public static final String MR001 = "0.5";
	public static final String MR002 = "1.0";
	public static final String MR003 = "1.5";
	public static final String MR004 = "2.0";
	public static ArrayList<String> sRateList = new ArrayList<String>();
	private MILEAGE_RATE() {
		sRateList.clear();
		sRateList.add(MR001);
		sRateList.add(MR002);
		sRateList.add(MR003);
		sRateList.add(MR004);
	}
	
	private static MILEAGE_RATE mInst;
	public static synchronized MILEAGE_RATE GetInstance() {
		if (mInst == null) {
			mInst = new MILEAGE_RATE();
		}
		return mInst;
	}
	
	public void setTopTitle(String str) {
		this.mTopTitle = str;
	}
	
	public String getTopTitle() {
		return this.mTopTitle;
	}
}
