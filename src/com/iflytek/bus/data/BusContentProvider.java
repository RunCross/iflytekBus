package com.iflytek.bus.data;

import iflytek.traffic.bean.BusLine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class BusContentProvider extends ContentProvider {

	private static final String AUTHORITY = "com.iflytek.com";
	
	private static final String SEARCH_ALL = "search";
	private static final int SEARCH_ALL_CODE = 0;
	
	private SQLiteDatabase sqliteData;
	private static UriMatcher URI_MATCHER;
	
	static{
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(AUTHORITY, SEARCH_ALL,SEARCH_ALL_CODE);
		
	}
	
	@Override
	public boolean onCreate() {
		
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		 Cursor cursor = null;
		
		switch(URI_MATCHER.match(uri)){
		case SEARCH_ALL_CODE:
			
			System.out.println("search provider");
			
			break;
		}
		
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

	public List<BusLine> searchAll(String data){
		List<BusLine> results = new ArrayList<BusLine>(); 
		
		Set<Integer> ids = new HashSet<Integer>();
		
		
		
		return results;
	}
	
	
}
