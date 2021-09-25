package com.shaznishiraz.bmicalculator.business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class  DBHelper extends SQLiteOpenHelper {

    private static final String DB_Name = "bmi";
    private static final int DB_VERSION = 2;
    public static final String TABLE_BMIDATA = "bmidata";
    public static final String TABLE_BMIDATAID = "bmiid";
    public static final String TABLE_BMIDATE = "bmidate";
    public static final String TABLE_BMIVALUE = "bmivalue";
    public static final String TABLE_BMISTATE = "bmitype";

    String CREATE_TABLE = "CREATE TABLE `bmidata` (\n" +
            "\t`bmiid`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`bmidate`\tTEXT,\n" +
            "\t`bmivalue`\tTEXT,\n" +
            "\t`bmitype`\tTEXT\n" +
            ")";



    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
