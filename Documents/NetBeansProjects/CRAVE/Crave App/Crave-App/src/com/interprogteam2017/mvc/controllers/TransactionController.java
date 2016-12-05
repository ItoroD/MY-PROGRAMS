/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.controllers;

import com.interprogteam2017.dbconnect.DBConnect;
import com.interprogteam2017.mvc.models.TransactionModel;
import com.interprogteam2017.mvc.views.TransactionView;
//import com.interprogteam2017.mvc.views.errorDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Olatunde Babalola
 */
public class TransactionController {

    double totalCost;
    TransactionView tv;
    TransactionModel tm;
    //errorDialog er;
    boolean firstPress = false;
    boolean secondPress = false;
    String transDate;
    String cid;
    String roomType;
    Date d1, d2;

    public TransactionController(TransactionView tv, TransactionModel tm) {
        this.tv = tv;
        this.tm = tm;

        this.tv.addNext1Listener(new NextListener1());
        this.tv.addNext2Listener(new NextListener2());
        this.tv.addSaveListener(new SaveListener());
        this.tv.addNewTransactionListener(new NewTransListener());
    }

    public void start() {
        clearAllFields();
        tv.pack();
        tv.setVisible(true);
    }

    private void clearAllFields() {
        firstPress = false;
        secondPress = false;
        tv.custIdField1.setText("");
        tv.custIdField1.setEditable(true);
        tv.firstnameField.setText("");
        tv.firstnameField.setEditable(true);
        tv.lastnameField.setText("");
        tv.lastnameField.setEditable(true);
        tv.idNumField.setText("");
        tv.idNumField.setEditable(true);
        tv.addressField.setText("");
        tv.addressField.setEditable(true);
        tv.cityField.setText("");
        tv.cityField.setEditable(true);
        tv.countryField.setText("");
        tv.countryField.setEditable(true);
        tv.custIdField2.setText("");
        tv.transDateField.setText("");
        tv.transDateField.setEditable(false);
        tv.roomsAvailableField.setText("");
        clearFields();
        tv.amountPaidField.setEnabled(false);
        tv.paymentIdField.setEnabled(false);
    }

    class NewTransListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            tv.dispose();
            start();
        }
    }

    class NextListener1 implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            // if the first ID field is not empty
            // check the given id
            if (!tv.getFirstId().equals("")) {
                // if the given id is not in the records
                if (!tm.checkId(tv.getFirstId())) {
                    //display an error
                    JOptionPane.showMessageDialog(tv, "The customer with that ID does not exist in records");
                } else {
                    //Move to transaction stage
                    firstPress = true;
                    stageTwo(tv.getFirstId());
                }
            } else {
                //Make a new user
                //System.out.println("No id entered, making a new user");

                String firstname = tv.getFirstName();
                String lastname = tv.getLastName();
                String address = tv.getAddress();
                String country = tv.getCountry();
                String city = tv.getCity();
                String idType = tv.getIdType();
                String gender = tv.getGender();
                String title = tv.getTitleText();
                String idNumber = tv.getIdNum();
                if (!firstname.equals("") && !lastname.equals("") && !firstname.equals("") && !address.equals("") && !country.equals("") && !city.equals("") && !idType.equals("") && !gender.equals("") && !title.equals("") && !idNumber.equals("")) {

                    tm.newUser(title, firstname, lastname, gender, idType, idNumber, address, city, country);
                    cid = tm.getCid(idNumber);
                    if (cid != null) {
                        firstPress = true;
                    }
                    stageTwo(cid);
                } else {
                    JOptionPane.showMessageDialog(tv, "Fill in all values first");
                }
            }
        }

        private void stageTwo(String firstId) {
            tv.firstnameField.setEditable(false);
            tv.lastnameField.setEditable(false);
            tv.idNumField.setEditable(false);
            tv.addressField.setEditable(false);
            tv.cityField.setEditable(false);
            tv.countryField.setEditable(false);
            tv.custIdField1.setEditable(false);

            tv.custIdField2.setEditable(true);
            tv.custIdField2.setText(firstId);
            tv.custIdField2.setEditable(false);

            transDate = tm.getDate();
            tv.transDateField.setText(transDate);

        }
    }

    class NextListener2 implements ActionListener {

        Statement st;

        public void actionPerformed(ActionEvent ae) {
            //System.out.println("Second Next button pressed");
            if (firstPress) {
                // search to make sure room selected is vacant
                roomType = tv.getRoomType();

                // if it is not vacant
                if (tm.getRoomCount(roomType) == 0) {
                    //print error message
                    JOptionPane.showMessageDialog(tv, "No More " + roomType + " Rooms Available");
                } else {
                    //show it in the form
                    int count = tm.getRoomCount(roomType);
                    tv.roomsAvailableField.setText(String.valueOf(count));

                    //calculate the price and show it
                    double price = tm.getRoomPrice(roomType);
                    tv.priceField.setText(String.valueOf(price));

                    //calculate and display length of stay
                    d1 = (Date) tv.checkInSpinner.getValue();
                    d2 = (Date) tv.checkOutSpinner.getValue();
                    int duration = tm.getDuration(d2, d1);
                    tv.durationField.setText(duration + " days");

                    //calculate total cost and show it
                    totalCost = price * duration;
                    tv.totalCostField.setText(String.valueOf(totalCost) + "GHC");

                    //Open Text Fields for input
                    tv.amountPaidField.setEnabled(true);
                    tv.paymentIdField.setEnabled(true);

                    secondPress = true;

                }
            } else {
                JOptionPane.showMessageDialog(tv, "You must finish with the customer information first");
            }

        }
    }

    class SaveListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            //System.out.println("Save button pressed");
            if (secondPress) {
                try {
                    //get amount paid and payment id
                    String amountPaidString = tv.amountPaidField.getText();
                    String paymentId = tv.getPayId();
                    if ((paymentId != null) && (!paymentId.equals("")) && (amountPaidString != null) && (!amountPaidString.equals(""))) {
                        double amountPaid = Double.parseDouble(tv.amountPaidField.getText());
                        if (amountPaid < totalCost) {
                            JOptionPane.showMessageDialog(tv, "Amount paid must be equal to Total cost");
                            clearFields();
                        } else {
                            String dateIn = tm.getDateString(d1);
                            String dateOut = tm.getDateString(d2);
                            String paymentType = tv.getPaymentType();
                            //paymentId = tv.getPayId();
                            String transType = tv.getTransType();
                            cid = tv.custIdField1.getText();
                            int rid = tm.getRoomFor(roomType, cid);
                            tm.newTransaction(cid, rid, transDate, dateIn, dateOut, roomType, paymentType, amountPaid, transType, paymentId);
                            clearFields();
                        }
                    } else {
                        JOptionPane.showMessageDialog(tv, "Error. Please make sure all fields are filled in correctly");
                        clearFields();
                        //tv.paymentIdField.setText("");
                        //tv.amountPaidField.setText("");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    JOptionPane.showMessageDialog(tv, "Error. Please make sure all fields are filled in correctly");
                }

            } else {
                JOptionPane.showMessageDialog(tv, "You must finish entering all information first");
            }
        }

    }

    private void clearFields() {
        tv.paymentIdField.setText("");
        tv.amountPaidField.setText("");
        tv.priceField.setText("");
        tv.durationField.setText("");
        tv.totalCostField.setText("");
    }
}