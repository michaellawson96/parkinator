/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Objects;

/**
 *
 * @author Lukas
 */
public class Lot {
    private int lot_id;
    private String Parking_name;
    private int cc_id;

    public Lot(int lot_id, String Parking_name, int cc_id) {
        this.lot_id = lot_id;
        this.Parking_name = Parking_name;
        this.cc_id = cc_id;
    }
    public Lot() {

    }
    public int getLot_id() {
        return lot_id;
    }

    public void setLot_id(int lot_id) {
        this.lot_id = lot_id;
    }

    public String getParking_name() {
        return Parking_name;
    }

    public void setParking_name(String Parking_name) {
        this.Parking_name = Parking_name;
    }

    public int getCc_id() {
        return cc_id;
    }

    public void setCc_id(int cc_id) {
        this.cc_id = cc_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.lot_id;
        hash = 61 * hash + Objects.hashCode(this.Parking_name);
        hash = 61 * hash + this.cc_id;
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
        final Lot other = (Lot) obj;
        if (this.lot_id != other.lot_id) {
            return false;
        }
        if (this.cc_id != other.cc_id) {
            return false;
        }
        if (!Objects.equals(this.Parking_name, other.Parking_name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarLot{" + "lot_id=" + lot_id + ", Parking_name=" + Parking_name + ", cc_id=" + cc_id + '}';
    }
    
    
}
