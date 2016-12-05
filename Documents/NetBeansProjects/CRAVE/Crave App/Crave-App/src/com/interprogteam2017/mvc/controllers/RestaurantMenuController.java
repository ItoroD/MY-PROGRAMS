/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.controllers;

import com.interprogteam2017.dbconnect.DBConnect;
import com.interprogteam2017.mvc.models.LoginModel;
import com.interprogteam2017.mvc.models.RestaurantMenuModel;
import com.interprogteam2017.mvc.views.EditDishesView;
import com.interprogteam2017.mvc.views.LoginView;
import com.interprogteam2017.mvc.views.MenuView;
import com.interprogteam2017.mvc.views.RestaurantMenuView;
import com.interprogteam2017.mvc.views.SignUpView;
import com.interprogteam2017.mvc.views.addDishView;
import com.interprogteam2017.mvc.views.restInfoView;
import dataStorageModel.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author OElA
 */
public class RestaurantMenuController {

    static RestaurantMenuModel rmm = new RestaurantMenuModel();
    static RestaurantMenuView rmv = new RestaurantMenuView();
    static addDishView adv = new addDishView();
    static User u;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DBConnect db = new DBConnect();

    public RestaurantMenuController(RestaurantMenuView rmv, RestaurantMenuModel rmm) {
        this.rmm = rmm;
        this.rmv = rmv;

        this.rmv.addSearchListener(new SearchListener());

    }

    public void start(User u) {
        this.rmv.addDishBtn.addActionListener(new MenuItemListener());
        this.rmv.editRestBtn.addActionListener(new MenuItemListener());
        this.rmv.exitBtn.addActionListener(new MenuItemListener());
        ///this.rmv.editDishesBtn.addActionListener(new MenuItemListener());
        this.rmv.addAcceptListener(new AcceptListener());
        this.rmv.addChangeListener(new ChangeBtnListener());

        this.u = u;
        rmv.pack();
        rmv.setVisible(true);
    }

    public void start() {
        this.u = u;
        rmv.pack();
        rmv.setVisible(true);
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
            String search = rmv.getSearchText();
            String sql = "SELECT Name,Price,Available as `Available?` FROM crave.dishes where dishes.rid = " + u.restID + " and Name like '%"+search+"%'";
            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                rmv.getTable().setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                Logger.getLogger(MenuView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static class AcceptListener implements ActionListener {

        public AcceptListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
        }
    }

    private static class ChangeBtnListener implements ActionListener {

        public ChangeBtnListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class MenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if ("Edit Restaurant Details".equals(ae.getActionCommand())) {
                restInfoView.start(u);
            } else if ("Add Dish".equals(ae.getActionCommand())) {
                adv.start(u, rmv);
                adv.setVisible(true);
            } else if ("Edit Dish".equals(ae.getActionCommand())) {
                EditDishesView.start(u);
            } else if ("Exit".equals(ae.getActionCommand())) {
                rmv.dispose();
                LoginModel lm = new LoginModel();
                LoginView lv = new LoginView();
                SignUpView suv = new SignUpView();
                LoginController lc = new LoginController(lm, lv, suv);
                lc.control();
                lv.pack();
            }

        }
    }
}
