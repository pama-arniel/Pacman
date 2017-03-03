package Pacman;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.Font;
import static Pacman.Scores.*;

import static Pacman.Images.*;

public class HighScore extends BasicGameState {
    private static final String FILE_NAME = "FILE.ser";
    private boolean serialize = true;
    private Font font;
    private boolean isFlagExit = false;
    private Image hsBackground;

    public HighScore(int state) {

    }
    public HighScore(){

    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        font = new Font("Verdana", Font.BOLD, 20);
        hsBackground = new Image("res/Images/HighScore.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(hsBackground, 0, 0);
        if (!isFlagExit) {
            graphics.drawImage(ExitButtonForHelp, 395, 55);
        } else {
            ExitButtonForHelp.setFilter(Image.FILTER_LINEAR);
            ExitButtonForHelp.draw(385, 50, 1.2f);
        }
        graphics.drawString(classicDisp, 70 ,200);
        graphics.drawString(timedDisp, 200 ,200);
        graphics.drawString(revengeDisp, 320 ,200);
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
        } else {
            isFlagExit = false;
        }
    }


}
