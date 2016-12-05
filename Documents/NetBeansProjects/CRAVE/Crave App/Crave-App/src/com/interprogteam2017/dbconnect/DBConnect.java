/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.dbconnect;

import java.sql.*;

/**
 *
 * @author OElA
 */
public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    
    
    
    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hostel", "root", " ");
            st = con.createStatement();
            //System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hostel", "root", " ");
            return con;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        
    }

    public void reconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hostel", "root", " ");
            st = con.createStatement();
            //System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Statement getStatement() {
        return st;
    }

    public ResultSet getRestaurants() {
        try {
            String query = "select * from restaurants";
            rs = st.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    

    public ResultSet getDishes() {
        try {
            String query = "select * from dishes";
            rs = st.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getRoomPrices() {
        try {
            String query = "select * from room_prices";
            rs = st.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getRooms() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet getCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
