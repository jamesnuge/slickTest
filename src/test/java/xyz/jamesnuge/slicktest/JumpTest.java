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

package xyz.jamesnuge.slicktest;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Test;
import org.newdawn.slick.Input;
import xyz.jamesnuge.slicktest.objects.basic.BasicPlayerObject;
import xyz.jamesnuge.slicktest.util.SimulationProperties;

public class JumpTest {
    World world = new World(new Vec2(0f, -9.8f));
    BasicPlayerObject player = new BasicPlayerObject(Input.KEY_SPACE, Input.KEY_LEFT, Input.KEY_RIGHT, new Vec2(1,0), new Vec2(0.1f, 0.2f), world);

    @Test
    public void jumpTest() {
        while(player.body.getLinearVelocity().y != 0) {
            world.step(SimulationProperties.TIME_STEP, SimulationProperties.VELOCITY_ITERATIONS, SimulationProperties.POSITION_ITERATIONS);
        }
        //player.getKeyHandlers().stream().forEach((handler) -> );
    }


}
