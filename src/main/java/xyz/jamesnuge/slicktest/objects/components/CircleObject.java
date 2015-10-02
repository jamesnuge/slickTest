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

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Circle;

public class CircleObject extends Circle {
    public CircleShape shape = new CircleShape();
    public FixtureDef fixture = new FixtureDef();
    private boolean isCreated = false;
    private Body body;


    public CircleObject(float centerPointX, float centerPointY, float radius, float density, float friction, float restitution) {
        super(centerPointX, centerPointY, radius);
        setPhysicalProperties(radius, density, friction, restitution);
    }

    private void setPhysicalProperties(float radius, float density, float friction, float restitution) {
        shape.m_radius = radius;
        fixture.density = density;
        fixture.friction = friction;
        fixture.restitution = restitution;
        fixture.shape = shape;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void create(World world) {

    }
}
