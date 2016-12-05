/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc;

import com.interprogteam2017.mvc.controllers.MenuController;
import com.interprogteam2017.mvc.models.MenuModel;
import com.interprogteam2017.mvc.views.MenuView;
import dataStorageModel.User;
import javax.swing.JFrame;

/**
 *
 * @author OElA
 */
public class MenuMVC {

    MenuModel mm = new MenuModel();
    MenuView mv = new MenuView();
    MenuController mc;

    public static void main(String[] args) {

        MenuModel mm = new MenuModel();
        MenuView mv = new MenuView();
        MenuController mc = new MenuController(mv, mm);
        mc.start();

    }

    public MenuView getView() {
        return mv;
    }
    public MenuModel getModel() {
        return mm;
    }

    public void start(User u) {
        mv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuController mc = new MenuController(mv, mm);
        mc.start(u);
    }

    public void start() {
        mv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuController mc = new MenuController(mv, mm);
        mc.start();
    }
    public MenuMVC() {
        mv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuController mc = new MenuController(mv, mm);
        //mc.start();
    }

    
   
}
