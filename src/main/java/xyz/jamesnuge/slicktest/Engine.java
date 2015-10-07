package xyz.jamesnuge.slicktest;

import javafx.scene.shape.Circle;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.*;
import xyz.jamesnuge.slicktest.objects.components.CircleObject;
import xyz.jamesnuge.slicktest.objects.components.EngineObject;
import xyz.jamesnuge.slicktest.objects.components.RectangleObject;
import xyz.jamesnuge.slicktest.util.BodyDefinitions;
import xyz.jamesnuge.slicktest.util.FixtureDefinitions;

import java.util.ArrayList;
import java.util.List;

public class Engine extends BasicGame {

    public static float HEIGHT = 600;
    public static float WIDTH = 800;
    public static final String TITLE = "Slick Test";

    public static float speed = 0.25f;
    public final GameInfoWrapper gameInfo = new GameInfoWrapper();

    public BodyDefinitions bodyDefinitions = new BodyDefinitions();
    public FixtureDefinitions fixtureDefinitions = new FixtureDefinitions();

    public List<KeyHandler<CircleObject>> circleKeyHandlers = new ArrayList<>();

    public Vec2 impulseDown = new Vec2(10, 0);

    public CircleObject circle;
    public RectangleObject groundObject;

    public World world = new World(new Vec2(0.0f, -10.0f));

    public Engine() {
        super(TITLE);
    }

    public static void main(String[] args) {

        try {
            AppGameContainer appGameContainer = new AppGameContainer(new Engine());
            appGameContainer.setDisplayMode(800, 600, false);
            appGameContainer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        world.setAllowSleep(true);
        circle = new CircleObject(new Vec2(0, 0), 10, world, bodyDefinitions.getDynamicBodyDef());
        groundObject = new RectangleObject(new Vec2(0,580), new Vec2(800, 20), world, bodyDefinitions.getStaticBodyDef());
        addKeyHandlers();
        circle.printCoordinates();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        gameInfo.update(gameContainer, i);
        circleKeyHandlers.stream().forEach(circleKeyHandlers -> circleKeyHandlers.consume(gameInfo));
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        circle.draw(graphics);
        graphics.draw(groundObject.getDrawableObject());
    }

    private void addKeyHandlers() {
//        circleKeyHandlers.add(new KeyHandler<>(Input.KEY_UP, (updateInfo, circle) -> circle.setY(circle.getY() - (speed * updateInfo.i)), circle));
        circleKeyHandlers.add(new KeyHandler<>(Input.KEY_DOWN, (updateInfo, circle) -> {
            System.out.println("hit handler");
            circle.update();
            circle.applyLinearImpulse(impulseDown, circle.getCenterPoint());
        }, circle));
        circleKeyHandlers.add(new KeyHandler<>(Input.KEY_SPACE, (updateInfo, circle) -> System.out.println("slick x pos: " + circle.graphicalObject.getCenterX() + " slick y pos: " + circle.graphicalObject.getCenterY() + "\n" +
                "jbox x pos: " + circle.getCenterPoint().x + " jbox y pos: " + circle.getCenterPoint().y), circle));
//        circleKeyHandlers.add(new KeyHandler<>(Input.KEY_RIGHT, (updateInfo, circle) -> circle.setX(circle.getX() + (speed * updateInfo.i)), circle));
    }

    public void drawObject(Graphics graphics, EngineObject object) {
        if (Viewport.isPointInViewport(object.getPhysicalCoordinates())){
            Circle circle = new Circle();
        }
    }

}
