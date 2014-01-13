package com.iflytek.bus;

import iflytek.traffic.bean.BusLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.bus.data.BusDatabaseHelper;

import android.app.Application;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IflytekBusApplication extends Application {

	private List<BusLine> list;
	 
	@Override
	public void onCreate() {
		super.onCreate();
		try {
			loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		initDataBases();		
	}
	
	/**
	 * 新建数据库
	 */
	private void initDataBases(){
		BusDatabaseHelper db = new BusDatabaseHelper(getApplicationContext());	
		SQLiteDatabase dataBase =  db.getWritableDatabase();
		int id=0;
		for(BusLine bus:list){			
			ContentValues content = new ContentValues();
			content.put("_id", id);
			content.put("name", bus.name);
			content.put("busDuty", bus.busDuty);
			for(int i=0;i<bus.begin.length;i++){
				ContentValues times = new ContentValues();
				times.put("_id", id);
				times.put("time", String.valueOf(bus.begin[i]));
				dataBase.insert("begin_time", null, times);
			}
			
			for(int i=0;i<bus.line.length;i++){
				ContentValues lines = new ContentValues();
				lines.put("_id", id);
				lines.put("site", String.valueOf(bus.line[i]));
				dataBase.insert("line_site", null, lines);
			}
			
			for(int i=0;i<bus.pause.length;i++){
				ContentValues pauses = new ContentValues();
				pauses.put("_id", id);
				pauses.put("site", String.valueOf(bus.pause[i]));
				dataBase.insert("pause_site", null, pauses);
			}
			dataBase.insert("businfo", null, content);
			id++;
		}
		
		
	}
	
	private String readStr() throws IOException {
		String raw = new String();

		BufferedReader read = new BufferedReader(new InputStreamReader(
				getResources().getAssets().open("traffic.txt")));

		 String temp = new String();
		 
		while ((temp = read.readLine()) != null) {
			 if(temp.length()>2){
				 raw += temp;
			 }
		}
		return raw;
	}
	
	private void loadData() throws IOException {
		Gson gson = new Gson();
		list = gson.fromJson(readStr(), new TypeToken<List<BusLine>>(){}.getType());
	}
}
