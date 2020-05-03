/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dto;

/**
 *
 * @author USER
 */
public class Vip {
    private int zoneId;
    private int userId;
    
    public Vip(){
        
    }
    
    public Vip(int zoneId, int userId){
        this.zoneId = zoneId;
        this.userId = userId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.zoneId;
        hash = 89 * hash + this.userId;
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
        final Vip other = (Vip) obj;
        if (this.zoneId != other.zoneId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vip{" + "zoneId=" + zoneId + ", userId=" + userId + '}';
    }
    
    
}
