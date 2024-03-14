package edu.birzeit.todo1;

import java.util.ArrayList;

public class Player {
    public static ArrayList<Player> playerArrayList = new ArrayList<Player>();
    private int result;
    public Player(){}
    public Player(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
