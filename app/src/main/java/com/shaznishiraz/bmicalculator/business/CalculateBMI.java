package com.shaznishiraz.bmicalculator.business;

import android.widget.Toast;

import com.shaznishiraz.bmicalculator.R;

public class CalculateBMI {

    private double inputfeet;
    private double inputinches;
    private double inputkg;

    private final double FEET_TO_CM  = 30.48;
    private final double INCH_TO_CM = 2.54;

    public CalculateBMI(double inputfeet, double inputinches, double inputkg)
    {
        this.inputfeet = inputfeet;
        this.inputinches = inputinches;
        this.inputkg = inputkg;
    }

    public double getInputfeet() {
        return inputfeet;
    }

    public double getInputinches() {
        return inputinches;
    }

    public double getInputkg() {
        return inputkg;
    }

    public double camlculatebmi(double inputkg, double inputinches, double inputfeet)
    {
        double result = 0;

        inputfeet = inputfeet * FEET_TO_CM;
        inputinches = inputinches * INCH_TO_CM;

        double txtheightm = (inputfeet + inputinches)/100;

        result = inputkg/(txtheightm*txtheightm);

        result = (double) Math.round(result * 100) / 100;

        return result;


    }

    public String getbmitype(double bmi)
    {
        String type = "null";

        if (bmi<=18.5)
        {
            type = "Underweight";
        }
        else if (bmi<=24.9)
        {
            type = "Normal Weight";
        }
        else if (bmi<=29.9)
        {
            type = "Over Weight";
        }
        else if (bmi<=34.9)
        {
            type = "Obesity";
        }
        else if (bmi>35)
        {
            type = "Extremely Obesity";
        }

        return type;

    }
}
