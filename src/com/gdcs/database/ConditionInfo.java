package com.gdcs.database;

/**
 * database list title and correspond keyword.
 * @author bianxiaohui
 *
 */
public class ConditionInfo {
	private String mTitle;
	private String mKeyword;

	public ConditionInfo(String title, String keyword) {
		mTitle = title;
		if((keyword == null) || (keyword.equals(""))) {
			mKeyword = null;
		} else {
			mKeyword = keyword;
		}
	}

	public String GetTitle() {
		return mTitle;
	}

	public String GetKeyword() {
		return mKeyword;
	}
}
