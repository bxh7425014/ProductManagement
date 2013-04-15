package com.gdcs.codeType;

import java.util.ArrayList;
import java.util.HashMap;

public class CATEGORY_M {
	private String mTopTitle;
	// SPORT
	public static final String BIKE = "Bike";
	public static final String HEALTH = "Health";
	// CLOTH
	public static final String MAN = "Man";
	public static final String WOMAN = "Woman";
	public static final String CHLIDERN = "Children";
	
	public static ArrayList<String> sSportList = new ArrayList<String>();
	public static ArrayList<String> sClothList = new ArrayList<String>();
	
	public static HashMap<String, ArrayList<String>> sCategoryMap_M_Sport = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, ArrayList<String>> sCategoryMap_M_Cloth = new HashMap<String, ArrayList<String>>();
	private CATEGORY_M() {
		sSportList.clear();
		sSportList.add(BIKE);
		sSportList.add(HEALTH);
		
		sClothList.clear();
		sClothList.add(MAN);
		sClothList.add(WOMAN);
		sClothList.add(CHLIDERN);
		
		CATEGORY_S inst = CATEGORY_S.GetInstance(); 
		
		sCategoryMap_M_Sport.clear();
		sCategoryMap_M_Sport.put(BIKE, inst.sBikeList);
		sCategoryMap_M_Sport.put(HEALTH, inst.sHealthList);
		
		sCategoryMap_M_Cloth.clear();
		sCategoryMap_M_Cloth.put(MAN, inst.sManList);
		sCategoryMap_M_Cloth.put(WOMAN, inst.sWomanList);
		sCategoryMap_M_Cloth.put(CHLIDERN, inst.sChildrenList);
	}
	
	private static CATEGORY_M mInst;
	public static synchronized CATEGORY_M GetInstance() {
		if (mInst == null) {
			mInst = new CATEGORY_M();
		}
		return mInst;
	}
	
	public ArrayList<String> getListByName(String name) {
		if (name.equals(CATEGORY_L.SPORT)) {
			return this.sSportList;
		} else if (name.equals(CATEGORY_L.CLOTH)) {
			return this.sClothList;
		}
		return null;
	}
	
	public ArrayList<String> getListByUpperName() {
		return getListByName(CATEGORY_L.GetInstance().getTopTitle());
	}
	
	public void setTopTitle(String name) {
		this.mTopTitle = name;
	}
	
	public void setTopTitleByUpper() {
		CATEGORY_L inst = CATEGORY_L.GetInstance();
		String upperTopTitle = inst.getTopTitle();
		if(upperTopTitle.equals(inst.SPORT)) {
			this.setTopTitle(BIKE);
		} else if (upperTopTitle.equals(inst.CLOTH)) {
			this.setTopTitle(MAN);
		}
	}
	
	public String getTopTitle() {
		return this.mTopTitle;
	}
}
