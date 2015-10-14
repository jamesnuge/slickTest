package xyz.jamesnuge.slicktest;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.*;
import xyz.jamesnuge.slicktest.controls.KeyHandler;
import xyz.jamesnuge.slicktest.controls.ReleaseKeyHandler;
import xyz.jamesnuge.slicktest.objects.basic.BasicPlatformObject;
import xyz.jamesnuge.slicktest.objects.components.CircleObject;
import xyz.jamesnuge.slicktest.util.DebugUtilities;
import xyz.jamesnuge.slicktest.objects.components.EngineObject;
import xyz.jamesnuge.slicktest.objects.components.RectangleObject;
import xyz.jamesnuge.slicktest.objects.basic.BasicGroundObject;
import xyz.jamesnuge.slicktest.objects.basic.BasicPlayerObject;

import java.util.ArrayList;
import java.util.List;

public class Engine extends BasicGame {

    public static final String TITLE = "Slick Test";
    public static final Integer FPS = 60;

    public final GameInfoWrapper gameInfo = new GameInfoWrapper();

    public List<KeyHandler<CircleObject>> circleKeyHandlers = new ArrayList<>();
    public List<ReleaseKeyHandler<World>> worldKeyHandlers = new ArrayList<>();
    public List<BasicPlayerObject> circles = new ArrayList<>();
    public BasicGroundObject groundObject;
    public BasicPlayerObject player;
    public List<BasicPlatformObject> platforms = new ArrayList<>();

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
        player = new BasicPlayerObject(Input.KEY_SPACE, Input.KEY_LEFT, Input.KEY_RIGHT, new Vec2(0,2), new Vec2(0.1f, 0.2f), world);
        platforms.add(new BasicPlatformObject(new Vec2(2, 1), new Vec2(2, 0.1f), world));
        //platforms.add(new BasicPlatformObject(new Vec2(-2, 1.5f), new Vec2(2, 0.1f), world));
        circles.add(player);
        groundObject = new BasicGroundObject(new Vec2(0f,0f), new Vec2(8f, 0.2f), world);
        System.out.println(isPointInRectangle(new Vec2(0f, 2f), groundObject));
    }

    public static boolean isPointInRectangle(Vec2 point, RectangleObject rect){
        boolean x = point.x > rect.body.getPosition().x - rect.getSize().x && point.x < rect.body.getPosition().x + rect.getSize().x;
        boolean y = point.y > rect.body.getPosition().y - rect.getSize().y && point.y < rect.body.getPosition().y + rect.getSize().y;
        return x && y;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        gameInfo.update(gameContainer, i);
        updateWorld();
        circleKeyHandlers.stream().forEach(circleKeyHandlers -> circleKeyHandlers.consume(gameInfo));
        worldKeyHandlers.stream().forEach(worldKeyHandler -> worldKeyHandler.consume(gameInfo));
    }

    private void updateWorld() {
        world.step(SimulationProperties.TIME_STEP, SimulationProperties.VELOCITY_ITERATIONS, SimulationProperties.POSITION_ITERATIONS);
        circles.stream().forEach(circle -> circle.applyKeyHandlers(gameInfo));
        circles.stream().forEach(EngineObject::update);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        platforms.stream().forEach(platform -> platform.draw(graphics));
        circles.stream().forEach(circleObject -> circleObject.draw(graphics));
        groundObject.draw(graphics);
        DebugUtilities.drawPos(graphics, platforms.get(0).body);
        Viewport.drawViewportInfo(graphics);
    }


}
