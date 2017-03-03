package Pacman;

import java.io.Serializable;

public class Player implements Comparable, Serializable {

    private String name;
    private int score;
    private int mode;

    public Player() {
        name = "";
        score = 0;
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public Player(int score){
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getMode(){ return mode;}

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMode(int mode){ this.mode = mode;}

    public String toString() {
        return String.format("%s        %d meters", name, score);
    }

    @Override
    public int compareTo(Object o) {
        int compareScore = ((Player)o).getScore();
        return compareScore - this.score;
        }
}

