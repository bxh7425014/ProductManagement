package com.gdcs.utils;

import java.util.ArrayList;

import com.gdcs.database.ConditionInfo;
import com.gdcs.database.SQLExecInfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Utils {
	
	public static final String ProductDB = "/data/data/com.gdcs.subject/databases/product.db";
	
	public static String[] StringList2StringArray(ArrayList<String> sList) {
		if(sList == null) {
			return null;
		}
		if(sList.size() == 0) {
			return null;
		}
		String[] strArray = new String[sList.size()];
		for(int index = 0; index < sList.size(); index++) {
			strArray[index] = sList.get(index);
		}
		return strArray;
	}
	/**
	 * Get cursor by key word and data table
	 * @author bianxiaohui
	 * @param tableName
	 * @param conditionList
	 * @return
	 */
	public static synchronized Cursor GetSelectCursor(SQLiteDatabase db, String tableName, ArrayList<ConditionInfo> conditionList) {
		if (conditionList == null) {
			return null;
		}
		int size = conditionList.size();
		int keywordSize = 0;
		for (int index = 0; index < size; index++) {
			if (conditionList.get(index).GetKeyword() != null) {
				keywordSize++;
			}
		}
		if (keywordSize == 0) {
			// Select all
			return db.query(tableName, null, null, null, null, null, null);	
		} else {
			SQLExecInfo sInfo = GetSQLExecInfo(tableName, "select * from ", keywordSize, conditionList);
			return db.rawQuery(sInfo.GetSql(), sInfo.GetSelectionArgs());
		}
	}
	
	public static synchronized void DeletDBByCondition(SQLiteDatabase db, String tableName, ArrayList<ConditionInfo> conditionList) {
		if (conditionList == null) {
			return;
		}
		int size = conditionList.size();
		int keywordSize = 0;
		for (int index = 0; index < size; index++) {
			if (conditionList.get(index).GetKeyword() != null) {
				keywordSize++;
			}
		}
		if (keywordSize == 0) {
			String SQL = "delete from " + tableName;
			db.execSQL(SQL, new Object[] {});
		} else {
			SQLExecInfo sInfo = GetSQLExecInfo(tableName, "delete from ", keywordSize, conditionList);
			db.execSQL(sInfo.GetSql(), sInfo.GetBindArgs());
		}
	}
	
	private static SQLExecInfo GetSQLExecInfo(String tableName, String firstWord, int keywordSize, ArrayList<ConditionInfo> conditionList) {
		if (keywordSize <= 0) {
			return null;
		}
		String SQL = firstWord + tableName + " where ";
		String[] selectionArgs = new String[keywordSize];
		int srtGroupIndex = 0;
		for (int index = 0; index < conditionList.size(); index++) {
			String keyword = conditionList.get(index).GetKeyword();
			if (keyword == null) {
				continue;
			}
			if (srtGroupIndex == 0) {
				SQL += conditionList.get(index).GetTitle() + "=?";
			} else {
				SQL += " and " + conditionList.get(index).GetTitle() + "=?";
			}
			selectionArgs[srtGroupIndex] = conditionList.get(index).GetKeyword();
			srtGroupIndex++;
		}
		return new SQLExecInfo(SQL, selectionArgs);
	}
	
	public static boolean isValidInputString(String str) {
		if ((str == null) || (str.length() == 0) || (str.equals(""))) {
			return false;
		}
		return true;
	}
	
	public static boolean isIntOrFloat(String str) {
		return isInt(str) || (isFloat(str));
	}
	
	public static boolean isFloat(String str) {
		boolean isFloat = false;
		try {
			Integer.parseInt(str);
			isFloat = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return isFloat;
	}
	
	public static boolean isInt(String str) {
		boolean isInt = false;
		try {
			Integer.parseInt(str);
			isInt = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return isInt;
	}
	
	public static int parseInt(String numString) {
		int num = -1;
		try {
			num = Integer.parseInt(numString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static float parseFloat(String numString) {
		float num = -1;
		try {
			num = Float.parseFloat(numString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return num;
	}
}
