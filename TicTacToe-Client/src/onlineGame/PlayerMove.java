/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineGame;

/**
 *
 * @author 20106
 */
public class PlayerMove {
    private long position ;
    private long winPosition1 ;
    private long winPosition2 ;
    private long playerTurn ;
    private int gameStat;
    public static final int Player1Win = 1;
    public static final int Player2Win = 2;
    public static final int tie = 2;
    
    
    public PlayerMove(long position, long winPosition1, long winPosition2, long playerTurn, int gameStat) {
        this.position = position;
        this.winPosition1 = winPosition1;
        this.winPosition2 = winPosition2;
        this.playerTurn = playerTurn;
        this.gameStat = gameStat;
    }

    public long getPosition() {
        return position;
    }

    public long getWinPosition1() {
        return winPosition1;
    }

    public long getWinPosition2() {
        return winPosition2;
    }

    public long getPlayerTurn() {
        return playerTurn;
    }

    public int getGameStat() {
        return gameStat;
    }
    

    

    public void setPosition(int position) {
        this.position = position;
    }

    public void setWinPosition1(int winPosition1) {
        this.winPosition1 = winPosition1;
    }

    public void setWinPosition2(int winPosition2) {
        this.winPosition2 = winPosition2;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setGameStat(int gameStat) {
        this.gameStat = gameStat;
    }
    

    
   

    
    
}
