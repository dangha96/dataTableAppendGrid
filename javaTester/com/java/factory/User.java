package com.java.factory;

public class User {
    public static void main(String[] args){
       CarFactory carFactory= new CarFactory();
       carFactory.viewCar("Honda");
       carFactory.viewCar("Toyota");

       carFactory.driveCar("Toyota");
       carFactory.driveCar("Honda");

    }

}
