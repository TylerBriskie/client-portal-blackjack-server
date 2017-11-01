package io.clientportal.blackjack.setup;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<String> cards = new ArrayList<String>();
    private int betPlaced;
    private SplitHand splitHand = null;
//    private SplitHand splitHand = new SplitHand();

    public Hand(){}

    public List<String> getCards() {
        return cards;
    }

    public void setCards(String card) {
        this.cards.add(card);
    }

    public void clearCards(){this.cards = new ArrayList<String>();}

    public int getBetPlaced() {
        return betPlaced;
    }

    public void setBetPlaced(int betPlaced) {
        this.betPlaced = betPlaced;
    }

    public Object getSplitHand() {
        return splitHand;
    }

    public void setSplitHand(SplitHand splitHand) {
        this.splitHand = splitHand;
    }

}
