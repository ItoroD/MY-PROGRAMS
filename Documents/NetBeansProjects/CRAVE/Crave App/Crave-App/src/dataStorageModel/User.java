/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStorageModel;

//import com.interprogteam2017.mvc.views.SignUpView;
import java.io.Serializable;
//import javax.swing.JOptionPane;

/**
*
 * @author Olatunde Babalola
 */
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

//    SignUpView newSignUpView = new SignUpView();
    private String firstName;
    private String surname;
    private String username;
    private char[] password;
    private String email;
    private String userType;
    public boolean hasRest = false;
    public int restID = 0;
    public String restName = null;

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
    public String getUserType() {
        return userType;
    }

    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public User()
    { 
    }
    public User(String firstname, String surname, String username, char[] pass1, char[] pass2, String email, String userType) {
        this.firstName = firstname;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = pass1;
        this.userType = userType;
//        JOptionPane.showMessageDialog(newSignUpView, "User " + username + " created succesfully.");
    }

    public String getFullName() {
        return firstName + " " + surname;
    }
}
