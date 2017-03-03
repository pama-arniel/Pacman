package Pacman;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import static Pacman.HelpScreen.*;
import static Pacman.Images.*;
import static Pacman.Main.*;
import static Pacman.Sounds.*;
import static Pacman.MenuScreen.*;
import static Pacman.Scores.*;

import java.util.*;

public class Play extends BasicGameState {

    private List<Ghosts> listOfGhosts;
    private List<Pellet> listOfPellets;
    private int spawnTimeGhost = 500;
    private int spawnTimePellet = 1000;
    private int powerDuration;
    private int timerPellet;
    private int timerGhost;
    public Pacman pacman;
    public String mouse;
    public static int distance;
    public int temp;
    int ghostFrequency;
    public float spriteDivider = 5.f;

    public Play() {

    }

    public Play(int state) {

    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.resume();
        listOfGhosts = new ArrayList<Ghosts>();
        listOfPellets = new ArrayList<Pellet>();
        pacman = new Pacman();
        mouse = "";
        timerPellet = 0;
        timerGhost = 0;
        distance = 0;
        ghostFrequency = 10;
        temp = 2;
        powerDuration = 0;
        if(soundState) {
            s.stopAll();
            if (superisHalloween) {
                s.halloween.loop();
            } else if (superisDefault) {
                s.backgroundMenu.loop();
            } else if (superisCity) {
                s.day.loop();
            } else if (superisNight) {
                s.night.loop();
            } else if (superisSummer) {
                s.summer.loop();
            } else if (superisSchool) {
                s.school.loop();
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if (superisDefault) {
            graphics.drawImage(modeDefault, 0, 0);
        } else if (superisHalloween) {
            graphics.drawImage(modeHalloween, 0, 0);
        } else if (superisDefault) {
            graphics.drawImage(modeDefault, 0, 0);
        } else if (superisSchool) {
            graphics.drawImage(modeSnow, 0, 0);
        } else if (superisSummer) {
            graphics.drawImage(modeSummer, 0, 0);
        } else if (superisNight) {
            graphics.drawImage(modeNight, 0, 0);
        } else if (superisCity) {
            graphics.drawImage(modeCity, 0, 0);
        }

        graphics.setColor(Color.yellow);
        graphics.fillOval(pacman.pacmanX, pacman.pacmanY, pacman.radius, pacman.radius, 200);
        if (pacman.powerUP) {
            for (Ghosts g : listOfGhosts) {
                g.invisible.draw(g.ghostX, g.ghostY);
            }
        } else {
            for (Ghosts g : listOfGhosts) {
                g.move.draw(g.ghostX, g.ghostY);
            }
        }
        for (Pellet p : listOfPellets) {
            p.move.draw(p.pelletX, p.pelletY);
        }
        graphics.setColor(Color.white);
        graphics.drawString("Distance: " + (distance / 100) + " meters", 170, 13);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if (distance % 20000 == 0) {
            listOfPellets.add(new Pellet(1));
            distance += 100;
        }
        //Movement for Pacman
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_LEFT)) {
            if (pacman.pacmanX >= 20.5) {
                pacman.pacmanX -= delta / 3.f;
            }
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            if ((pacman.pacmanX + pacman.radius) <= 480) {
                pacman.pacmanX += delta / 3.f;
            }
        }
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            if (gameContainer.isPaused()) {
                gameContainer.resume();
            } else {
                gameContainer.pause();
            }
        }
        input.clearKeyPressedRecord();
        distance += delta;
        timerGhost += delta;
        timerPellet += delta;

