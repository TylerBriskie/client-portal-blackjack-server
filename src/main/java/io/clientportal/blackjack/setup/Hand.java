package io.clientportal.blackjack.setup;
//package io.clientportal.blackjack.setup.SetupService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import class SetupService;

public class Hand {

    private List<String> cards = new ArrayList<String>();
    private int betPlaced;
    private SplitHand splitHand = null;

    public Hand(){}

    public List<String> getCards() {
        return cards;
    }

    public void setOneCard(List<String> deck, String card) {
        this.cards.add(card);
        for (int i = 0; i < deck.size(); i++ ){
            if (deck.get(i).equals(card)){
                deck.remove(i);
                break;
            }
        }

    }

    public void setTwoCards(List<String> deck, String card1, String card2) {
        setOneCard(deck, card1);
        setOneCard(deck, card2);
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
