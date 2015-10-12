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
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import xyz.jamesnuge.slicktest.GameInfoWrapper;
import xyz.jamesnuge.slicktest.KeyHandler;
import xyz.jamesnuge.slicktest.controls.ReleaseKeyHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerRectangleObject extends RectangleObject implements Controllable<PlayerRectangleObject> {

    Map<Integer, KeyHandler<PlayerRectangleObject>> keyHandlers = new HashMap<>();
    Map<Integer, ReleaseKeyHandler<PlayerRectangleObject>> releaseKeyHandlers = new HashMap<>();

    public PlayerRectangleObject(int jump, int left, int right, Vec2 pos, Vec2 size, World world, BodyDef bodyDef) {
        super(pos, size, world, bodyDef);
        addMoveRightHandler(right);
        addMoveLeftHandler(left);
        addJumpHandler(jump);
    }

    @Override
    public List<KeyHandler<PlayerRectangleObject>> getKeyHandlers() {
        return null;
    }

    private void addJumpHandler(int KEY) {
        keyHandlers.put(KEY, new KeyHandler<>(KEY, object -> {
            if(object.canJump()) {
                object.body.applyForceToCenter(new Vec2(0f, 5f));
            }
        }, this));
    }

    private boolean canJump() {
        return this.body.getLinearVelocity().y == 0f;
    }

    private void addMoveLeftHandler(int KEY){
        addMoveLeftReleaseHandler(KEY);
        keyHandlers.put(KEY, new KeyHandler<>(KEY, (object) -> object.body.setLinearVelocity(new Vec2(-0.5f, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveLeftReleaseHandler(int key) {
        releaseKeyHandlers.put(key, new ReleaseKeyHandler<>(key, object -> object.body.setLinearVelocity(new Vec2(0, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveRightHandler(int KEY){
        addMoveRightReleaseHandler(KEY);
        keyHandlers.put(KEY, new KeyHandler<>(KEY, (object) -> object.body.setLinearVelocity(new Vec2(0.5f, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveRightReleaseHandler(int key) {
        releaseKeyHandlers.put(key, new ReleaseKeyHandler<>(key, object -> object.body.setLinearVelocity(new Vec2(0, object.body.getLinearVelocity().y)), this));
    }

    public void applyKeyHandlers(GameInfoWrapper info) {
        keyHandlers.entrySet().stream().map(Map.Entry::getValue).forEach(keyHandler -> keyHandler.consume(info));
        releaseKeyHandlers.entrySet().stream().map(Map.Entry::getValue).forEach(keyHandler -> keyHandler.consume(info));
    }
}
