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

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

public class BodyDefinitions {
    private BodyDef dynamicBodyDef = new BodyDef();
    private BodyDef staticBodyDef = new BodyDef();
    private BodyDef kinematicBodyDef = new BodyDef();

    public BodyDefinitions() {
        dynamicBodyDef.type = BodyType.DYNAMIC;
        staticBodyDef.type = BodyType.STATIC;
        kinematicBodyDef.type = BodyType.KINEMATIC;
    }

    public BodyDef getDynamicBodyDef() {
        return dynamicBodyDef;
    }

    public BodyDef getStaticBodyDef() {
        return staticBodyDef;
    }

    public BodyDef getKinematicBodyDef() {
        return kinematicBodyDef;
    }
}
