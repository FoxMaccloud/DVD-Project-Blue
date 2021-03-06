package inf112.RoboRally.app.models.game.boardelements.cog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.RoboRally.app.models.cards.Rotation;
import inf112.RoboRally.app.models.game.boardelements.IRegistrationPhaseElement;
import inf112.RoboRally.app.models.robot.Pos;
import inf112.RoboRally.app.models.robot.Robot;

public class Cog implements IRegistrationPhaseElement {

    private TiledMapTileLayer layer;
    private final boolean ACTIVE;
    private final Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/Sound/Rotate-Cog.wav"));

    public Cog(TiledMapTileLayer layer) {
        ACTIVE = layer != null;
        this.layer = layer;
    }

    @Override
    public void effectRobotInRegistrationPhase(Robot robot) {
        Pos pos = robot.pos();
        int x = pos.getX(), y = pos.getY();
        if (checkCogType(x, y, CogType.ROTATE_RIGHT)) {
            robot.rotate(Rotation.RIGHT);
            sound.play();
        }
        else if (checkCogType(x, y, CogType.ROTATE_LEFT)) {
            robot.rotate(Rotation.LEFT);
            sound.play();
        }
    }

    @Override
    public boolean inEffectForSlotNumber(int slotNumber) {
        return ACTIVE;
    }

    private boolean checkCogType(int x, int y, CogType cogType) {
        return layer.getCell(x, y) != null && layer.getCell(x, y).getTile().getId() == cogType.getTileId();
    }
}
