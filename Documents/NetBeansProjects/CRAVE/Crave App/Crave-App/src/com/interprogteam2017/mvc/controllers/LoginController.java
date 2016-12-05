
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kobby Annor
 */
package com.interprogteam2017.mvc.controllers;

import com.interprogteam2017.mvc.models.*;
import com.interprogteam2017.mvc.views.LoginView;
import com.interprogteam2017.mvc.views.SignUpView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javafx.application.Platform.exit;

public class LoginController implements ActionListener {

    SignUpModel sum;
    LoginModel lm;
    LoginView lv;
    SignUpView suv;
    ActionListener actionListener;

    public LoginController(LoginModel lm, LoginView lv, SignUpView suv) {
        this.lv = lv;
        this.lm = lm;
        this.suv = suv;
    }

    public void control() {
        lv.getLoginButton().addActionListener(this);
        lv.getSignupButton().addActionListener(this);
        lv.getCloseButton().addActionListener(this);

        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        };
        lv.getCloseButton().addActionListener(actionListener);
        lv.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent newActionEvent) {
        if (newActionEvent.getActionCommand().equalsIgnoreCase("Login")) {
            String username = lv.getUserName();
            char[] password = lv.getUserPassword();

            lm.login(username, password, lv);
            lv.pFPassword.setText("");
            lv.txtUserName.setText("");
        }

        if (newActionEvent.getActionCommand().equalsIgnoreCase("Sign Up")) {
            SignUpController suc = new SignUpController(suv, sum);
            suc.control();
            suv.setVisible(true);
        }
        
        if (newActionEvent.getActionCommand().equalsIgnoreCase("Close")) {
            exit();
        }
        
        
    }
}
