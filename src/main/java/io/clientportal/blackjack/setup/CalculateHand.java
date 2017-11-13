package io.clientportal.blackjack.setup;

//import antlr.StringUtils;
//import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class CalculateHand {

    public CalculateHand(){}

    public int getHandTotal(List<String> hand, String name){
        int total=0;
        int aceCount=0;
        for (String card:hand){
            String currentCard = card.substring(0, 1);
            if (    currentCard.equals("0") ||
                    currentCard.equals("J") ||
                    currentCard.equals("Q") ||
                    currentCard.equals("K") ){ currentCard = "10"; }
            if (currentCard.equals("A")){
                currentCard = "11";
                aceCount++;
            }
            total += Integer.parseInt(currentCard);

            if (total > 21 && aceCount > 0){
                aceCount--;
                total -= 10;
            }
        }
        System.out.println("this is total for "+name+ " : "+total);
        return total;
    }
}
