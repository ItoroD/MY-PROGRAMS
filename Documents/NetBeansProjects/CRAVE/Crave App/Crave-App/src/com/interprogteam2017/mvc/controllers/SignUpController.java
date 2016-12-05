/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.controllers;

/**
 *
 * @author Kobby Annor
 */
import com.interprogteam2017.mvc.views.*;
import com.interprogteam2017.mvc.models.SignUpModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SignUpController implements ActionListener {

    SignUpView newSignUpView;
    SignUpModel newSignUpModel;
    ActionListener actionListener;
    MenuView mv;

    public SignUpController(SignUpView newSignUpView, SignUpModel newSignUpModel) {
        this.newSignUpView = newSignUpView;
        this.newSignUpModel = newSignUpModel;
    }

    public void control() {
        newSignUpView.getSubmitButton().addActionListener(this);
        newSignUpView.getBackButton().addActionListener(this);
        newSignUpView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent newActionEvent) {

        if (newActionEvent.getSource() == newSignUpView.getBackButton()) {
            newSignUpView.TxtFirstName.setText("");
            newSignUpView.TxtSurname.setText("");
            newSignUpView.TxtEmailAddy.setText("");
            newSignUpView.TxtUserName.setText("");
            newSignUpView.jPasswordField1.setText("");
            newSignUpView.jPasswordField2.setText("");
            newSignUpView.dispose();
        }

        if (newActionEvent.getActionCommand().equalsIgnoreCase("Submit")) {
            String firstname = newSignUpView.getTxtFirstName();
            String surname = newSignUpView.getTxtSurname();
            String emailAddress = newSignUpView.getTxtEmailAddress();
            String username = newSignUpView.getTxtUserName();
            char[] password1 = newSignUpView.getPassword();
            char[] password2 = newSignUpView.getPasswordRepeat();
            String userType = newSignUpView.getUserType();
            
            if (newSignUpView.getTxtFirstName().length() > 0 && newSignUpView.getTxtSurname().length() > 0
                    && newSignUpView.getTxtEmailAddress().length() > 0
                    && newSignUpView.getTxtUserName().length() > 0
                    && newSignUpView.getPassword().length > 0
                    && newSignUpView.getPasswordRepeat().length > 0) {

                newSignUpModel = new SignUpModel(firstname, surname, username, password1, password2, emailAddress,userType);
            } else {
                JOptionPane.showMessageDialog(newSignUpView, "Please check to see if you've entered all the necessary information."
                        + " One or more required information have not been entered yet. Thank you.");
            }
            newSignUpView.TxtFirstName.setText(null);
            newSignUpView.TxtFirstName.setText(null);
            newSignUpView.TxtSurname.setText(null);
            newSignUpView.TxtEmailAddy.setText(null);
            newSignUpView.TxtUserName.setText(null);
            newSignUpView.jPasswordField1.setText(null);
            newSignUpView.jPasswordField2.setText(null);
        }
    }
}
