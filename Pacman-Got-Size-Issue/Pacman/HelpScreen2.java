package Pacman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static Pacman.Images.*;

import static Pacman.Sounds.*;

public class HelpScreen2 extends BasicGameState {

    public boolean isFlagExit = false;
    public boolean left = false;
    public boolean right = false;

    public HelpScreen2(int state) {

    }

    @Override
    public int getID() {
        return 11;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(Help2, 0, 0);
        if (left) {
            leftArrow.setFilter(Image.FILTER_LINEAR);
            leftArrow.draw(10, 270, 1.2f);
        } else {
            graphics.drawImage(leftArrow, 15, 280);
        }

        if (right) {
            rightArrow.setFilter(Image.FILTER_LINEAR);
            rightArrow.draw(420, 270, 1.2f);
        } else {
            graphics.drawImage(rightArrow, 425, 280);
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
                stateBasedGame.enterState(0);
            }
        } else if ((xPos >= 31 && xPos <= 60) && (yPos <= 307 && yPos >= 277)) {
            left = true;
            if (input.isMousePressed(0)) {
                stateBasedGame.enterState(2);
            }
        } else if ((xPos >= 438 && xPos <= 460) && (yPos <= 303 && yPos >= 278)) {
            right = true;
            if (input.isMousePressed(0)) {
                stateBasedGame.enterState(12);
            }
        } else {
            isFlagExit = false;
            left = right = false;
        }
    }
}
