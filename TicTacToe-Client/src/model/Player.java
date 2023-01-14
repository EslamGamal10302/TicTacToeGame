/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Nada
 */
public class Player {
    private String username;
    private int no_game;
    private int score;
    private int states;
    public static final int AVAILBLE =1;
    public static final int INGAME =2;
    public static final int OFFLINE =3;

    
    public Player(){
        
    }   
    
    public Player(String username,int states,int no_game,int score){
        
        this.username = username;
        this.states=states;
        this.no_game = no_game;
        this.score = score;
        
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setNo_game(int no_game) {
        this.no_game = no_game;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStates(int states) {
        this.states = states;
    }
    public String getUsername() {
        return username;
    }
    public int getNo_game() {
        return no_game;
    }

    public int getScore() {
        return score;
    }

    public int getStates() {
        return states;
    }
    
    }