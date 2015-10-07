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
import xyz.jamesnuge.slicktest.util.ConversionUtility;

public class Viewport {
    public static float HEIGHT = 600;
    public static float WIDTH = 800;
    public static Vec2 POSITION = new Vec2(0,0);


    public static boolean isPointInViewport(Vec2 point) {
        boolean x = ConversionUtility.toPixelPosX(point.x) < (POSITION.x + WIDTH/2) && ConversionUtility.toPixelPosX(point.x) > (POSITION.x - WIDTH/2);
        boolean y = ConversionUtility.toPixelPosY(point.y) < (POSITION.y + HEIGHT/2) && ConversionUtility.toPixelPosY(point.y) > (POSITION.y - HEIGHT/2);
        return x && y;
    }
}
