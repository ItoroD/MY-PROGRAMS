/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc;

import com.interprogteam2017.mvc.controllers.TransactionController;
import com.interprogteam2017.mvc.models.TransactionModel;
import com.interprogteam2017.mvc.views.TransactionView;
import javax.swing.JFrame;

/**
 *
 * @author Olatunde Babalola
 */
public class TransactionMVC {

    TransactionView tv = new TransactionView();
    TransactionModel tm = new TransactionModel();
    TransactionController tc = new TransactionController(tv, tm);

    
    public static void main(String[] args) {
        TransactionView tv = new TransactionView();
        TransactionModel tm = new TransactionModel();
        TransactionController tc = new TransactionController(tv, tm);
        tc.start();
    }

    public TransactionMVC() {
        TransactionView tv = new TransactionView();
        TransactionModel tm = new TransactionModel();
        TransactionController tc = new TransactionController(tv, tm);
        tv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tc.start();
    }

    public void start() {
        tc.start();
    }
}