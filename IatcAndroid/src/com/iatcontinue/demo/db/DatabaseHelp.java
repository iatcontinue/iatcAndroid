package com.iatcontinue.demo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelp extends SQLiteOpenHelper {

	private final String TAG = "DatabaseHelp";
	private static final String DATABASE_NAME = "test";
	private static final String TABLE_NAME = "testTable";
	private static final int version = 4;

	private static final String TEST_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME + " (id int,name TEXT);";

	public DatabaseHelp(Context context) {
		super(context, DATABASE_NAME, null, version);
	}
	
	public DatabaseHelp(Context context,boolean test) {
		super(context, DATABASE_NAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TEST_TABLE_CREATE);

		Log.i(TAG, "onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		Log.i(TAG, "onUpgrade oldVer:" + oldVer + ",newVer:" + newVer);
		// String sql = "alter table [" + TABLE_NAME
		// + "] add [desc] nvarchar(300)";
		// db.execSQL(sql);
	}

	public void insert() {
		Log.i(TAG, "insert");
		SQLiteDatabase writeDB = getWritableDatabase();
		writeDB.execSQL("INSERT INTO "
				+ TABLE_NAME
				+ " (id,name) VALUES(1,'Bill1'),(2,'Bill2'),(3,'Bill3'),(4,'Bill4')");
	}

	public void query() {
		SQLiteDatabase readDB = getReadableDatabase();
		SQLiteDatabase writeDB = getWritableDatabase() ;
//		 Cursor dbCursor = readDB.query("tempTable", null, null, null, null,
//		 null, null);
//		 String[] columnNames = dbCursor.getColumnNames();
//		 Log.i(TAG, "Colum length :"+columnNames.length);
//		 for(String s : columnNames){
//		 Log.i(TAG, "Colum name:"+s);
//		 }
//		String name = "name";
//		String newName = "test";
//		String columnTyp = "";

//		String Query = "PRAGMA table_info(tempTable" + ")";
//		Cursor my_cursor = readDB.rawQuery(Query, null);
//		;
//
//		StringBuffer  buf = new StringBuffer("(");
//		while (my_cursor.moveToNext()) {
//			String nameS = my_cursor.getString(my_cursor
//					.getColumnIndex("name"));
//			String type = my_cursor.getString(my_cursor
//					.getColumnIndex("type"));
//
//			buf.append(nameS +" "+type+",");
//		}
//		//delete last ','
//		int index  = buf.lastIndexOf(",");
//		if(index == buf.length()-1){
//			buf.replace(index, buf.length(), "");
//		}
//		buf.append(")");
//		Log.i(TAG, "buf:"+buf.toString());
		
		//add column 
//		String addNewColumn = "alter table "+TABLE_NAME +" add "+newName+" "+columnTyp;
//		writeDB.execSQL(addNewColumn);
		//Insert into Table2(field1,field2,...) select value1,value2,... from Table1
//		String insertData = "insert into " + TABLE_NAME +"("+newName+") select " + name+" from "+TABLE_NAME;
//		writeDB.execSQL(insertData);
		
		
//		 Cursor dbCursor = readDB.query(TABLE_NAME, null, null, null, null,
//				 null, null);
		 
		 
//		while( dbCursor.moveToNext()){
//			int id = dbCursor.getInt(dbCursor.getColumnIndex("id"));
//			String nameStr = dbCursor.getString(dbCursor.getColumnIndex("name"));
//			String test = dbCursor.getString(dbCursor.getColumnIndex("test"));
//			Log.i(TAG, "id:"+id+",name:"+nameStr+",test:"+test);
//		}
//		
		changeColumnName(writeDB, TABLE_NAME, "name", "how");
		 Cursor dbCursor = readDB.query(TABLE_NAME, null, null, null, null,
				 null, null);
		 
//		 
		while( dbCursor.moveToNext()){
			int id = dbCursor.getInt(dbCursor.getColumnIndex("id"));
//			String nameStr = dbCursor.getString(dbCursor.getColumnIndex("id"));
			String test = dbCursor.getString(dbCursor.getColumnIndex("how"));
			Log.i(TAG, "id:"+id+",how:"+test);
		}

	}
	
	/**
	 * Change column name from table
	 * 
	 * @param db
	 * @param table
	 * @param columnName
	 * @param newColumnName
	 */
	private void changeColumnName(SQLiteDatabase db, String table,
			String oldName, String newName) {
		String Query = "PRAGMA table_info(" + table + ")";
		Cursor my_cursor = db.rawQuery(Query, null);

		StringBuffer columns = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		while (my_cursor.moveToNext()) {
			String name = my_cursor.getString(my_cursor.getColumnIndex("name"));
			String type = my_cursor.getString(my_cursor.getColumnIndex("type"));
			sql.append(name + " " + type + ",");
			columns.append(name+",");
		}
		// delete last ','
		int index = sql.lastIndexOf(",");
		if (index == sql.length() - 1) {
			sql.replace(index, sql.length(), "");
		}
		
		index = columns.lastIndexOf(",");
		if(index == columns.length()-1){
			columns.replace(index, columns.length(), "");
		}
		Log.i(TAG, "columns:"+columns);

		String newColumns = columns.toString().replace(oldName, newName);
		Log.i(TAG, "newColumns:"+newColumns);
		String newSql = sql.toString().replace(oldName, newName);
		String createNewTableSql = "create table tempTable (" + newSql + ")";

		String drop = "drop table if exists tempTable";
		db.execSQL(drop);

		db.execSQL(createNewTableSql);
		
		String insertData = "insert into tempTable ("+newColumns+") select " + columns+" from "+table;
		db.execSQL(insertData);
		
		String dropTable = "drop table if exists " + table;
		db.execSQL(dropTable);
		// 3.Rename temperate table name;
		String rename = "alter table tempTable rename to " + table;
		db.execSQL(rename);

	}

}
