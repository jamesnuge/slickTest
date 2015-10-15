package xyz.jamesnuge.slicktest.controls;

import xyz.jamesnuge.slicktest.util.GameInfoWrapper;

public interface KeyHandler<T> {

    void consumeOther(GameInfoWrapper info, T object);
    void consume(GameInfoWrapper info);
    boolean isKeyPressed(GameInfoWrapper info);
}
