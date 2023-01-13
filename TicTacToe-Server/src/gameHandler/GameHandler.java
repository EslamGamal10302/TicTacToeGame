/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameHandler;

import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author 20106
 */
public class GameHandler {
    private PlayerHandler player1;
    private PlayerHandler player2;
    private String [] playedMoves;
    private int currentTurn;
    private JSONObject moveJson;
    private ArrayList<Integer> move;

    

    public GameHandler() {
        moveJson = new JSONObject();
        playedMoves = new String[9];
        currentTurn=1;
        move = new ArrayList<>();
    }

    public void setPlayer1(PlayerHandler player1) {
        this.player1 = player1;
    }

    public void setPlayer2(PlayerHandler player2) {
        this.player2 = player2;
    }
    public String getJosnMassige(int position) {
        move.add(position);
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
            if(playedMoves[0]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
           setWinposition(1,3);
       }else if(playedMoves[3]==playedMoves[5]&&playedMoves[3]==playedMoves[4]&&(playedMoves[3]=="x"||playedMoves[3]=="o")){
           if(playedMoves[3]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
           
           setWinposition(4,6);
       }else if(playedMoves[6]==playedMoves[7]&&playedMoves[6]==playedMoves[8]&&(playedMoves[6]=="x"||playedMoves[6]=="o")){
           if(playedMoves[6]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
         
           setWinposition(7,9);
       }else if(playedMoves[0]==playedMoves[3]&&playedMoves[0]==playedMoves[6]&&(playedMoves[0]=="x"||playedMoves[0]=="o")){
           if(playedMoves[0]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
           
           setWinposition(1,7);
       }else if(playedMoves[1]==playedMoves[4]&&playedMoves[1]==playedMoves[7]&&(playedMoves[1]=="x"||playedMoves[1]=="o")){
           if(playedMoves[1]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
           
           setWinposition(2,8);
       }else if(playedMoves[2]==playedMoves[5]&&playedMoves[2]==playedMoves[8]&&(playedMoves[2]=="x"||playedMoves[2]=="o")){
           if(playedMoves[2]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
           
           setWinposition(3,9);
       }else if(playedMoves[0]==playedMoves[4]&&playedMoves[0]==playedMoves[8]&&(playedMoves[0]=="x"||playedMoves[0]=="o")){
           if(playedMoves[0]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
          
           setWinposition(1,9);
       }else if(playedMoves[2]==playedMoves[4]&&playedMoves[2]==playedMoves[6]&&(playedMoves[2]=="x"||playedMoves[2]=="o")){
           if(playedMoves[2]=="x"){
                moveJson.put("gameStat", 1);
            }else{
                moveJson.put("gameStat", 2);
            }
           
           setWinposition(3,7);
       }else{
            boolean boardNotFill = false;
            for(String move:playedMoves){
               if(move==null){
                   System.out.println("move is null");
                   boardNotFill=true;
                   
               }
            }
            if (boardNotFill) {
                moveJson.put("gameStat", 0);
                setWinposition(0,0);
            }else{
                moveJson.put("gameStat", 3);
                setWinposition(0,0);
            }
          
       }
    }

    private void setWinposition(int position1, int position2) {
       moveJson.put("winPosition1", position1);
       moveJson.put("winPosition2", position2);
    }

    void sendMassigeToPlayer(String turnMassige) {
        player1.sendTurn(turnMassige);
        player2.sendTurn(turnMassige);
    }
}
