package com.gdcs.codeType;

import java.util.ArrayList;

public class CATEGORY_S {
	private String mTopTitle;
	//BIKE
	public static final String BIKE = "Bike";
	public static final String BIKE_ACC = "Bike Accessory";
	//HEALTH
	public static final String HEALTH_CLOTH = "Health Cloth";
	public static final String HEALTH_TOOL = "Health Equipment";
	public static final String HEALTH_SUPPL = "Health Supplement";
	//WOMAN
	public static final String WOMAN_TOP = "Top";
	public static final String WOMAN_PANTS = "Pants";
	public static final String WOMAN_ACC = "Accessories";
	//MAN
	public static final String MAN_TOP = "Top";
	public static final String MAN_PANTS = "Pants";
	//CHILDREN
	public static final String CHILD_TOP = "Top";
	public static final String CHILD_PANTS = "Pants";
	
	public static ArrayList<String> sBikeList = new ArrayList<String>();
	public static ArrayList<String> sHealthList = new ArrayList<String>();
	public static ArrayList<String> sWomanList = new ArrayList<String>();
	public static ArrayList<String> sManList = new ArrayList<String>();
	public static ArrayList<String> sChildrenList = new ArrayList<String>();
	private CATEGORY_S() {
		sBikeList.clear();
		sBikeList.add(BIKE);
		sBikeList.add(BIKE_ACC);
		
		sHealthList.clear();
		sHealthList.add(HEALTH_CLOTH);
		sHealthList.add(HEALTH_TOOL);
		sHealthList.add(HEALTH_SUPPL);
		
		sWomanList.clear();
		sWomanList.add(WOMAN_TOP);
		sWomanList.add(WOMAN_PANTS);
		sWomanList.add(WOMAN_ACC);
		
		sManList.clear();
		sManList.add(MAN_TOP);
		sManList.add(MAN_PANTS);
		
		sChildrenList.clear();
		sChildrenList.add(CHILD_TOP);
		sChildrenList.add(CHILD_PANTS);
	}
	
	private static CATEGORY_S mInst;
	public synchronized static CATEGORY_S GetInstance() {
		if (mInst == null) {
			mInst = new CATEGORY_S();
		}
		return mInst;
	}
	
	public ArrayList<String> getListByName(String name) {
		if (name.equals(CATEGORY_M.BIKE)) {
			return this.sBikeList;
		} else if (name.equals(CATEGORY_M.HEALTH)) {
			return this.sHealthList;
		} else if (name.equals(CATEGORY_M.MAN)) {
			return this.sManList;
		}else if (name.equals(CATEGORY_M.WOMAN)) {
			return this.sWomanList;
		}else if (name.equals(CATEGORY_M.CHLIDERN)) {
			return this.sChildrenList;
		}
		return null;
	}
	
	public ArrayList<String> getListByUpperName() {
		return getListByName(CATEGORY_M.GetInstance().getTopTitle());
	}
	
	public void setTopTitle(String name) {
		this.mTopTitle = name;
	}
	
	public void setTopTitleByUpper() {
		CATEGORY_M inst = CATEGORY_M.GetInstance();
		String upperTopTitle = inst.getTopTitle();
		if(upperTopTitle.equals(inst.BIKE)) {
			this.setTopTitle(BIKE);
		} else if (upperTopTitle.equals(inst.HEALTH)) {
			this.setTopTitle(HEALTH_CLOTH);
		} else if (upperTopTitle.equals(inst.MAN)) {
			this.setTopTitle(WOMAN_TOP);
		} else if (upperTopTitle.equals(inst.WOMAN)) {
			this.setTopTitle(MAN_TOP);
		} else if (upperTopTitle.equals(inst.CHLIDERN)) {
			this.setTopTitle(CHILD_TOP);
		} 
	}
	
	public String getTopTitle() {
		return this.mTopTitle;
	}
}
