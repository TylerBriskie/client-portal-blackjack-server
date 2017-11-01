package io.clientportal.blackjack.setup;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupService {

    public List<StateOfHand> currentHand = new ArrayList<StateOfHand>();
    public CalculateHand calculateHand = new CalculateHand();

    public Cards cards = new Cards();
    public List<String> oneDeck = cards.getStandardCardDeck();
//    public List<String> newDeck = cards.getStandardCardDeck();

    int bust = 21;
    private int dealerHandValue;

    public void resetHand(){
        currentHand.clear();
        oneDeck.clear();
        oneDeck.addAll(cards.getStandardCardDeck());
    }

    public List<StateOfHand> getCurrentHand(){
        return currentHand;
    }

    public List<StateOfHand> setFirstHandPost(List<Player> players){

        //checking to see if they are still playing with the same players
        if (currentHand.size() != 0) {
            return nextHand(players);
        }

        for (Player player:players) {
            StateOfHand personToSet = new StateOfHand();
            //setting name, id, first bet
            personToSet.setName(player.getName());
            personToSet.setId(player.getId());
            personToSet.hand.setBetPlaced(player.getFirstBetAmount());
            //giving two cards
            personToSet.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));
            personToSet.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));
            //adding to current hand
            currentHand.add(personToSet);
        }

        Dealer dealer = new Dealer(0);
        dealer.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));
        dealer.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));

        currentHand.add(dealer);

        return currentHand;
    }

    public List<StateOfHand> nextHand(List<Player> players){


        //adding cards
        for (StateOfHand player:currentHand){
            player.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));
            player.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));
        }

        //setting new bets
        for (int i = 0; i < players.size(); i++){
            currentHand.get(i).hand.setBetPlaced(players.get(i).getFirstBetAmount());
        }

        Dealer dealer = new Dealer(0);
        dealer.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));
        dealer.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));

        currentHand.add(dealer);

        return currentHand;
    }

    public List<StateOfHand> hitOneCard(int id){
        for (StateOfHand player:currentHand){
            if(player.getId() == id){
                Cards cards = new Cards();
                player.hand.setOneCard(oneDeck, cards.returnOneRandomCard(oneDeck));
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

        currentHand.remove(currentHand.size()-1);

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
