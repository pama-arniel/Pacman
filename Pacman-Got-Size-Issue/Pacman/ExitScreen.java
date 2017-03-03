package Pacman;

import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static Pacman.Images.*;

public class ExitScreen extends BasicGameState {

    private boolean isYesExit = false;
    private boolean isNoExit = false;


    public ExitScreen(int state) {

    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(ExitScreen, 0, 0);

        if (!isNoExit && !isYesExit) {
            graphics.drawImage(yes, 120, 390);
            graphics.drawImage(no, 280, 390);
        } else {
            if (isNoExit) {
                graphics.drawImage(yes, 120, 390);
                no.setFilter(Image.FILTER_LINEAR);
                no.draw(270, 385, 1.2f);
            } else if (isYesExit) {
                graphics.drawImage(no, 280, 390);
                yes.setFilter(Image.FILTER_LINEAR);
                yes.draw(110, 385, 1.2f);
            }

        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        if ((xPos >= 122 && xPos <= 230) && (yPos <= 206 && yPos >= 160)) {
            isYesExit = true;
            if (input.isMousePressed(0)) {
                AL.destroy();
                System.exit(0);
            }
        } else if ((xPos >= 280 && xPos <= 380) && (yPos <= 207 && yPos >= 164)) {
            isNoExit = true;
            if (input.isMousePressed(0)) {
                stateBasedGame.enterState(0);
            }
        } else {
            isNoExit = isYesExit = false;
        }
    }
}
