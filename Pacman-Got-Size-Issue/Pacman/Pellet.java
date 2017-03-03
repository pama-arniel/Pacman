package Pacman;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.awt.Rectangle;

import java.util.List;
import java.util.Random;

public class Pellet extends Play {
    public Image[] pellet;
    public int[] duration = {200, 200};
    public Animation move;
    public float pelletX;
    public float pelletY;
    public boolean powerUp;

    public Pellet(int type) throws SlickException {
        if (type == 1) {
            pellet = new Image[]{new Image("res/Images/ghost/pellet2.png"), new Image("res/Images/ghost/pellet2.png")};
            powerUp = true;
        } else {
            pellet = new Image[]{new Image("res/Images/ghost/pellet.png"), new Image("res/Images/ghost/pellet.png")};
            powerUp = false;
        }
        //to randomize position
        Random rand = new Random();
        pelletX = rand.nextInt(410) + 17;
        pelletY = -200;
        move = new Animation(pellet, duration, true);
    }

    public void changeLocation() {
        Random rand = new Random();
        pelletX = rand.nextInt(410) + 17;
        pelletY = -200;
    }


    public Rectangle bounds(){
        return new Rectangle((int) pelletX, (int) pelletY, pellet[0].getWidth(), pellet[0].getHeight());
    }
}
