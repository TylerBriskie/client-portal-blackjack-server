package io.clientportal.blackjack.setup;

//import antlr.StringUtils;
//import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class CalculateHand {

    public CalculateHand(){}

    public int getHandTotal(List<String> hand){
        int total=0;
        for (String card:hand){
            String currentCard = card.substring(0, 1);
            if (    currentCard.equals("0") ||
                    currentCard.equals("J") ||
                    currentCard.equals("Q") ||
                    currentCard.equals("K") ){
                currentCard = "10";

            } else if (currentCard.equals("A")){
                currentCard = "11";
            }
            total += Integer.parseInt(currentCard);
        }
        return total;
    }
}
