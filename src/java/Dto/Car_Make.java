/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Objects;

/**
 *
 * @author snake
 */
public class Car_Make {
    private int car_Make_ID;
    private String car_Make_Name;
    
    public Car_Make(){
        
    }

    public Car_Make(int car_Make_ID, String car_Make_Name) {
        this.car_Make_ID = car_Make_ID;
        this.car_Make_Name = car_Make_Name;
    }

    public int getCar_Make_id() {
        return car_Make_ID;
    }

    public void setCar_Make_id(int car_Make_ID) {
        this.car_Make_ID = car_Make_ID;
    }

    public String getCar_Make_Name() {
        return car_Make_Name;
    }

    public void setCar_Make_Name(String car_Make_Name) {
        this.car_Make_Name = car_Make_Name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.car_Make_ID;
        hash = 83 * hash + Objects.hashCode(this.car_Make_Name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car_Make other = (Car_Make) obj;
        if (this.car_Make_ID != other.car_Make_ID) {
            return false;
        }
        if (!Objects.equals(this.car_Make_Name, other.car_Make_Name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car_Make{" + "car_Make_ID=" + car_Make_ID + ", car_Make_Name=" + car_Make_Name + '}';
    }



}
