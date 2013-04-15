package com.gdcs.database;

public class SQLExecInfo {
	private String mSql;
	private String[] mSelectionArgs;

	public SQLExecInfo(String sql, String[] selectionArgs) {
		mSql = sql;
		mSelectionArgs = selectionArgs;
	}

	public String GetSql() {
		return mSql;
	}

	public String[] GetSelectionArgs() {
		return mSelectionArgs;
	}
	
	public Object[] GetBindArgs() {
		return (Object[])mSelectionArgs;
	}
}