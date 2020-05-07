package inf112.RoboRally.app.models.game;

import inf112.RoboRally.app.models.cards.CardFactory;
import inf112.RoboRally.app.models.cards.ICard;
import inf112.RoboRally.app.models.game.boardelements.IElement;
import inf112.RoboRally.app.models.game.executors.CollectCardFromSlotExecutor;

/*
Next delivery
 */
public class Round {

    private CardFactory cardFactory = new CardFactory();
    private Player[] players;
    private Player humanPlayer;
    private final int CARD_SLOT_AMOUNT;
    private IElement[] boardElements;
    private int roundNumber = 0; // only used for system.out

    public Round(Game game) {
        this.players = game.players();
        this.humanPlayer = game.getHumanPlayer();
        this.boardElements = game.getBoardElements().boardEffects();
        CARD_SLOT_AMOUNT = humanPlayer.numberOfCardSlots();
    }

    public void dealCardsAndBotsChooseCards() {
        dealCards();
        botPlayersChooseCards();
    }

    private void botPlayersChooseCards() {
        for (Player player: players) {
            if (player.isBotPlayer())
                player.botPlayerChooseCardsForCardSlots();
        }
    }

    public void dealCards () {
        for (Player player : players) {
            for (int i = 0; i < player.numberOfReceivedCards(); i++) {
                ICard card = cardFactory.randomCard();
                player.receiveCard(i, card);
            }
        }

    }



    public void executeRound(Timer timer) {

        System.out.println("----------------------------------------- ROUND "+(++roundNumber)+" ------------------------------------------" );

        powerDownRobots();                      // power down robots that have announced powerdown
        updateRobotsThatDiedThePreviousRound(); // making all robots that died the previous round alive again
        checkForWinner();
        CollectCardFromSlotExecutor cardChoiceExecutor = new CollectCardFromSlotExecutor(players, boardElements, timer);
        cardChoiceExecutor.CardChoiceRoundExecutor();

    }

    private void checkForWinner() {
        int playersAlive = 0;
        for (Player player: players) {
            if (player.robot().livesLeft() > 0) playersAlive++;
            if (player.robot().isWinner()) return;
        }
        if (playersAlive == 1) {
            for (Player player: players) {
                if (player.robot().livesLeft() > 0) {
                    player.robot().getRobotViewController().hasWon();
                    return;
                }
            }
        }
    }


    private void powerDownRobots() {
        for (Player player: players) {
            if (player.robot().isPoweredDown()) {
                player.clearCardSlots();
                player.robot().reset(false);
            }
        }

    }

    private void updateRobotsThatDiedThePreviousRound() {
        for (Player player: players) {
            if (player.robot().livesLeft() > 0)
                player.robot().setAlive();
        }
    }



}



