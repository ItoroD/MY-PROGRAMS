/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.models;

import com.interprogteam2017.dbconnect.DBConnect;
import com.interprogteam2017.mvc.controllers.TransactionController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Olatunde Babalola
 */
public class TransactionModel {

    Date transDate = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Statement st;

    public boolean checkId(String firstId) {
        DBConnect db = new DBConnect();
        ResultSet customerDB = db.getCustomers();
        boolean exists = false;
        try {
            while (customerDB.next()) {
                String cid = customerDB.getString("cid");
                if (cid.equals(firstId)) {
                    exists = true;
                    //System.out.println("The ID Exists");
                    break;
                } else {
                    //System.out.println("The ID does not exist");
                    exists = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    public void newUser(String title, String firstname, String lastname, String gender, String idType, String idNumber, String address, String city, String country) {
        System.out.println("Inserting into the customer table");
        DBConnect database = new DBConnect();
        Statement st;
        String insert = "INSERT INTO customer (`title`, `firstname`, `lastname`, `gender`, `id type`, `id number`, `address`, `city`, `country`) VALUES ('" + title + "','" + firstname + "','" + lastname + "','" + gender + "','" + idType + "','" + idNumber + "','" + address + "','" + city + "','" + country + "')";
        try {
            st = database.getStatement();
            st.executeUpdate(insert);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDate() {
        String returnString = ft.format(transDate);
        return returnString;
    }

    public int getDuration(Date d2, Date d1) {
        ft = new SimpleDateFormat("dd");

        String d2String = ft.format(d2);
        String d1String = ft.format(d1);
        int d2Int = Integer.parseInt(d2String);
        int d1Int = Integer.parseInt(d1String);
        int duration = d2Int - d1Int;
        if (duration > 0) {
            return duration;
        } else {
            duration = 1;
            return duration;
        }
    }

    public String getCid(String idNumber) {
        DBConnect db = new DBConnect();
        ResultSet rs;
        int cid = 0;
        try {
            db.reconnect();
            String query = "SELECT * FROM customer where `id number` = '" + idNumber + "'";
            System.out.println(query);
            Statement st = db.getStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                cid = rs.getInt("cid");
                //System.out.println(cid);

                //stageTwo(String.valueOf(cid));
                //firstPress = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
        return String.valueOf(cid);
    }

    public int getRoomCount(String roomType) {
        DBConnect db = new DBConnect();
        ResultSet rs = db.getRooms();
        int count = 0;
        st = db.getStatement();
        String query = "SELECT count(*) FROM room where `room type` = '" + roomType + "' and `room status` = 'Vacant'";
        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int getRoomFor(String roomType, String cid) {
        int cidInt = Integer.parseInt(cid);
        DBConnect db = new DBConnect();
        ResultSet rs = db.getRooms();
        int rid = 0;
        st = db.getStatement();
        String query = "SELECT * FROM room where `room type` = '" + roomType + "' and `room status` = 'Vacant'";
        int x = 0;
        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                rid = rs.getInt("rid");
                String update = "UPDATE `room` SET `room status`='Occupied', `occupant`='" + cidInt + "' WHERE `rid`='" + rid + "'";
                //System.out.println(update);
                st.executeUpdate(update);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rid;
    }

    public double getRoomPrice(String roomType) {
        DBConnect db = new DBConnect();
        ResultSet rs = db.getRoomPrices();
        String query = "SELECT * FROM room_prices where `Type` = '" + roomType + "'";
        try {
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        double price = 0;
        try {
            while (rs.next()) {
                price = rs.getDouble("Price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public String getDateString(Date d1) {
        ft = new SimpleDateFormat("yyyy-MM-dd");
        String returnString = ft.format(d1);
        return returnString;
    }

    public void newTransaction(String cid, int rid, String transDate, String dateIn, String dateOut, String roomType, String paymentType, double amountPaid, String transType, String paymentId) {
        //System.out.println("Inserting into the transaction table");
        DBConnect database = new DBConnect();
        Statement st;
        String insert = "INSERT INTO `transactions` (`Customer ID`, `Room ID`, `Date/Time`, `Date In`, `Date out`, `Room Type`, `Payment Type`, `Amount Paid`, `Transaction Type`,`Payment ID`) VALUES ('" + cid + "', '" + rid + "', '" + transDate + "', '" + dateIn + "', '" + dateOut + "', '" + roomType + "', '" + paymentType + "', '" + amountPaid + "', '" + transType + "', '" + paymentId + "')";
        //System.out.println(insert);
        try {
            st = database.getStatement();
            st.executeUpdate(insert);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}