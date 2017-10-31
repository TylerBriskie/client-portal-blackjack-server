package io.clientportal.blackjack.setup;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupService {

    public List<StateOfHand> currentHand = new ArrayList<StateOfHand>();
    public CalculateHand calculateHand = new CalculateHand();
    private int bust = 21;
    private int dealerHand;

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
            //make a func for getOneCard and getTwoCards? Pass the Player's Hand
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

    public List<StateOfHand> handComplete(){
        //need to parse this into Dealer's hand "dealerHand" and Players Hands
        //still need to remove the dealer or put into a new List<StateOfHand> with
        //only the players
        for (StateOfHand whoIsDealer:currentHand){
            if (whoIsDealer.getName().equals("Dealer")){
                dealerHand = calculateHand.getHandTotal(whoIsDealer.hand.getCards());
                //find a way to remove the dealer from this object would be perfect
            }
        }

        for (StateOfHand player:currentHand){

            int currentHandValue = calculateHand.getHandTotal(player.hand.getCards());

            if(currentHandValue > bust){
                System.out.println("YOU LOSE");
            } else if (currentHandValue > (dealerHand > bust ? 0 : dealerHand)){
                System.out.println("winner winner");
            } else if (currentHandValue == dealerHand){
                System.out.println("this feels like a push");
            } else {
                System.out.println("you didn't bust, but you still suck");
            }
        }
        return currentHand;
    }

}
