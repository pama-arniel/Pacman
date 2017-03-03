package Pacman;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.io.*;
import java.util.ArrayList;

import static Pacman.MenuScreen.hs;

public class Main extends StateBasedGame {
    private static final String FILE_NAME = "C:\\Users\\USER\\Desktop\\Pacman\\FILE.ser";
    public static final String gameName = "Pacman Got Size Issue";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int help = 2;
    public static final int highScore = 3;
    public static final int exit = 4;
    public static final int lose = 5;
    public static final int win = 6;
    public static final int mode = 7;
    public static final int timedMode = 8;
    public static final int revengeMode = 9;
    public static final int choiceMode = 10;
    public static final int help2 = 11;
    public static final int help3 = 12;
    public static final int help4 = 13;
    public static int prev = 0;

    public static boolean superisDefault;
    public static boolean superisNight;
    public static boolean superisCity;
    public static boolean superisSchool;
    public static boolean superisHalloween;
    public static boolean superisSummer;
    public static boolean soundState = true;

    public Main(String gameName) throws SlickException {
        super(gameName);
        this.addState(new MenuScreen(menu));
        this.addState(new Play(play));
        this.addState(new HelpScreen(help));
        this.addState(new HighScore(highScore));
        this.addState(new ExitScreen(exit));
        this.addState(new LoseScreen(lose));
        this.addState(new WinScreen(win));
        this.addState(new ModeScreen(mode));
        this.addState(new Timed(timedMode));
        this.addState(new Revenge(revengeMode));
        this.addState(new ChoiceScreen(choiceMode));
        this.addState(new HelpScreen2(help2));
        this.addState(new HelpScreen3(help3));
        this.addState(new HelpScreen4(help4));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        gameContainer.setShowFPS(false);
        this.getState(0).init(gameContainer,this);
        this.enterState(menu);
    }


    public static void main(String[] args) {
        hs = new Scores();
        FileOutputStream fileOut;
        ObjectOutputStream out;File fin = new File(FILE_NAME);
        FileInputStream fileIn;
        ObjectInputStream in_;
//        try {
//            if (fin.length() != 0) {
//                fileIn = new FileInputStream(FILE_NAME);
//                in_ = new ObjectInputStream(fileIn);
//                hs.classicHS = (ArrayList<Player>) in_.readObject();
//                hs.timedHS = (ArrayList<Player>) in_.readObject();
//                hs.revengeHS = (ArrayList<Player>) in_.readObject();
//                in_.close();
//                fileIn.close();
//            }
//        } catch (IOException i) {
//            i.printStackTrace();
//        } catch (ClassNotFoundException c) {
//            System.out.println("ERR");
//            c.printStackTrace();
//        }

        AppGameContainer appGameContainer;
        try {
            appGameContainer = new AppGameContainer(new Main(gameName));
            appGameContainer.setDisplayMode(500, 600, false);
            appGameContainer.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}