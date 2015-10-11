package xyz.jamesnuge.slicktest;

import org.newdawn.slick.Input;

import java.util.function.Consumer;

public class KeyHandler<T> {
    public final int KEY;
    public Consumer<T> handler;
    public T object;

    public KeyHandler(int KEY, Consumer<T> handler, T object) {
        this.KEY = KEY;
        this.handler = handler;
        this.object = object;
    }

    public void consumeOther(GameInfoWrapper info, T object) {
        if (isKeyPressed(info))
            this.handler.accept(object);
    }

    public void consume(GameInfoWrapper info) {
        if (isKeyPressed(info))
            this.handler.accept(this.object);
    }

    public boolean isKeyPressed(GameInfoWrapper info) {
        Input input = info.gameContainer.getInput();
        return input.isKeyDown(KEY);
    }
}
