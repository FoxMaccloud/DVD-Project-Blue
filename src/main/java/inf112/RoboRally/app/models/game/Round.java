package inf112.RoboRally.app.models.game;

import inf112.RoboRally.app.models.cards.CardFactory;
import inf112.RoboRally.app.models.cards.ICard;
import inf112.RoboRally.app.models.game.boardelements.IElement;
import inf112.RoboRally.app.models.game.executors.CollectCardFromSlotExecutor;

import java.util.ArrayList;

/*
Next delivery
 */
public class Round {

    private CardFactory cardFactory = new CardFactory();
    private Player[] players;
    private Player humanPlayer;
    private final int CARD_SLOT_AMOUNT;
    private IElement[] boardElements;

    public Round(Game game) {
        this.players = game.players();
        this.humanPlayer = game.getHumanPlayer();
        this.boardElements = game.getBoardElements().boardEffects();
        CARD_SLOT_AMOUNT = humanPlayer.numberOfCardSlots();
    }

    public void startNewRound() {
        dealCards();
        botPlayersChooseCards();
    }

    private void botPlayersChooseCards() {
        for (Player player: players) {
            if (player.isBotPlayer())
                player.chooseCards();
        }
    }

    public void dealCards () {
//        System.out.println("FROM Round: Sure thing. Lets do it one more time.");
        removeDealtCards(); // does not do anything the first round
        for (Player player : players) {
            for (int i = 0; i < player.numberOfReceivedCards(); i++) {
                ICard card = cardFactory.randomCard();
                player.receiveCard(i, card);
            }
        }

    }

    // only executes our human players card choices for now
    public void executeHumanCardChoices() {
        ICard[] cardChoices = humanPlayer.getCardSlots();
        for (int slotNumber = 0; slotNumber < cardChoices.length; slotNumber++) {
            ICard card = cardChoices[slotNumber];
            if (card == null) break;    // means no cards are left to execute
//            System.out.println("FROM Round: I am moving the robot with a slotted card");
//            card.moveRobot(humanPlayer.robot());
            cardChoices[slotNumber] = null;   // card is executed, remove it from the slot
        }
        removeDealtCards();
    }

    private void removeDealtCards () {
        for (Player player: players) {
            ICard[] dealtCards = player.getReceivedCards();
            for (int i = 0; i < dealtCards.length; i++) {
                dealtCards[i] = null;
            }

        }

    }

    private ArrayList<ICard> collectAllCardsFromSlots() {
        ArrayList<ICard> allCards = new ArrayList<>();
        for (Player player: players) {
            ICard[] cardSlots = player.getCardSlots();
            for (int slotNumber = 0; slotNumber < CARD_SLOT_AMOUNT; slotNumber++) {
                if (cardSlots[slotNumber] != null)
                    allCards.add(cardSlots[slotNumber]);
            }

        }

        return allCards;
    }

    private ArrayList<ICard> collectCardsFromSlotNumber(int slotNumber) {
        ArrayList<ICard> cards = new ArrayList<>();
        for (Player player: players) {
            ICard[] slottedCards = player.getCardSlots();
            if (slottedCards[slotNumber] != null) {
                cards.add(slottedCards[slotNumber]);
                slottedCards[slotNumber] = null;
            }
        }
        return cards;
    }


    public void executeRound(Timer timer) {
        updateOpponentHUDCardSlots(); // flipping all cards to face up, game stats, etc.
        updateRobots(); // making all robots that died the previous round alive again

        CollectCardFromSlotExecutor cardChoiceExecutor = new CollectCardFromSlotExecutor(players, boardElements, timer);
        cardChoiceExecutor.CardChoiceRoundExecutor();


    }

    private void updateRobots() {
        for (Player player: players) {
            player.robot().setAlive();
        }
    }


    private void updateOpponentHUDCardSlots() {
        for (Player player: players)
            player.updateOpponentCardSlotsCardsFacingUp();
    }


}



