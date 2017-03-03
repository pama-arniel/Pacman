package Pacman;

import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import static Pacman.Images.*;
import static Pacman.Main.*;
import static Pacman.MenuScreen.s;
import static Pacman.Sounds.*;

public class ModeScreen extends BasicGameState {
    private boolean isSnow = false;
    private boolean isNight = false;
    private boolean isCity = false;
    private boolean isHalloween = false;
    private boolean isSummer = false;
    private boolean isDefault = false;
    private boolean isFlagExit = false;


    public ModeScreen(int state) {

    }

    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        isFlagExit = isSummer = isCity = isSnow = isHalloween = isDefault = isNight = false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(Mode, 0, 0);
        if (!isFlagExit) {
            graphics.drawImage(ExitButtonForHelp, 395, 55);
        } else {
            ExitButtonForHelp.setFilter(Image.FILTER_LINEAR);
            ExitButtonForHelp.draw(385, 50, 1.2f);
        }
        if (!isSummer) {
            graphics.drawImage(modeSummerButton, 100, 150);
        } else {
            modeSummerButton.setFilter(Image.FILTER_LINEAR);
            modeSummerButton.draw(90, 145, 1.2f);
        }
        if (!isCity) {
            graphics.drawImage(modeCityButton, 100, 270);
        } else {
            modeCityButton.setFilter(Image.FILTER_LINEAR);
            modeCityButton.draw(90, 265, 1.2f);
        }
        if (!isHalloween) {
            graphics.drawImage(modeHalloweenButton, 100, 390);
        } else {
            modeHalloweenButton.setFilter(Image.FILTER_LINEAR);
            modeHalloweenButton.draw(90, 385, 1.2f);
        }
        if (!isDefault) {
            graphics.drawImage(modeDefaultButton, 270, 145);
        } else {
            modeDefaultButton.setFilter(Image.FILTER_LINEAR);
            modeDefaultButton.draw(260, 140, 1.2f);
        }
        if (!isSnow) {
            graphics.drawImage(modeSnowButton, 270, 267);
        } else {
            modeSnowButton.setFilter(Image.FILTER_LINEAR);
            modeSnowButton.draw(260, 260, 1.2f);
        }
        if (!isNight) {
            graphics.drawImage(modeNightButton, 270, 385);
        } else {
            modeNightButton.setFilter(Image.FILTER_LINEAR);
            modeNightButton.draw(260, 380, 1.2f);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        if ((xPos >= 392 && xPos <= 456) && (yPos <= 520 && yPos >= 458)) {
            isFlagExit = true;
            if (input.isMousePressed(0)) {
                input.clearControlPressedRecord();
                stateBasedGame.enterState(0);
            }
        } else if ((xPos >= 106 && xPos <= 231) && (yPos <= 440 && yPos >= 347)) {
            isSummer = true;
            if (input.isMousePressed(0)) {
                input.clearControlPressedRecord();
                superisSummer = true;
            }
        } else if ((xPos >= 105 && xPos <= 230) && (yPos <= 318 && yPos >= 230)) {
            isCity = true;
            if (input.isMousePressed(0)) {
                input.clearControlPressedRecord();
                superisCity = true;
            }
        } else if ((xPos >= 105 && xPos <= 230) && (yPos <= 205 && yPos >= 115)) {
            isHalloween = true;
            if (input.isMousePressed(0)) {
                input.clearControlPressedRecord();
                superisHalloween = true;
            }
        } else if ((xPos >= 275 && xPos <= 402) && (yPos <= 441 && yPos >= 350)) {
            isDefault = true;
            if (input.isMousePressed(0)) {
                input.clearControlPressedRecord();
                superisDefault = true;
            }
        } else if ((xPos >= 275 && xPos <= 402) && (yPos <= 320 && yPos >= 231)) {
            isSnow = true;
            if (input.isMousePressed(0)) {
                input.clearMousePressedRecord();
                superisSchool = true;
            }
        } else if ((xPos >= 275 && xPos <= 405) && (yPos <= 205 && yPos >= 111)) {
            isNight = true;
            if (input.isMousePressed(0)) {
                input.clearMousePressedRecord();
                superisNight = true;
            }
        } else {
            input.clearMousePressedRecord();
            isFlagExit = isSummer = isCity = isSnow = isHalloween = isDefault = isNight = false;
            superisCity = superisHalloween = superisDefault = superisNight = superisSummer = superisSchool = false;
        }
        if (superisSchool || superisCity || superisHalloween || superisDefault || superisNight || superisSummer) {
            input.clearMousePressedRecord();
            stateBasedGame.getState(1).init(gameContainer, stateBasedGame);
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
