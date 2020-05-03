package inf112.RoboRally.app.views.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.RoboRally.app.GameLauncher;
import inf112.RoboRally.app.models.game.Player;
import inf112.RoboRally.app.views.card.GameScreenCards;
import inf112.RoboRally.app.views.menus.Button;
import inf112.RoboRally.app.views.opponents.OpponentHUDTable;

public class PlayerUI extends InputAdapter {

    private Player player; // the player who's thus UI belongs to

    private Table readyButtonTable;
    private TextButton readyButton;

    private Table generateCardsTable;
    private TextButton generateCardsButton;

    private OpponentHUDTable opponentHUDTable;

    private Stage stage;
    private Viewport viewport;
    private PlayerHUD playerHUD;
    private GameScreenCards gameScreenCards;

    public PlayerUI (Player player) {
        this.player = player;
        viewport = new FitViewport(GameLauncher.GAME_WIDTH, GameLauncher.GAME_HEIGHT);
        stage = new Stage(viewport);

        readyButtonTable = new Table();
        generateCardsTable = new Table();

        playerHUD = new PlayerHUD(player);
        opponentHUDTable = new OpponentHUDTable(player, -1); // -1 means no cards are facing up

        stage.addActor(opponentHUDTable.getOpponentTable());
        stage.addActor(playerHUD.getPlayerHudDashBoardTable());
        stage.addActor(playerHUD.getDamageTokensTable());
        stage.addActor(playerHUD.getPowerDownTable());
        stage.addActor(playerHUD.getLifeTokensTable());
        stage.addActor(readyButtonTable());
        stage.addActor(generateCardsTable());

        gameScreenCards = new GameScreenCards(player);
        for (int slotNumber = 0; slotNumber < player.numberOfCardSlots(); slotNumber++)
            stage.addActor(gameScreenCards.getCardSlotTable(slotNumber));

        stage.addActor(gameScreenCards.getReceivedCardsTable());
        Gdx.input.setInputProcessor(gameScreenCards);
    }

    public Stage getStage () {
        return stage;
    }

    public void dispose() {
        stage.dispose();
    }


    public Table readyButtonTable() {

        readyButtonTable.pad(0, 3765, 430, 0);
        readyButton = new Button().createTextButton("READY");

        readyButtonTable.row().padTop(20);
        readyButtonTable.add(readyButton);

        readyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("--------------------------------------------------------------------");
//                System.out.println("FROM PlayerUI: ready button pressed! Player is ready for some action!");
                player.setCardSlotsFromUserInput(gameScreenCards.getCardChoices());
                player.getGame().executeCardsChoices();
                gameScreenCards.clearCards();
            }
        });

        return readyButtonTable;
    }

    public Table generateCardsTable() {

        generateCardsTable.pad(0, 3765, 180, 0);
        generateCardsButton = new Button().createTextButton("CARDS");

        generateCardsTable.row().padTop(20);
        generateCardsTable.add(generateCardsButton);

        generateCardsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("--------------------------------------------------------------------");
//                System.out.println("FROM PlayerUI: generated new cards button pressed. Passing this information along.");
                gameScreenCards.clearCards();
                player.getGame().newRound();
                gameScreenCards = new GameScreenCards(player);
                for (int slotNumber = 0; slotNumber < player.numberOfCardSlots(); slotNumber++)
                    stage.addActor(gameScreenCards.getCardSlotTable(slotNumber));

                stage.addActor(gameScreenCards.getReceivedCardsTable());
            }
        });

        return generateCardsTable;
    }


    public void updateOpponentCardSlots(int slotNumberFacingUp) {
        synchronized (this) {
            System.out.println("getting here, Player"+player.getPlayerNumber());
            System.out.println("slotNumberFacingUp : " + slotNumberFacingUp);
            opponentHUDTable = new OpponentHUDTable(player, slotNumberFacingUp);
            stage.addActor(opponentHUDTable.getOpponentTable());
        }
//        System.out.println("getting here, Player"+player.getPlayerNumber());
//        System.out.println("slotNumberFacingUp : " + slotNumberFacingUp);
//        opponentHUDTable = new OpponentHUDTable(player, gameCardController, slotNumberFacingUp);
//        stage.addActor(opponentHUDTable.getOpponentTable());
    }


}
