package com.shaznishiraz.bmicalculator;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.shaznishiraz.bmicalculator.business.bmidatatable;

public class BMIHistory extends Fragment {
bmidatatable bmidatatable;
Cursor cursor;
    Button btnview;
    Button btndelete;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report,container,false);
btndelete = (Button) v.findViewById(R.id.btndelete);


     try {

         bmidatatable = new bmidatatable(getActivity());
         bmidatatable.openDB();
         cursor = bmidatatable.getAllRecords();

         ListView listhistory = (ListView) v.findViewById(R.id.listview);
         CustomAdapter customAdapter = new CustomAdapter();

         listhistory.setAdapter(customAdapter);

         bmidatatable.closeDB();

     }

     catch (Exception e)
     {
         Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();

     }

btndelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {

            bmidatatable = new bmidatatable(getActivity());
            bmidatatable.openDB();
            cursor = bmidatatable.clearallRecords();
            bmidatatable.closeDB();
            Toast.makeText(getActivity(),"Deleting Records",Toast.LENGTH_SHORT).show();
        }
        catch (Exception x)
        {
            Toast.makeText(getActivity(),"Delete Failed",Toast.LENGTH_SHORT).show();
        }

    }


});













        return v;

    }


public class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cursor.getCount() ;
        }

        @Override
        public Object getItem(int position) {
            return cursor;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_layout,null);
            TextView txt_rowid = (TextView)  convertView.findViewById(R.id.txtrowid);
            TextView txt_bmidate = (TextView) convertView.findViewById(R.id.txtbmidate);
            TextView txt_bmivalue = (TextView) convertView.findViewById(R.id.txtbmivalue);
            TextView txt_bmitype = (TextView) convertView.findViewById(R.id.txtbmitype);

            cursor.moveToPosition(position);
            txt_rowid.setText(cursor.getString(0));
            txt_bmidate.setText(cursor.getString(1));
            txt_bmivalue.setText(cursor.getString(2));
            txt_bmitype.setText(cursor.getString(3));


            return convertView;
        }
    }
}


