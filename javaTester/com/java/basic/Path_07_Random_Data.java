package com.java.basic;

import java.util.Random;

public class Path_07_Random_Data {
    public static void main(String args[]){
        Path_07_Random_Data part = new Path_07_Random_Data();
        System.out.println(part.randomNumber());
    }
    public int randomNumber(){
        Random random= new Random();
        return random.nextInt(999999);
    }
}
