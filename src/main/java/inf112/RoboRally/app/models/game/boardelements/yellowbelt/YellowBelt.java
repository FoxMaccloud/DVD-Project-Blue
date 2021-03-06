package inf112.RoboRally.app.models.game.boardelements.yellowbelt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.RoboRally.app.models.cards.Rotation;
import inf112.RoboRally.app.models.game.boardelements.IRegistrationPhaseElement;
import inf112.RoboRally.app.models.robot.Direction;
import inf112.RoboRally.app.models.robot.Pos;
import inf112.RoboRally.app.models.robot.Robot;

public class YellowBelt implements IRegistrationPhaseElement {

    private TiledMapTileLayer layer;
    private final Boolean ACTIVE;
    private final Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/Sound/PushForward.wav"));

    public YellowBelt(TiledMapTileLayer layer) {
        ACTIVE = layer != null;
        this.layer = layer;
    }


    @Override
    public void effectRobotInRegistrationPhase(Robot robot) {
        Pos pos = robot.pos();
        int x = pos.getX(), y = pos.getY();

        if (checkYellowBeltType(x, y, YellowBeltType.PUSH_DOWN) && checkYellowBeltType(x, y-1, YellowBeltType.YELLOW_12)) {
            robot.rotate(Rotation.RIGHT);
            robot.moveOneStepInDirection(Direction.DOWN);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.YELLOW_12)) {
            robot.moveOneStepInDirection(Direction.LEFT);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_DOWN) && checkYellowBeltType(x, y-1, YellowBeltType.LEFT_TURN_INTO_RIGHT)) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.DOWN);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.LEFT_TURN_INTO_RIGHT)) {
            robot.moveOneStepInDirection(Direction.RIGHT);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_DOWN) && checkYellowBeltType(x, y-1, YellowBeltType.RIGHT_TURN_INTO_LEFT)) {
            robot.rotate(Rotation.RIGHT);
            robot.moveOneStepInDirection(Direction.DOWN);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.RIGHT_TURN_INTO_LEFT)) {
            robot.moveOneStepInDirection(Direction.LEFT);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_DOWN)) {
            robot.moveOneStepInDirection(Direction.DOWN);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.LEFT_TURN_INTO_LEFT)) {
            robot.moveOneStepInDirection(Direction.LEFT);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_UP) && (checkYellowBeltType(x, y+1, YellowBeltType.LEFT_TURN_INTO_LEFT))) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.UP);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_UP) && checkYellowBeltType(x, y+1, YellowBeltType.RIGHT_TURN_INTO_RIGHT)) {
            robot.rotate(Rotation.RIGHT);
            robot.moveOneStepInDirection(Direction.UP);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.RIGHT_TURN_INTO_RIGHT)) {
            robot.moveOneStepInDirection(Direction.RIGHT);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_UP) && checkYellowBeltType(x, y+1, YellowBeltType.YELLOW_12)) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.UP);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_UP)) {
            robot.moveOneStepInDirection(Direction.UP);
            sound.play();

        }
        else if (checkYellowBeltType(x-1, y, YellowBeltType.LEFT_TURN_INTO_DOWN) && checkYellowBeltType(x, y, YellowBeltType.PUSH_LEFT)) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.LEFT);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.LEFT_TURN_INTO_DOWN)) {
            robot.moveOneStepInDirection(Direction.DOWN);
        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_LEFT)) {
            robot.moveOneStepInDirection(Direction.LEFT);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.PUSH_RIGHT)) {
            robot.moveOneStepInDirection(Direction.RIGHT);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_LEFT_TO_LEFT)) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.LEFT);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_LEFT_TO_RIGHT)) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.RIGHT);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_LEFT_TO_UP)) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.UP);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_LEFT_TO_DOWN)) {
            robot.rotate(Rotation.LEFT);
            robot.moveOneStepInDirection(Direction.DOWN);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_RIGHT_TO_RIGHT)) {
            robot.rotate(Rotation.RIGHT);
            robot.moveOneStepInDirection(Direction.RIGHT);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_RIGHT_TO_LEFT)) {
            robot.rotate(Rotation.RIGHT);
            robot.moveOneStepInDirection(Direction.LEFT);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_RIGHT_TO_UP)) {
            robot.rotate(Rotation.RIGHT);
            robot.moveOneStepInDirection(Direction.UP);
            sound.play();

        }
        else if (checkYellowBeltType(x, y, YellowBeltType.ROTATE_RIGHT_TO_DOWN)) {
            robot.rotate(Rotation.RIGHT);
            robot.moveOneStepInDirection(Direction.DOWN);
            sound.play();
        }
    }

    @Override
    public boolean inEffectForSlotNumber(int slotNumber) {
        return ACTIVE;
    }

    private boolean checkYellowBeltType(int x, int y, YellowBeltType yellowBelt) {
        return ACTIVE && layer.getCell(x, y) != null && layer.getCell(x, y).getTile().getId() == yellowBelt.getTileId();
    }
}
