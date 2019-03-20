package frontend;

public class Location {
	private int row,column;
	private char value;
	//integers record position,char value records  what is in the position
	public Location(int y,int x,char val) {
		//constructor for location
		column=y;
		row=x;
		value=val;
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
	public void setRow(int input) {
		row=input;
	}
	public void setColumn(int input) {
		column = input;
	}
	public void setValue(char input) {
		value=input;
	}
	public void setNewLocation(int y,int x,char val) {
		column=y;
		row=x;
		value=val;
	}


}
