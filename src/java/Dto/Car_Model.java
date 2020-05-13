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
public class Car_Model {
    private int car_Model_ID;
    private String car_Model_Name;

    public Car_Model(int car_Model_ID, String car_Model_Name, int car_Make_ID) {
        this.car_Model_ID = car_Model_ID;
        this.car_Model_Name = car_Model_Name;
        this.car_Make_ID = car_Make_ID;
    }
    private int car_Make_ID;

    public Car_Model() {
    }

    public int getCar_Model_ID() {
        return car_Model_ID;
    }

    public void setCar_Model_ID(int car_Model_ID) {
        this.car_Model_ID = car_Model_ID;
    }

    public String getCar_Model_Name() {
        return car_Model_Name;
    }

    public void setCar_Model_Name(String car_Model_Name) {
        this.car_Model_Name = car_Model_Name;
    }

    public int getCar_Make_ID() {
        return car_Make_ID;
    }

    public void setCar_Make_ID(int car_Make_ID) {
        this.car_Make_ID = car_Make_ID;
    }

    @Override
    public String toString() {
        return "Car_Model{" + "car_Model_ID=" + car_Model_ID + ", car_Model_Name=" + car_Model_Name + ", car_Make_ID=" + car_Make_ID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.car_Model_ID;
        hash = 61 * hash + Objects.hashCode(this.car_Model_Name);
        hash = 61 * hash + this.car_Make_ID;
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
        final Car_Model other = (Car_Model) obj;
        if (this.car_Model_ID != other.car_Model_ID) {
            return false;
        }
        if (this.car_Make_ID != other.car_Make_ID) {
            return false;
        }
        if (!Objects.equals(this.car_Model_Name, other.car_Model_Name)) {
            return false;
        }
        return true;
    }
    
    
}
