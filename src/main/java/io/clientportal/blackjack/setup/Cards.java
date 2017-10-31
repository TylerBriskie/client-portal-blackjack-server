package io.clientportal.blackjack.setup;

import java.util.ArrayList;
import java.util.List;

public class Cards {


    private String standardCardDeck[] = {
            "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "0C", "JC", "QC", "KC",
            "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "0D", "JD", "QD", "KD",
            "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "0H", "JH", "QH", "KH",
            "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "0S", "JS", "QS", "KS"
    };

    public String[] getStandardCardDeck() {
        return standardCardDeck;
    }

    public void setStandardCardDeck(String[] standardCardDeck) {
        this.standardCardDeck = standardCardDeck;
    }

    private List<String> customSizeDeck = new ArrayList<String>();

    public Cards(){}

//    public Cards(int numDecks){
//        super();
//        Cards shoe = new Cards;
//        for (int i = 0; i < numDecks; i++){
//            ArrayUtils
//            customSizeDeck.add(standardCardDeck);
//        }

}

