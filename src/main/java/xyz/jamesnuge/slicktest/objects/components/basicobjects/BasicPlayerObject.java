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

package xyz.jamesnuge.slicktest.objects.components.basicobjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import xyz.jamesnuge.slicktest.GameInfoWrapper;
import xyz.jamesnuge.slicktest.KeyHandler;
import xyz.jamesnuge.slicktest.controls.ReleaseKeyHandler;
import xyz.jamesnuge.slicktest.objects.components.Controllable;
import xyz.jamesnuge.slicktest.objects.components.RectangleObject;
import xyz.jamesnuge.slicktest.objects.components.userData.PlayerUserData;
import xyz.jamesnuge.slicktest.util.BodyDefinitions;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicPlayerObject extends RectangleObject<PlayerUserData> implements Controllable<BasicPlayerObject> {

    Map<Integer, KeyHandler<BasicPlayerObject>> keyHandlers = new HashMap<>();
    Map<Integer, ReleaseKeyHandler<BasicPlayerObject>> releaseKeyHandlers = new HashMap<>();

    private PlayerUserData userData = new PlayerUserData();

    public BasicPlayerObject(int jump, int left, int right, Vec2 pos, Vec2 size, World world) {
        super(pos, size, world, BodyDefinitions.getDynamicBodyDef());
        addMoveRightHandler(right);
        addMoveLeftHandler(left);
        addJumpHandler(jump);
    }

    @Override
    public List<KeyHandler<BasicPlayerObject>> getKeyHandlers() {
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

    @Override
    public FixtureDef getFixtureDef() {
        return FixtureDefinitions.getRectangleFixtureDefinition(this.getSize());
    }

    @Override
    protected BodyDef getBodyDef() {
        return BodyDefinitions.getDynamicBodyDef();
    }

    public void setUserData(PlayerUserData userData) {
        this.userData = userData;
    }

    @Override
    public PlayerUserData getUserData() {
        return userData;
    }

}
