package com.gdcs.database;


public class TempEditProductInfo {
	private static TempEditProductInfo mInst;
	private ProductInfo mProductInfo = new ProductInfo();
	public synchronized static TempEditProductInfo GetInstance() {
		if (mInst == null) {
			mInst = new TempEditProductInfo();
		}
		return mInst;
	}
	
	public void setProductInfo(ProductInfo pdi) {
		this.mProductInfo = pdi;
	}
	
	public ProductInfo getProductInfo() {
		return this.mProductInfo;
	}
}
