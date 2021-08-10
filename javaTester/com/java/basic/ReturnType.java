package com.java.basic;

public class ReturnType {
    public static void main (String [] args) {
        ReturnType testReturn= new ReturnType();
        testReturn.getTitle();
        testReturn.getTitles();

    }
    public void getTitle(){
        String title= "Facebook";
        System.out.println(title);
    }
    public String getTitles(){
        String title= "face";
        System.out.println(title);
        return title;
    }
}
