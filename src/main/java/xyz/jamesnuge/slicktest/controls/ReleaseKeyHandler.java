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

import xyz.jamesnuge.slicktest.util.GameInfoWrapper;

import java.util.function.Consumer;

public class ReleaseKeyHandler<T> extends PressKeyHandler<T> {

    boolean keyDown = false;

    public ReleaseKeyHandler(int KEY, Consumer<T> handler, T object) {
        super(KEY, handler, object);
    }

    @Override
    public void consume(GameInfoWrapper info) {
        if(isKeyReleased(info))
            this.handler.accept(this.object);
    }

    private boolean isKeyReleased(GameInfoWrapper info) {
        if (isKeyPressed(info)) {
            keyDown = true;
            return false;
        } else {
            if (keyDown && isKeyUp(info)) {
                keyDown = false;
                return true;
            }
            keyDown = false;
        }
        return false;
    }

    public boolean isKeyUp(GameInfoWrapper info) {
        return !isKeyPressed(info);
    }
}
