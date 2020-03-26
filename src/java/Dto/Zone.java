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
public class Zone {
    private int zone_id;
    private String zone_name;
    private int max_spaces;
    private boolean is_vip;
    private int lot_id;
    private int max_disabled_spaces;
    private double lat;
    private double lng;

    public Zone(int zone_id, String zone_name, int max_spaces, boolean is_vip, int lot_id, int max_disabled_spaces, double lat, double lng) {
        this.zone_id = zone_id;
        this.zone_name = zone_name;
        this.max_spaces = max_spaces;
        this.is_vip = is_vip;
        this.lot_id = lot_id;
        this.max_disabled_spaces = max_disabled_spaces;
        this.lat = lat;
        this.lng = lng;
    }
    public Zone(){
        
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public int getMax_spaces() {
        return max_spaces;
    }

    public void setMax_spaces(int max_spaces) {
        this.max_spaces = max_spaces;
    }

    public boolean isIs_vip() {
        return is_vip;
    }

    public void setIs_vip(boolean is_vip) {
        this.is_vip = is_vip;
    }

    public int getLot_id() {
        return lot_id;
    }

    public void setLot_id(int lot_id) {
        this.lot_id = lot_id;
    }

    public int getMax_disabled_spaces() {
        return max_disabled_spaces;
    }

    public void setMax_disabled_spaces(int max_disabled_spaces) {
        this.max_disabled_spaces = max_disabled_spaces;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.zone_id;
        hash = 37 * hash + Objects.hashCode(this.zone_name);
        hash = 37 * hash + this.max_spaces;
        hash = 37 * hash + (this.is_vip ? 1 : 0);
        hash = 37 * hash + this.lot_id;
        hash = 37 * hash + this.max_disabled_spaces;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.lat) ^ (Double.doubleToLongBits(this.lat) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.lng) ^ (Double.doubleToLongBits(this.lng) >>> 32));
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
        final Zone other = (Zone) obj;
        if (this.zone_id != other.zone_id) {
            return false;
        }
        if (this.max_spaces != other.max_spaces) {
            return false;
        }
        if (this.is_vip != other.is_vip) {
            return false;
        }
        if (this.lot_id != other.lot_id) {
            return false;
        }
        if (this.max_disabled_spaces != other.max_disabled_spaces) {
            return false;
        }
        if (Double.doubleToLongBits(this.lat) != Double.doubleToLongBits(other.lat)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lng) != Double.doubleToLongBits(other.lng)) {
            return false;
        }
        if (!Objects.equals(this.zone_name, other.zone_name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zone{" + "zone_id=" + zone_id + ", zone_name=" + zone_name + ", max_spaces=" + max_spaces + ", is_vip=" + is_vip + ", lot_id=" + lot_id + ", max_disabled_spaces=" + max_disabled_spaces + ", lat=" + lat + ", lng=" + lng + '}';
    }
    
    

 
    
}
