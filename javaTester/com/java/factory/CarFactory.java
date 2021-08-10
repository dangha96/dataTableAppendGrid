package com.java.factory;

public class CarFactory {
public void viewCar(String carName){
    if(carName.equals("Honda")){
        Honda honda= new Honda();
        honda.view();
    }else if(carName.equals("Toyota")){
        Toyota toyota= new Toyota();
        toyota.view();
    }
    }
  public void driveCar(String carName){
      if(carName.equals("Honda")){
          Honda honda= new Honda();
          honda.driver();
      }else if(carName.equals("Toyota")){
          Toyota toyota= new Toyota();
          toyota.driver();
      }
  }
}

