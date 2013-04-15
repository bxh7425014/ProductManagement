package com.gdcs.codeType;

import java.util.ArrayList;
import java.util.HashMap;

public class CATEGORY_L {
	private String mTopTitle;
	public static final String SPORT = "Sport";
	public static final String CLOTH = "Cloth";
	
	public static ArrayList<String> sCategory_L_List = new ArrayList<String>();
	
	public static HashMap<String, ArrayList<String>> sCategoryMap_L = new HashMap<String, ArrayList<String>>();
	private static CATEGORY_L mInst;
	private CATEGORY_L() {
		sCategory_L_List.clear();
		sCategory_L_List.add(SPORT);
		sCategory_L_List.add(CLOTH);
		
		CATEGORY_M inst = CATEGORY_M.GetInstance(); 
		sCategoryMap_L.clear();
		sCategoryMap_L.put(SPORT, inst.sSportList);
		sCategoryMap_L.put(CLOTH, inst.sSportList);
	}
	public synchronized static CATEGORY_L GetInstance() {
		if (mInst == null) {
			// Get instance in first time. 第一次初始化并返回实例。
			mInst = new CATEGORY_L();
		}
		return mInst;
	}
	
	public void setTopTitle(String name) {
		this.mTopTitle = name;
		CATEGORY_M.GetInstance().setTopTitleByUpper();
	}
	
	public String getTopTitle() {
		return this.mTopTitle;
	}
}
