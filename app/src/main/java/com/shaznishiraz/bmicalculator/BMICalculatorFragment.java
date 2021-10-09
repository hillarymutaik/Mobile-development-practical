package com.shaznishiraz.bmicalculator;

import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.core.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shaznishiraz.bmicalculator.business.CalculateBMI;
import com.shaznishiraz.bmicalculator.business.bmidatatable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BMICalculatorFragment extends Fragment {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();


        TextView lblBMI;
        Button btnCalculate;
        TextView txtHeightFeet;
        TextView txtHeightInches;
        TextView txtWeight;
        ImageView imgbmi;

        CalculateBMI calculateBMI;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmicalculator,container,false);
            btnCalculate = (Button) v.findViewById(R.id.btnCalculate);
            lblBMI = (TextView) v.findViewById(R.id.lblBMI);
            txtHeightFeet = (TextView) v.findViewById(R.id.txtFeet);
            txtHeightInches = (TextView) v.findViewById(R.id.txtInches);
            txtWeight = (TextView) v.findViewById(R.id.txtWeight);
            imgbmi = (ImageView) v.findViewById(R.id.imgbmi);


            btnCalculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    try {
                        Double txthfeet = Double.parseDouble(txtHeightFeet.getText().toString());
                        Double txthinch = Double.parseDouble(txtHeightInches.getText().toString());
                        Double txtweight = Double.parseDouble(txtWeight.getText().toString());


                        calculateBMI = new CalculateBMI(txthfeet,txthinch,txtweight);
                        // Calculating BMI
                        double bmi = calculateBMI.camlculatebmi(calculateBMI.getInputkg(),calculateBMI.getInputinches(),calculateBMI.getInputfeet());

                        //Getting BMI Type
                        String bmitype = calculateBMI.getbmitype(bmi);

                        //Getting device Date
                        String dat2e = formatter.format(date);


                        // Adding Data to the Database
                        bmidatatable bmidatatable = new bmidatatable(getActivity());
                        bmidatatable.openDB();
                        bmidatatable.insertRecord(dat2e,Double.toString(bmi),bmitype);
                        bmidatatable.closeDB();

                        Toast.makeText(getActivity(),"Your BMI" + bmi +" " + bmitype,Toast.LENGTH_SHORT).show();

                        //Adding to Display Elements
                        lblBMI.setText("Your BMI is "+bmi);
                        switch (bmitype)
                        {
                            case  "Underweight" :
                                imgbmi.setImageResource(R.drawable.underweight);
                                break;

                            case  "Normal Weight" :
                                imgbmi.setImageResource(R.drawable.normal);
                                break;

                            case "Over Weight"   :
                                imgbmi.setImageResource(R.drawable.overweight);
                                break;

                            case  "Obesity" :
                                imgbmi.setImageResource(R.drawable.obese);
                                break;

                            case "Extremely Obesity":
                                imgbmi.setImageResource(R.drawable.extremelyobese);
                                break;

                             default:
                                 imgbmi.setImageResource(R.drawable.maxresdefault);
                                 break;

                        }

                    }

                    catch (Exception x)
                    {
                        Toast.makeText(getActivity(),"Enter Valid Input" + x,Toast.LENGTH_SHORT).show();

                    }


                }
            });


    return v;


    }
}
