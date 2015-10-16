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

package xyz.jamesnuge.slicktest.objects.listeners.references;

import org.jbox2d.dynamics.Fixture;
import xyz.jamesnuge.slicktest.objects.components.EngineObject;
import xyz.jamesnuge.slicktest.objects.components.EngineObjectUserData;

public abstract class ObjectReference<T extends EngineObject, S extends EngineObjectUserData> {
    T referenceObject;

    public ObjectReference(T referenceObject) {
        this.referenceObject = referenceObject;
    }

    public boolean doesFixtureBelongToObject(Fixture fixture) {
        EngineObjectUserData userData = (EngineObjectUserData)fixture.getUserData();
        return userData.getId() == referenceObject.getUserData().getId();
    }

    @SuppressWarnings("unchecked")
    S getUserData(EngineObjectUserData data) {
        return (S)data;
    }

    T getReferenceObject() {
        return referenceObject;
    }
}
