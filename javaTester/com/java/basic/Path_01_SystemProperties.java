package com.java.basic;

public class Path_01_SystemProperties{
    public static void main(String[] args){
        String projectPath= System.getProperty("user.dir");
        System.out.println(projectPath);
    }
}
