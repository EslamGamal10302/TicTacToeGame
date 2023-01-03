/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playersList.playersListCell;

/**
 *
 * @author 20106
 */
public class PlayersData {
    private String playerName;
    private String playerScore;
    private String playerStat;

    public PlayersData() {
    }

    public PlayersData(String playerName, String playerScore, String playerStat) {
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.playerStat = playerStat;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerScore() {
        return playerScore;
    }

    public String getPlayerStat() {
        return playerStat;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore = playerScore;
    }

    public void setPlayerStat(String playerStat) {
        this.playerStat = playerStat;
    }
    
    
    
}
