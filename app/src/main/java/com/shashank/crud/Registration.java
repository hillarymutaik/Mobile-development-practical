package com.shashank.crud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    TextView fname, lname, dob, gender;
    String fName, lName, d_o_b,gend;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        dob = findViewById(R.id.dob);
//        gender = findViewById(R.id.gender);
        progressDialog = new ProgressDialog(this);



//        //get the spinner from the xml.
//        Spinner dropdown = findViewById(R.id.gender);
////create a list of items for the spinner.
//        String[] items = new String[]{"","Male", "Female"};
////create an adapter to describe how the items are displayed, adapters are used in several places in android.
////There are multiple variations of this, but this is the basic variant.
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
////set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName = fname.getText().toString();
                lName = lname.getText().toString();
                d_o_b = dob.getText().toString();
//                gend = gender.getText().toString();

                if(TextUtils.isEmpty(fName)){
                    fname.setError("First Name Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;

                    if(TextUtils.isEmpty(lName)){
                        lname.setError("Last Name Cannot be Empty");
                        valid = false;
                    }else {
                        valid = true;

                        if(TextUtils.isEmpty(d_o_b)){
                            dob.setError("DOB Cannot be Empty");
                            valid = false;
                        }else {
                            valid = true;

//                            if(TextUtils.isEmpty(gend)){
//                                gender.setError("Gender Cannot be Empty");
//                                valid = false;
//                            }else {
//                                valid = true;
//                            }
                        }

                    }
                }

                if(valid){
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_ADD, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(Registration.this, jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                                if(jsonObject.getString("message").equals("Data Added Successfully")){

                                    Intent myIntent = new Intent(Registration.this, Vital.class);
//                                    myIntent.putExtra("key", 0); //Optional parameters
                                    Registration.this.startActivity(myIntent);

//                                    Intent my = new Intent(MainActivity.this, Vital.class);
//                                    startActivity();
//                                    finish();
//                                    ListActivity.ma.refresh_list();
//                                    finish();
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(Registration.this, "Failed to Data",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("fname", fName);
                            params.put("lname", lName);
                            params.put("dob",d_o_b);
                            params.put("gender", gend);
                            return params;
                        }
                    };
                    RequestHandler.getInstance(Registration.this).addToRequestQueue(stringRequest);
                }
            }
        });
    }
}
