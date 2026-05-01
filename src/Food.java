import java.awt.*;

public class Food {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public Rectangle hitbox;
    public boolean isEaten;





    public Food(int pXpos, int pYpos) {
        int randdy=(int)(Math.random()*10)+1;
        int randdx=(int)(Math.random()*10)+1;
        xpos = pXpos;
        ypos = pYpos;
        dx =randdx;
        dy =randdy;
        width = 20;
        height = 20;
        hitbox=new Rectangle(xpos,ypos,width, height);
        isEaten=false;



    }
    public void move(){
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox= new Rectangle(xpos,ypos,width,height);
        if(xpos>179){//all this makes the bouncing off walls effect
            dx=-dx;
        }
        if(xpos<-0){
            dx=-dx;
        }
        if(ypos>179){
            dy=-dy;
        }
        if(ypos<0){
            dy=-dy;
        }

    }
    }
