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

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Graphics;

public class DebugUtilities {

    public static void drawLinearVelocity(Graphics g, Body b){
        g.drawString("Linear Velocity x: " + b.getLinearVelocity().x, 200, 200);
        g.drawString("Linear Velocity y: " + b.getLinearVelocity().y, 200, 220);
    }

    public static void drawAngle(Graphics g, Body b){
        g.drawString("Body angle: " + b.getAngle(), 200, 200);
    }

    public static void drawPos(Graphics g, Body b) {
        g.drawString("x: " + b.getPosition().x, 200, 200);
        g.drawString("y: " + b.getPosition().y, 200, 220);
    }

    public static void drawMiddleOfScreen(Graphics g) {
        g.drawRect(Viewport.WIDTH/2, Viewport.HEIGHT/2, 2, 2);
    }
}
