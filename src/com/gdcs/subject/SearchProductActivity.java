package com.gdcs.subject;

import java.util.ArrayList;

import com.gdcs.codeType.CATEGORY_L;
import com.gdcs.codeType.CATEGORY_M;
import com.gdcs.codeType.CATEGORY_S;
import com.gdcs.database.ProductDB;
import com.gdcs.database.ProductInfo;
import com.gdcs.database.SingletonProduct;
import com.gdcs.database.TempEditProductInfo;
import com.gdcs.utils.LogExt;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class SearchProductActivity extends Activity {
	private EditText mET_Name;
	private EditText mET_Manufacture;
	private EditText mET_COO;	//Country of Origin
	private ListView mListView;
	private Button mBtn_Search;
	private Button mBtn_Register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_product_layout);
		initView();
		refreshSpinOrder();
	}
	
    private void initView() {
    	mET_Name = (EditText)this.findViewById(R.id.et_name);
    	mET_Manufacture = (EditText)this.findViewById(R.id.et_manufacture);
    	mET_COO = (EditText)this.findViewById(R.id.et_country_of_origin);
    	
    	mBtn_Search = (Button) findViewById(R.id.btn_search);
    	mBtn_Search.setOnClickListener(mOnSearchButtonListener);

    	mBtn_Register = (Button) findViewById(R.id.btn_register_new_product);
    	mBtn_Register.setOnClickListener(mOnRegisterButtonListener);
 		
		mListView = (ListView) findViewById(R.id.lv);
	}

	private OnClickListener mOnSearchButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			updatelistview();
		}
	};
	
	private OnClickListener mOnRegisterButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(SearchProductActivity.this,
					RegisterNewProductActivity.class);
			startActivity(intent);
			SearchProductActivity.this.finish();
		}
	};
	
	private void refreshSpinOrder() {
    	this.refreshLargeOrder();
    	this.refreshMediumOrder();
    	this.refreshSmallOrder();
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
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
    };
    
    private ArrayList<String> mItemIDList = new ArrayList<String>();
	public void updatelistview() {
		ProductInfo pdi = new ProductInfo();
		pdi.setProductName(this.mET_Name.getText().toString());
		pdi.setCategoryLarge(CATEGORY_L.GetInstance().getTopTitle());
		pdi.setCategoryMedium(CATEGORY_M.GetInstance().getTopTitle());
		pdi.setCategorySmall(CATEGORY_S.GetInstance().getTopTitle());
		pdi.setManufacture(this.mET_Manufacture.getText().toString());
		pdi.setCountryOfOrigin(this.mET_COO.getText().toString());
		Cursor cr = SingletonProduct.GetInstance().getProductDB().selectTable(pdi);
		
		
//		pdi.setProductName("New Product");
		cr.moveToFirst();
		mItemIDList.clear();
		for(int index = 0; index < cr.getCount(); index ++) {
			mItemIDList.add(cr.getString(ProductDB.COLUMN_PRODUCT_NO));
		}
//		String productNo = cr.getString(ProductDB.COLUMN_PRODUCT_NO);
//		String name = cr.getString(ProductDB.COLUMN_PRODUCT_NAME); 
//        String LargeC = cr.getString(ProductDB.COLUMN_CATEGORY_LARGE);
//        String MediumC = cr.getString(ProductDB.COLUMN_CATEGORY_MEDIUM);
//        String SmallC = cr.getString(ProductDB.COLUMN_CATEGORY_SMALL);
//        String Manufacturer = cr.getString(ProductDB.COLUMN_MANUFACTURE);
//        String CountryOfOrigin = cr.getString(ProductDB.COLUMN_COUNTRY_OF_ORIGIN);
        //String SaleStatus = "On Sale"; 
		String[] ColumnNames = {ProductDB.TITLE_PRODUCT_NAME, ProductDB.TITLE_CATEGORY_LARGE,
				ProductDB.TITLE_CATEGORY_LARGE, ProductDB.TITLE_CATEGORY_SMALL, 
				ProductDB.TITLE_MANUFACTURE, ProductDB.TITLE_COUNTRY_OF_ORIGIN}; 
		
		ListAdapter adapter = new MySimpleCursorAdapter(this,
				R.layout.listviewlayout, cr, ColumnNames,
				new int[] { R.id.lv_tv_name,
				R.id.lv_tv_large, R.id.lv_tv_medium,
				R.id.lv_tv_small, R.id.lv_tv_manufacturer,
				R.id.lv_tv_country_of_origin});
		mListView.setOnItemClickListener(mOnItemClickListener);
		mListView.setAdapter(adapter);
	}
	
	private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			LogExt.LogD(this, Thread.currentThread().getStackTrace(), "" + position);
//			Toast.makeText(SearchProductActivity.this, "" + position,
//					Toast.LENGTH_SHORT).show();
			ProductInfo pdi = new ProductInfo();
			Cursor cr = SingletonProduct.GetInstance().getProductDB().selectTable(pdi);
			if ((cr != null) && (cr.getCount() > 0)) {
				cr.moveToFirst();
				pdi.setProductNo(mItemIDList.get(position));
				pdi.setProductName(cr.getString(ProductDB.COLUMN_PRODUCT_NAME));
				pdi.setCategoryLarge(cr.getString(ProductDB.COLUMN_CATEGORY_LARGE));
				pdi.setCategoryMedium(cr.getString(ProductDB.COLUMN_CATEGORY_MEDIUM));
				pdi.setCategorySmall(cr.getString(ProductDB.COLUMN_CATEGORY_SMALL));
				pdi.setPrice(cr.getString(ProductDB.COLUMN_PRICE));
				pdi.setBonusPointRatio(cr.getString(ProductDB.COLUMN_BONUS_POINT_RATIO));
				pdi.setDeliveryFee(cr.getString(ProductDB.COLUMN_DELIVERY_FEE));
				pdi.setAverageDeliveryDays_Short(cr.getString(ProductDB.COLUMN_AVERAGE_DELIVERY_DAYS_SHORT));
				pdi.setAverageDeliveryDays_Long(cr.getString(ProductDB.COLUMN_AVARAGE_DELIVERY_DAYS_LONG));
				pdi.setManufacture(cr.getString(ProductDB.COLUMN_MANUFACTURE));
				pdi.setCountryOfOrigin(cr.getString(ProductDB.COLUMN_COUNTRY_OF_ORIGIN));
				pdi.setSaleStartDate(cr.getString(ProductDB.COLUMN_SALE_START_DATE));
				pdi.setSaleEndDate(cr.getString(ProductDB.COLUMN_SALE_END_DATE));
				pdi.setMinimumOrderQuantity(cr.getString(ProductDB.COLUMN_MIN_ORDER_QUANTITY));
				pdi.setMaximumOrderQuantity(cr.getString(ProductDB.COLUMN_MAX_ORDER_QUANTITY));
				pdi.setDescription(cr.getString(ProductDB.COLUMN_DESCRIPTION));
				pdi.setInitialRegisterDate(cr.getString(ProductDB.COLUMN_INIT_REGISTER_DATE));
				pdi.setFinalUpdateDate(cr.getString(ProductDB.COLUMN_FINAL_UPDATE_DATE));
				TempEditProductInfo.GetInstance().setProductInfo(pdi);
			}
			Intent intent = new Intent(SearchProductActivity.this,
					ModifyProductActivity.class);
			startActivity(intent);
			SearchProductActivity.this.finish();
		}
	};
}
