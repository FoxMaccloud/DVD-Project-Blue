package inf112.RoboRally.app.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.RoboRally.app.models.robot.Direction;
import inf112.RoboRally.app.models.robot.Pos;
import inf112.RoboRally.app.views.robot.RobotView;

public class RobotViewController {

    // game stat
    private int playerNumber;

    // files
    private final String IMAGE_PATH = "Robot/Player";
    private final String FILE_EXTENSION = ".png";

    // view image
    private Texture robotTexture;

    // the view
    private RobotView robotView;

    public RobotViewController(int playerNumber, Pos startPos, Direction startDirection) {
        this.playerNumber = playerNumber;
        robotTexture = new Texture(IMAGE_PATH+playerNumber+FILE_EXTENSION);
        robotTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        robotView = new RobotView(playerNumber, new Sprite(robotTexture), startPos, startDirection);
    }

    public void updateRobotViewPosition(Pos pos, Direction direction) {
        robotView.updateView(pos.x(), pos.y(), direction);
    }

    public void updateRobotViewDirection(Direction direction) {

    }

    public RobotView getRobotView() {
        return robotView;
    }
}