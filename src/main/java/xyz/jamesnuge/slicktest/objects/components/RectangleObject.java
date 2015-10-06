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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class RectangleObject extends Body {

    private Rectangle graphicalObject;
    public BodyDef bodyDef;

    public RectangleObject(Vec2 pos, Vec2 size, World world, BodyDef bodyDef) {
        super(bodyDef, world);
        this.bodyDef = bodyDef;
        graphicalObject = new Rectangle(pos.x, pos.y, size.x, size.y);
    }

    public Shape getDrawableObject() {
        return graphicalObject;
    }

}
