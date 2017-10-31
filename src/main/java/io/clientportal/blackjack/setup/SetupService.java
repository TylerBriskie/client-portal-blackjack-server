package io.clientportal.blackjack.setup;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupService {

    public List<StateOfHand> currentHand = new ArrayList<StateOfHand>();

    public void resetHand(){
        currentHand.clear();
    }

    public List<StateOfHand> getCurrentHand(){
        return currentHand;
    }

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

    public List<StateOfHand> hitOneCard(int id){
        StateOfHand currentPlayer = new StateOfHand();
        for (StateOfHand player:currentHand){
            if(player.getId() == id){
                Cards cards = new Cards();
                String oneDeck[] = cards.getStandardCardDeck();
                String oneCard = oneDeck[(int)(Math.random()*52)];
                player.hand.setCards(oneCard);
                return currentHand;
            }
        }
        return currentHand;
    }

}
