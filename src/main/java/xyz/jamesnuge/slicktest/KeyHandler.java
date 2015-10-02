package xyz.jamesnuge.slicktest;

import org.newdawn.slick.Input;

import java.util.function.BiConsumer;

public class KeyHandler<T> {
    public final int KEY;
    public BiConsumer<GameInfoWrapper, T> handler;
    public T object;

    public KeyHandler(int KEY, BiConsumer<GameInfoWrapper, T> handler, T object) {
        this.KEY = KEY;
        this.handler = handler;
        this.object = object;
    }

    public void consumeOther(GameInfoWrapper info, T object) {
        if (isKeyPressed(info))
            this.handler.accept(info, object);
    }

    public void consume(GameInfoWrapper info) {
        if (isKeyPressed(info))
            this.handler.accept(info, this.object);
    }

    public boolean isKeyPressed(GameInfoWrapper info) {
        Input input = info.gameContainer.getInput();
        return input.isKeyDown(KEY);
    }
}
