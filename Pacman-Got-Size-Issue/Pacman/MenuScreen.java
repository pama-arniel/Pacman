package Pacman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.io.*;
import java.util.ArrayList;

import static Pacman.Images.*;
import static Pacman.Sounds.*;
import static Pacman.Main.*;

public class MenuScreen extends BasicGameState {

    public String mouse = "";
    private boolean isFlagPlay = false;
    private boolean isFlagHelp = false;
    private boolean isFlagHighScore = false;
    private boolean isFlagExit = false;
    private Images i;
    public static Sounds s;
    public static Scores hs;
    public static Player p;
    private static final String FILE_NAME = "CC:\\Users\\USER\\Desktop\\Pacman\\FILE.ser";
    public MenuScreen(int state) {

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        i = new Images();
        s = new Sounds();
        hs = new Scores();
        p = new Player();
        soundState = true;
        backgroundMenu.loop();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(Menu, 0, 0);

        if (!isFlagPlay) {
            graphics.drawImage(PlayButton, 200, 260);
        } else {
            PlayButton.setFilter(Image.FILTER_NEAREST);
            PlayButton.draw(190, 255, 1.3f);
        }

        if (!isFlagHelp) {
            graphics.drawImage(HelpButton, 195, 310);
        } else {
            HelpButton.setFilter(Image.FILTER_NEAREST);
            HelpButton.draw(180, 305, 1.3f);
        }

        if (!isFlagHighScore) {
            graphics.drawImage(HighScoreButton, 120, 360);
        } else {
            HighScoreButton.setFilter(Image.FILTER_NEAREST);
            HighScoreButton.draw(80, 355, 1.3f);
        }

        if (!isFlagExit) {
            graphics.drawImage(QuitButton, 200, 410);
        } else {
            QuitButton.setFilter(Image.FILTER_NEAREST);
            QuitButton.draw(185, 405, 1.3f);
        }
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        int xPos = Mouse.getX();
        int yPos = Mouse.getY();

        mouse = "Position X: " + xPos + "| Position Y: " + yPos;

        if ((xPos >= 200 && xPos <= 300) && (yPos <= 337 && yPos >= 320)) { //play button
            isFlagPlay = true;
            if (input.isMousePressed(0)) {
                stateBasedGame.enterState(10);
            }
        } else if ((xPos >= 200 && xPos <= 300) && (yPos <= 286 && yPos >= 263)) { //help button

            isFlagHelp = true;
            if (input.isMousePressed(0)) {
                stateBasedGame.enterState(2);
            }
        } else if ((xPos >= 123 && xPos <= 381) && (yPos <= 237 && yPos >= 214)) { //high score button
            isFlagHighScore = true;
            if (input.isMousePressed(0)) {
                stateBasedGame.getState(3).init(gameContainer,stateBasedGame);
                stateBasedGame.enterState(3);
            }
        } else if ((xPos >= 200 && xPos <= 300) && (yPos <= 188 && yPos >= 168)) { //quit button
            isFlagExit = true;
            if (input.isMousePressed(0)) {
                stateBasedGame.enterState(4);
            }
        } else {
            isFlagPlay = isFlagHelp = isFlagHighScore = isFlagExit = false;
        }
    }
}