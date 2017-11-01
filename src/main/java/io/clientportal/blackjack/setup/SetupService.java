package io.clientportal.blackjack.setup;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupService {

    public List<StateOfHand> currentHand = new ArrayList<StateOfHand>();
    public CalculateHand calculateHand = new CalculateHand();

    public Cards cards = new Cards();
    public List<String> currentDeck = cards.getStandardCardDeck();

    int bust = 21;
    private int dealerHandValue;

    public void resetHand(){
        currentHand.clear();
        resetCardDeck();
    }

    public List<StateOfHand> getCurrentHand(){
        return currentHand;
    }

    public List<StateOfHand> setFirstHandPost(List<Player> players){

        //checking to see if they are still playing with the same players
        if (currentHand.size() != 0) {
            if (currentDeck.size() < (52*0.2)){
                resetCardDeck();
            }
            return nextHand(players);
        }

        for (Player player:players) {
            StateOfHand personToSet = new StateOfHand();
            //setting name, id, first bet
            personToSet.setName(player.getName());
            personToSet.setId(player.getId());
            personToSet.hand.setBetPlaced(player.getFirstBetAmount());
            //giving two cards
            personToSet.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));
            personToSet.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));
            //adding to current hand
            currentHand.add(personToSet);
        }

        Dealer dealer = new Dealer(0);
        dealer.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));
        dealer.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));

        currentHand.add(dealer);

        return currentHand;
    }

    public List<StateOfHand> nextHand(List<Player> players){


        //adding cards
        for (StateOfHand player:currentHand){
            player.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));
            player.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));
        }

        //setting new bets
        for (int i = 0; i < players.size(); i++){
            currentHand.get(i).hand.setBetPlaced(players.get(i).getFirstBetAmount());
        }

        Dealer dealer = new Dealer(0);
        dealer.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));
        dealer.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));

        currentHand.add(dealer);

        return currentHand;
    }

    public List<StateOfHand> hitOneCard(int id){
        for (StateOfHand player:currentHand){
            if(player.getId() == id){
                Cards cards = new Cards();
                player.hand.setOneCard(currentDeck, cards.returnOneRandomCard(currentDeck));
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

    public void resetCardDeck(){
        currentDeck.clear();
        currentDeck.addAll(cards.getStandardCardDeck());
    }

}
