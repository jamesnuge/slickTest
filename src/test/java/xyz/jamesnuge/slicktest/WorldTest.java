package xyz.jamesnuge.slicktest;/*
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

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.junit.Test;
import xyz.jamesnuge.slicktest.util.BodyDefinitions;

public class WorldTest {

    BodyDefinitions bds;
    World world = new World(new Vec2(0f, -9.8f));
    Fixture f;

    @Test
    public void testWorldGravityUpdate() {
        Body ground = buildGround();
        System.out.println(f.testPoint(new Vec2(10f,0f)));
    }

    public Body buildCircle() {
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(0f,2f);
        bd.active = true;

        CircleShape cs = new CircleShape();
        cs.m_radius = 1f;

        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        fd.density = 0.6f;
        fd.friction = 0.3f;
        fd.restitution = 0.8f;

        Body body = world.createBody(bd);
        body.createFixture(fd);
        return body;
    }

    public Body buildGround() {
        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position.set(0f, -0.5f);
        bd.active = true;

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(10f,0.5f);

        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.density = 1;
        fd.friction = 0.3f;

        Body body = world.createBody(bd);
        f = body.createFixture(fd);
        return body;
    }
}
