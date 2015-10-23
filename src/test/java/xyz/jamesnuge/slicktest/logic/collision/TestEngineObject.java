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

package xyz.jamesnuge.slicktest.logic.collision;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import xyz.jamesnuge.slicktest.objects.components.CircleObject;

import java.util.List;

public class TestEngineObject extends CircleObject<TestObjectUserData> {
    public TestEngineObject(Vec2 pos, float radius, World world, BodyDef bodyDef) {
        super(pos, radius, world, bodyDef);
    }

    @Override
    public List<FixtureDef> createFixtureDef() {
        return null;
    }

    @Override
    public BodyDef createBodyDef() {
        return null;
    }

    @Override
    protected BodyDef getBodyDef() {
        return null;
    }

    @Override
    protected List<FixtureDef> getFixtureDefs() {
        return null;
    }

    @Override
    public TestObjectUserData getUserData() {
        return null;
    }

    @Override
    public boolean hasContactListener() {
        return true;
    }
}
