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

package xyz.jamesnuge.slicktest.objects.basic;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import xyz.jamesnuge.slicktest.objects.basic.userData.EmptyUserData;
import xyz.jamesnuge.slicktest.objects.basic.userData.PlatformUserData;
import xyz.jamesnuge.slicktest.objects.components.EngineObjectUserData;
import xyz.jamesnuge.slicktest.objects.components.RectangleObject;
import xyz.jamesnuge.slicktest.util.BodyDefinitions;
import xyz.jamesnuge.slicktest.util.ConversionUtility;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;

import java.util.Collections;
import java.util.List;

public class BasicPlatformObject extends RectangleObject<EngineObjectUserData> {
    public BasicPlatformObject(Vec2 pos, Vec2 size, World world) {
        super(pos, size, world, EmptyUserData.getInstance());

        System.out.println("Body x: " + this.body.getPosition().x);
        System.out.println("Body y: " + this.body.getPosition().y);
        System.out.println("Graphical x: " + this.getDrawableObject().getX());
        System.out.println("Graphical y: " + this.getDrawableObject().getY());
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.draw(new Rectangle(ConversionUtility.toViewportX(this.body.getPosition().x), ConversionUtility.toViewportY(this.body.getPosition().y), 1, 1));
        g.drawString("graphical x: " + this.getDrawableObject().getX(), 400, 400);
        g.drawString("graphical y: " + this.getDrawableObject().getY(), 400, 420);
    }

    @Override
    public List<FixtureDef> createFixtureDef() {
        return Collections.singletonList(FixtureDefinitions.getRectangleFixtureDefinition(this.getSize()));
    }

    @Override
    public BodyDef createBodyDef() {
        return BodyDefinitions.getKinematicBodyDef();
    }

    @Override
    public PlatformUserData getUserData() {
        return null;
    }

    @Override
    public boolean hasContactListener() {
        return false;
    }
}
