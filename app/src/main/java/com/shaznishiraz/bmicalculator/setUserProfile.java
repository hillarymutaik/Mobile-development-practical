package com.shaznishiraz.bmicalculator;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;


public class setUserProfile extends Fragment {
    TextView mname;
    TextView memail;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v2 =inflater.inflate(R.layout.nav_header,container,false);




        return  v2;
    }
    public void setuser(FragmentActivity v)
    {





        mname.setText("Hi");
        memail.setText("bye");
    }
}
