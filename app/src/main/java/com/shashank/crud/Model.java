package com.shashank.crud;

public class Model {
    String id;
    String fname;
    String lname;
    String dob;
    String gender;

    public Model(String id , String fname, String lname, String dob, String gender ) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
    }

    public String getId(){
        return id;
    }
    public String getUId(){
        return fname;
    }
    public String getName(){
        return lname;
    }

    public String getPhone(){
        return dob;
    }

    public String getAddress(){
        return gender;
    }
}

