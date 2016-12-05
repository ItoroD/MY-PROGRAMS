/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interprogteam2017.mvc.models;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Simon
 */
public class RoomTableModel extends AbstractTableModel {

    //create an object of MyTableModel
    private static RoomTableModel instance = null;

    private ArrayList columnNames;
    // the label strings
    private ArrayList<Room> data;
    // arraylist of arraylists
  

    private RoomTableModel() {
        super();
        columnNames = new ArrayList();
        columnNames.add("Room ID");
        columnNames.add("Room Type");
        columnNames.add("Room Status");
        columnNames.add("Room Price");
        columnNames.add("Occupant");
        data = new ArrayList<>();
        
        //dataHash = new HashMap<>(50, (float) 0.5);
    }

    //Get the only object available
    public static RoomTableModel getInstance() {
        if(instance == null)
            instance = new RoomTableModel();
        
        return instance;
    }

    public ArrayList<Room> getData() {
        return data;
    }

    public void setData(Room room) {
        data.add(new Room(room.getRid(), room.getRoomType(), room.getRoomStatus(),
        room.getRoomPrice(), room.getOccupant()));
        fireTableRowsInserted(0, 0);
    }
    
    public boolean emptyList(){
        return data.removeAll(data);
    }

    @Override
    public String getColumnName(int col) {
        return (String) columnNames.get(col);
    }

    @Override
    public int getColumnCount() {
        return (columnNames.size());
    }

    @Override
    public int getRowCount() {
        return (data.size());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //return dataHash.get(row)[col];

        Room roomRow = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return roomRow.getRid();
            case 1:
                return roomRow.getRoomType();
            case 2:
                return roomRow.getRoomStatus();
            case 3:
                return roomRow.getRoomPrice();
            case 4:
                return roomRow.getOccupant();
            default:
                return null;
        }
    }

    
    // uses this to determine the default renderer or 
    // editor for each cell. 
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
//implement this to be able to alter your table data!

    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Room roomRow = data.get(rowIndex);
        if (columnIndex == 0) {
            roomRow.setRid((String) aValue);
        } else if (columnIndex == 1) {
            roomRow.setRoomType((Room.RoomType) aValue);
        } else if (columnIndex == 2) {
            roomRow.setRoomStatus((Room.RoomStatus) aValue);
        } else if (columnIndex == 3) {
            roomRow.setRoomPrice((double) aValue);
        }else if (columnIndex == 4) {
            roomRow.setOccupant((int) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
       
    }
    
}
