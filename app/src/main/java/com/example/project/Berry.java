package com.example.project;

import androidx.annotation.NonNull;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Berry {
    private String category;
    private String name;

    private int cost;


    public Berry() {
        category = "Saknar category";
        name="Saknar namn";
        cost=3;
    }
    public Berry (String n, String c, int kr) {
        name=n;
        category=c;
        cost=kr;
    }

    public Berry(String name) {
        this.name = name;
    }



    public void setName (String n) {
        name=n;
    }
    public void setCatagory (String c) {
        category=c;
    }
    public String getName() {
        return name;
    }
    @Override
    @NonNull

    public String toString() {

        return "The "+ category+ ": " +name ;
    }
}
