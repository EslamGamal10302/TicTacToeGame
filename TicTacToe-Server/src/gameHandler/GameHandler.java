/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameHandler;

import dataAccesslayer.Game;
import dataAccesslayer.GamesDAL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import tictactoe.server.DataAccessmethods;

/**
 *
 * @author 20106
 */
public class GameHandler {

    private PlayerHandler player1;
    private PlayerHandler player2;
    private String[] playedMoves;
    private int currentTurn;
    private JSONObject moveJson;

    private boolean gameDidStarted;

    //private ArrayList<Integer> move;
    private int move[];

    public int[] getMove() {
        return move;
    }

    int i = 1;



    public GameHandler() {
        moveJson = new JSONObject();
        playedMoves = new String[9];
        move = new int[10];
        currentTurn = 1;

    }

    public void setPlayer1(PlayerHandler player1) {
        this.player1 = player1;
    }

    public void setPlayer2(PlayerHandler player2) {
        this.player2 = player2;
    }

    public String getJosnMassige(int position) {
        //move.add(position);

        move[i] = position;
        i++;
        moveJson.put("position", position);
        addToMovesArr(position);
        changPlayer();
        checkWin();
        return moveJson.toString();
    }

    private void addToMovesArr(int position) {
        if (currentTurn == 1) {
            playedMoves[position - 1] = "x";
        } else {
            playedMoves[position - 1] = "o";
        }
    }

    private void changPlayer() {
        if (currentTurn == 1) {
            currentTurn = 2;
        } else {
            currentTurn = 1;
        }
        moveJson.put("playerTurn", currentTurn);
    }

    private void checkWin() {
        if (playedMoves[0] == playedMoves[1] && playedMoves[0] == playedMoves[2] && (playedMoves[0] == "x" || playedMoves[0] == "o")) {
            if (playedMoves[0] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }
            setWinposition(1, 3);
        } else if (playedMoves[3] == playedMoves[5] && playedMoves[3] == playedMoves[4] && (playedMoves[3] == "x" || playedMoves[3] == "o")) {
            if (playedMoves[3] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }

            setWinposition(4, 6);
        } else if (playedMoves[6] == playedMoves[7] && playedMoves[6] == playedMoves[8] && (playedMoves[6] == "x" || playedMoves[6] == "o")) {
            if (playedMoves[6] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }

            setWinposition(7, 9);
        } else if (playedMoves[0] == playedMoves[3] && playedMoves[0] == playedMoves[6] && (playedMoves[0] == "x" || playedMoves[0] == "o")) {
            if (playedMoves[0] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }

            setWinposition(1, 7);
        } else if (playedMoves[1] == playedMoves[4] && playedMoves[1] == playedMoves[7] && (playedMoves[1] == "x" || playedMoves[1] == "o")) {
            if (playedMoves[1] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }

            setWinposition(2, 8);
        } else if (playedMoves[2] == playedMoves[5] && playedMoves[2] == playedMoves[8] && (playedMoves[2] == "x" || playedMoves[2] == "o")) {
            if (playedMoves[2] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }

            setWinposition(3, 9);
        } else if (playedMoves[0] == playedMoves[4] && playedMoves[0] == playedMoves[8] && (playedMoves[0] == "x" || playedMoves[0] == "o")) {
            if (playedMoves[0] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }

            setWinposition(1, 9);
        } else if (playedMoves[2] == playedMoves[4] && playedMoves[2] == playedMoves[6] && (playedMoves[2] == "x" || playedMoves[2] == "o")) {
            if (playedMoves[2] == "x") {
                moveJson.put("gameStat", 1);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player1.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            } else {
                moveJson.put("gameStat", 2);
                dataOfGame();
                try {
                    DataAccessmethods.noGame(player1.userName, player2.userName);
                    DataAccessmethods.score(player2.userName);
                } catch (SQLException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                backOnline();
            }

            setWinposition(3, 7);
        } else {
            boolean boardNotFill = false;
            for (String move : playedMoves) {
                if (move == null) {
                    System.out.println("move is null");
                    boardNotFill = true;

                }
            }
            if (boardNotFill) {
                moveJson.put("gameStat", 0);

                setWinposition(0, 0);
            } 
            else{
                gameDidStarted=false;
                moveJson.put("gameStat", 3);
                setWinposition(0, 0);
            }

        }
    }

    private void setWinposition(int position1, int position2) {


        gameDidStarted=false;
       moveJson.put("winPosition1", position1);
       moveJson.put("winPosition2", position2);
    }

    void sendMassigeToPlayer(String turnMassige) {
        player1.sendTurn(turnMassige);
        player2.sendTurn(turnMassige);
    }


    public void dataOfGame() {
        Game dataGame = new Game();
        dataGame.setPlayer_1(player1.userName);
        dataGame.setPlayer_2(player2.userName);
        dataGame.setStep_1(move[1]);
        dataGame.setStep_2(move[2]);
        dataGame.setStep_3(move[3]);
        dataGame.setStep_4(move[4]);
        dataGame.setStep_5(move[5]);
        dataGame.setStep_6(move[6]);
        dataGame.setStep_7(move[7]);
        dataGame.setStep_8(move[8]);
        dataGame.setStep_9(move[9]);
        dataGame.setStatus(1);
        //Date date = new Date();
        //dataGame.setDate((java.sql.Date) date);
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        dataGame.setDate(date);
        try {
            GamesDAL.insertGame(dataGame);
        } catch (SQLException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void backOnline(){
     int status=1;
        try {
            DataAccessmethods.online(status,player1.userName, player2.userName);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void setGameDidStarted(boolean gameDidStarted) {
        this.gameDidStarted = gameDidStarted;
    }

    public boolean isGameDidStarted() {
        return gameDidStarted;
    }

    void didSurrender(PlayerHandler player) {
        if(player==player1){
             moveJson.put("position", -1);
             moveJson.put("playerTurn", -1);
            moveJson.put("gameStat", 4);
            moveJson.put("winPosition1", 0);
            moveJson.put("winPosition2", 0);
            player2.sendTurn(moveJson.toString());
        }else{
              moveJson.put("position", -1);
            moveJson.put("playerTurn", -1);
            moveJson.put("gameStat", 4);
            moveJson.put("winPosition1", 0);
            moveJson.put("winPosition2", 0);
            player1.sendTurn(moveJson.toString());
        }
         gameDidStarted=false;
    }
    

  

    void setPlayerStates() {
        int status=1;
        try {
            DataAccessmethods.online(status,player1.userName, player2.userName);
            player1.sendPlayersListToAll();
            player2.sendPlayersListToAll();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
