/*
 * Copyright (c) 2015 Clearbox Systems Pty. Ltd.
 * http://www.clearboxsystems.com.au
 *
 * Disclaimer - Please Read:
 * Clearbox Systems cannot accept responsibility or liability for
 * any loss, damage, cost or expense users might incur as a result
 * of the use of, or reliance upon, any example code or software or
 * program produced by Clearbox Systems Pty. Ltd.
 *
 * This software and the intellectual property therein belongs to
 * Clearbox Systems Pty. Ltd. and shall not be used without prior
 * license agreement by any third party for commercial purposes.
 * Clearbox Systems may allow third party use of this software for
 * research or research related activities upon written approval.
 */

package xyz.jamesnuge.slicktest.controls;

import org.newdawn.slick.Input;
import xyz.jamesnuge.slicktest.util.GameInfoWrapper;

import java.util.function.Consumer;

public class PressKeyHandler<T> implements KeyHandler<T>{
    public final int KEY;
    public Consumer<T> handler;
    public T object;

    public PressKeyHandler(int KEY, Consumer<T> handler, T object) {
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
