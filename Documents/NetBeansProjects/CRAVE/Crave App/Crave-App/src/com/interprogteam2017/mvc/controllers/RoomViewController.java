/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.interprogteam2017.mvc.models.Room;
import com.interprogteam2017.mvc.models.RoomTableModel;
import com.interprogteam2017.mvc.views.MenuView;
import com.interprogteam2017.mvc.views.RoomView;

/**
 *
 * @author Simon
 */

public class RoomViewController implements ActionListener {

    private RoomTableModel roomModel;
    private RoomView roomView;
    private MenuView menuView;

    public RoomViewController(RoomTableModel roomModel, RoomView roomView, MenuView menuView) {
        this.roomModel = roomModel;
        this.roomView = roomView;
        this.menuView = menuView;
        this.roomView.getMyTable().setModel(roomModel);
    }

    public void control() {
      //  menuView.getViewRoomsButton().addActionListener(this);
        roomView.getClose().addActionListener(this);
        
        menuView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //following method wont work if btn text is changed.
        if (ae.getActionCommand().equals("View Rooms")) {
            java.sql.Connection conn = null;

            System.out.println("This program demos DB connectivity");

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = java.sql.DriverManager.getConnection(
                        "jdbc:mysql://localhost/hotel_management_project?user=remote_db&password=@db4projects");

            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }

            System.out.println("Connection established");
            System.out.println("");

            try {
                java.sql.Statement s = conn.createStatement();
                java.sql.ResultSet r = s.executeQuery("SELECT * FROM hotel_management_project.room");

                while (r.next()) {
                    Room room = new Room(r.getString("rid"), Room.RoomType.valueOf(r.getString("room type")),
                            Room.RoomStatus.valueOf(r.getString("room status")), r.getDouble("room price"),
                            r.getInt("occupant"));
                    
                    RoomTableModel.getInstance().setData(room);
                }

                r.close();
                s.close();
                conn.close();

                roomView.setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
        
        if (ae.getActionCommand().equals("Close")){
            RoomTableModel.getInstance().emptyList();
            roomView.dispose();
        }

    }
    
}