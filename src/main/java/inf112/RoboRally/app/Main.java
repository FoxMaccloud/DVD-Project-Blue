package inf112.RoboRally.app;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Robo Rally";
        cfg.width = 576;
        cfg.height = 576;
        cfg.resizable = false;
        new LwjglApplication(new gameScreen(), cfg);
    }
}

