package xyz.jamesnuge.slicktest;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import xyz.jamesnuge.slicktest.sprites.BarneySprite;

import java.util.ArrayList;
import java.util.List;

public class Engine extends BasicGame {

    public static final String TITLE = "Slick Test";
    public float speed = 0.25f;
    public Input gameInput;
    public final GameInfo gameInfo = new GameInfo();
    public final Rectangle testRect = new Rectangle(400,300, 10, 10);
    public List<KeyHandler<Rectangle>> rectangleKeyHandlers = new ArrayList<>();
    public BarneySprite barney;

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
        barney = new BarneySprite();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        gameInfo.update(gameContainer, i);
        rectangleKeyHandlers.stream().forEach(rectangleKeyHandler -> rectangleKeyHandler.consume(gameInfo));
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.draw(testRect);
        barney.getSprite(0,0).draw(100,100);
    }

    private void addKeyHandlers() {
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_UP, (updateInfo, rectangle) -> rectangle.setY(rectangle.getY() - (speed * updateInfo.i)), testRect));
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_DOWN, (updateInfo, rectangle) -> rectangle.setY(rectangle.getY() + (speed * updateInfo.i)), testRect));
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_LEFT, (updateInfo, rectangle) -> rectangle.setX(rectangle.getX() - (speed * updateInfo.i)), testRect));
        rectangleKeyHandlers.add(new KeyHandler<>(Input.KEY_RIGHT, (updateInfo, rectangle) -> rectangle.setX(rectangle.getX() + (speed * updateInfo.i)), testRect));
    }


}
