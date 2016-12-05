
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc;

import com.interprogteam2017.mvc.views.LoginView;
import com.interprogteam2017.mvc.controllers.LoginController;
import com.interprogteam2017.mvc.models.LoginModel;
import com.interprogteam2017.mvc.views.SignUpView;

/**
 *
 * @author
 */
public class MainClassCraveApplication {

    public static void main(String[] args) {
        LoginModel lm = new LoginModel();
        LoginView lv = new LoginView();
        SignUpView suv = new SignUpView();
        LoginController lc = new LoginController(lm, lv, suv);
        lc.control();
        lv.pack();
    }

    public void start() {
        LoginModel lm = new LoginModel();
        LoginView lv = new LoginView();
        SignUpView suv = new SignUpView();
        LoginController lc = new LoginController(lm, lv, suv);
        lc.control();
        lv.pack();
    }

}
