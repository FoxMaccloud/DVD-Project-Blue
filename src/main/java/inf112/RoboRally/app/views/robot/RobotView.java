package inf112.RoboRally.app.views.robot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.RoboRally.app.models.cards.Rotation;
import inf112.RoboRally.app.models.robot.Pos;

public class RobotView extends Sprite {

    private int flags = 0;
    private boolean isDeadThisRound = false;
    private boolean isPoweredDown = false;
    private boolean hasWon = false;

    private final float TILE_HEIGHT_PX = 96.053575f; // 256 /  2.665179302
    private final float TILE_WIDTH_PX = 98.46153846153846f; // 256 / 2.6 (tile px size / scaling down property)

    private float targetX;
    private float targetY;


    public RobotView(Sprite sprite, Pos startPos) {
        super(sprite);
        setRotation(270.0f);
        setStartPosition(startPos);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    private void update(float delta) {
        int spriteMovementSpeed;
        if (isDeadThisRound) spriteMovementSpeed = 150; // move slower when getting reset
        else                 spriteMovementSpeed = 300;

        if (getX() < targetX) {
            setX(getX() + spriteMovementSpeed * delta);
        }
        if (getX() > targetX) {
            setX(getX() - spriteMovementSpeed * delta);
        }
        if (getY() < targetY) {
            setY(getY() + spriteMovementSpeed * delta);
        }
        if (getY() > targetY) {
            setY(getY() - spriteMovementSpeed * delta);
        }
    }

    public void updateX(int x) {
        targetX = ( x * TILE_WIDTH_PX);
    }

    public void updateY(int y) {
        targetY = ( y * TILE_HEIGHT_PX);
    }

    public void updateDirection(Rotation rotation) {
        switch (rotation) {
            case LEFT:
                rotate90(false);
                return;
            case RIGHT:
                rotate90(true);
                return;
            case UTURN:
                rotate90(true);
                rotate90(true);
                return;
            default:
                throw new IllegalArgumentException("RobotView is told to rotate '"+rotation+"', which is not supported");
        }

    }



    public void setStartPosition(Pos startPos) {
        setX( (startPos.getX() * TILE_WIDTH_PX) );
        setY( (startPos.getY() * TILE_HEIGHT_PX) );
        targetX = getX();
        targetY = getY();
    }


    public void capturedFlag() {
        flags++;
    }

    public void setDeadThisRound(boolean deadThisRound) {
        this.isDeadThisRound = deadThisRound;
    }

    public boolean isDeadThisRound() {
        return isDeadThisRound;
    }


    public int flagCaptures() {
        return flags;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public void setToWinSprite() {
        hasWon = true;
    }

    public boolean isPoweredDown() {
        return isPoweredDown;
    }

    public void changePoweredDown(boolean isPoweredDown) {
        this.isPoweredDown = isPoweredDown;
    }


}
