package io.clientportal.blackjack.setup;

public class Player {

    private int id;
    private String name;
    private int firstBetAmount;

    public Player() {}

    public Player(int id, String name, int firstBetAmount){
        super();
        this.id = id;
        this.name = name;
        this.firstBetAmount = firstBetAmount;
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

    public int getFirstBetAmount() {
        return firstBetAmount;
    }

    public void setFirstBetAmount(int firstBetAmount) {
        this.firstBetAmount = firstBetAmount;
    }
}
