package xyz.jamesnuge.slicktest.util;

import org.newdawn.slick.GameContainer;

public class GameInfoWrapper {
    public GameContainer gameContainer;
    public int i;

    public GameInfoWrapper(GameContainer gameContainer, int i) {
        this.gameContainer = gameContainer;
        this.i = i;
    }

    public GameInfoWrapper() {
    }

    public void update(GameContainer gameContainer, int i) {
        this.gameContainer = gameContainer;
        this.i = i;
    }
}
