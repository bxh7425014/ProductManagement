package com.gdcs.database;

import java.util.ArrayList;

import com.gdcs.utils.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDB extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "product.db";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_PRODUCT = "table_product";	// Product table name
	
	// Column index in table_product_main
	public static final int COLUMN_PRODUCT_NO = 0;
	public static final int COLUMN_PRODUCT_NAME = 1;
	public static final int COLUMN_CATEGORY_LARGE = 2;
	public static final int COLUMN_CATEGORY_MEDIUM = 3;
	public static final int COLUMN_CATEGORY_SMALL = 4;
	public static final int COLUMN_PRICE = 5;
	public static final int COLUMN_BONUS_POINT_RATIO = 6;
	public static final int COLUMN_DELIVERY_FEE = 7;
	public static final int COLUMN_AVERAGE_DELIVERY_DAYS_SHORT = 8;
	public static final int COLUMN_AVARAGE_DELIVERY_DAYS_LONG = 9;
	public static final int COLUMN_MANUFACTURE = 10;
	public static final int COLUMN_COUNTRY_OF_ORIGIN = 11;
	public static final int COLUMN_SALE_START_DATE = 12;
	public static final int COLUMN_SALE_END_DATE = 13;
	public static final int COLUMN_MIN_ORDER_QUANTITY = 14;
	public static final int COLUMN_MAX_ORDER_QUANTITY = 15;
	public static final int COLUMN_DESCRIPTION = 16;
	public static final int COLUMN_INIT_REGISTER_DATE = 17;
	public static final int COLUMN_FINAL_UPDATE_DATE = 18;
	

	public final static String TITLE_PRODUCT_NO = "ProductNo";
	public final static String TITLE_PRODUCT_NAME = "_id";	//Using _id for simpleCursorAdapter, if using ProductName, programe will Collapse.
	public final static String TITLE_CATEGORY_LARGE = "CategoryLarge";
	public final static String TITLE_CATEGORY_MEDIUM = "CategoryMedium";
	public final static String TITLE_CATEGORY_SMALL = "CategorySmall";
	public final static String TITLE_PRICE = "Price";
	public final static String TITLE_BONUS_POINT_RATIO = "BonusPointRatio";
	public final static String TITLE_DELIVERY_FEE = "DeliveryFee";
	public final static String TITLE_AVERAGE_DELIVERY_DAYS_SHORT = "AverageDeliveryDays_Short";
	public final static String TITLE_AVARAGE_DELIVERY_DAYS_LONG = "AverageDeliveryDays_Long";
	public final static String TITLE_MANUFACTURE = "Manufacture";
	public final static String TITLE_COUNTRY_OF_ORIGIN = "CountryOfOrigin";
	public final static String TITLE_SALE_START_DATE = "SaleStartDate";
	public final static String TITLE_SALE_END_DATE = "SaleEndDate";
	public final static String TITLE_MIN_ORDER_QUANTITY = "MinimumOrderQuantity";
	public final static String TITLE_MAX_ORDER_QUANTITY = "MaximumOrderQuantity";
	public final static String TITLE_DESCRIPTION = "Description";
	public final static String TITLE_INIT_REGISTER_DATE = "InitialRegisterDate";
	public final static String TITLE_FINAL_UPDATE_DATE = "FinalUpdateDate";
	
	public ProductDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		CreateTable_Product(db);
	}

	private void CreateTable_Product(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_PRODUCT + " (" 
		+ TITLE_PRODUCT_NO + " text, "
		+ TITLE_PRODUCT_NAME + " text, "
		+ TITLE_CATEGORY_LARGE + " text, "
		+ TITLE_CATEGORY_MEDIUM + " text, "
		+ TITLE_CATEGORY_SMALL + " text, "
		+ TITLE_PRICE + " text, "
		+ TITLE_BONUS_POINT_RATIO + " text, "
		+ TITLE_DELIVERY_FEE + " text, "
		+ TITLE_AVERAGE_DELIVERY_DAYS_SHORT + " text, "
		+ TITLE_AVARAGE_DELIVERY_DAYS_LONG + " text, "
		+ TITLE_MANUFACTURE + " text, "
		+ TITLE_COUNTRY_OF_ORIGIN + " text, "
		+ TITLE_SALE_START_DATE + " text, "
		+ TITLE_SALE_END_DATE + " text, "
		+ TITLE_MIN_ORDER_QUANTITY + " text, "
		+ TITLE_MAX_ORDER_QUANTITY + " text, "
		+ TITLE_DESCRIPTION + " text, "
		+ TITLE_INIT_REGISTER_DATE + " text, "
		+ TITLE_FINAL_UPDATE_DATE + " text)";
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	
	public synchronized long InsertDataToTable(ProductInfo productData) {
		ContentValues cv = new ContentValues();
		cv.put(TITLE_PRODUCT_NO, productData.getProductNo());
		cv.put(TITLE_PRODUCT_NAME, productData.getProductName());
		cv.put(TITLE_CATEGORY_LARGE, productData.getCategoryLarge());
		cv.put(TITLE_CATEGORY_MEDIUM, productData.getCategoryMedium());
		cv.put(TITLE_CATEGORY_SMALL, productData.getCategorySmall());
		cv.put(TITLE_PRICE, productData.getPrice());
		cv.put(TITLE_BONUS_POINT_RATIO, productData.getBonusPointRatio());
		cv.put(TITLE_DELIVERY_FEE, productData.getDeliveryFee());
		cv.put(TITLE_AVERAGE_DELIVERY_DAYS_SHORT, productData.getAverageDeliveryDays_Short());
		cv.put(TITLE_AVARAGE_DELIVERY_DAYS_LONG, productData.getAverageDeliveryDays_Long());
		cv.put(TITLE_MANUFACTURE, productData.getManufacture());
		cv.put(TITLE_COUNTRY_OF_ORIGIN, productData.getCountryOfOrigin());
		cv.put(TITLE_SALE_START_DATE, productData.getSaleStartDate());
		cv.put(TITLE_SALE_END_DATE, productData.getSaleEndDate());
		cv.put(TITLE_MIN_ORDER_QUANTITY, productData.getMinimumOrderQuantity());
		cv.put(TITLE_MAX_ORDER_QUANTITY, productData.getMaximumOrderQuantity());
		cv.put(TITLE_DESCRIPTION, productData.getDescription());
		cv.put(TITLE_INIT_REGISTER_DATE, productData.getInitialRegisterDate());
		cv.put(TITLE_FINAL_UPDATE_DATE, productData.getFinalUpdateDate());
		SQLiteDatabase db = this.getWritableDatabase();
		long row = db.insert(TABLE_PRODUCT, null, cv);
		return row;
	}
	public synchronized Cursor selectTable(ProductInfo data) {
		return Utils.GetSelectCursor(this.getReadableDatabase(), TABLE_PRODUCT, getConditionInfos(data));
	}
	
	public synchronized void DeleteTableByCondition(ProductInfo data) {
		Utils.DeletDBByCondition(this.getWritableDatabase(), TABLE_PRODUCT, getConditionInfos(data));
	}

//	public Cursor selectByRoot() {
//		SQLiteDatabase db = this.getReadableDatabase();
//		String SQL = "select * from " + TABLE_PRODUCT + " where " + TITLE_PRODUCT_NAME + "=?";
//		Cursor cursor = db.rawQuery(SQL, new String[]{"NewProduct"});
//		String SQL = "select * from " + TABLE_PRODUCT + " where " + TITLE_CATEGORY_LARGE + "=?";
//		Cursor cursor = db.rawQuery(SQL, new String[]{"Sport"});
//		return cursor;
//	}
	
	private ArrayList<ConditionInfo> getConditionInfos(ProductInfo productData) {
		ArrayList<ConditionInfo> conditionList = new ArrayList<ConditionInfo>();
		conditionList.add(new ConditionInfo(TITLE_PRODUCT_NO, productData.getProductNo()));
		conditionList.add(new ConditionInfo(TITLE_PRODUCT_NAME, productData.getProductName()));
		conditionList.add(new ConditionInfo(TITLE_CATEGORY_LARGE, productData.getCategoryLarge()));
		conditionList.add(new ConditionInfo(TITLE_CATEGORY_MEDIUM, productData.getCategoryMedium()));
		conditionList.add(new ConditionInfo(TITLE_CATEGORY_SMALL, productData.getCategorySmall()));
		conditionList.add(new ConditionInfo(TITLE_PRICE, productData.getPrice()));
		conditionList.add(new ConditionInfo(TITLE_BONUS_POINT_RATIO, productData.getBonusPointRatio()));
		conditionList.add(new ConditionInfo(TITLE_DELIVERY_FEE, productData.getDeliveryFee()));
		conditionList.add(new ConditionInfo(TITLE_AVERAGE_DELIVERY_DAYS_SHORT, productData.getAverageDeliveryDays_Short()));
		conditionList.add(new ConditionInfo(TITLE_AVARAGE_DELIVERY_DAYS_LONG, productData.getAverageDeliveryDays_Long()));
		conditionList.add(new ConditionInfo(TITLE_MANUFACTURE, productData.getManufacture()));
		conditionList.add(new ConditionInfo(TITLE_COUNTRY_OF_ORIGIN, productData.getCountryOfOrigin()));
		conditionList.add(new ConditionInfo(TITLE_SALE_START_DATE, productData.getSaleStartDate()));
		conditionList.add(new ConditionInfo(TITLE_SALE_END_DATE, productData.getSaleEndDate()));
		conditionList.add(new ConditionInfo(TITLE_MIN_ORDER_QUANTITY, productData.getMinimumOrderQuantity()));
		conditionList.add(new ConditionInfo(TITLE_MAX_ORDER_QUANTITY, productData.getMaximumOrderQuantity()));
		conditionList.add(new ConditionInfo(TITLE_DESCRIPTION, productData.getDescription()));
		conditionList.add(new ConditionInfo(TITLE_INIT_REGISTER_DATE, productData.getInitialRegisterDate()));
		conditionList.add(new ConditionInfo(TITLE_FINAL_UPDATE_DATE, productData.getFinalUpdateDate()));
		return conditionList;
	}
}