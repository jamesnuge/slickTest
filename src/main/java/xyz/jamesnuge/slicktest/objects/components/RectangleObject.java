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

import java.util.List;

public abstract class RectangleObject<T extends EngineObjectUserData> extends EngineObject<T> {

    private Class userDataClass;

    private Shape graphicalObject;
    private Rectangle rectangle;


    private BodyDef bodyDef;
    private List<FixtureDef> fixtureDefs;

    public Vec2 getSize() {
        return new Vec2(size);
    }

    private Vec2 size;

    /**
     *
     * @param pos This vector is the world coordinates of the rectangular object to be created
     * @param size This is the size in metres that that rectangular shape should be. This takes the form of (w, h)
     * @param world This is the world you are currently using. The object will be created in this world
     * @param userData This is the user data you have specified for this object
     */
    public RectangleObject(Vec2 pos, Vec2 size, World world, T userData) {
        super(userData);
        this.bodyDef = createBodyDef();
        this.bodyDef.position.set(pos);
        this.body = world.createBody(bodyDef);
        this.size = size;
        this.fixtureDefs = createFixtureDef();
        fixtureDefs.stream().forEach(this::setFixtureToBody);
        rectangle = new Rectangle(ConversionUtility.toViewportX(getCenterPos().x), ConversionUtility.toViewportY(getCenterPos().y), ConversionUtility.toPixelWidth(size.x), ConversionUtility.toPixelHeight(size.y));
        graphicalObject = new Polygon(rectangle.getPoints());
    }

    public RectangleObject(Vec2 pos, Vec2 size, World world) {
        this.bodyDef = createBodyDef();
        this.bodyDef.position.set(pos);
        this.body = world.createBody(bodyDef);
        this.size = size;
        this.fixtureDefs = createFixtureDef();
        fixtureDefs.stream().forEach(this::setFixtureToBody);
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
        return new Vec2(body.getPosition().x - this.size.x / 2, body.getPosition().y + this.size.y / 2);
    }

    @Override
    protected BodyDef getBodyDef() {
        return bodyDef;
    }

    @Override
    protected List<FixtureDef> getFixtureDefs() {
        return fixtureDefs;
    }


    public Vec2 getViewpointCenterPos() {
        return new Vec2();
    }

    private void setFixtureToBody(FixtureDef fixture) {
        this.body.createFixture(fixture);
    }

    public void setUserDataClass(Class userDataClass) {

    }
}
