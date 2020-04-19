/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dto;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class Car {
    private int carNo;
    private String carReg;
    private String carColour;
    private String carMake;
    private String carModel;
    private int userNo;

    public Car() {
    }

    public Car(int carNo, String carReg, String carColour, String carMake, String carModel, int userNo) {
        this.carNo = carNo;
        this.carReg = carReg;
        this.carColour = carColour;
        this.carMake = carMake;
        this.carModel = carModel;
        this.userNo = userNo;
    }
    public Car(int carNo, String carReg) {
        this.carNo = carNo;
        this.carReg = carReg;
    }    

    public int getCarNo() {
        return carNo;
    }

    public void setCarNo(int carNo) {
        this.carNo = carNo;
    }

    public String getCarReg() {
        return carReg;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.carNo;
        hash = 71 * hash + Objects.hashCode(this.carReg);
        hash = 71 * hash + Objects.hashCode(this.carColour);
        hash = 71 * hash + Objects.hashCode(this.carMake);
        hash = 71 * hash + Objects.hashCode(this.carModel);
        hash = 71 * hash + this.userNo;
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
        final Car other = (Car) obj;
        if (this.carNo != other.carNo) {
            return false;
        }
        if (this.userNo != other.userNo) {
            return false;
        }
        if (!Objects.equals(this.carReg, other.carReg)) {
            return false;
        }
        if (!Objects.equals(this.carColour, other.carColour)) {
            return false;
        }
        if (!Objects.equals(this.carMake, other.carMake)) {
            return false;
        }
        if (!Objects.equals(this.carModel, other.carModel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "carNo=" + carNo + ", carReg=" + carReg + ", carColour=" + carColour + ", carMake=" + carMake + ", carModel=" + carModel + ", userNo=" + userNo + '}';
    }

    
    
}
