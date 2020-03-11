/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SeppQ
 */
public class ParkedCars {
    private int zone_id;
    private int car_id;
    private Date bookFrom;
    private Date bookTo;

    public ParkedCars(int zone_id, int car_id, Date bookFrom, Date bookTo) {
        this.zone_id = zone_id;
        this.car_id = car_id;
        this.bookFrom = bookFrom;
        this.bookTo = bookTo;
    }

    public ParkedCars() {
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public Date getBookFrom() {
        return bookFrom;
    }

    public void setBookFrom(Date bookFrom) {
        this.bookFrom = bookFrom;
    }

    public Date getBookTo() {
        return bookTo;
    }

    public void setBookTo(Date bookTo) {
        this.bookTo = bookTo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.zone_id;
        hash = 29 * hash + this.car_id;
        hash = 29 * hash + Objects.hashCode(this.bookFrom);
        hash = 29 * hash + Objects.hashCode(this.bookTo);
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
        final ParkedCars other = (ParkedCars) obj;
        if (this.zone_id != other.zone_id) {
            return false;
        }
        if (this.car_id != other.car_id) {
            return false;
        }
        if (!Objects.equals(this.bookFrom, other.bookFrom)) {
            return false;
        }
        if (!Objects.equals(this.bookTo, other.bookTo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParkedCars{" + "zone_id=" + zone_id + ", car_id=" + car_id + ", bookFrom=" + bookFrom + ", bookTo=" + bookTo + '}';
    }
    
}
