/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.models;

public class Room {
    
    private String rid;
    private RoomType roomType;
    private RoomStatus roomStatus;
    private double roomPrice; 
    private int occupant;

    public static enum RoomType{
        Standard, Deluxe, Suite
    }
    public static enum RoomStatus{
        Vacant, Occupied, Reserved, OutOfOrder
    }

    public Room(String rid, RoomType roomType, RoomStatus roomStatus, double roomPrice, int occupant) {
        this.rid = rid;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
        this.occupant = occupant;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getOccupant() {
        return occupant;
    }

    public void setOccupant(int occupant) {
        this.occupant = occupant;
    }

    
}