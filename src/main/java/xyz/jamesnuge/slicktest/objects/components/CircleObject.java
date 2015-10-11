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
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import xyz.jamesnuge.slicktest.util.ConversionUtility;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;

public class CircleObject extends EngineObject {
    public Circle graphicalObject;
    public float radius;
    public Body body;

    public CircleObject(Vec2 pos, float radius, World world, BodyDef bodyDef) {
        bodyDef.position.set(pos);
        this.body = world.createBody(bodyDef);
        this.body.createFixture(FixtureDefinitions.getCircleFixtureDefinition(radius));
        this.radius = radius;
        graphicalObject = new Circle(ConversionUtility.toViewportPos(pos).x, ConversionUtility.toViewportPos(pos).y, ConversionUtility.fromMetreToPixel(radius));
    }

    public Vec2 getCenterPoint() {
        return new Vec2(this.body.getPosition().x, this.body.getPosition().y);
    }

    public Shape getDrawableObject() {
        return graphicalObject;
    }

    @Override
    public void update() {
        graphicalObject.setX(ConversionUtility.toViewportX(this.body.getPosition().x - radius));
        graphicalObject.setY(ConversionUtility.toViewportY(this.body.getPosition().y + radius));
    }

    @Override
    Shape getGraphicalObject() {
        return this.graphicalObject;
    }

    @Override
    public Vec2 getWorldCoordinates() {
        return this.body.getPosition();
    }

    @Override
    public Vec2 getViewportCoordinates() {
        return new Vec2(graphicalObject.getCenterX(), graphicalObject.getCenterY());
    }

}
