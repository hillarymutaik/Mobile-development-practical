package com.shashank.crud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormA extends AppCompatActivity {

    TextView date, gh, diet, comment;
    String dates, ghs, diets, comments;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forma);

        date = findViewById(R.id.date);
        gh = findViewById(R.id.gh);
        diet = findViewById(R.id.diet);
        comment = findViewById(R.id.comment);
        progressDialog = new ProgressDialog(this);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dates = date.getText().toString();
                ghs = gh.getText().toString();
                diets = diet.getText().toString();
                comments = comment.getText().toString();

                if(TextUtils.isEmpty(dates)){
                    date.setError("Date Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;

                    if(TextUtils.isEmpty(ghs)){
                        gh.setError("General Health Cannot be Empty");
                        valid = false;
                    }else {
                        valid = true;

                        if(TextUtils.isEmpty(diets)){
                            diet.setError("Diet Cannot be Empty");
                            valid = false;
                        }else {
                            valid = true;

                            if(TextUtils.isEmpty(comments)){
                                comment.setError("Gender Cannot be Empty");
                                valid = false;
                            }else {
                                valid = true;
                            }
                        }

                    }
                }

                if(valid){
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_FORMA, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(FormA.this, jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                                if(jsonObject.getString("message").equals("Form A Data Saved")){

                                    Intent i = new Intent(FormA.this, FormB.class);
                                    startActivity(i);
                                    finish();
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
                            Toast.makeText(FormA.this, "Failed to Data",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("date", dates);
                            params.put("gh", ghs);
                            params.put("diet",diets);
                            params.put("comment", comments);
                            return params;
                        }
                    };
                    RequestHandler.getInstance(FormA.this).addToRequestQueue(stringRequest);
                }
            }
        });
    }
}
