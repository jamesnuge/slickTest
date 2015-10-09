package xyz.jamesnuge.slicktest;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.*;
import xyz.jamesnuge.slicktest.controls.ReleaseKeyHandler;
import xyz.jamesnuge.slicktest.objects.components.CircleObject;
import xyz.jamesnuge.slicktest.objects.components.RectangleObject;
import xyz.jamesnuge.slicktest.util.BodyDefinitions;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;

import java.util.ArrayList;
import java.util.List;

public class Engine extends BasicGame {

    public static final String TITLE = "Slick Test";
    public static final Integer FPS = 60;

    public final GameInfoWrapper gameInfo = new GameInfoWrapper();

    public List<KeyHandler<CircleObject>> circleKeyHandlers = new ArrayList<>();
    public List<ReleaseKeyHandler<World>> worldKeyHandlers = new ArrayList<>();
    public CircleObject circle;
    public RectangleObject groundObject;

    public World world = new World(new Vec2(0.0f, -10.0f));
    {
        world.setAllowSleep(true);
    }

    public Engine() {
        super(TITLE);
    }

    public static void main(String[] args) {

        try {
            AppGameContainer appGameContainer = new AppGameContainer(new Engine());
            appGameContainer.setDisplayMode(Viewport.WIDTH, Viewport.HEIGHT, false);
            appGameContainer.setTargetFrameRate(FPS);
            appGameContainer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        world.setAllowSleep(true);

        circle = new CircleObject(new Vec2(0, 2), 0.1f, world, BodyDefinitions.getDynamicBodyDef(), FixtureDefinitions.getCircleFixtureDefinition(10));
        //TODO: Why does this only work with the y pos as -10f and nothing higher?
        groundObject = new RectangleObject(new Vec2(0f,-10f), new Vec2(8f, 0.2f), world, BodyDefinitions.getStaticBodyDef());
        addKeyHandlers();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        updateWorld();
        gameInfo.update(gameContainer, i);
        circleKeyHandlers.stream().forEach(circleKeyHandlers -> circleKeyHandlers.consume(gameInfo));
        worldKeyHandlers.stream().forEach(worldKeyHandler -> worldKeyHandler.consume(gameInfo));
    }

    private void updateWorld() {
        world.step(SimulationProperties.TIME_STEP, SimulationProperties.VELOCITY_ITERATIONS, SimulationProperties.POSITION_ITERATIONS);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //Print circle coordinates
        graphics.drawString("viewport posX: " + circle.getViewportCoordinates().x, 200,200);
        graphics.drawString("viewport posY: " + circle.getViewportCoordinates().y, 200,220);
        graphics.drawString("world posX: " + circle.body.getPosition().x, 200, 240);
        graphics.drawString("world posY: " + circle.body.getPosition().y, 200, 260);

        //Print ground coordinates
        graphics.drawString("viewport posX: " + groundObject.getViewportCoordinates().x, 200,500);
        graphics.drawString("viewport posY: " + groundObject.getViewportCoordinates().y, 200,520);
        graphics.drawString("world posX: " + groundObject.body.getPosition().x, 200, 540);
        graphics.drawString("world posY: " + groundObject.body.getPosition().y, 200, 560);

        circle.draw(graphics);
        groundObject.draw(graphics);
        Viewport.drawViewportInfo(graphics);
    }

    private void addKeyHandlers() {
        worldKeyHandlers.add(new ReleaseKeyHandler<>(Input.KEY_SPACE, (updateInfo, world) -> world.step(SimulationProperties.TIME_STEP, SimulationProperties.VELOCITY_ITERATIONS, SimulationProperties.POSITION_ITERATIONS), world));
        circleKeyHandlers.add(new ReleaseKeyHandler<>(Input.KEY_DOWN, (updateInfo, circle) -> {
            System.out.println("hit handler");
            //circle.body.applyForceToCenter(new Vec2(0f, -9.8f));
            circle.update();
        }, circle));

    }

}
