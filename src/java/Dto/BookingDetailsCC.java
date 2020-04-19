/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Objects;

/**
 *
 * @author SeppQ
 */
public class BookingDetailsCC {
    private String car_reg;
    private String zone_name;
    private String parking_name;
    private String fullname;

    public BookingDetailsCC() {
    }

    public BookingDetailsCC(String car_reg, String zone_name, String parking_name, String fullname) {
        this.car_reg = car_reg;
        this.zone_name = zone_name;
        this.parking_name = parking_name;
        this.fullname = fullname;
    }

    public String getCar_reg() {
        return car_reg;
    }

    public void setCar_reg(String car_reg) {
        this.car_reg = car_reg;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getParking_name() {
        return parking_name;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.car_reg);
        hash = 67 * hash + Objects.hashCode(this.zone_name);
        hash = 67 * hash + Objects.hashCode(this.parking_name);
        hash = 67 * hash + Objects.hashCode(this.fullname);
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
        final BookingDetailsCC other = (BookingDetailsCC) obj;
        if (!Objects.equals(this.car_reg, other.car_reg)) {
            return false;
        }
        if (!Objects.equals(this.zone_name, other.zone_name)) {
            return false;
        }
        if (!Objects.equals(this.parking_name, other.parking_name)) {
            return false;
        }
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BookingDetailsCC{" + "car_reg=" + car_reg + ", zone_name=" + zone_name + ", parking_name=" + parking_name + ", fullname=" + fullname + '}';
    }
    
}
