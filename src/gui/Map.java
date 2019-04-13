package gui;

import backend.mainController.Point;

public class Map {
 private Location[][] grid = new Location[100][100];
public void setMap() {
	//B = building, S = street , A =  active ambulance,U = inactive ambulance H = hospital, P =  patient, O = origin or homebase. for values
	for (int count =0;count<grid.length;count++) {
		for(int index=0;index<grid.length;index++) {
			Point p = new Point(count,index);
			grid[count][index] = new Location(p,'B',null);
			//turning entire grid into buildings
		}
	}
	for (int count =0;count<grid.length;count++) {
		for(int index=0;index<=grid.length;index++) {
			grid[count][index].setValue('S');
			index=index+2;
		}
		
	}
	for (int count =0;count<grid.length;count++) {
		for(int index=0;index<grid.length;index++) {
			grid[count][index].setValue('S');
		}
		count=count+2;
	}
	/*
	grid[0][0].setValue('U');
	grid[0][4].setValue('O');
	grid[0][2].setValue('H');
	grid[0][3].setValue('P');
	grid[0][1].setValue('A');
*/
	

}
public void DisplayMapValues() {
	for(int count =0;count<grid.length;count++) {
		for(int index=0;index<grid.length;index++) {
			System.out.print(grid[count][index].getValue()+",");
		}
		System.out.println();
	}
	
}
public Location[][] getMap() {
	return grid;
}
public Location findEntry(int x, int y){
	return grid[x][y];
}
}