        //movement for pellet
        movePellet(delta, gameContainer);
        //movement for ghost
        moveGhost(stateBasedGame, gameContainer, delta);
        //Add Ghost
        addGhost();
        //Add Pellet
        addPellet();
    }

    public void moveGhost(StateBasedGame SBG, GameContainer GC, int delta) throws SlickException {
        for (Iterator<Ghosts> iterator = listOfGhosts.iterator(); iterator.hasNext(); ) {
            Ghosts ghost = iterator.next();
            ghost.ghostY += delta / spriteDivider;
            if (ghost.ghostY >= 550) {
                ghost.changeLocation();
            }
            if (ghost.bounds().intersects(pacman.bounds()) || pacman.radius <= 4) {
                    ghost.changeLocation();
                if (!pacman.powerUP) {
                    s.stopAll();
                    p.setScore(distance/100);
                    p.setMode(1);
                    if (soundState){
                        gameOver.play();
                        backgroundMenu.play();
                    }
                    if(hs.checkTopTen(p)){
                        SBG.enterState(6, new FadeOutTransition(Color.black, 2000), new FadeInTransition());
                    }
                    else{
                        SBG.enterState(5, new FadeOutTransition(Color.black, 2000), new FadeInTransition());
                    }
                } else {
                    s.feed.play();
                    ghost.ghostY = -100;
                    distance += 5000;
                }
            }
            for (Pellet p : listOfPellets) {
                if (p.bounds().intersects(ghost.bounds())) {
                    ghost.changeLocation();
                }
            }
            for (Ghosts g : listOfGhosts) {
                if (g.ghostY != ghost.ghostY && g.ghostX != ghost.ghostX) {
                    if (g.bounds().intersects(ghost.bounds())) {
                        ghost.changeLocation();
                    }
                }
            }

        }
    }

    public void movePellet(int delta, GameContainer GC) {
        for (Iterator<Pellet> iterator = listOfPellets.iterator(); iterator.hasNext(); ) {
            Pellet pellet = iterator.next();
            pellet.pelletY += delta / spriteDivider;
            if (pellet.pelletY >= 520) {
                if(pellet.powerUp){
                    iterator.remove();
                }else{
                    pellet.changeLocation();
                 }

            }
            if (pacman.powerUP) {
                powerDuration += delta;
                if (powerDuration >= 40000) {
                    s.stopAll();
                    if(superisHalloween) {
                        s.halloween.loop();
                    } else if (superisDefault) {
                        s.backgroundMenu.loop();
                    } else if (superisNight) {
                        s.night.loop();
                    } else if (superisSummer) {
                        s.summer.loop();
                    } else if (superisSchool) {
                        s.school.loop();
                    } else if (superisCity) {
                        s.day.loop();
                    }
                    powerDuration = 0;
                    pacman.powerUP = false;
                    spriteDivider = 5.f;
                }
            }
            if (!pacman.powerUP && !GC.isPaused()) {
                pacman.radius -= 0.005f;
            }
            if (pellet.bounds().intersects(pacman.bounds()) && !pacman.powerUP) {//Check if pacman intersects with pellet
                if(soundState) {
                    feed.play();
                }
                pellet.changeLocation();
                if (pacman.pacmanY + pacman.radius <= 570) {
                    pacman.radius += 5.f;
                }
                if (pellet.powerUp) {
                    s.stopAll();
                    if(soundState){
                        if(superisHalloween) {
                            s.halloweenFast.loop();
                        } else if (superisDefault) {
                            s.dayFast.loop();
                        } else if (superisNight) {
                            s.nightFast.loop();
                        } else if (superisSummer) {
                            s.summerFast.loop();
                        } else if (superisSchool) {
                            s.schoolFast.loop();
                        } else if (superisCity) {
                            s.dayFast.loop();
                        }
                    }
                    pacman.powerUP = true;
                    iterator.remove();
                    spriteDivider = 2.f;
                }
            }
            for (Ghosts g : listOfGhosts) {
                if (pellet.bounds().intersects(g.bounds())) {
                    pellet.changeLocation();
                }
            }
            for (Pellet p : listOfPellets) {
                if (pellet.pelletY != p.pelletY && pellet.pelletX != p.pelletX) {
                    if (pellet.bounds().intersects(p.bounds())) {
                        pellet.changeLocation();
                    }
                }
            }
        }
    }

    public void addPellet() throws SlickException {
        if (timerPellet >= spawnTimePellet) {
            Pellet pellet;
            pellet = new Pellet(2);
            listOfPellets.add(pellet);
            for (Ghosts ghost : listOfGhosts) {
                if (pellet.bounds().intersects(ghost.bounds())) {
                    pellet.changeLocation();
                }
            }
            timerPellet = 0;
            spawnTimePellet += 310;
        }
    }

    public void addGhost() throws SlickException {
        if (timerGhost >= spawnTimeGhost) {
            if (listOfGhosts.size() <= 10) {
                Random rand = new Random();
                Ghosts g = new Ghosts(rand.nextInt(4) + 1);
                listOfGhosts.add(g);
                for (Ghosts ghost : listOfGhosts) {
                    if (g.bounds().intersects(ghost.bounds())) {
                        g.changeLocation();
                    }
                }
                timerGhost = 0;
                spawnTimeGhost += 325;
            }
        }
    }
}
