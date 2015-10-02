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
import xyz.jamesnuge.slicktest.GameInfoWrapper;
import xyz.jamesnuge.slicktest.KeyHandler;
import xyz.jamesnuge.slicktest.objects.components.Controllable;

import java.util.function.BiConsumer;

public class MoveRightKeyHandler<T extends Controllable<T>> extends KeyHandler<T> {
    public static final int RIGHT_KEY = Input.KEY_RIGHT;
    public BiConsumer<GameInfoWrapper, ? extends Controllable> HANDLER;

    public MoveRightKeyHandler(BiConsumer<GameInfoWrapper, T> consumer, T object) {
        super(RIGHT_KEY, consumer, object);
    }
}
