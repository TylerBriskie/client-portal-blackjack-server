package io.clientportal.blackjack.setup;


public class StateOfHand {

    public int id;
    private String name;
    private int bankRoll = 100;
    public Hand hand = new Hand();

    public StateOfHand(){}

    public StateOfHand(Player player) {
        super();
        this.id = player.getId();
        this.name = player.getName();
        this.bankRoll = bankRoll;
        this.hand = hand;
    }

    public StateOfHand(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        this.bankRoll = bankRoll;
        this.hand = hand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getBankRoll() {
        return bankRoll;
    }

    public void setBankRoll(int bankRoll) {
        this.bankRoll = bankRoll;
    }

}
