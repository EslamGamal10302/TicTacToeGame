/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameHandler;

import org.json.simple.JSONObject;

/**
 *
 * @author 20106
 */
public class GameHandler {
    RequestHandler palyer1;
    RequestHandler palyer2;
    private String [] playedMoves;
    private int currentTurn;
     private JSONObject moveJson;

    public GameHandler() {
        moveJson = new JSONObject();
        playedMoves = new String[9];
        currentTurn=1;
    }

    public void setPalyer1(RequestHandler palyer1) {
        this.palyer1 = palyer1;
    }

    public void setPalyer2(RequestHandler palyer2) {
        this.palyer2 = palyer2;
    }
    public String getJosnMassige(int position) {
        moveJson.put("position", position);
        addToMovesArr(position);
        changPlayer();
        checkWin();
        return moveJson.toString();
    }

    private void addToMovesArr(int position) {
        if(currentTurn==1){
            playedMoves[position -1]="x";
        }else{
            playedMoves[position -1]="o";
        }
    }

    private void changPlayer() {
         if(currentTurn==1){
            currentTurn=2;
        }else{
           currentTurn=1;
        }
        moveJson.put("playerTurn", currentTurn);
    }

    private void checkWin() {
       if(playedMoves[0]==playedMoves[1]&&playedMoves[0]==playedMoves[2]&&(playedMoves[0]=="x"||playedMoves[0]=="o")){
            moveJson.put("win", true);
           setWinposition(1,3);
       }else if(playedMoves[3]==playedMoves[5]&&playedMoves[3]==playedMoves[4]&&(playedMoves[3]=="x"||playedMoves[3]=="o")){
           moveJson.put("win", true);
           setWinposition(4,6);
       }else if(playedMoves[6]==playedMoves[7]&&playedMoves[6]==playedMoves[8]&&(playedMoves[6]=="x"||playedMoves[6]=="o")){
           moveJson.put("win", true);
           setWinposition(7,9);
       }else if(playedMoves[0]==playedMoves[3]&&playedMoves[0]==playedMoves[6]&&(playedMoves[0]=="x"||playedMoves[0]=="o")){
           moveJson.put("win", true);
           setWinposition(1,7);
       }else if(playedMoves[1]==playedMoves[4]&&playedMoves[1]==playedMoves[7]&&(playedMoves[1]=="x"||playedMoves[1]=="o")){
           moveJson.put("win", true);
           setWinposition(2,8);
       }else if(playedMoves[2]==playedMoves[5]&&playedMoves[2]==playedMoves[8]&&(playedMoves[2]=="x"||playedMoves[2]=="o")){
           moveJson.put("win", true);
           setWinposition(3,9);
       }else if(playedMoves[0]==playedMoves[4]&&playedMoves[0]==playedMoves[8]&&(playedMoves[0]=="x"||playedMoves[0]=="o")){
           moveJson.put("win", true);
           setWinposition(1,9);
       }else if(playedMoves[2]==playedMoves[4]&&playedMoves[2]==playedMoves[6]&&(playedMoves[2]=="x"||playedMoves[2]=="o")){
           moveJson.put("win", true);
           setWinposition(3,7);
       }else{
           moveJson.put("win", false);
           setWinposition(0,0);
       }
    }

    private void setWinposition(int position1, int position2) {
       moveJson.put("winPosition1", position1);
       moveJson.put("winPosition2", position2);
    }
}