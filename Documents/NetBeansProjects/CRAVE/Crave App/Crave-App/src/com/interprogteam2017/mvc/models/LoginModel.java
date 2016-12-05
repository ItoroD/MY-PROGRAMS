package com.interprogteam2017.mvc.models;

import com.interprogteam2017.mvc.views.MenuView;
import com.interprogteam2017.mvc.views.SignUpView;
import java.io.*;
import dataStorageModel.User;
import java.util.HashMap;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Olatunde Babalola
 */
import com.interprogteam2017.mvc.MenuMVC;
import com.interprogteam2017.mvc.RestaurantMVC;
import com.interprogteam2017.mvc.views.LoginView;
import com.interprogteam2017.mvc.views.restInfoView;

public class LoginModel {

    MenuMVC mvc = new MenuMVC();
    MenuView newMenuView = new MenuView();
    RestaurantMVC restMVC = new RestaurantMVC();
    SignUpView newSignUpView = new SignUpView();
    HashMap<String, User> h = new HashMap<String, User>();

    private String username;
    private char[] password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public void login(String u, char[] p,LoginView lv) {
        HashMap<String, User> i;
        if (validateUsername(u) == true) {
            boolean custType = validatePassword(u, p);
            if (custType) {
                i = readAgain2(h);
                if (i.get(u).getUserType().equals("Customer")) {
                    mvc.start(i.get(u));
                    lv.dispose();
                } else if (i.get(u).getUserType().equals("Restaurant")) {
                    if (!i.get(u).hasRest) {
                        restInfoView.start(i.get(u), i, restMVC);
                        lv.dispose();
                    }
                    i = readAgain2(h);
                    if (i.get(u).hasRest) {
                        restMVC.start(i.get(u));
                        lv.dispose();
                    }

                }

            } else {
                JOptionPane.showMessageDialog(newMenuView, "Password is incorrect");
            }

        } else {
            JOptionPane.showMessageDialog(newMenuView, "User Does Not Exist");
        }
    }

    public boolean validateUsername(String username) {
        boolean validate = false;
        HashMap i;
        i = readAgain2(h);
        readAgain(h);
        
        if (i.get(username) != null) {
            validate = true;
        } else {
            validate = false;
        }
        return validate;
    }

    public boolean validatePassword(String u, char[] password) {
        boolean validate = false;
        HashMap i;
        i = readAgain2(h);
        readAgain(h);
        User x = (User) i.get(u);
        String pass = new String(password);
        String compare = new String(x.getPassword());
        if (compare.equals(pass)) {
            validate = true;
        } else {
            validate = false;
        }

        return validate;
    }

    private void readAgain(HashMap<String, User> h) {
        try {

            FileInputStream fi = new FileInputStream("userlist.txt");
            ObjectInputStream input = new ObjectInputStream(fi);
            /*
            User u;
            try {
                while (true) {
                    u = (User) input.readObject();
                    h.put(u.getUsername(), u);
                }
            } catch (Exception e) {
               //e.printStackTrace();
            }*/
            h = (HashMap<String, User>) input.readObject();
            fi.close();
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
     private HashMap<String,User> readAgain2(HashMap<String, User> h) {
        try {

            FileInputStream fi = new FileInputStream("userlist.txt");
            ObjectInputStream input = new ObjectInputStream(fi);
            /*
            User u;
            try {
                while (true) {
                    u = (User) input.readObject();
                    h.put(u.getUsername(), u);
                }
            } catch (Exception e) {
               //e.printStackTrace();
            }*/
            h = (HashMap<String, User>) input.readObject();
            fi.close();
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return h;
    }

}
