package com.shaznishiraz.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserProfile extends Fragment {

    EditText fname;
    EditText lname;
    EditText email;
    Button btnUpdateProfile;
    TextView mname;
    TextView memail;


    public static final String SHARED_PREFS_CATEGORY = "sharedPrefs";
    public static final String SHARED_PREFS_NAME = "name";
    public  static  final String SHARED_PREFS_VALUE = "value";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        View v2 = inflater.inflate(R.layout.nav_header,container,false);

        fname = (EditText) v.findViewById(R.id.txtfname);
        lname = (EditText) v.findViewById(R.id.txtlname);
        email = (EditText) v.findViewById(R.id.txtemail);
        btnUpdateProfile = (Button) v.findViewById(R.id.btnUpdateProfile);
        mname =  (TextView) v2.findViewById(R.id.txtNamefl);
     // memail = (TextView) v.findViewById(R.id.txtEmailAdd);






        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //mname.setText(fname.getText().toString()+" "+lname.getText().toString());
               // memail.setText(email.getText().toString());


                SharedPreferences2 shared = new SharedPreferences2();

                shared.saveInfo(getContext(),"UserProfileData","FirstName",fname.getText().toString());
                shared.saveInfo(getContext(),"UserProfileData","LastName",lname.getText().toString());
                shared.saveInfo(getContext(),"UserProfileData","Email",email.getText().toString());

                shared.loadData(getContext(),"UserProfileData","FirstName");

                mname.setText("Hi");







            }
        });

return  v;


    }




}
