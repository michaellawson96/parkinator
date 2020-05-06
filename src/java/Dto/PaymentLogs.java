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
public class PaymentLogs {
    
    private String create_time;
    private String id;
    private String intent;
    private String status;

    public PaymentLogs(String create_time, String id, String intent, String status) {
        this.create_time = create_time;
        this.id = id;
        this.intent = intent;
        this.status = status;
    }
    public PaymentLogs() {

    }
    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.create_time);
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.intent);
        hash = 67 * hash + Objects.hashCode(this.status);
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
        final PaymentLogs other = (PaymentLogs) obj;
        if (!Objects.equals(this.create_time, other.create_time)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.intent, other.intent)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PaymentLogs{" + "create_time=" + create_time + ", id=" + id + ", intent=" + intent + ", status=" + status + '}';
    }
    

}
