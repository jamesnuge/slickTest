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

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

import java.util.ArrayList;
import java.util.List;

public class FixtureDefinitions {

    public static final Vec2 PIXEL_BY_PIXEL_FIXTURE = new Vec2(ConversionUtility.toWorldWidth(1), ConversionUtility.toWorldHeight(1));

    public static FixtureDef getRectangleFixtureDefinition(Vec2 size, boolean isSensor) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1;
        fd.friction = 0.3f;
        return fd;
    }

    public static FixtureDef getRectangleFixtureDefinition(Vec2 size, Vec2 pos, boolean isSensor) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2, pos, 0);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1;
        fd.friction = 0.3f;
        fd.isSensor = isSensor;
        return fd;
    }

    public static FixtureDef getCircleFixtureDefinition(float radius, boolean isSensor) {
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = radius;
        return createFixtureDef(circleShape, 0.6f, 0.3f, 0.8f, isSensor);
    }

    public static FixtureDef createFixtureDef(Shape shape, float density, float friction, float restitution, boolean isSensor){
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = density;
        fd.friction = friction;
        fd.restitution = restitution;
        fd.isSensor = isSensor;
        return fd;
    }

    /**
     *
     * @param size This is the size of the bounding box of the player object
     * @return returns the list of fixture definitions required to create a basic player object
     */
    public static List<FixtureDef> getBasicPlayerFixtureDef(Vec2 size, Vec2 pos) {
        List<FixtureDef> listToReturn = new ArrayList<>();
        //Add bounding box
        listToReturn.add(getRectangleFixtureDefinition(size, true));
        //Add bottom left box
        FixtureDef fd = new FixtureDef();

        listToReturn.add(getRectangleFixtureDefinition(PIXEL_BY_PIXEL_FIXTURE, false));
        return listToReturn;
    }

}
