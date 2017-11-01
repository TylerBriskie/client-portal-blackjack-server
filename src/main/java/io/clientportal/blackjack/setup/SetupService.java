package io.clientportal.blackjack.setup;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupService {

    public List<StateOfHand> currentHand = new ArrayList<StateOfHand>();
    public CalculateHand calculateHand = new CalculateHand();
    private int bust = 21;
    private int dealerHandValue;

    public void resetHand(){
        currentHand.clear();
    }

    public List<StateOfHand> getCurrentHand(){
        return currentHand;
    }

    public List<StateOfHand> setFirstHandPost(List<Player> players){

        if (currentHand.size() != 0) {
            System.out.println("I'm NOT empty");
            return nextHand(players);
        }

        Cards cards = new Cards();
        String oneDeck[] = cards.getStandardCardDeck();

        String handPlayed = oneDeck[(int)(Math.random()*52)];
        Dealer dealer = new Dealer(0);
        dealer.hand.setOneCard(handPlayed);
        handPlayed = oneDeck[(int)(Math.random()*52)];
        dealer.hand.setOneCard(handPlayed);

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
            personToSet.hand.setOneCard(handPlayed);
            handPlayed = oneDeck[(int)(Math.random()*52)];
            personToSet.hand.setOneCard(handPlayed);
            //adding to current hand
            currentHand.add(personToSet);
        }
        return currentHand;
    }

    public List<StateOfHand> nextHand(List<Player> players){

        Cards cards = new Cards();
        String oneDeck[] = cards.getStandardCardDeck();
        String handPlayed = oneDeck[(int)(Math.random()*52)];

        for (StateOfHand player:currentHand){
            handPlayed = oneDeck[(int)(Math.random()*52)];
            player.hand.setOneCard(handPlayed);
            handPlayed = oneDeck[(int)(Math.random()*52)];
            player.hand.setOneCard(handPlayed);
        }

        Dealer dealer = new Dealer(0);
        dealer.hand.setOneCard(handPlayed);
        handPlayed = oneDeck[(int)(Math.random()*52)];
        dealer.hand.setOneCard(handPlayed);

        currentHand.add(0, dealer);

        return currentHand;
    }

    public List<StateOfHand> hitOneCard(int id){
        for (StateOfHand player:currentHand){
            if(player.getId() == id){
                Cards cards = new Cards();
                String oneDeck[] = cards.getStandardCardDeck();
                String oneCard = oneDeck[(int)(Math.random()*52)];
                player.hand.setOneCard(oneCard);
                return currentHand;
            }
        }
        return currentHand;
    }

    public List<StateOfHand> handComplete(){

        for (StateOfHand whoIsDealer:currentHand){
            if (whoIsDealer.getName().equals("Dealer")){
                dealerHandValue = calculateHand.getHandTotal(whoIsDealer.hand.getCards());
            }
        }

        currentHand.remove(0);

        for (StateOfHand player:currentHand){

            System.out.println(player.getName());
            int currentHandValue = calculateHand.getHandTotal(player.hand.getCards());

            if(currentHandValue > bust){
                player.setBankRoll(player.getBankRoll() - player.hand.getBetPlaced());
                System.out.println("YOU LOSE");
            } else if (currentHandValue > (dealerHandValue > bust ? 0 : dealerHandValue)){
                player.setBankRoll(player.getBankRoll() + player.hand.getBetPlaced());
                System.out.println("Winner Winner Chicken Dinner");
            } else if (currentHandValue == dealerHandValue){
                System.out.println("this feels like a push");
            } else {
                player.setBankRoll(player.getBankRoll() - player.hand.getBetPlaced());
                System.out.println("you didn't bust, but you still lose");
            }
            player.hand.setBetPlaced(0);
            player.hand.clearCards();
        }
        return currentHand;
    }

}
