package com.shaznishiraz.bmicalculator.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class bmidatatable {

    DBHelper dbhelper;
    SQLiteDatabase sqLiteDatabase;

            public bmidatatable(Context context){
                dbhelper = new DBHelper(context);
            }

            public void openDB(){
                sqLiteDatabase = dbhelper.getWritableDatabase();
            }

            public void closeDB(){
                sqLiteDatabase.close();
            }

            public void insertRecord(String date,String bmivalue, String bmitype){
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.TABLE_BMIDATE,date);
                contentValues.put(DBHelper.TABLE_BMIVALUE,bmivalue);
                contentValues.put(DBHelper.TABLE_BMISTATE,bmitype);

                sqLiteDatabase.insert(DBHelper.TABLE_BMIDATA,null,contentValues);

            }

            public Cursor getAllRecords(){
                return sqLiteDatabase.rawQuery("select * from " + DBHelper.TABLE_BMIDATA,null);
            }

            public  Cursor clearallRecords(){
                return  sqLiteDatabase.rawQuery("delete from " + DBHelper.TABLE_BMIDATA,null);            }

}
