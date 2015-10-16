package xyz.jamesnuge.slicktest;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.*;
import xyz.jamesnuge.slicktest.controls.KeyHandler;
import xyz.jamesnuge.slicktest.controls.ReleaseKeyHandler;
import xyz.jamesnuge.slicktest.objects.basic.BasicGroundObject;
import xyz.jamesnuge.slicktest.objects.basic.BasicPlatformObject;
import xyz.jamesnuge.slicktest.objects.basic.BasicPlayerObject;
import xyz.jamesnuge.slicktest.objects.components.CircleObject;
import xyz.jamesnuge.slicktest.objects.components.Controllable;
import xyz.jamesnuge.slicktest.objects.components.EngineObject;
import xyz.jamesnuge.slicktest.util.GameInfoWrapper;
import xyz.jamesnuge.slicktest.util.SimulationProperties;
import xyz.jamesnuge.slicktest.util.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Engine extends BasicGame {

    public static final String TITLE = "Slick Test";
    public static final Integer FPS = 60;

    public final GameInfoWrapper gameInfo = new GameInfoWrapper();

    private List<EngineObject> objects = new ArrayList<>();

    public List<KeyHandler<CircleObject>> circleKeyHandlers = new ArrayList<>();
    public List<ReleaseKeyHandler<World>> worldKeyHandlers = new ArrayList<>();

    public World world = new World(new Vec2(0.0f, -10.0f));
    {
        world.setAllowSleep(true);
    }

    public Engine() {
        super(TITLE);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        world.setAllowSleep(true);
        objects.add(new BasicPlayerObject(Input.KEY_SPACE, Input.KEY_LEFT, Input.KEY_RIGHT, new Vec2(0, 2), new Vec2(0.1f, 0.2f), world));
        objects.add(new BasicPlatformObject(new Vec2(2, 1), new Vec2(2, 0.1f), world));
        objects.add(new BasicGroundObject(new Vec2(0f,0f), new Vec2(8f, 0.2f), world));
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        gameInfo.update(gameContainer, i);
        updateWorld();
        objects.stream().forEach(object -> {
            if (object instanceof Controllable) {
                ((Controllable<?>)object).getKeyHandlers().stream().forEach(handler -> handler.consume(gameInfo));
            }
        });
    }

    private void updateWorld() {
        objects.stream().forEach(EngineObject::update);
        world.step(SimulationProperties.TIME_STEP, SimulationProperties.VELOCITY_ITERATIONS, SimulationProperties.POSITION_ITERATIONS);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        objects.stream().forEach(object -> object.draw(graphics));
        Viewport.drawViewportInfo(graphics);
    }


}
