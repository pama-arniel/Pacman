package Pacman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.Rectangle;

public class Bullet {
    public Image[] pellet;
    public int[] duration = {50, 50};
    public Animation move;
    public float bulletX;
    public float bulletY;
    public int type;


    public Bullet(float bulletX) throws SlickException {
        pellet = new Image[]{new Image("res/Images/ghost/pellet.png"), new Image("res/Images/ghost/pellet.png")};
        move = new Animation(pellet, duration, true);
        this.bulletX = bulletX + 10; //para ma-center
        bulletY = 490;
        type = 1;
    }

    public Bullet(float bulletX, float bulletY) throws SlickException {
        pellet = new Image[]{new Image("res/Images/ghost/pellet.png"), new Image("res/Images/ghost/pellet.png")};
        move = new Animation(pellet, duration, true);
        this.bulletX = bulletX + 10; //para ma-center
        this.bulletY = bulletY;
        type = 1;
    }

    public Bullet(int x) throws SlickException{//Constructor for bomb
        pellet = new Image[]{new Image("res/Images/Explosion.png"),new Image("res/Images/Explosion.png")};
        move = new Animation(pellet,duration,true);
        this.bulletX = 0; //para ma-center
        this.bulletY = 500;
        type = 2;
    }

    public Rectangle bounds() {
        return new Rectangle((int) bulletX, (int) bulletY, pellet[0].getWidth(), pellet[0].getHeight());
    }
    public Rectangle boundsBomb() {
        return new Rectangle((int) bulletX, (int) bulletY, pellet[1].getWidth(), pellet[1].getHeight());
    }
}
