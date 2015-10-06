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
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;

public class CircleObject extends Body implements Updatable {
    public Circle graphicalObject;
    public float radius;


    public CircleObject(Vec2 pos, float radius, World world, BodyDef bodyDef, FixtureDef fixtureDef) {
        super(bodyDef, world);
        this.createFixture(fixtureDef);
        this.radius = radius;
        graphicalObject = new Circle(pos.x, pos.y, radius);
    }

    public CircleObject(Vec2 pos, float radius, World world, BodyDef bodyDef) {
        super(bodyDef, world);
        this.createFixture(FixtureDefinitions.getCircleFixtureDefinition(radius));
        this.radius = radius;
        graphicalObject = new Circle(pos.x, pos.y, radius);
    }

    public Vec2 getCenterPoint() {
        return new Vec2(this.getPosition().x - radius, this.getPosition().y - radius);
    }

    public Shape getDrawableObject() {
        return graphicalObject;
    }

    @Override
    public void update() {
        graphicalObject.setX(this.getPosition().x);
        graphicalObject.setY(this.getPosition().y);
    }
}
