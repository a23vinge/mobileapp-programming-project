package com.example.project;

import androidx.annotation.NonNull;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Mountain {
    private String name;
    private String location;
    private int height;


    public Mountain() {
        name="Saknar namn";
        location="Saknar plats";
        height=2;
    }
    public Mountain (String n, String l, int h) {
        name=n;
        location=l;
        height=h;
    }

    public Mountain(String name) {
        this.name = name;
    }


    public String information() {
        String tmp=new String();
        tmp+=name+"Är lokaliserad i mauntian räckvidd" +location+" och når"+height+"m över vatten level.";
        return tmp;
    }

    public void setName (String n) {
        name=n;
    }

    public String getName() {
        return name;
    }
    @Override
    @NonNull
    public String toString() {
        return name;
    }
}
