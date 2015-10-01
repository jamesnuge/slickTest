package xyz.jamesnuge.slicktest;

import org.newdawn.slick.Input;

import java.util.function.BiConsumer;

public class KeyHandler<T> {
    public final int KEY;
    public BiConsumer<GameInfo, T> handler;
    public T object;

    public KeyHandler(int KEY, BiConsumer<GameInfo, T> handler, T object) {
        this.KEY = KEY;
        this.handler = handler;
        this.object = object;
    }

    public void consumeOther(GameInfo info, T object) {
        if (isKeyPressed(info))
            this.handler.accept(info, object);
    }

    public void consume(GameInfo info) {
        if (isKeyPressed(info))
            this.handler.accept(info, this.object);
    }

    public boolean isKeyPressed(GameInfo info) {
        Input input = info.gameContainer.getInput();
        return input.isKeyDown(KEY);
    }
}
