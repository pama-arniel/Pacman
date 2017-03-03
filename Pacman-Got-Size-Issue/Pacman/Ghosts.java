package Pacman;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.Random;

public class Ghosts extends Play{
    public Image[] miniGhosts;
    public Image[] invisibleGhost;
    public int [] duration = {200,200,200,200,200};
    public int [] durationInvisible = {200,200,200,200};
    public Animation move;
    public Animation invisible;
    public float ghostX;
    public float ghostY;

    public Ghosts(int color)throws SlickException{
        invisibleGhost = new Image[] {new Image("res/Images/ghost/Blue1.png"),
                    new Image("res/Images/ghost/White1.png"),
                    new Image("res/Images/ghost/blue2.png"),
                    new Image("res/Images/ghost/White2.png")};

        if(color == 1){
            miniGhosts = new Image[] {new Image("res/Images/ghost/BlueDown.png"),
                    new Image("res/Images/ghost/BlueDown2.png"),
                    new Image("res/Images/ghost/BlueLeft.png"),
                    new Image("res/Images/ghost/BlueLeft2.png"),
                    new Image("res/Images/ghost/BlueRight.png")};
        }
        else if(color == 2){
            miniGhosts = new Image[] {new Image("res/Images/ghost/OrangeDown.png"),
                    new Image("res/Images/ghost/OrangeDown2.png"),
                    new Image("res/Images/ghost/OrangeLeft.png"),
                    new Image("res/Images/ghost/OrangeLeft2.png"),
                    new Image("res/Images/ghost/OrangeRight.png")};
        }
        else if(color == 3){
            miniGhosts = new Image[] {new Image("res/Images/ghost/PinkDown.png"),
                    new Image("res/Images/ghost/PinkDown2.png"),
                    new Image("res/Images/ghost/PinkLeft.png"),
                    new Image("res/Images/ghost/PinkLeft2.png"),
                    new Image("res/Images/ghost/PinkRight.png")};
        }
        else {
            miniGhosts = new Image[]{new Image("res/Images/ghost/RedDown.png"),
                    new Image("res/Images/ghost/RedDown2.png"),
                    new Image("res/Images/ghost/RedLeft.png"),
                    new Image("res/Images/ghost/RedLeft2.png"),
                    new Image("res/Images/ghost/RedRight.png")};
        }

        //to randomize position
        Random rand = new Random();
        ghostX = rand.nextInt(410) + 17; //position x from 17 to 410
        ghostY = -100;
        move = new Animation(miniGhosts, duration, true);
        invisible = new Animation(invisibleGhost, durationInvisible, true);
    }

    public Rectangle bounds(){
        return new Rectangle((int)ghostX + 15, (int)ghostY + 15, miniGhosts[0].getWidth() - 15, miniGhosts[0].getHeight() - 15);
    }

    public void changeLocation(){
        Random rand = new Random();
        ghostX = rand.nextInt(410) + 17; //position x from 17 to 410
        ghostY = -100;
    }
}
