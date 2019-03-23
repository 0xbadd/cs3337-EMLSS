package frontend;

import backend.simulation.GenerateItem;

public class Map {
	public GenerateItem item = new GenerateItem();
 private Location[][] grid = new Location[100][100];
public void setMap() {
	//B = building, S = street , A = ambulance, H = hospital, P =  patient, O = origin or homebase. for values
	for (int count =0;count<grid.length;count++) {
		for(int index=0;index<grid.length;index++) {
			grid[count][index] = new Location(count,index,'B');
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
	for(int count =10;count<14;count++) {
		for(int index=10;index<16;index++) {
			grid[count][index].setValue('H');
		}
	}
	for(int count =60;count<64;count++) {
		for(int index=20;index<26;index++) {
			grid[count][index].setValue('H');
		}
	}
	for(int count =50;count<54;count++) {
		for(int index=90;index<96;index++) {
			grid[count][index].setValue('H');
		}
	}
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

public boolean checkIfAvailable (double x, double y){

		return true;
	}
}

