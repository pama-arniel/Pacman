package Pacman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static Pacman.Images.*;
import static Pacman.Main.*;
import static Pacman.MenuScreen.*;


public class ChoiceScreen extends BasicGameState {
    private boolean isClassic = false;
    private boolean isTimed = false;
    private boolean isRevenge = false;
    private boolean isFlagExit = false;
    public ChoiceScreen(int state) {

    }

    @Override
    public int getID() {
        return 10;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(choice, 0, 0);

        if (!isClassic) {
            graphics.drawImage(classic, 240, 200);
        } else {
            classic.setFilter(Image.FILTER_LINEAR);
            classic.draw(230, 195, 1.2f);
        }
        if (!isTimed) {
            graphics.drawImage(timed, 80, 280);
        } else {
            timed.setFilter(Image.FILTER_LINEAR);
            timed.draw(70, 275, 1.2f);
        }
        if (!isRevenge) {
            graphics.drawImage(revenge, 230, 350);
        } else {
            revenge.draw(220, 345, 1.2f);
        }

        if (!isFlagExit) {
            graphics.drawImage(ExitButtonForHelp, 395, 55);
        } else {
            ExitButtonForHelp.setFilter(Image.FILTER_LINEAR);
            ExitButtonForHelp.draw(385, 50, 1.2f);
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
                input.clearMousePressedRecord();
                stateBasedGame.enterState(0);
            }
        } else if ((xPos >= 287 && xPos <= 363) && (yPos <= 390 && yPos >= 271)) {
            isClassic = true;
            if (input.isMousePressed(0)) {
                prev = 1;
                input.clearMousePressedRecord();
                stateBasedGame.getState(7).init(gameContainer,stateBasedGame);
                stateBasedGame.enterState(7);
            }
        } else if ((xPos >= 123 && xPos <= 198) && (yPos <= 315 && yPos >= 193)) {
            isTimed = true;
            if (input.isMousePressed(0)) {
                s.stopAll();
                if(soundState) {
                    s.timed.loop();
                }
                prev = 8;
                input.clearMousePressedRecord();
                stateBasedGame.getState(8).init(gameContainer, stateBasedGame);
                stateBasedGame.enterState(8);
            }
        } else if ((xPos >= 275 && xPos <= 351) && (yPos <= 237 && yPos >= 123)) {
            isRevenge = true;
            if (input.isMousePressed(0)) {
                s.stopAll();
                if(soundState){
                s.revenge.loop();}

                prev = 9;
                input.clearMousePressedRecord();
                stateBasedGame.getState(9).init(gameContainer, stateBasedGame);
                stateBasedGame.enterState(9);
            }
        } else {
            isFlagExit = isClassic = isTimed = isRevenge = false;
        }
    }
}
