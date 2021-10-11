package com.shashank.crud;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
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

public class Vital extends AppCompatActivity {

    TextView date, height, weight, bmi;
    String dates, heights, weights, bmis;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vital);

        date = findViewById(R.id.date);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        bmi = findViewById(R.id.bmi);

        progressDialog = new ProgressDialog(this);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dates = date.getText().toString();
                heights = height.getText().toString();
                weights = weight.getText().toString();
                bmis = bmi.getText().toString();

                if(TextUtils.isEmpty(dates)){
                    date.setError("Date Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;

                    if(TextUtils.isEmpty(heights)){
                        height.setError("Height Cannot be Empty");
                        valid = false;
                    }else {
                        valid = true;

                        if(TextUtils.isEmpty(weights)){
                            weight.setError("Weight Cannot be Empty");
                            valid = false;
                        }else {
                            valid = true;

                            if(TextUtils.isEmpty(bmis)){
                                bmi.setError("BMI Cannot be Empty");
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
                            Constants.URL_VITAL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(Vital.this, jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                                if(jsonObject.getString("message").equals("Vital Data Added Successfully")){

                                    Intent intent = getIntent();

                                    Intent i = new Intent(Vital.this, FormA.class);
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
                            Toast.makeText(Vital.this, "Failed to Data",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("date", dates);
                            params.put("height", heights);
                            params.put("weight",weights);
                            params.put("bmi", bmis);
                            return params;
                        }
                    };
                    RequestHandler.getInstance(Vital.this).addToRequestQueue(stringRequest);
                }
            }
        });
    }
}
