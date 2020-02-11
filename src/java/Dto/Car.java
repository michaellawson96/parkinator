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
    private String carDetails;
    private int userNo;

    public Car() {
    }

    public Car(String carReg, String carDetails, int userNo) {
        this.carReg = carReg;
        this.carDetails = carDetails;
        this.userNo = userNo;
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

    public String getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(String carDetails) {
        this.carDetails = carDetails;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.carNo;
        hash = 67 * hash + Objects.hashCode(this.carReg);
        hash = 67 * hash + Objects.hashCode(this.carDetails);
        hash = 67 * hash + this.userNo;
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
        if (!Objects.equals(this.carDetails, other.carDetails)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "carNo=" + carNo + ", carReg=" + carReg + ", carDetails=" + carDetails + ", userNo=" + userNo + '}';
    }
    
    
}
