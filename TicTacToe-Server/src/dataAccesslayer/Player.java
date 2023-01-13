/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccesslayer;

/**
 *
 * @author menna
 */
public class Player {
    private String playerName;
    private int playerScore;
    private int playerStat;
    private int no_games;

    public int getNo_games() {
        return no_games;
    }

    public void setNo_games(int no_games) {
        this.no_games = no_games;
    }
    
    

    public Player() {
    }

    public Player(String playerName, int playerScore, int playerStat,int no_games) {
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.playerStat = playerStat;
         this.no_games = no_games;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getPlayerStat() {
        return playerStat;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setPlayerStat(int playerStat) {
        this.playerStat = playerStat;
    }
    
}
