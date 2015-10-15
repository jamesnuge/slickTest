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
import xyz.jamesnuge.slicktest.objects.components.RectangleObject;
import xyz.jamesnuge.slicktest.objects.basic.userData.GroundUserData;
import xyz.jamesnuge.slicktest.util.BodyDefinitions;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;

public class BasicGroundObject extends RectangleObject<GroundUserData> {

    public BasicGroundObject(Vec2 pos, Vec2 size, World world) {
        super(pos, size, world);
    }

    @Override
    public FixtureDef createFixtureDef() {
        return FixtureDefinitions.getRectangleFixtureDefinition(this.getSize());
    }

    @Override
    public BodyDef createBodyDef() {
        return BodyDefinitions.getStaticBodyDef();
    }

    @Override
    public GroundUserData getUserData() {
        return new GroundUserData();
    }
}
