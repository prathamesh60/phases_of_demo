package com.example.phases_of_democracy;

public class score {

     public String player;
     public float score;

    public score(String player, float score) {
        this.player = player;
        this.score = score;
    }
    public score(){

    }

    public String getPlayer() {
        return player;
    }

    public float getScore() {
        return score;
    }
}
