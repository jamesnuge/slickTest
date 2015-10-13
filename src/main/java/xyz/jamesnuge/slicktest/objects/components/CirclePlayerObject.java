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
import org.newdawn.slick.Graphics;
import xyz.jamesnuge.slicktest.GameInfoWrapper;
import xyz.jamesnuge.slicktest.KeyHandler;
import xyz.jamesnuge.slicktest.controls.ReleaseKeyHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CirclePlayerObject extends CircleObject implements Controllable<CirclePlayerObject> {
    Map<Integer, KeyHandler<CirclePlayerObject>> keyHandlers = new HashMap<>();
    Map<Integer, ReleaseKeyHandler<CirclePlayerObject>> releaseKeyHandlers = new HashMap<>();


    public CirclePlayerObject(int jump, int left, int right, Vec2 pos, float radius, World world, BodyDef bodyDef) {
        super(pos, radius, world, bodyDef);
        addMoveLeftHandler(left);
        addMoveRightHandler(right);
    }

    @Override
    public List<KeyHandler<CirclePlayerObject>> getKeyHandlers() {
        return keyHandlers.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    //TODO: Add release keyhandler for buttons
    private void addMoveLeftHandler(int KEY){
        addMoveLeftReleaseHandler(KEY);
        keyHandlers.put(KEY, new KeyHandler<>(KEY, (object) -> object.body.setLinearVelocity(new Vec2(-0.5f, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveLeftReleaseHandler(int key) {
        releaseKeyHandlers.put(key, new ReleaseKeyHandler<>(key, object -> object.body.setLinearVelocity(new Vec2(0, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveRightHandler(int KEY){
        addMoveRightReleaseHandler(KEY);
        keyHandlers.put(KEY, new KeyHandler<>(KEY, (object) -> object.body.applyLinearImpulse(new Vec2(0.01f, 0f), object.body.getPosition()), this));
    }

    private void addMoveRightReleaseHandler(int key) {
        releaseKeyHandlers.put(key, new ReleaseKeyHandler<>(key, object -> object.body.setLinearVelocity(new Vec2(0, object.body.getLinearVelocity().y)), this));
    }

    @Override
    public void update() {
        super.update();
    }

    public void applyKeyHandlers(GameInfoWrapper info) {
        keyHandlers.entrySet().stream().map(Map.Entry::getValue).forEach(keyHandler -> keyHandler.consume(info));
        releaseKeyHandlers.entrySet().stream().map(Map.Entry::getValue).forEach(keyHandler -> keyHandler.consume(info));
    }


    public void drawLinearVelocity(Graphics g) {
        g.drawString("Linear Velocity x: " + this.body.getLinearVelocity().x, 200, 200);
        g.drawString("Linear Velocity y: " + this.body.getLinearVelocity().y, 200, 220);
    }
}
