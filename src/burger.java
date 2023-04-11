//Cheese class 
//Bounces

import java.awt.*;

public class burger {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.
    public boolean isIntersecting;
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;
    public int hits;

    // METHOD DEFINITION SECTION

    //This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public burger(int pXpos, int pYpos) {

        xpos =(int)(Math.random()*600);
        ypos = (int)(Math.random()*425);
        width = 25;
        height = 25;
        dx = 5;
        dy = -5;
        isAlive = true;
        hits = 0;
        rec = new Rectangle(xpos, ypos, width, height);


    } // constructor


    public burger(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 30;
        height = 30;
        dx = ((int)(Math.random()*5));
        dy = ((int)(Math.random()*5));
        pic = picParameter;
        isAlive = true;
        hits = 0;
        rec = new Rectangle(xpos, ypos, width, height);


    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + ((int) (Math.random()*5));
        ypos = ypos + ((int) (Math.random()*5));

        if (xpos > 1000 - width || xpos < 0) {
            dx = -dx;
        }

        if (ypos < 0 || ypos + height > 700) {
            dy = -dy;
        }

        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void wrap(){

        xpos = xpos + dx;
        ypos = ypos + dy;


        if (xpos>1000) {
            xpos =0;}

        if (xpos<0){
            xpos=1000;}

        if (ypos>600){
            ypos = 0;
        }
        if (ypos<0){
            ypos = 600;
        }
        rec = new Rectangle(xpos, ypos, width, height);

    }


} //end of the Burger object class  definition

