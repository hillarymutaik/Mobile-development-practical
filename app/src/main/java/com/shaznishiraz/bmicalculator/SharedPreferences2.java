package com.shaznishiraz.bmicalculator;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

public class SharedPreferences2 extends Fragment {
    private Context mContext;
    public static final String SHARED_PREFS_CATEGORY = "sharedPrefs";
    public static final String SHARED_PREFS_NAME = "name";
    public  static  final String SHARED_PREFS_VALUE = "value";


    public void saveInfo(Context view, String SHARED_PREFS_CATEGORY, String SHARED_PREFS_NAME, String SHARED_PREFS_VALUE)
    {

        SharedPreferences sharedPreferences = view.getSharedPreferences(SHARED_PREFS_CATEGORY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREFS_NAME,SHARED_PREFS_VALUE );
        editor.apply();



    }

    public  void loadData(Context view,String SHARED_PREFS_CATEGORY, String SHARED_PREFS_NAME){

        SharedPreferences sharedPreferences =view.getSharedPreferences(SHARED_PREFS_CATEGORY,Context.MODE_PRIVATE);
        String text = sharedPreferences.getString(SHARED_PREFS_NAME,"");
        Toast.makeText( view,text, Toast.LENGTH_LONG).show();

    }

    public void DeleteData(Context view, String SHARED_PREFS_CATEGORY, String SHARED_PREFS_NAME)
    {

        SharedPreferences sharedPreferences = view.getSharedPreferences(SHARED_PREFS_CATEGORY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREFS_NAME,"");
        editor.apply();

    }
}
