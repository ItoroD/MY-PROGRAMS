/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.models;

import com.interprogteam2017.mvc.models.Person;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author OElA
 */
class Transaction {

    String id;
    Date transDate = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss a ',' dd.MM.yyyy");

    String transType;
    double cost, amountPaid, change;
    String paymentType;
    Person personDetails;

    public String getTransTime() {
        String returnString = ft.format(transDate);
        System.out.println("Transaction time is " + returnString);
        return returnString;
    }

    public boolean setTransType(String s) {
        if (s.equalsIgnoreCase("Check in") || s.equalsIgnoreCase("Check out") || s.equals("Reservation")) {
            System.out.println("The transaction type is a " + s);
            transType = s;
            return true;
        } else {
            System.err.println("Invalid transaction type chosen");
            return false;
        }
    }

    public boolean setPaymentType(String s) {
        if (s.equals("Cash") || s.equals("Card") || s.equals("Cheque") || s.equals("Credit")) {
            System.out.println("The payment type is by " + s);
            paymentType = s;
            return true;
        } else {
            System.err.println("Invalid payment type chosen");
            return false;
        }
    }

    /*
    public static void main(String[] args) {
        Transaction r = new Transaction();
        r.setPaymentType("Credit");
        r.setTransType("Check Out");
        r.getTransactionTime();
    }
    */
}
