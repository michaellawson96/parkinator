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
public class County {
    public int countyID;
    public String countyName;

    public County(int countyID, String countyName) {
        this.countyID = countyID;
        this.countyName = countyName;
    }

    public County() {
    }

    public int getCountyID() {
        return countyID;
    }

    public void setCountyID(int countyID) {
        this.countyID = countyID;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.countyID;
        hash = 11 * hash + Objects.hashCode(this.countyName);
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
        final County other = (County) obj;
        if (this.countyID != other.countyID) {
            return false;
        }
        if (!Objects.equals(this.countyName, other.countyName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "County{" + "countyID=" + countyID + ", countyName=" + countyName + '}';
    }
    
    
}
