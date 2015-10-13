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
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import xyz.jamesnuge.slicktest.util.ConversionUtility;

public abstract class RectangleObject<T extends EngineObjectUserData> extends EngineObject<T> {

    private Class userDataClass;

    private Rectangle graphicalObject;
    public BodyDef bodyDef;

    public Vec2 getSize() {
        return new Vec2(size);
    }

    private Vec2 size;

    public RectangleObject(Vec2 pos, Vec2 size, World world, BodyDef bodyDef) {
        this.bodyDef = bodyDef;
        this.bodyDef.position.set(pos);
        this.body = world.createBody(bodyDef);
        this.size = size;
        body.createFixture(this.getFixtureDef());
        graphicalObject = new Rectangle(ConversionUtility.toViewportX(getCenterPos().x), ConversionUtility.toViewportY(getCenterPos().y), ConversionUtility.toPixelWidth(size.x), ConversionUtility.toPixelHeight(size.y));
    }

    public Shape getDrawableObject() {
        return graphicalObject;
    }

    @Override
    Shape getGraphicalObject() {
        return this.graphicalObject;
    }

    @Override
    public Vec2 getWorldCoordinates() {
        return body.getPosition();
    }

    @Override
    public Vec2 getViewportCoordinates() {
        return new Vec2(graphicalObject.getX(), graphicalObject.getY());
    }

    @Override
    public void update() {
        graphicalObject.setY(ConversionUtility.toViewportY(this.body.getPosition().y));
        graphicalObject.setX(ConversionUtility.toViewportX(this.body.getPosition().x));
    }

    public Vec2 getCenterPos() {
        return new Vec2(body.getPosition().x-this.size.x/2, body.getPosition().y + this.size.y);
    }

    public Vec2 getViewpointCenterPos() {
        return new Vec2();
    }

    public void setUserDataClass(Class userDataClass) {

    }
}
