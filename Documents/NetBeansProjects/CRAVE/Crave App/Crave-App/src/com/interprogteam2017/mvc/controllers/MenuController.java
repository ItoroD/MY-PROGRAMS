/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.controllers;

import com.interprogteam2017.dbconnect.DBConnect;
import com.interprogteam2017.mvc.models.LoginModel;
import com.interprogteam2017.mvc.models.MenuModel;
import com.interprogteam2017.mvc.models.TransactionModel;
import com.interprogteam2017.mvc.views.LoginView;
import com.interprogteam2017.mvc.views.MenuView;
import com.interprogteam2017.mvc.views.SignUpView;
import dataStorageModel.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author OElA
 */
public class MenuController {

    static MenuModel mm = new MenuModel();
    static MenuView mv = new MenuView();
    static Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DBConnect db = new DBConnect();
    static User currentUser;

    public MenuController(MenuView mv, MenuModel mm) {
        this.mm = mm;
        this.mv = mv;

    }

    public void start(User u) {

        this.mv.addSearchListener(new SearchListener());
        this.mv.addBookListener(new BookListener());
        this.mv.addExitListener(new ExitListener());
        currentUser = u;
        mv.pack();
        mv.setVisible(true);
    }

    public void start() {
        mv.pack();
        mv.setVisible(true);
    }

    private static class SearchListener implements ActionListener {

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        DBConnect db = new DBConnect();

        public SearchListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            conn = db.getConn();
            String search = mv.getSearchText();
            String sql = "Select dishes.Name, dishes.Available, dishes.price as 'Price', restaurant.Name as 'Restaurant' from dishes,restaurant where dishes.rid = restaurant.id and dishes.name like '%" + search + "%'";
            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                mv.getTable().setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                Logger.getLogger(MenuView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private static class BookListener implements ActionListener {

        public BookListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            int row = mv.getTable().getSelectedRow();
            int column = 0;
            String cell = (String) mv.getTable().getValueAt(row, column);
            System.out.println(cell + " selected");
            String available = (String) mv.getTable().getValueAt(row, 1);
            if (available.equals("No")) {
                    JOptionPane.showMessageDialog(mv, "You cannot book this, unavailable right now");
               
            } else {
                DBConnect database = new DBConnect();
                conn = database.getConn();
                Statement st = null;
                String served = "No";
                String name = currentUser.getFullName();
                String restName = (String) mv.getTable().getValueAt(row, 3);
                Statement s;
                int rid = 0;
                int did = 0;
                try {
                    s = conn.createStatement();
                    java.sql.ResultSet r = s.executeQuery("SELECT id FROM crave.restaurant where Name = '" + restName + "'");
                    while (r.next()) {
                        rid = r.getInt("id");
                    }
                    r = s.executeQuery("Select id from dishes where Name = '" + cell + "' and rid = '" + rid + "'");
                    while (r.next()) {
                        did = r.getInt("id");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }

                String insert = "INSERT INTO `crave`.`bookings` (`rid`, `dish_id`, `Customer name`, `Served?`) VALUES ('" + rid + "', '" + did + "', '" + name + "', '" + served
                        + "')";
                try {
                    st = database.getStatement();
                    st.executeUpdate(insert);
                    JOptionPane.showMessageDialog(mv, "Booking Successful");
               
                } catch (SQLException ex) {
                    Logger.getLogger(TransactionModel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    private static class ExitListener implements ActionListener {

        public ExitListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            mv.dispose();
            LoginModel lm = new LoginModel();
            LoginView lv = new LoginView();
            SignUpView suv = new SignUpView();
            LoginController lc = new LoginController(lm, lv, suv);
            lc.control();
            lv.pack();
        }
    }

}
