package xyz.jamesnuge.slicktest.objects.basic;

import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import xyz.jamesnuge.slicktest.controls.KeyHandler;
import xyz.jamesnuge.slicktest.controls.LatchedPressKeyHandler;
import xyz.jamesnuge.slicktest.controls.PressKeyHandler;
import xyz.jamesnuge.slicktest.controls.ReleaseKeyHandler;
import xyz.jamesnuge.slicktest.objects.basic.userData.EmptyUserData;
import xyz.jamesnuge.slicktest.objects.basic.userData.PlayerUserData;
import xyz.jamesnuge.slicktest.objects.components.Controllable;
import xyz.jamesnuge.slicktest.objects.components.EngineObjectUserData;
import xyz.jamesnuge.slicktest.objects.components.RectangleObject;
import xyz.jamesnuge.slicktest.util.BodyDefinitions;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;
import xyz.jamesnuge.slicktest.util.GameInfoWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class BasicPlayerObject extends RectangleObject<EngineObjectUserData> implements Controllable<BasicPlayerObject> {

    public static final float FORCE_EPSILON = 0.3f;
    public static final float JUMP_FORCE = 5f;
    public static final int TOTAL_NUM_OF_JUMPS = 3;
    List<KeyHandler<BasicPlayerObject>> keyHandlers = new ArrayList<>();
    List<ReleaseKeyHandler<BasicPlayerObject>> releaseKeyHandlers = new ArrayList<>();
    LatchedPressKeyHandler<BasicPlayerObject> latchedPressKeyHandler;
    public boolean isJumping = false;
    private boolean jumpLatch = false;

    int numOfJumps = TOTAL_NUM_OF_JUMPS;

    private PlayerUserData userData = new PlayerUserData();

    public BasicPlayerObject(int jump, int left, int right, Vec2 pos, Vec2 size, World world) {
        super(pos, size, world, EmptyUserData.getInstance());

        //this.body.setAngularDamping(300);
        setUserData(userData);
        addMoveRightHandler(right);
        addMoveLeftHandler(left);
        addJumpHandler(jump);
    }

    private void addJumpHandler(int KEY) {
        latchedPressKeyHandler = new LatchedPressKeyHandler<>(KEY, jump, this);
    }

    private boolean canJump() {
        if (numOfJumps <= 0) {
            return false;
        } else {
            if (isJumping) {
                return true;
            }
            return !isMovingY();
        }
    }

    private void addMoveLeftHandler(int KEY){
        addMoveLeftReleaseHandler(KEY);
        keyHandlers.add(new PressKeyHandler<>(KEY, (object) -> object.body.setLinearVelocity(new Vec2(-0.5f, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveLeftReleaseHandler(int key) {
        releaseKeyHandlers.add(new ReleaseKeyHandler<>(key, object -> object.body.setLinearVelocity(new Vec2(0, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveRightHandler(int KEY){
        addMoveRightReleaseHandler(KEY);
        keyHandlers.add(new PressKeyHandler<>(KEY, (object) -> object.body.setLinearVelocity(new Vec2(0.5f, object.body.getLinearVelocity().y)), this));
    }

    private void addMoveRightReleaseHandler(int key) {
        releaseKeyHandlers.add(new ReleaseKeyHandler<>(key, object -> object.body.setLinearVelocity(new Vec2(0, object.body.getLinearVelocity().y)), this));
    }

    public void applyKeyHandlers(GameInfoWrapper info) {
        keyHandlers.stream().forEach(keyHandler -> keyHandler.consume(info));
        releaseKeyHandlers.stream().forEach(keyHandler -> keyHandler.consume(info));
        latchedPressKeyHandler.consume(info);
    }

    @Override
    public void update() {
        super.update();
        jumpHandler();
    }

    private void jumpHandler() {
        if (!isMovingY()) {
            if(!isJumping) {
                numOfJumps = TOTAL_NUM_OF_JUMPS;
            }
            if(!jumpLatch) {
                isJumping = false;
            }
        }
        if (isMovingDown()) {
            jumpLatch = false;
        }
    }


    @Override
    public List<FixtureDef> createFixtureDef() {
        return Collections.singletonList(FixtureDefinitions.getRectangleFixtureDefinition(this.getSize()));
    }

    @Override
    public BodyDef createBodyDef() {
        BodyDef def =  BodyDefinitions.getDynamicBodyDef();
        //def.angularDamping = 32;
        return def;
    }

    @Override
    public PlayerUserData getUserData() {
        return userData;
    }

    @Override
    public boolean hasContactListener() {
        return false;
    }

    @Override
    public ContactListener getContactListener() {
        throw new IllegalStateException("Object does not contain contact listener");
    }

    public boolean isMovingY() {
        return this.body.getLinearVelocity().y > FORCE_EPSILON || this.body.getLinearVelocity().y < -FORCE_EPSILON;
    }

    public boolean isMovingUp() {
        return this.body.getLinearVelocity().y > FORCE_EPSILON;
    }

    public boolean isMovingDown() {
        return this.body.getLinearVelocity().y < -FORCE_EPSILON;
    }

    public boolean isMovingX() {
        return this.body.getLinearVelocity().x > FORCE_EPSILON || this.body.getLinearVelocity().x < -FORCE_EPSILON;
    }

    private Consumer<BasicPlayerObject> jump = object -> {

        if (object.canJump()) {
            object.isJumping = true;
            jumpLatch = true;
            numOfJumps--;
            object.body.applyForceToCenter(new Vec2(0f, JUMP_FORCE));
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicPlayerObject that = (BasicPlayerObject) o;

        if (isJumping != that.isJumping) return false;
        if (jumpLatch != that.jumpLatch) return false;
        if (numOfJumps != that.numOfJumps) return false;
        if (keyHandlers != null ? !keyHandlers.equals(that.keyHandlers) : that.keyHandlers != null) return false;
        if (releaseKeyHandlers != null ? !releaseKeyHandlers.equals(that.releaseKeyHandlers) : that.releaseKeyHandlers != null)
            return false;
        if (latchedPressKeyHandler != null ? !latchedPressKeyHandler.equals(that.latchedPressKeyHandler) : that.latchedPressKeyHandler != null)
            return false;
        if (userData != null ? !userData.equals(that.userData) : that.userData != null) return false;
        return !(jump != null ? !jump.equals(that.jump) : that.jump != null);
    }

    @Override
    public int hashCode() {
        int result = keyHandlers != null ? keyHandlers.hashCode() : 0;
        result = 31 * result + (releaseKeyHandlers != null ? releaseKeyHandlers.hashCode() : 0);
        result = 31 * result + (latchedPressKeyHandler != null ? latchedPressKeyHandler.hashCode() : 0);
        result = 31 * result + (userData != null ? userData.hashCode() : 0);
        result = 31 * result + (jump != null ? jump.hashCode() : 0);
        return result;
    }

    @Override
    public List<KeyHandler<BasicPlayerObject>> getKeyHandlers() {
        List<KeyHandler<BasicPlayerObject>> list = new ArrayList<>();
        list.addAll(keyHandlers);
        list.addAll(releaseKeyHandlers);
        list.add(latchedPressKeyHandler);
        return list;
    }
}
