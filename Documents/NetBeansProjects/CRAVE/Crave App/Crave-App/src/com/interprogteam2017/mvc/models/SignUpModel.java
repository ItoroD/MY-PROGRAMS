/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.models;

import dataStorageModel.User;
import java.util.Arrays;
import java.util.HashMap;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kobby Annor
 */
public class SignUpModel implements Serializable {

    private static final long serialVersionUID = 1L;
    //SignUpView newSignUpView = new SignUpView();
    static HashMap<String, User> userList = new HashMap<String, User>();

    public SignUpModel() {
    }

    private HashMap<String, User> readAgain() {
        HashMap h = null;
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
            //e.printStackTrace();
            FileOutputStream fo;
            try {
                fo = new FileOutputStream("userlist.txt");
                //ObjectOutputStream output = new ObjectOutputStream(fo);
            } catch (Exception ex) {
                Logger.getLogger(SignUpModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return h;

    }

    public SignUpModel(String firstname, String surname, String username, char[] pass1, char[] pass2, String email, String userType) {

        userList = readAgain();
        if(userList == null)
        {
        userList = new HashMap<String, User>();
        }
        
        username = username.replace(" ", "");
        if (!userList.containsKey(username)) {
            if (Arrays.equals(pass1, pass2)) {
                User newUser = new User(firstname, surname, username, pass1, pass2, email, userType);
                userList.put(username, newUser);
                writeToFile(userList);
                writeAll(userList);
            } else {
                System.err.println("Passwords Do not match. Please re-enter passwords. Thank you");
            }
        } else {
            //JOptionPane.showMessageDialog(newSignUpView, "UserName Exists Already!");
        }
    }

    public void writeToFile(HashMap<String, User> map) {
        try {
            try (
                    PrintWriter printUsernamesToFile = new PrintWriter(new BufferedWriter(new FileWriter("username_passwords.txt", true)))) {
                // PrintWriter printUsernamesToFile = new PrintWriter(new BufferedWriter(new FileWriter("D:/test.txt", true)))) {
                map.entrySet().stream().map((m) -> {
                    //printUsernamesToFile.println(m.getKey() + " ");
                    return m;
                }).forEach((m) -> {
                    printUsernamesToFile.println(m.getValue().getFirstName());
                    printUsernamesToFile.println(m.getValue().getSurname());
                    printUsernamesToFile.println(m.getValue().getEmail());
                    printUsernamesToFile.println(m.getValue().getUsername());
                    printUsernamesToFile.println(m.getValue().getPassword());
                    printUsernamesToFile.println(m.getValue().getUserType());

                });
            }
        } catch (IOException e) {
            System.out.println("The file is unavailable.");
        }

    }

    private void writeAll(HashMap<String, User> user) {
        try {
            FileOutputStream fo = new FileOutputStream("userlist.txt");
            ObjectOutputStream output = new ObjectOutputStream(fo);
            output.writeObject(user);
            output.close();
            fo.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
