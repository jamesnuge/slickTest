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

package xyz.jamesnuge.slicktest.util;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import java.util.HashMap;
import java.util.Map;

public class WorldContactListener implements ContactListener {
    private Map<Fixture, ContactListener> fixtureContactListenerMap = new HashMap<>();

    @Override
    public void beginContact(Contact contact) {
        fixtureContactListenerMap.get(contact.getFixtureA()).beginContact(contact);
        fixtureContactListenerMap.get(contact.getFixtureB()).beginContact(contact);
    }

    @Override
    public void endContact(Contact contact) {
        fixtureContactListenerMap.get(contact.getFixtureA()).endContact(contact);
        fixtureContactListenerMap.get(contact.getFixtureB()).endContact(contact);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        fixtureContactListenerMap.get(contact.getFixtureA()).preSolve(contact, oldManifold);
        fixtureContactListenerMap.get(contact.getFixtureB()).preSolve(contact, oldManifold);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        fixtureContactListenerMap.get(contact.getFixtureA()).postSolve(contact, impulse);
        fixtureContactListenerMap.get(contact.getFixtureB()).postSolve(contact, impulse);
    }

    public void addListener(Fixture fixture, ContactListener listener) {
        fixtureContactListenerMap.put(fixture, listener);
    }
}
