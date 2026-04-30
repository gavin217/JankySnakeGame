import java.awt.*;

public class WinningFood {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public Rectangle hitbox;
    public boolean isEaten;





    public WinningFood(int pXpos, int pYpos) {
        int randdy2=(int)(Math.random()*10)+1;
        int randdx2=(int)(Math.random()*10)+1;
        xpos = pXpos;
        ypos = pYpos;
        dx = randdx2;
        dy = randdy2;
        width = 20;
        height = 20;
        hitbox=new Rectangle(xpos,ypos,width, height);
        isEaten=false;



    }
    public void move(){
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox= new Rectangle(xpos,ypos,width,height);
        if(xpos>221){
            xpos=-20;
        }
        if(xpos<-20){
            xpos=220;
        }
        if(ypos>221){
            ypos=-20;
        }
        if(ypos<-21){
            ypos=221;
        }
    }
}
