package io.clientportal.blackjack.setup;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupService {

    public List<StateOfHand> currentHand = new ArrayList<StateOfHand>();

    public StateOfHand getFirsthand(){
        StateOfHand currentHand = new StateOfHand(1, "Billy");
        return currentHand;
    }

    public List<StateOfHand> getCurrentHand(){
        return currentHand;
    }

//    public List<StateOfHand> setFirstHand(int numPlayers){
//
//        Cards cards = new Cards();
//        String oneDeck[] = cards.getStandardCardDeck();
//
//        String players[] = {"John", "Bill", "Dave", "Kevin"};
//        int playersBets[] = {50, 20, 100, 35};
//
//
//
//        List<StateOfHand> currentHand = new ArrayList<StateOfHand>();
//
//        String handPlayed = oneDeck[(int)(Math.random()*52)];
//        Dealer dealer = new Dealer(0);
//        dealer.hand.setCards(handPlayed);
//        handPlayed = oneDeck[(int)(Math.random()*52)];
//        dealer.hand.setCards(handPlayed);
//        currentHand.add(dealer);
//
//
//        for (int i = 0; i < players.length; i++) {
//
//            StateOfHand newPlayer = new StateOfHand((i + 1), players[i]);
//
//            newPlayer.hand.setCards(handPlayed);
//            handPlayed = oneDeck[(int)(Math.random()*52)];
//            newPlayer.hand.setCards(handPlayed);
//            newPlayer.hand.setBetPlaced(playersBets[i]);
//            currentHand.add(newPlayer);
//        }
//        return currentHand;
//
//    }

    public List<StateOfHand> setFirstHandPost(List<Player> players){

        Cards cards = new Cards();
        String oneDeck[] = cards.getStandardCardDeck();

        String handPlayed = oneDeck[(int)(Math.random()*52)];
        Dealer dealer = new Dealer(0);
        dealer.hand.setCards(handPlayed);
        handPlayed = oneDeck[(int)(Math.random()*52)];
        dealer.hand.setCards(handPlayed);

        currentHand.add(dealer);

        for (Player player:players) {
            StateOfHand personToSet = new StateOfHand();
            //setting name, id, first bet
            personToSet.setName(player.getName());
            personToSet.setId(player.getId());
            personToSet.hand.setBetPlaced(player.getFirstBetAmount());
            //giving two cards
            handPlayed = oneDeck[(int)(Math.random()*52)];
            personToSet.hand.setCards(handPlayed);
            handPlayed = oneDeck[(int)(Math.random()*52)];
            personToSet.hand.setCards(handPlayed);
            //adding to current hand
            currentHand.add(personToSet);

        }

        return currentHand;

    }

}
