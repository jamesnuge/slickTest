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

package xyz.jamesnuge.slicktest.objects.basic.userData;

import org.jbox2d.callbacks.ContactListener;
import xyz.jamesnuge.slicktest.objects.components.EngineObject;
import xyz.jamesnuge.slicktest.objects.components.EngineObjectUserData;

public class EmptyUserData<T extends EngineObject> implements EngineObjectUserData<T> {
    public static final EngineObjectUserData<?> INSTANCE = new EmptyUserData<>();

    public static <T extends EngineObject> EngineObjectUserData<T> getInstance() {
        return (EngineObjectUserData<T>) INSTANCE;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public ContactListener getContactListener() {
        return null;
    }

    @Override
    public Class getObjectClass() {
        return null;
    }

    @Override
    public boolean hasContactListener() {
        return false;
    }
}
