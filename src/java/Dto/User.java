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
    private String userFullname;
    private String Email;
    private String userHash;
    private String userType;
    private String question;
    private String answer_hash;
    private boolean hasDisabledBadge;

    
    public User() {
    }

    public User(int userNo, String userFullname, String Email, String userHash, String userType, String question, String answer_hash, boolean hasDisabledBadge) {
        this.userNo = userNo;
        this.userFullname = userFullname;
        this.Email = Email;
        this.userHash = userHash;
        this.userType = userType;
        this.question = question;
        this.answer_hash = answer_hash;
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

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String pass_Quesstion) {
        this.question = pass_Quesstion;
    }

    public String getAnswer_hash() {
        return answer_hash;
    }

    public void setAnswer_hash(String answer_hash) {
        this.answer_hash = answer_hash;
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
        hash = 29 * hash + Objects.hashCode(this.userHash);
        hash = 29 * hash + Objects.hashCode(this.userType);
        hash = 29 * hash + Objects.hashCode(this.question);
        hash = 29 * hash + Objects.hashCode(this.answer_hash);
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
        if (!Objects.equals(this.userHash, other.userHash)) {
            return false;
        }
        if (!Objects.equals(this.userType, other.userType)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.answer_hash, other.answer_hash)) {
            return false;
        }
        if (!Objects.equals(this.hasDisabledBadge, other.hasDisabledBadge)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userNo=" + userNo + ", userFullname=" + userFullname + ", Email=" + Email + ", userHash=" + userHash + ", userType=" + userType + ", question=" + question + ", answer_hash=" + answer_hash + ", hasDisabledBadge=" + hasDisabledBadge + '}';
    }

    
    
    
}
