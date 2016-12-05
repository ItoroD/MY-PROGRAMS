/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc;

import com.interprogteam2017.mvc.controllers.RestaurantMenuController;
import com.interprogteam2017.mvc.models.RestaurantMenuModel;
import com.interprogteam2017.mvc.views.RestaurantMenuView;
import dataStorageModel.User;
import javax.swing.JFrame;

/**
 *
 * @author OElA
 */
public class RestaurantMVC {

    RestaurantMenuModel rmm = new RestaurantMenuModel();;
    RestaurantMenuView rmv = new RestaurantMenuView();; 
    RestaurantMenuController rmc;

    public RestaurantMVC() {
        rmv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RestaurantMenuController rmc = new RestaurantMenuController(rmv, rmm);
    }

    public static void main(String[] args) {
        RestaurantMenuModel rmm = new RestaurantMenuModel();
        RestaurantMenuView rmv = new RestaurantMenuView();
        RestaurantMenuController rmc = new RestaurantMenuController(rmv, rmm);
        rmc.start();
    }

    public void start(User u) {
        rmv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RestaurantMenuController rmc = new RestaurantMenuController(rmv, rmm);
        rmc.start(u);
        rmv.setValue(u);
    }

}
