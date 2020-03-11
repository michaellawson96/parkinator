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
public class Cc {
    private String ccName;
    private int ccNo;

    public Cc() {
    }

    public Cc(String ccName, int ccNo) {
        this.ccName = ccName;
        this.ccNo = ccNo;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public int getCcNo() {
        return ccNo;
    }

    public void setCcNo(int ccNo) {
        this.ccNo = ccNo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.ccName);
        hash = 19 * hash + this.ccNo;
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
        final Cc other = (Cc) obj;
        if (this.ccNo != other.ccNo) {
            return false;
        }
        if (!Objects.equals(this.ccName, other.ccName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cc{" + "ccName=" + ccName + ", ccNo=" + ccNo + '}';
    }
    
}
