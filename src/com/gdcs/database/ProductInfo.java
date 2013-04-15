package com.gdcs.database;

import com.gdcs.utils.Utils;
import com.gdcs.utils.onNotify;

public class ProductInfo {
	private String mProductNo;
	private String mProductName;
	private String mCategoryLarge;
	private String mCategoryMedium;
	private String mCategorySmall;
	private String mPrice;
	private String mBonusPointRatio;
	private String mDeliveryFee;
	private String mAverageDeliveryDays_Short;
	private String mAverageDeliveryDays_Long;
	private String mManufacture;
	private String mCountryOfOrigin;
	private String mSaleStartDate;
	private String mSaleEndDate;
	private String mMinimumOrderQuantity;
	private String mMaximumOrderQuantity;
	private String mDescription;
	private String mInitialRegisterDate;
	private String mFinalUpdateDate;
	
	public ProductInfo() {
		this.clear();
	}
	
	public String getProductNo() {
		return mProductNo;
	}
	
	public String getProductName() {
		return mProductName;
	}
	
	public String getCategoryLarge() {
		return mCategoryLarge;
	}
	
	public String getCategoryMedium() {
		return mCategoryMedium;
	}
	
	public String getCategorySmall() {
		return mCategorySmall;
	}
	
	public String getPrice() {
		return mPrice;
	}
	
	public String getBonusPointRatio() {
		return mBonusPointRatio;
	}

	public String getDeliveryFee() {
		return mDeliveryFee;
	}

	public String getAverageDeliveryDays_Short() {
		return mAverageDeliveryDays_Short;
	}

	public String getAverageDeliveryDays_Long() {
		return mAverageDeliveryDays_Long;
	}

	public String getManufacture() {
		return mManufacture;
	}

	public String getCountryOfOrigin() {
		return mCountryOfOrigin;
	}

	public String getSaleStartDate() {
		return mSaleStartDate;
	}

	public String getSaleEndDate() {
		return mSaleEndDate;
	}

	public String getMinimumOrderQuantity() {
		return mMinimumOrderQuantity;
	}

	public String getMaximumOrderQuantity() {
		return mMaximumOrderQuantity;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getInitialRegisterDate() {
		return mInitialRegisterDate;
	}

	public String getFinalUpdateDate() {
		return mFinalUpdateDate;
	}
	
	public void setProductNo(String str) {
		mProductNo = str;
	}

	public void setProductName(String str) {
		mProductName = str;
	}

	public void setCategoryLarge(String str) {
		mCategoryLarge = str;
	}

	public void setCategoryMedium(String str) {
		mCategoryMedium = str;
	}

	public void setCategorySmall(String str) {
		mCategorySmall = str;
	}

	public void setPrice(String str) {
		mPrice = str;
	}

	public void setBonusPointRatio(String str) {
		mBonusPointRatio = str;
	}

	public void setDeliveryFee(String str) {
		mDeliveryFee = str;
	}

	public void setAverageDeliveryDays_Short(String str) {
		mAverageDeliveryDays_Short = str;
	}

	public void setAverageDeliveryDays_Long(String str) {
		mAverageDeliveryDays_Long = str;
	}

	public void setManufacture(String str) {
		mManufacture = str;
	}

	public void setCountryOfOrigin(String str) {
		mCountryOfOrigin = str;
	}

	public void setSaleStartDate(String str) {
		mSaleStartDate = str;
	}

	public void setSaleEndDate(String str) {
		mSaleEndDate = str;
	}

	public void setMinimumOrderQuantity(String str) {
		mMinimumOrderQuantity = str;
	}

	public void setMaximumOrderQuantity(String str) {
		mMaximumOrderQuantity = str;
	}

	public void setDescription(String str) {
		mDescription = str;
	}

	public void setInitialRegisterDate(String str) {
		mInitialRegisterDate = str;
	}

	public void setFinalUpdateDate(String str) {
		mFinalUpdateDate = str;
	}
	
	public boolean isFormatOK(onNotify onCall) {
		if (!Utils.isValidInputString(mProductNo)) {
			onCall.onCall("Invalid \"Product No\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mProductName)) {
			onCall.onCall("Invalid \"Product Name\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mCategoryLarge)) {
			onCall.onCall("Invalid \"Category Large\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mCategoryMedium)) {
			onCall.onCall("Invalid \"Category Medium\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mCategorySmall)) {
			onCall.onCall("Invalid \"Price\" value.");
			return false;
		}
		if (!Utils.isIntOrFloat(mPrice)) {
			onCall.onCall("Invalid \"Price\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mBonusPointRatio)) {
			onCall.onCall("Invalid \"Bonus Point Ratio\" value.");
			return false;
		}
		if(!Utils.isIntOrFloat(mDeliveryFee)) {
			onCall.onCall("Invalid \"Delivery Fee\" value.");
			return false;
		}
		if(!Utils.isIntOrFloat(mAverageDeliveryDays_Short)) {
			onCall.onCall("Invalid \"Average Delivery Days Start\" value.");
			return false;
		}
		if(!Utils.isIntOrFloat(mAverageDeliveryDays_Long)) {
			onCall.onCall("Invalid \"Average Delivery Days End\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mManufacture)) {
			onCall.onCall("Invalid \"Manufacture\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mCountryOfOrigin)) {
			onCall.onCall("Invalid \"Country Of Origin\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mSaleStartDate)) {
			onCall.onCall("Invalid \"Sale Start Date\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mSaleEndDate)) {
			onCall.onCall("Invalid \"Sale End Date\" value.");
			return false;
		}
		if(!Utils.isIntOrFloat(mMinimumOrderQuantity)) {
			onCall.onCall("Invalid \"Minimum Order Quantity\" value.");
			return false;
		}
		if(!Utils.isIntOrFloat(mMaximumOrderQuantity)) {
			onCall.onCall("Invalid \"Maximum Order Quantity\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mDescription)) {
			onCall.onCall("Invalid \"Description\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mInitialRegisterDate)) {
			onCall.onCall("Invalid \"Initial Register Date\" value.");
			return false;
		}
		if (!Utils.isValidInputString(mFinalUpdateDate)) {
			onCall.onCall("Invalid \"Final Update Date\" value.");
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "{" + mProductNo + ", " + mProductName + ", " + mCategoryLarge
				+ ", " + mCategoryMedium + ", " + mCategorySmall + ", "
				+ mPrice + ", " + mBonusPointRatio + ", " + mDeliveryFee + ", "
				+ mAverageDeliveryDays_Short + ", " + mAverageDeliveryDays_Long
				+ ", " + mManufacture + ", " + mCountryOfOrigin + ", "
				+ mSaleStartDate + ", " + mSaleEndDate + ", "
				+ mMinimumOrderQuantity + ", " + mMaximumOrderQuantity + ", "
				+ mDescription + ", " + mInitialRegisterDate + ", "
				+ mFinalUpdateDate + "}";
	}

	public void clear() {
		mProductNo = "";
		mProductName = "";
		mCategoryLarge = "";
		mCategoryMedium = "";
		mCategorySmall = "";
		mPrice = "";
		mBonusPointRatio = "";
		mDeliveryFee = "";
		mAverageDeliveryDays_Short = "";
		mAverageDeliveryDays_Long = "";
		mManufacture = "";
		mCountryOfOrigin = "";
		mSaleStartDate = "";
		mSaleEndDate = "";
		mMinimumOrderQuantity = "";
		mMaximumOrderQuantity = "";
		mDescription = "";
		mInitialRegisterDate = "";
		mFinalUpdateDate = "";
	}
}
