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

import org.jbox2d.common.Vec2;
import xyz.jamesnuge.slicktest.Viewport;

public class ConversionUtility {

    public static final Float PIXEL_TO_METRE_RATIO = 0.01f;

    //Convert a Slick2d pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float posX) {
        float x = (posX * 100.0f * 1.0f) / Viewport.WIDTH;
        return x;
    }

    //Convert a Slick2d pixel y coordinate to a JBox2D y coordinate
    public static float toPosY(float posY) {
        float y = 100.0f - ((posY * 100 * 1.0f) / Viewport.HEIGHT);
        return y;
    }

    //Convert a JBox2D width to pixel width
    public static float toPixelWidth(float width) {
        return width / PIXEL_TO_METRE_RATIO;
    }

    public static float fromMetreToPixel(float measurement) {
        return measurement / PIXEL_TO_METRE_RATIO;
    }

    //Convert a JBox2D height to pixel height
    public static float toPixelHeight(float height) {
       return height / PIXEL_TO_METRE_RATIO;
    }

    public static float toWorldHeight(float height) {
        return height * PIXEL_TO_METRE_RATIO;
    }

    public static float toWorldWidth(float width) {
        return width * PIXEL_TO_METRE_RATIO;
    }

    public static float toViewportX(float posX) {
        float diff = fromMetreToPixel(posX) - Viewport.POSITION.x;
        return Viewport.WIDTH / 2 + diff;
    }

    public static float toViewportY(float posY) {
        float diff = fromMetreToPixel(posY) - Viewport.POSITION.y;
        return Viewport.HEIGHT / 2 - diff;
    }

    public static Vec2 toViewportPos(Vec2 point) {
        float x = fromMetreToPixel(Viewport.POSITION.x + point.x) + (Viewport.WIDTH / 2);
        float y = fromMetreToPixel(Viewport.POSITION.y - point.y) + (Viewport.HEIGHT / 2);
        return new Vec2(x, y);
    }
}
