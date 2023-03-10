//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class videoGame implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 650;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image burgerPic;
    public Image planktonPic;
    public Image spongebobPic;
    public Image background;
    public Image garyPic;
public Image dirtyBubblePic;
public Image manRayPic;
    public enemy plankton;
    public enemy[] planktonArray;
    public enemy manRay;
    public burger krabbyPatty;

    public burger[] krabbyPattyArray;
    public Player spongeBob;
    public powerup gary;
    public powerup[] garyArray;
    public powerdown dirtyBubble;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        videoGame myApp = new videoGame();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public videoGame() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        background = Toolkit.getDefaultToolkit().getImage("BikiniBottom.jpeg");
        burgerPic = Toolkit.getDefaultToolkit().getImage("krabbyPatty.png");
        planktonPic = Toolkit.getDefaultToolkit().getImage("Plankton.png");
        spongebobPic = Toolkit.getDefaultToolkit().getImage("spongebob.png");
        garyPic = Toolkit.getDefaultToolkit().getImage("Gary.png");
        dirtyBubblePic = Toolkit.getDefaultToolkit().getImage("dirtyBubble.png");
        manRayPic = Toolkit.getDefaultToolkit().getImage("manRay.png");

        //create (construct) the objects needed for the game
        plankton = new enemy(250, 300, 4, 4, planktonPic);
        krabbyPatty = new burger(400, 300, 3, -4, burgerPic);
        spongeBob = new Player(50, 50, 0, 0, spongebobPic);
        gary = new powerup(100, 325, 2, 5, garyPic);
        dirtyBubble = new powerdown(500, 250, 3, 7, dirtyBubblePic);
        manRay = new enemy(220, 250, 12, 12, manRayPic);

        planktonArray = new enemy[7];

        for(int i=0; i<planktonArray.length; i++){
            planktonArray[i] = new enemy((int)(Math.random()*800), (int)(Math.random()*600), 8, 8, planktonPic );
        }

        garyArray = new powerup[3];

        for(int i=0; i<garyArray.length; i++){
            garyArray[i] = new powerup((int)(Math.random()*700), (int)(Math.random()*500), 10, 10, garyPic );
        }

        krabbyPattyArray = new burger[10];

        for(int i=0; i<krabbyPattyArray.length; i++){
            krabbyPattyArray[i] = new burger((int)(Math.random()*400), (int)(Math.random()*250), 9, 4, krabbyPatty.pic );
        }



    } // videoGame()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        plankton.move();
        krabbyPatty.move();
        spongeBob.move();
        gary.move();
        dirtyBubble.move();
        manRay.move();
        for (int i = 0; i < planktonArray.length; i++) {
            planktonArray[i].move();
        }

        for (int i = 0; i < garyArray.length; i++) {
            garyArray[i].move();
        }

        for (int i = 0; i < krabbyPattyArray.length; i++) {
            krabbyPattyArray[i].move();
        }
    }

    public void collision (){

        if (spongeBob.rec.intersects(plankton.rec) && spongeBob.isIntersecting==false) {
            spongeBob.isIntersecting = true;
            spongeBob.isAlive=false;
            System.out.println("Spongebob is dead");
        }

    }


    public void checkIntersections() {

    }

    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();               // paint the graphics
            pause(20);         // sleep for 20 ms
            collision();
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw characters to the screen
        g.drawImage(background, 0, 0, 1000, 660, null);

        if(spongeBob.isAlive==true){
            g.drawImage(spongebobPic, spongeBob.xpos, spongeBob.ypos, spongeBob.width, spongeBob.height, null);
        }
        g.drawImage(plankton.pic, plankton.xpos, plankton.ypos, plankton.width, plankton.height, null);

        for(int i=0; i<planktonArray.length; i++){
            g.drawImage(planktonArray[i].pic, planktonArray[i].xpos, planktonArray[i].ypos, planktonArray[i].width, planktonArray[i].height, null);
        }

        for(int i=0; i<garyArray.length; i++){
            g.drawImage(garyArray[i].pic, garyArray[i].xpos, garyArray[i].ypos, garyArray[i].width, garyArray[i].height, null);
        }

        for(int i=0; i<krabbyPattyArray.length; i++){
            g.drawImage(krabbyPattyArray[i].pic, krabbyPattyArray[i].xpos, krabbyPattyArray[i].ypos, krabbyPattyArray[i].width, krabbyPattyArray[i].height, null);
        }

        g.drawImage(krabbyPatty.pic, krabbyPatty.xpos, krabbyPatty.ypos, krabbyPatty.width, krabbyPatty.height, null);
        g.drawImage(gary.pic, gary.xpos, gary.ypos, gary.width, gary.height, null);
        g.drawImage(dirtyBubble.pic, dirtyBubble.xpos, dirtyBubble.ypos, dirtyBubble.width, dirtyBubble.height, null);
        g.drawImage(manRay.pic, manRay.xpos, manRay.ypos, manRay.width, manRay.height, null);



        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // arrow
            spongeBob.right = true;
        }
        if (keyCode == 65) { // arrow
            spongeBob.left = true;
        }

        if (keyCode == 83) { // arrow
            spongeBob.down = true;
        }
        if (keyCode == 87) { // arrow
            spongeBob.up = true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            spongeBob.right = false;
        }
        if (keyCode == 65) { // a
            spongeBob.left = false;
        }
        if (keyCode == 83) { // s
            spongeBob.down = false;
        }
        if (keyCode == 87) { // w
            spongeBob.up = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("videoGame");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//class
