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

public class ConversionUtility {
    public static Vec2 convertVectorFromGraphicsToPhysics(float x_pos, float y_pos, float center_x, float center_y, Vec2 screenSize){
        float relative_x = convertPointFromGraphicsToPhysics(x_pos, center_x, screenSize.x);
        float relative_y = convertPointFromGraphicsToPhysics(y_pos, center_y, screenSize.y);
        return new Vec2(relative_x, relative_y);
    }

    public static float convertPointFromGraphicsToPhysics(float pos, float center, float axisSize) {
        return pos - (axisSize/2) + center;
    }

    public static Vec2 converVectorFromPhysicsToGraphics(float x_pos, float y_pos, float center_x, float center_y, Vec2 screenSize) {
        if (pointIsOnScreen(x_pos, y_pos, center_x, center_y, screenSize)) {
            Vec2 physicsBoundary = new Vec2(center_x + (screenSize.x/2), center_y + (screenSize.y/2));
            return new Vec2(x_pos - (physicsBoundary.x - screenSize.x), y_pos - (physicsBoundary.y - screenSize.y));
        } else {
            throw new IllegalStateException("Point is not on the screen");
        }
    }

    public static boolean pointIsOnScreen(float x_pos, float y_pos, float center_x, float center_y, Vec2 screenSize) {
        boolean xIsOnScreen = x_pos > center_x - (screenSize.x/2) && x_pos < center_x + (screenSize.x/2);
        boolean yIsOnScreen = y_pos > center_y - (screenSize.y/2) && y_pos < center_y + (screenSize.y/2);
        return xIsOnScreen && yIsOnScreen;
    }

    //Convert a JBox2D x coordinate to a JavaFX pixel x coordinate
    public static float toPixelPosX(float posX, float WIDTH) {
        float x = WIDTH*posX / 100.0f;
        return x;
    }

    //Convert a JavaFX pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float posX, float WIDTH) {
        float x =   (posX*100.0f*1.0f)/WIDTH;
        return x;
    }

    //Convert a JBox2D y coordinate to a JavaFX pixel y coordinate
    public static float toPixelPosY(float posY, float HEIGHT) {
        float y = HEIGHT - (1.0f*HEIGHT) * posY / 100.0f;
        return y;
    }

    //Convert a JavaFX pixel y coordinate to a JBox2D y coordinate
    public static float toPosY(float posY, float HEIGHT) {
        float y = 100.0f - ((posY * 100*1.0f) /HEIGHT) ;
        return y;
    }

    //Convert a JBox2D width to pixel width
    public static float toPixelWidth(float width, float screenWidth) {
        return screenWidth*width / 100.0f;
    }

    //Convert a JBox2D height to pixel height
    public static float toPixelHeight(float height, float screenHeight) {
        return screenHeight*height/100.0f;
    }
}
