/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.PaymentLogs;

/**
 *
 * @author SeppQ
 */
public interface PaymentLogDAOInterface {
    String insertPaymentLogs(PaymentLogs pl);
    Object selectPaymentLogs();
}
