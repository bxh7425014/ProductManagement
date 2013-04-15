package com.gdcs.subject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gdcs.codeType.CATEGORY_L;
import com.gdcs.codeType.CATEGORY_M;
import com.gdcs.codeType.CATEGORY_S;
import com.gdcs.codeType.MILEAGE_RATE;
import com.gdcs.database.ProductDB;
import com.gdcs.database.ProductInfo;
import com.gdcs.database.SingletonProduct;
import com.gdcs.database.TempEditProductInfo;
import com.gdcs.utils.LogExt;
import com.gdcs.utils.Utils;
import com.gdcs.utils.onNotify;

public class RegisterNewProductActivity extends Activity {
	private EditText mET_Name;
	private EditText mET_Price;
	private EditText mET_DeliveryFee;
	private EditText mET_AverageDeliveryDays_Start;
	private EditText mET_AverageDeliveryDays_End;
	private EditText mET_Manufacture;
	private EditText mET_CountryOfOrigin;
	private EditText mET_SaleStartDate;
	private EditText mET_SaleEndDate;
	private EditText mET_MinOrderQuatity;
	private EditText mET_MaxOrderQuatity;
	private EditText mET_Description;
	private Button mBtn_List;
	private Button mBtn_Save;
	
	
	private ProductDB mProductDB;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        CATEGORY_L.GetInstance().setTopTitle(CATEGORY_L.SPORT);
        mProductDB = SingletonProduct.GetInstance(this).getProductDB();
        refreshSpinOrder();
    }
    
    private void initView() {
    	mET_Name = (EditText)this.findViewById(R.id.et_name);
    	mET_Price = (EditText)this.findViewById(R.id.et_price);
    	mET_DeliveryFee = (EditText)this.findViewById(R.id.et_delivery_fee);
    	mET_AverageDeliveryDays_Start = (EditText)this.findViewById(R.id.et_average_delivery_days_start);
    	mET_AverageDeliveryDays_End = (EditText)this.findViewById(R.id.et_average_delivery_days_end);
    	mET_Manufacture = (EditText)this.findViewById(R.id.et_manufacture);
    	mET_CountryOfOrigin = (EditText)this.findViewById(R.id.et_country_of_origin);
    	mET_SaleStartDate = (EditText)this.findViewById(R.id.et_sale_start_date);
    	mET_SaleEndDate = (EditText)this.findViewById(R.id.et_sale_end_date);
    	mET_MinOrderQuatity = (EditText)this.findViewById(R.id.et_minimum_order_quantity);
    	mET_MaxOrderQuatity = (EditText)this.findViewById(R.id.et_maximum_order_quantity);
    	mET_Description = (EditText)this.findViewById(R.id.et_description);
    	
    	mBtn_List = (Button) findViewById(R.id.btn_list);
 		mBtn_List.setOnClickListener(mOnListButtonListener);

 		mBtn_Save = (Button) findViewById(R.id.btn_save);
 		mBtn_Save.setOnClickListener(mOnSaveButtonListener);
	}
    
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		LogExt.LogD(this, Thread.currentThread().getStackTrace());
		initView();
		ImportEditProdutInfoToView();
	}

	private void ImportEditProdutInfoToView() {
		ProductInfo pdi = TempEditProductInfo.GetInstance().getProductInfo();
		mET_Name.setText(pdi.getProductName());
    	mET_Price.setText(pdi.getPrice());
    	mET_DeliveryFee.setText(pdi.getDeliveryFee());
    	mET_AverageDeliveryDays_Start.setText(pdi.getAverageDeliveryDays_Short());
    	mET_AverageDeliveryDays_End.setText(pdi.getAverageDeliveryDays_Long());
    	mET_Manufacture.setText(pdi.getManufacture());
    	mET_CountryOfOrigin.setText(pdi.getCountryOfOrigin());
    	mET_SaleStartDate.setText(pdi.getSaleStartDate());
    	mET_SaleEndDate.setText(pdi.getSaleEndDate());
    	mET_MinOrderQuatity.setText(pdi.getMinimumOrderQuantity());
    	mET_MaxOrderQuatity.setText(pdi.getMaximumOrderQuantity());
    	mET_Description.setText(pdi.getDescription());
	}

	ArrayAdapter<String> mLargeSpinAdapter;
    private void refreshLargeOrder() {
		mLargeSpinAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				CATEGORY_L.GetInstance().sCategory_L_List);
		Spinner large_spin = (Spinner) this.findViewById(R.id.large_category_spiner);
		large_spin.setAdapter(mLargeSpinAdapter);
		large_spin.setOnItemSelectedListener(mOnLargeCategorySpinListener);
    }
    
    ArrayAdapter<String> mMediumSpinAdapter;
    private void refreshMediumOrder() {
    	ArrayList<String> listMedium = CATEGORY_M.GetInstance().getListByUpperName();
    	mMediumSpinAdapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_item, listMedium);
    	Spinner middle_spin = (Spinner) this.findViewById(R.id.middle_category_spiner);
    	middle_spin.setAdapter(mMediumSpinAdapter);
    	middle_spin.setOnItemSelectedListener(mOnMediumCategorySpinListener);
    }
    
    ArrayAdapter<String> mSmallSpinAdapter;
    private void refreshSmallOrder() {
    	ArrayList<String> listSmall = CATEGORY_S.GetInstance().getListByUpperName();
    	mSmallSpinAdapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_item, listSmall);
    	Spinner small_spin = (Spinner) this.findViewById(R.id.small_category_spiner);
    	small_spin.setAdapter(mSmallSpinAdapter);
    	small_spin.setOnItemSelectedListener(mOnSmallCategorySpinListener);
    }
    
    ArrayAdapter<String> mBPRSpinAdapter;
    private void refreshBPROrder() {
    	ArrayList<String> listSmall = MILEAGE_RATE.GetInstance().sRateList;
    	mBPRSpinAdapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_item, listSmall);
    	Spinner small_spin = (Spinner) this.findViewById(R.id.bonus_point_ratio_spiner);
    	small_spin.setAdapter(mBPRSpinAdapter);
    	small_spin.setOnItemSelectedListener(mOnBPRSpinListener);
    }
    
    private void refreshSpinOrder() {
    	this.refreshLargeOrder();
    	this.refreshMediumOrder();
    	this.refreshSmallOrder();
    	this.refreshBPROrder();
    }
    
