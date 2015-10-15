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

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import xyz.jamesnuge.slicktest.controls.KeyHandler;
import xyz.jamesnuge.slicktest.util.UserDataHelper;

import java.util.List;

public abstract class EngineObject<T extends EngineObjectUserData> implements Updatable {

    public Body body;

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
    public abstract T getUserData();

    public void setUsetData(T userData){
        UserDataHelper.addUserData(body, userData);
    }
    public abstract List<KeyHandler> getHandlers();
}
