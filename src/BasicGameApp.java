//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
//lowk fix the spawn so itll be on a grid at some point


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener {//

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image snakePic;
    public Image foodPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	public Snake head;
    public SnakeBody rope;
    public Food Apple;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		snakePic = Toolkit.getDefaultToolkit().getImage("snekBody.png");//load the picture
        foodPic= Toolkit.getDefaultToolkit().getImage("Red_Square.png");
            Apple=new Food(700,500);
            head = new Snake(500, 100);
            rope=new SnakeBody(480,100);


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
            crashing();
         render();  // paint the graphics
         pause(20);// sleep for 10 ms
		}
	}
    public void crashing(){
if(head.hitbox.intersects(Apple.hitbox)){
    Apple.isEaten=true;
}
    }


	public void moveThings()
	{
      //calls the move( ) code in the objects
		head.move();
        rope.move();
        pause(50);
       // Teleporting();
       betterMove();

	}
    public void Teleporting(){//make work for up left, down right,left down
        if(rope.xpos+20>head.xpos){
            rope.xpos=rope.xpos-20;
        }
        if(rope.xpos+20<head.xpos){
            rope.xpos=rope.xpos+20;
        }
        if(rope.ypos+21> head.ypos){
            rope.ypos= rope.ypos-20;
        }
        if(rope.ypos-20<head.ypos){
            rope.ypos=rope.ypos+20;
        }
        if(rope.xpos>head.xpos&&rope.ypos>head.ypos&&head.dy==0){
            rope.ypos= rope.ypos-20;
        }
        if(rope.xpos<head.xpos&&rope.ypos>head.ypos&&head.dy==0){
            rope.ypos= rope.ypos-20;
        }

        if(rope.xpos>head.xpos&&rope.ypos>head.ypos&&head.dy==0){
            rope.ypos= rope.ypos-20;
        }
        if(rope.xpos== head.xpos&&rope.ypos< head.ypos){
            rope.ypos= rope.ypos+20;
            rope.xpos= rope.xpos+20;
        }
        if(rope.xpos<head.xpos&&rope.ypos<head.ypos&&head.dx==0){
            rope.xpos= rope.xpos+20;
        }
        if(rope.xpos>head.xpos&&rope.ypos>head.ypos){
           rope.xpos= rope.xpos-20;
        }
        if(rope.xpos<head.xpos&&rope.ypos>head.ypos){
            rope.xpos= rope.xpos+20;
        }







    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();
       canvas.addKeyListener(this);
       canvas.addMouseListener(this);
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


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the astronaut
		g.drawImage(snakePic, head.xpos, head.ypos, head.width, head.height, null);
        g.drawImage(snakePic, rope.xpos, rope.ypos, rope.width, rope.height, null);
        g.drawRect(head.hitbox.x, head.hitbox.y, head.width, head.height);
        if (Apple.isEaten == false) {//make reappear when eaten
            g.drawImage(foodPic, Apple.xpos, Apple.ypos, Apple.width, Apple.height, null);
            g.drawRect(Apple.hitbox.x, Apple.hitbox.y, Apple.width, Apple.height);
        }
		g.dispose();

		bufferStrategy.show();
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode()==38){
            head.isNorth=true;
            head.isSouth=false;
            head.isEast=false;
            head.isWest=false;
            System.out.println("goin up");
            head.dy=-Math.abs(head.dy);

        }
        if (e.getKeyCode()==40){
            head.isSouth=true;
            head.isNorth=false;
            head.isEast=false;
            head.isWest=false;
            System.out.println("goin down");
            head.dy=Math.abs(head.dy);
        }
        if(e.getKeyCode()==39){
            head.isEast=true;
            head.isSouth=false;
            head.isNorth=false;
            head.isWest=false;
            System.out.println("goin east");
            head.dx=Math.abs(head.dx);
        }
        if(e.getKeyCode()==37){
            head.isWest=true;
            head.isSouth=false;
            head.isEast=false;
            head.isNorth=false;
            System.out.println("goin west");
            head.dx=-Math.abs(head.dx);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void betterMove(){
       if (head.dx==20){
           rope.xpos=head.xpos-20;
           rope.ypos=head.ypos;
       }
       if (head.dx==-20){
           rope.xpos=head.xpos+20;
           rope.ypos=head.ypos;
       }
       if (head.dy==20){
           rope.ypos=head.ypos-20;
           rope.xpos=head.xpos;
       }
       if(head.dy==-20){
           rope.ypos=head.ypos+20;
           rope.xpos=head.xpos;
       }
    }
}