package inf112.RoboRally.app.models.game.boardelements.flag;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.RoboRally.app.models.game.boardelements.IElement;
import inf112.RoboRally.app.models.robot.Pos;
import inf112.RoboRally.app.models.robot.Robot;

public class Flag implements IElement {

    private TiledMapTileLayer layer;
    private final boolean ACTIVE;

    public Flag(TiledMapTileLayer layer) {
        ACTIVE = layer != null;
        this.layer = layer;
    }

    @Override
    public int effectRobotSteps(int steps) {
        return 0;
    }

    @Override
    public void effectRobotAfterCardExec(Robot robot) {
        Pos pos = robot.position();
        int x = pos.getX(), y = pos.getY();
        if (checkFlagType(x, y, FlagType.FIRST_FLAG))
            robot.touchFlag(FlagType.FIRST_FLAG, x, y);
        else if (checkFlagType(x, y, FlagType.SECOND_FLAG))
            robot.touchFlag(FlagType.SECOND_FLAG, x, y);
        else if (checkFlagType(x, y, FlagType.THIRD_FLAG)) {
            robot.touchFlag(FlagType.THIRD_FLAG, x, y);
        }
    }

    @Override
    public boolean inEffectForSlotNumber(int slotNumber) {
        return ACTIVE;
    }

    private boolean checkFlagType(int x, int y, FlagType flagType) {
        return layer.getCell(x, y) != null && layer.getCell(x, y).getTile().getId() == flagType.getTileId();
    }
}