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





    public Food(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =1;
        dy =0;
        width = 20;
        height = 20;
        hitbox=new Rectangle(xpos,ypos,width, height);



    }
}
