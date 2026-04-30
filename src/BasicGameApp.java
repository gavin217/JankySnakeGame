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
	final int WIDTH = 200;
	final int HEIGHT = 200;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image snakePic;
    public Image foodPic;
    public Image orangePic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	public Snake head;
    public SnakeBody rope;
    public SnakeBody rope2;

    public Food Apple;
    public Food Apple2;
    public Food[] SeveralApples;
    public WinningFood Orange;
    public boolean startgame;


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
        orangePic=Toolkit.getDefaultToolkit().getImage("orange.png");
        Orange=new WinningFood(50,50);
            Apple=new Food(150,150);
            Apple2=new Food(100,100);
            head = new Snake(10, 10);
            rope=new SnakeBody(480,100);
            rope2=new SnakeBody(480,100);
            SeveralApples=new Food[5];
            for(int x=0; x<SeveralApples.length;x=x+1){//makes other apples spawn in random spots
                SeveralApples[x]=new Food((int)(Math.random()*170)+1,(int)(Math.random()*170)+1);
                SeveralApples[x].dx=(int)(Math.random()*10)+1;
                SeveralApples[x].dy=(int)(Math.random()*10)+1;
            }


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

                    moveFood();//this moves the food
                    moveThings();  //move all the game objects
                    crashing();//deals with things colliding
                    render();  // paint the graphics
                    pause(20);
                // sleep for 10 ms
            }

	}
    public void crashing(){
if(head.hitbox.intersects(Apple.hitbox)){//apple disappears when eaten
    Apple.isEaten=true;
}
        if(head.hitbox.intersects(Apple2.hitbox)&&Apple.isEaten==true){//makes the second apple eaten only if first apple is eaten
            Apple2.isEaten=true;
        }//prevents accidental eating of 2nd apple if its not visible
        if(head.hitbox.intersects(Orange.hitbox)&& Apple2.isEaten==true){//makes oranges eaten if apples are eaten
            Orange.isEaten=true;
        }
        for(int x=0;x<SeveralApples.length;x=x+1){
            if (head.hitbox.intersects(SeveralApples[x].hitbox)){
                SeveralApples[x].isEaten=true;
            }
        }
    }

public void moveFood(){
        if(startgame==true) {
            Apple.move();
            Apple2.move();
            Orange.move();
            for(int i=0;i< SeveralApples.length;i=i+1){
                SeveralApples[i].move();
            }
        }
}
	public void moveThings()
	{
      //calls the move( ) code in the objects
        if(startgame==true) {
            head.move();
            pause(50);
            betterMove();
            pause(50);
            betterMove2();
        }

	}




    public void betterMove2(){//explain this
        if (head.dx==20&&rope2.xpos+30<head.xpos){
            rope2.xpos=rope.xpos-20;
            rope2.ypos=rope.ypos;
        }
        if(head.dx==20&&rope2.xpos+30>head.xpos){
            rope2.xpos=rope.xpos;
            if(rope2.ypos<rope.ypos){
                rope2.ypos=rope.ypos-20;
            }
            if(rope2.ypos>rope.ypos){
                rope2.ypos=rope.ypos+20;
            }
        }
        if (head.dx==-20&&rope2.xpos-30>head.xpos){
            rope2.xpos=rope.xpos+20;
            rope2.ypos=rope.ypos;
        }
        if(head.dx==-20&&rope2.xpos-30<head.xpos){
            rope2.xpos=rope.xpos;
            if(rope2.ypos<rope.ypos){
                rope2.ypos=rope.ypos-20;
            }
            if(rope2.ypos>rope.ypos){
                rope2.ypos=rope.ypos+20;
            }
        }
        if (head.dy==20&&rope2.ypos+30<head.ypos){
            rope2.ypos=rope.ypos-20;
            rope2.xpos=rope.xpos;
        }
        if (head.dy==20&&rope2.ypos+30>head.ypos){
            rope2.ypos=rope.ypos;
            if(rope2.xpos<rope.xpos) {
                rope2.xpos = rope.xpos - 20;
            }
            if(rope2.xpos>rope.xpos){
                rope2.xpos=rope.xpos+20;
            }
        }

        if(head.dy==-20&&rope2.ypos-30>head.ypos){
            rope2.ypos=rope.ypos+20;
            rope2.xpos=rope.xpos;
        }
        if (head.dy==-20&&rope2.ypos-30<head.ypos){
            rope2.ypos=rope.ypos;
            if(rope2.xpos<rope.xpos) {
                rope2.xpos = rope.xpos - 20;
            }
            if(rope2.xpos>rope.xpos){
                rope2.xpos=rope.xpos+20;
            }
        }
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
        if(startgame==true) {
            for(int o=1; o< SeveralApples.length;o=o+1){
                if(SeveralApples[1].isEaten==false){
                    g.drawImage(foodPic, SeveralApples[1].xpos, SeveralApples[1].ypos, SeveralApples[1].width, SeveralApples[1].height, null);
                }
                if(SeveralApples[o].isEaten==false&&SeveralApples[o-1].isEaten==true) {
                    g.drawImage(foodPic, SeveralApples[o].xpos, SeveralApples[o].ypos, SeveralApples[o].width, SeveralApples[o].height, null);
                }
            }
            g.drawRect(head.hitbox.x, head.hitbox.y, head.width, head.height);
            g.drawImage(snakePic, head.xpos, head.ypos, head.width, head.height, null);
            if (Apple.isEaten == true) {
                g.drawImage(snakePic, rope.xpos, rope.ypos, rope.width, rope.height, null);
            }
            if (Apple.isEaten == false) {//make reappear when eaten
                g.drawImage(foodPic, Apple.xpos, Apple.ypos, Apple.width, Apple.height, null);
                g.drawRect(Apple.hitbox.x, Apple.hitbox.y, Apple.width, Apple.height);
            }
            if (Apple.isEaten == true && Apple2.isEaten == false) {
                g.drawImage(foodPic, Apple2.xpos, Apple2.ypos, Apple2.width, Apple2.height, null);
                g.drawRect(Apple2.hitbox.x, Apple2.hitbox.y, Apple2.width, Apple2.height);
            }

            if (Apple2.isEaten == true && Orange.isEaten == false) {
                g.drawImage(orangePic, Orange.xpos, Orange.ypos, Orange.width, Orange.height, null);
            }
            if (Apple2.isEaten == true) {
                g.drawImage(snakePic, rope2.xpos, rope2.ypos, rope2.width, rope2.height, null);
            }
        }
        if(startgame==false) {
            g.setColor(Color.GREEN);
            g.fillRect(100, 100, 10, 10);
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
        System.out.println(e.getPoint());
            Rectangle pointHitbox = new Rectangle(e.getX(), e.getY(), 1, 1);
            Rectangle startHitbox = new Rectangle(100, 100, 100, 100);

            if (startHitbox.intersects(pointHitbox)) {
                startgame = true;
            }

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

}