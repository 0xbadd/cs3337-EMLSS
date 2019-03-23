package backend.simulation;
import frontend.*;
import javafx.geometry.Point2D;

import java.util.Random;

// this class has the ability
// to generate an item and place it on map, is serves as the link between simulation back end and front end
public class GenerateItem {
    //we try first by trying to palce a home base on map
    Map m1 = new Map();

    HomeBase testBase; // this eventually would become an array of home bases , we set the

    public void generateHomebase (){
        Random rand = new Random();
        double x=rand.nextDouble(); //depending how many "spaces" we have on the grid, for example if 50x50 spaces available
        double y=rand.nextDouble();
        if (m1.checkIfAvailable(x,y)==false) {
            while(!(m1.checkIfAvailable(x,y))){
                x=rand.nextInt(50);
                y=rand.nextInt(50);
            }
        }
        else{
            Point2D location= new Point2D(x,y);
            testBase= new HomeBase(1, location, 1);

            //place homebase on map.

        }

    }
}
