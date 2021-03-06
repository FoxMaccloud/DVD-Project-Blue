package inf112.RoboRally.app.models.robot;

public class Pos {

    private int restartX;
    private int restartY;
    private int x;
    private int y;

    public Pos(int x, int y) {
        restartX = x;
        restartY = y;
        this.x = x;
        this.y = y;
    }

    public void setNewRestartPos(int x, int y) {
        restartX = x; restartY = y;
    }

    public void restart() {
        this.x = restartX; this.y = restartY;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
