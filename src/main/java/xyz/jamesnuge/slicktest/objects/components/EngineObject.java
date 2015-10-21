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

package xyz.jamesnuge.slicktest.objects.components;

import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class EngineObject<T extends EngineObjectUserData> implements Updatable {



    public Body body;
    public T userData;

    public EngineObject(T userData) {
        this.userData = userData;
    }
    public EngineObject(){}

    abstract Shape getGraphicalObject();
    abstract Vec2 getWorldCoordinates();
    abstract Vec2 getViewportCoordinates();

    public abstract FixtureDef createFixtureDef();
    public abstract BodyDef createBodyDef();

    protected abstract BodyDef getBodyDef();
    protected abstract FixtureDef getFixtureDef();

    public void draw(Graphics graphics){
        graphics.draw(getGraphicalObject());
    }

    public T getUserData() {
        if (userData != null) {
            return userData;
        } else {
            throw new IllegalStateException("User data has not been set for this object");
        }
    }

    public void setUserData(T userData){
        this.userData = userData;
    }

    public abstract boolean hasContactListener();

    public ContactListener getContactListener() {
        if (hasContactListener()) {
            return userData.getContactListener();
        } else {
            throw new IllegalStateException("Object does not have contact listener");
        }
    }

}
