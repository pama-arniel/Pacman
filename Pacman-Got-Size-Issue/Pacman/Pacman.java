package Pacman;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.awt.*;

public class Pacman {
    public Shape pacman;
    public float radius = 30;
    public static float pacmanY = 490;
    public float pacmanX;
    public boolean powerUP;

    public Pacman(){
        powerUP = false;
        pacmanX = 250;
        pacman = new Circle(pacmanX, pacmanY, radius);
        pacman.setCenterY(pacmanY);
    }

    public Rectangle bounds() {
        int radiusTemp = (int) radius;
        int pacmanXTemp = (int) pacmanX;
        int pacmanYTemp = (int) pacmanY;
        return new Rectangle(pacmanXTemp, pacmanYTemp, radiusTemp, radiusTemp);
    }
}






