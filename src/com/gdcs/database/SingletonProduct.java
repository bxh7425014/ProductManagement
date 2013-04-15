package com.gdcs.database;

import android.content.Context;

public class SingletonProduct {
	private static SingletonProduct mInst;
	
	private static ProductDB mProductDB;
	private SingletonProduct() {
	}
	
	public synchronized static SingletonProduct GetInstance(Context ctx) {
		if (mInst == null) {
			mInst = new SingletonProduct();
		}
		if (mProductDB == null) {
			if (ctx != null) {
				mProductDB = new ProductDB(ctx);
			}
		}
		return mInst;
	}
	
	public synchronized static SingletonProduct GetInstance() {
		return GetInstance(null); 
	}
	
	public ProductDB getProductDB() {
		return this.mProductDB;
	}
}
