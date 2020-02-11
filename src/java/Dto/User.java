/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.util.Objects;

/**
 *
 * @author Jonas
 */
public class User {
    
    private int userNo;
    private String userFullname;//
    private String Email;
    private String userPassword;
    private String userType;
    private String pass_Question;//
    private String pass_answer;//
    private boolean hasDisabledBadge;

    
    public User() {
    }

    public User(int userNo, String userFullname, String Email, String userPassword, String userType, String pass_Question, String pass_answer, boolean hasDisabledBadge) {
        this.userNo = userNo;
        this.userFullname = userFullname;
        this.Email = Email;
        this.userPassword = userPassword;
        this.userType = userType;
        this.pass_Question = pass_Question;
        this.pass_answer = pass_answer;
        this.hasDisabledBadge = hasDisabledBadge;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPass_Question() {
        return pass_Question;
    }

    public void setPass_Question(String pass_Quesstion) {
        this.pass_Question = pass_Quesstion;
    }

    public String getPass_answer() {
        return pass_answer;
    }

    public void setPass_answer(String pass_answer) {
        this.pass_answer = pass_answer;
    }

    public boolean getHasDisabledBadge() {
        return hasDisabledBadge;
    }

    public void setHasDisabledBadge(Boolean hasDisabledBadge) {
        this.hasDisabledBadge = hasDisabledBadge;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.userNo;
        hash = 29 * hash + Objects.hashCode(this.userFullname);
        hash = 29 * hash + Objects.hashCode(this.Email);
        hash = 29 * hash + Objects.hashCode(this.userPassword);
        hash = 29 * hash + Objects.hashCode(this.userType);
        hash = 29 * hash + Objects.hashCode(this.pass_Question);
        hash = 29 * hash + Objects.hashCode(this.pass_answer);
        hash = 29 * hash + Objects.hashCode(this.hasDisabledBadge);
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
        final User other = (User) obj;
        if (this.userNo != other.userNo) {
            return false;
        }
        if (!Objects.equals(this.userFullname, other.userFullname)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.userPassword, other.userPassword)) {
            return false;
        }
        if (!Objects.equals(this.userType, other.userType)) {
            return false;
        }
        if (!Objects.equals(this.pass_Question, other.pass_Question)) {
            return false;
        }
        if (!Objects.equals(this.pass_answer, other.pass_answer)) {
            return false;
        }
        if (!Objects.equals(this.hasDisabledBadge, other.hasDisabledBadge)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userNo=" + userNo + ", userFullname=" + userFullname + ", Email=" + Email + ", userPassword=" + userPassword + ", userType=" + userType + ", pass_Question=" + pass_Question + ", pass_answer=" + pass_answer + ", hasDisabledBadge=" + hasDisabledBadge + '}';
    }

    
    
    
}
