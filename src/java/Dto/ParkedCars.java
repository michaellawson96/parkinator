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
    private Date book_from;
    private Date book_to;
    private int user_id;

    public ParkedCars(int zone_id, int car_id, Date book_from, Date book_to, int user_id) {
        this.zone_id = zone_id;
        this.car_id = car_id;
        this.book_from = book_from;
        this.book_to = book_to;
        this.user_id = user_id;
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
        return book_from;
    }

    public void setBookFrom(Date book_from) {
        this.book_from = book_from;
    }

    public Date getBookTo() {
        return book_to;
    }

    public void setBookTo(Date book_to) {
        this.book_to = book_to;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.zone_id;
        hash = 29 * hash + this.car_id;
        hash = 29 * hash + Objects.hashCode(this.book_from);
        hash = 29 * hash + Objects.hashCode(this.book_to);
        hash = 29 * hash + this.user_id;
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
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.book_from, other.book_from)) {
            return false;
        }
        if (!Objects.equals(this.book_to, other.book_to)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParkedCars{" + "zone_id=" + zone_id + ", car_id=" + car_id + ", book_from=" + book_from + ", book_to=" + book_to + ", user_id=" + user_id + '}';
    }

}
