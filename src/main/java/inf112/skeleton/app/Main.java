package inf112.skeleton.app;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Robo Rally");
        cfg.setWindowedMode(576, 576);
        cfg.setResizable(false);
        new Lwjgl3Application(new RoboRallyWorld(), cfg);
        // TODO Create explosions
    }
}

