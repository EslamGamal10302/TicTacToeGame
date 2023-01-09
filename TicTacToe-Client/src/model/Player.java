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
    private String password;
    private String email;
    private int no_game;
    private int score;
    static final int AVAILBLE =1;
    static final int INGAME =2;
    static final int OFFLINE =3;

    
    public Player(){
        
    }   
    
    public Player(String username,String password,String email,int no_game,int score){
        this.email = email;
        this.username = username;
        this.no_game = no_game;
        this.score = score;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNo_game(int no_game) {
        this.no_game = no_game;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getNo_game() {
        return no_game;
    }

    public int getScore() {
        return score;
    }

    public static int getAVAILBLE() {
        return AVAILBLE;
    }

    public static int getINGAME() {
        return INGAME;
    }

    public static int getOFFLINE() {
        return OFFLINE;
    }
    
    
    
}
