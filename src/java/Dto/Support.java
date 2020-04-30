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
public class Support {
    private int message_id;
    private String title;
    private String message;
    private Date date;
    private int user_id;
    
    
    public Support(){
        
    }

    public Support(int message_id, String title, String message, Date date, int user_id) {
        this.message_id = message_id;
        this.title = title;
        this.message = message;
        this.date = date;
        this.user_id = user_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.message_id;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.message);
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + Objects.hashCode(this.user_id);
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
        final Support other = (Support) obj;
        if (this.message_id != other.message_id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Support{" + "message_id=" + message_id + ", title=" + title + ", message=" + message + ", date=" + date + ", user_id=" + user_id + '}';
    }
    
    

}
