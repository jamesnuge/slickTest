package xyz.jamesnuge.slicktest;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Engine extends BasicGame {

    public static final String TITLE = "Slick Test";
    public float speed = 0.25f;
    public final GameInfoWrapper gameInfo = new GameInfoWrapper();
    public final Rectangle testRect = new Rectangle(400,300, 10, 10);
    public List<KeyHandler<Rectangle>> rectangleKeyHandlers = new ArrayList<>();

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
        addKeyHandlers();

        world.setAllowSleep(true);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        gameInfo.update(gameContainer, i);
        rectangleKeyHandlers.stream().forEach(rectangleKeyHandler -> rectangleKeyHandler.consume(gameInfo));
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.draw(testRect);
    }

    private void addKeyHandlers() {
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_UP, (updateInfo, rectangle) -> rectangle.setY(rectangle.getY() - (speed * updateInfo.i)), testRect));
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_DOWN, (updateInfo, rectangle) -> rectangle.setY(rectangle.getY() + (speed * updateInfo.i)), testRect));
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_LEFT, (updateInfo, rectangle) -> rectangle.setX(rectangle.getX() - (speed * updateInfo.i)), testRect));
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_RIGHT, (updateInfo, rectangle) -> rectangle.setX(rectangle.getX() + (speed * updateInfo.i)), testRect));
    }


}
