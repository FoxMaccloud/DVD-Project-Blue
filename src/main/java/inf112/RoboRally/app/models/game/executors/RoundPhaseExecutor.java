package inf112.RoboRally.app.models.game.executors;

import inf112.RoboRally.app.models.cards.ICard;
import inf112.RoboRally.app.models.cards.SortCardByPriority;
import inf112.RoboRally.app.models.game.Player;
import inf112.RoboRally.app.models.game.Timer;
import inf112.RoboRally.app.models.game.boardelements.IRegistrationPhaseElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundPhaseExecutor {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private AtomicInteger slotNumber = new AtomicInteger(0);
    private final int NUMBER_OF_SLOTS = 5;
    private Player[] players;
    private IRegistrationPhaseElement[] registrationPhaseEffects;
    private Timer timer; // access to timer in game for reset when round execution is complete

    public RoundPhaseExecutor(Player[] players, IRegistrationPhaseElement[] boardEffects, Timer timer) {
        this.players = players;
        this.registrationPhaseEffects = boardEffects;
        this.timer = timer;
        System.out.println("getting here");
    }




    public void roundPhaseExecutor() {
        Runnable collectCards = () -> {

            CountDownLatch cardExecutionLatch = new CountDownLatch(1);

            ArrayList<ICard> cards = collectCardsFromSlotNumber(slotNumber.get());

            if (cards == null) {
                timer.reset();
                scheduler.shutdown(); // no more cards in slots
            }

            sortCardsByPriority(cards);

            CardMoveRobotExecutor cardExecutor = new CardMoveRobotExecutor(cards, cardExecutionLatch);
            cardExecutor.executeCards();
            System.out.println("-------------------- " + (slotNumber.get() + 1) + " slot performing -----------------------");
            try {
                cardExecutionLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CountDownLatch boardElementLatch = new CountDownLatch(1);

            if (registrationPhaseEffects != null) {
                BoardElementRegistrationExecutor boardElementExecutor = new BoardElementRegistrationExecutor(players, slotNumber.get(), registrationPhaseEffects, boardElementLatch);
                boardElementExecutor.executeBoardElements();
                try {
                    boardElementLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            if (slotNumber.incrementAndGet() == NUMBER_OF_SLOTS) {
                turnRobotsThatWerePoweredDownOnForNextRound();
                if (timer != null) timer.reset();
                scheduler.shutdown();
            }
        };
        scheduler.scheduleAtFixedRate(collectCards, 500, 5000, TimeUnit.MILLISECONDS);
    }


    public void sortCardsByPriority(ArrayList<ICard> allCardsFromSlots) {
        Collections.sort(allCardsFromSlots, new SortCardByPriority());
    }


    public ArrayList<ICard> collectCardsFromSlotNumber(int slotNumber) {
        ArrayList<ICard> cards = new ArrayList<>();
        for (Player player: players) {

            ICard[] slottedCards = player.getCardSlots();
            if (slottedCards[slotNumber] != null) {

                cards.add(slottedCards[slotNumber]);
                slottedCards[slotNumber] = null;

            }
        }
        if (cards.isEmpty()) return null; // no more card choices left to execute
        return cards;
    }


    public void turnRobotsThatWerePoweredDownOnForNextRound() {
        for (Player player: players) {
            if (player.robot().isPoweredDown())
                player.robot().changePowerDown(false, true);
        }
    }



}
