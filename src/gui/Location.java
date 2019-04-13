package gui;

import backend.mainController.Point;

public class Location {
    private int row, column;
    private char value;
    private Object object;

    //integers record position,char value records  what is in the position
    public Location(Point p, char val, Object inObj) {
        //constructor for location
        column = p.getY();
        row = p.getX();
        value = val;
        if (inObj != null) {
            object = inObj;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getValue() {
        return value;
    }

    public Object getObject() {
        return object;
    }

    public void setRow(int input) {
        row = input;
    }

    public void setColumn(int input) {
        column = input;
    }

    public void setValue(char input) {
        value = input;
    }

    public void setNewLocation(int y, int x, char val) {
        column = y;
        row = x;
        value = val;
    }

    public void setObject(Object obj) {
        object = obj;
    }


}
