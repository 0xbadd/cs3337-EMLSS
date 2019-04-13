package gui;

import backend.mainController.Point;

public class Map {
    private Location[][] grid = new Location[100][100];

    public void setMap() {
        //B = building, S = street , A =  active ambulance,U = inactive ambulance H = hospital, P =  patient, O = origin or homebase. for values
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                Point p = new Point(row, column);
                grid[row][column] = new Location(p, 'B', null);
                //turning entire grid into buildings
            }
        }
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column <= grid.length; column++) {
                grid[row][column].setValue('S');
                column = column + 2;
            }

        }
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                grid[row][column].setValue('S');
            }
            row = row + 2;
        }
    }

    public void DisplayMapValues() {
        for (int count = 0; count < grid.length; count++) {
            for (int index = 0; index < grid.length; index++) {
                System.out.print(grid[count][index].getValue() + ",");
            }
            System.out.println();
        }

    }

    public Location[][] getMap() {
        return grid;
    }

    public Location findEntry(int x, int y) {
        return grid[x][y];
    }
}
