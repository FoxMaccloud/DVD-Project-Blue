package inf112.RoboRally.app.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.RoboRally.app.gameScreen;

public class classicBoard extends InputAdapter implements Screen {

    private gameScreen gameScreen;
    private Stage stage;

    // map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private TiledMapTileLayer groundLayer, cogLayer, yellowArrowsLayer,
            blueArrowsLayer, holeLayer, flagLayer, fixLayer, wallLayer, pusherLayer;

    // camera variables
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    // player variables
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer.Cell playerCell, playerCellDead, playerCellWon;
    private Vector2 playerVector;
    private Texture playerTexture;
    private TextureRegion[] playerTextureRegion;

    public classicBoard (gameScreen game) {
        this.gameScreen = game;

        //User input
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // map code
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("GameBoard/GameBoard1.tmx");
        groundLayer = (TiledMapTileLayer) map.getLayers().get("ground");
        cogLayer = (TiledMapTileLayer) map.getLayers().get("cog");
        wallLayer = (TiledMapTileLayer) map.getLayers().get("walls");
        yellowArrowsLayer = (TiledMapTileLayer) map.getLayers().get("yellowArrows");
        blueArrowsLayer = (TiledMapTileLayer) map.getLayers().get("blueArrows");
        fixLayer = (TiledMapTileLayer) map.getLayers().get("fix");
        pusherLayer = (TiledMapTileLayer) map.getLayers().get("pusher");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("hole");

        // player code
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        playerTexture = new Texture("Tiles/ExampleRobots.png");
        playerTextureRegion = new TextureRegion[3];
        playerTextureRegion[0] = new TextureRegion(playerTexture, 0, 0, 48, 48);
        playerTextureRegion[1] = new TextureRegion(playerTexture, 48, 0, 48, 48);
        playerTextureRegion[2] = new TextureRegion(playerTexture, 96, 0, 48, 48);
        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureRegion[0]));
        playerCellDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureRegion[1]));
        playerCellWon = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextureRegion[2]));
        playerVector = new Vector2();
        playerVector.set(0, 0);

        // mapRenderer anc camera code
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 576, 576);
        camera.position.x = 288;
        camera.position.y = 288;
        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();
        playerLayer.setCell((int) playerVector.x, (int) playerVector.y, playerCell);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }

}
