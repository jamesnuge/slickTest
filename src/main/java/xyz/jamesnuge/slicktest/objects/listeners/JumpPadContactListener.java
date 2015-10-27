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

package xyz.jamesnuge.slicktest.objects.listeners;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
import xyz.jamesnuge.slicktest.objects.components.EngineObjectUserData;
import xyz.jamesnuge.slicktest.util.ContactListenerUtil;

public class JumpPadContactListener implements ContactListener {
    public static final int JUMP_PAD_FIXTURE_ID = 0x0000;

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Made contact");
        Fixture padFixture;
        Fixture objectFixture;

        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (!ContactListenerUtil.fixtureNullCheck(a, b)) {
            //Log error message
            return;
        }

        if ( a.getUserData()!= null &&((EngineObjectUserData)a.getUserData()).getId() == JUMP_PAD_FIXTURE_ID) {
            padFixture = a;
            objectFixture = b;
        } else {
            if (((EngineObjectUserData)b.getUserData()).getId() == JUMP_PAD_FIXTURE_ID) {
                padFixture = b;
                objectFixture = a;
            } else {
                throw new IllegalStateException();
            }
        }
        System.out.println("Applying jump force");
        objectFixture.getBody().setLinearVelocity(new Vec2(objectFixture.getBody().getLinearVelocity().x, 0f));
        objectFixture.getBody().applyForceToCenter(new Vec2(0, 5f));
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
