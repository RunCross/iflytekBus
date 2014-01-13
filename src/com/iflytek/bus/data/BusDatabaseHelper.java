package com.iflytek.bus.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BusDatabaseHelper extends SQLiteOpenHelper {

	protected static final String DATABASE_NAME = "iflytekBus";
	protected static final int DATABASE_VERSION	= 1;
	
	public BusDatabaseHelper(Context context){
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);   
	}
	
	public BusDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table businfo ( _id integer primary key , name varchar(50),busduty varchar(50))");
		db.execSQL("create table begin_time (_id integer  ,time varchar(10))");
		db.execSQL("create table line_site (_id integer  ,site integer,_name varchar(50))");
		db.execSQL("create table pause_site (_id integer  ,site integer,_name varchar(50))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
