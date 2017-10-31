package io.clientportal.blackjack.setup;

public class Dealer extends StateOfHand{

    private int bankRoll = 99999999;
    private String name = "Dealer";


    public Dealer(){}

    public Dealer(int id){
        super();
        this.id = id;
        this.bankRoll = bankRoll;
        this.name = "Dealer";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getBankRoll() {
        return bankRoll;
    }

    @Override
    public void setBankRoll(int bankRoll) {
        this.bankRoll = bankRoll;
    }

}