//	private void testInsertDataToProductDB() {
//		ProductInfo pdi = new ProductInfo();
//		pdi.setProductName("NewProduct");
//		pdi.setCategoryLarge("Sport");
//		Cursor cr = mProductDB.selectTable(pdi);
//		int count = cr.getCount();
//		cr.moveToPosition(0);
//		for (int index = 0; index < cr.getCount(); index++) {
//			ProductInfo tmpPdi = new ProductInfo();
//			tmpPdi.setProductNo(cr.getString(ProductDB.COLUMN_PRODUCT_NO));
//			tmpPdi.setProductName(cr.getString(ProductDB.COLUMN_PRODUCT_NAME));
//			tmpPdi.setCategoryLarge(cr
//					.getString(ProductDB.COLUMN_CATEGORY_LARGE));
//			LogExt.LogD(this, Thread.currentThread().getStackTrace(),
//					tmpPdi.toString());
//		}
//	}

	private OnClickListener mOnListButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(RegisterNewProductActivity.this,
					SearchProductActivity.class);
			startActivity(intent);
			RegisterNewProductActivity.this.finish();
		}

	};
	
	private OnClickListener mOnSaveButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (RefreshProductInfo()) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						RegisterNewProductActivity.this);
				builder.setMessage(
						"Do you want Save this product information?")
						.setCancelable(false)
						.setPositiveButton("Save",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										mProductDB.InsertDataToTable(TempEditProductInfo.GetInstance().getProductInfo());
										mProductDB.close();
									}
								})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						dialog.cancel();
					}
				});
				builder.create().show();
			} else {
				LogExt.LogD(this, Thread.currentThread().getStackTrace(), RegisterNewProductActivity.this.getString(R.string.invalid_format_info));
			}
			
			
		}

	};
	
	private boolean RefreshProductInfo() {
		ProductInfo pdi = TempEditProductInfo.GetInstance().getProductInfo();
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		String date = sDateFormat.format(new java.util.Date());
		pdi.setProductNo("ITME-" + date);
		pdi.setProductName(this.mET_Name.getText().toString());
		pdi.setCategoryLarge(CATEGORY_L.GetInstance().getTopTitle());
		pdi.setCategoryMedium(CATEGORY_M.GetInstance().getTopTitle());
		pdi.setCategorySmall(CATEGORY_S.GetInstance().getTopTitle());
		pdi.setPrice(this.mET_Price.getText().toString());
		pdi.setBonusPointRatio(MILEAGE_RATE.GetInstance().getTopTitle());
		pdi.setDeliveryFee(this.mET_DeliveryFee.getText().toString());
		pdi.setAverageDeliveryDays_Short(this.mET_AverageDeliveryDays_Start
				.getText().toString());
		pdi.setAverageDeliveryDays_Long(this.mET_AverageDeliveryDays_End
				.getText().toString());
		pdi.setManufacture(this.mET_Manufacture.getText().toString());
		pdi.setCountryOfOrigin(this.mET_CountryOfOrigin.getText().toString());
		pdi.setSaleStartDate(this.mET_SaleStartDate.getText().toString());
		pdi.setSaleEndDate(this.mET_SaleEndDate.getText().toString());
		pdi.setMinimumOrderQuantity(this.mET_MinOrderQuatity.getText()
				.toString());
		pdi.setMaximumOrderQuantity(this.mET_MaxOrderQuatity.getText()
				.toString());
		pdi.setDescription(this.mET_Description.getText().toString());
		pdi.setInitialRegisterDate(date);
		pdi.setFinalUpdateDate(date);
		LogExt.LogD(this, Thread.currentThread().getStackTrace(), pdi.toString());
		return pdi.isFormatOK(new onNotify(){
			@Override
			public void onCall(String msg) {
				Toast.makeText(RegisterNewProductActivity.this, msg, Toast.LENGTH_SHORT).show();				
			}});
	}
    
    private OnItemSelectedListener mOnLargeCategorySpinListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = parent.getSelectedItem().toString();
			LogExt.LogD(this, Thread.currentThread().getStackTrace(), str);
			CATEGORY_L.GetInstance().setTopTitle(str);
			refreshMediumOrder();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
    };
    
    private OnItemSelectedListener mOnMediumCategorySpinListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = parent.getSelectedItem().toString();
			LogExt.LogD(this, Thread.currentThread().getStackTrace(), str);
			CATEGORY_M.GetInstance().setTopTitle(str);
			refreshSmallOrder();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
    };
    
    private OnItemSelectedListener mOnSmallCategorySpinListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = parent.getSelectedItem().toString();
			LogExt.LogD(this, Thread.currentThread().getStackTrace(), str);
			CATEGORY_S.GetInstance().setTopTitle(str);
//			refreshSpinOrder();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
    };
    
    private OnItemSelectedListener mOnBPRSpinListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = parent.getSelectedItem().toString();
			LogExt.LogD(this, Thread.currentThread().getStackTrace(), str);
			MILEAGE_RATE.GetInstance().setTopTitle(str);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
    };;
}