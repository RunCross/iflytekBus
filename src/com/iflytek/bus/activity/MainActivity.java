package com.iflytek.bus.activity;

import iflytek.traffic.bean.BusLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.bus.R;
import com.iflytek.bus.adapter.Myadapter;
import com.iflytek.bus.data.BusContentProvider;

public class MainActivity extends Activity implements OnClickListener {

	private ExpandableListView busList;
	private List<BusLine> listBack;
	private List<BusLine> list;
	private Myadapter adapter;
	private TextView titleVi;
	private TextView titleGo;
	private TextView titleTxt;
	private EditText titleSearch;
	private ImageView titleBack;

	private BusContentProvider busProvider;

	private String data;

	// private String temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_list);

		try {

			loadData();
		} catch (IOException e) {
			e.printStackTrace();
		}

		initView();

		busProvider = new BusContentProvider();
	}

	/**
	 * 从Assets读入str
	 * 
	 * @return String
	 * @throws IOException
	 */
	private String readStr() throws IOException {
		String raw = new String();

		BufferedReader read = new BufferedReader(new InputStreamReader(
				getResources().getAssets().open("traffic.txt")));

		String temp = new String();

		while ((temp = read.readLine()) != null) {
			if (temp.length() > 2) {
				raw += temp;
			}
		}
		return raw;
	}

	/**
	 * 载入数据，并解析成list
	 * 
	 * @throws IOException
	 */
	private void loadData() throws IOException {
		Gson gson = new Gson();
		list = gson.fromJson(readStr(), new TypeToken<List<BusLine>>() {
		}.getType());
		listBack = new ArrayList<BusLine>();
		listBack.addAll(list);
	}

	/**
	 * 初始化组件
	 */
	private void initView() {

		titleVi = (TextView) findViewById(R.id.search_vi);
		titleGo = (TextView) findViewById(R.id.search_go);
		titleTxt = (TextView) findViewById(R.id.search_txt);
		titleSearch = (EditText) findViewById(R.id.search_site);
		titleBack = (ImageView) findViewById(R.id.search_back);

		busList = (ExpandableListView) findViewById(R.id.list);
		adapter = new Myadapter(MainActivity.this, list);
		busList.setAdapter(adapter);

		titleVi.setOnClickListener(this);
		titleGo.setOnClickListener(this);
		titleBack.setOnClickListener(this);

	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.search_vi:
			titleVi.setVisibility(View.GONE);
			titleTxt.setVisibility(View.GONE);
			titleGo.setVisibility(View.VISIBLE);
			titleSearch.setVisibility(View.VISIBLE);
			titleBack.setVisibility(View.VISIBLE);
			break;
		case R.id.search_go:

			data = titleSearch.getText().toString();

			System.out.println(data);

			if (data.length() == 0) {
				list.clear();
				list.addAll(listBack);
			} else {

				List<BusLine> temp = new ArrayList<BusLine>();

				for (BusLine bus : listBack) {
					if (bus.name.contains(data)) {
						temp.add(bus);
					} else if (BusLine.Arr2Str(bus.begin, "").contains(data)) {
						temp.add(bus);
					} else if (BusLine.Arr2Str(bus.line, "").contains(data)) {
						temp.add(bus);
					} else if (BusLine.Arr2Str(bus.pause, "").contains(data)) {
						temp.add(bus);
					}
				}
				for (BusLine bus : temp) {
					System.out.println("name " + bus.name);
				}
				list.clear();
				list.addAll(temp);
			}
			adapter.notifyDataSetChanged();

			break;
		case R.id.search_back:
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(MainActivity.this
							.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
			titleGo.setVisibility(View.GONE);
			titleSearch.setVisibility(View.GONE);
			titleBack.setVisibility(View.GONE);
			titleTxt.setVisibility(View.VISIBLE);
			titleVi.setVisibility(View.VISIBLE);
			break;
		}
	}
	// @Override
	// protected void onNewIntent(Intent intent) {
	// // super.onNewIntent(intent);
	// System.out.println("onNewIntent");
	// handleIntent(intent);
	// }
	//
	// private void handleIntent(Intent intent){
	// System.out.println("intent="+intent.getAction());
	// if (Intent.ACTION_VIEW.equals(intent.getAction())) {
	// //查询数据库
	// System.out.println("handle"+intent.getData().toString());
	// Cursor searchCursor = getContentResolver().query(intent.getData(), null,
	// null, null, null);
	// if (searchCursor != null && searchCursor.moveToFirst()) {
	// //
	// cityInput.setText(searchCursor.getString(searchCursor.getColumnIndex(City.CITY_DESCRIBE)));
	// }
	// }
	// }
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.search, menu);
	//
	// SearchManager searchManager = (SearchManager)
	// getSystemService(Context.SEARCH_SERVICE);
	// SearchView searchView = (SearchView)
	// menu.findItem(R.id.search).getActionView();
	// searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	// searchView.setIconifiedByDefault(false);
	// return super.onCreateOptionsMenu(menu);
	// }
}
