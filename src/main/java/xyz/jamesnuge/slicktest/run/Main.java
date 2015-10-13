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

package xyz.jamesnuge.slicktest.run;

import org.newdawn.slick.AppGameContainer;
import xyz.jamesnuge.slicktest.Engine;
import xyz.jamesnuge.slicktest.Viewport;

public class Main {
    public static void main(String[] args) {
        try {
            AppGameContainer appGameContainer = new AppGameContainer(new Engine());
            appGameContainer.setDisplayMode(Viewport.WIDTH, Viewport.HEIGHT, false);
            appGameContainer.setTargetFrameRate(Engine.FPS);
            appGameContainer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
