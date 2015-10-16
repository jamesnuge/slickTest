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
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import xyz.jamesnuge.slicktest.util.ConversionUtility;

public abstract class RectangleObject<T extends EngineObjectUserData> extends EngineObject<T> {

    private Class userDataClass;

    private Shape graphicalObject;
    private Rectangle rectangle;


    private BodyDef bodyDef;
    private FixtureDef fixtureDef;

    public Vec2 getSize() {
        return new Vec2(size);
    }

    private Vec2 size;

    public RectangleObject(Vec2 pos, Vec2 size, World world) {
        this.bodyDef = createBodyDef();
        this.bodyDef.position.set(pos);
        this.body = world.createBody(bodyDef);
        this.size = size;
        this.fixtureDef = createFixtureDef();
        body.createFixture(fixtureDef);
        rectangle = new Rectangle(ConversionUtility.toViewportX(getCenterPos().x), ConversionUtility.toViewportY(getCenterPos().y), ConversionUtility.toPixelWidth(size.x), ConversionUtility.toPixelHeight(size.y));
        graphicalObject = new Polygon(rectangle.getPoints());
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
        rectangle.setY(ConversionUtility.toViewportY(getCenterPos().y));
        rectangle.setX(ConversionUtility.toViewportX(getCenterPos().x));
        graphicalObject = rectangle.transform(Transform.createRotateTransform(ConversionUtility.toViewportAngle(body.getAngle()), rectangle.getCenterX(), rectangle.getCenterY()));
    }

    public Vec2 getCenterPos() {
        return new Vec2(body.getPosition().x-this.size.x/2, body.getPosition().y + this.size.y/2);
    }

    @Override
    protected BodyDef getBodyDef() {
        return bodyDef;
    }

    @Override
    protected FixtureDef getFixtureDef() {
        return fixtureDef;
    }


    public Vec2 getViewpointCenterPos() {
        return new Vec2();
    }

    public void setUserDataClass(Class userDataClass) {

    }
}
