package xyz.jamesnuge.slicktest;

import org.newdawn.slick.GameContainer;

public class GameInfo {
    public GameContainer gameContainer;
    public int i;

    public GameInfo(GameContainer gameContainer, int i) {
        this.gameContainer = gameContainer;
        this.i = i;
    }

    public GameInfo() {
    }

    public void update(GameContainer gameContainer, int i) {
        this.gameContainer = gameContainer;
        this.i = i;
    }
}
