package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

//contains all info about the current state of the game
public class PigGameState extends GameState {
    private int turn;
    private int score0;
    private int score1;
    private int runningScore;
    private int dieVal;

    public PigGameState(){
        turn = 0;
        score0 = 0;
        score1 = 0;
        runningScore = 0;
        dieVal = 1;
    }

    public PigGameState(PigGameState state){

        turn = state.getTurn();
        score0 = state.getScore0();
        score1 = state.getScore1();
        runningScore = state.getRunningScore();
        dieVal = state.getDieVal();
    }

    public int getTurn(){
        return turn;
    }

    public int getScore0(){
        return score0;
    }

    public int getScore1(){
        return score1;
    }

    public int getRunningScore(){
        return runningScore;
    }

    public int getDieVal(){
        return dieVal;
    }

    public void setTurn(int t){
        turn=t;
    }

    public void setScore0(int s){
        score0 = s;
    }
    public void setScore1(int s){
        score1 = s;
    }

    public void setRunningScore(int s){
        runningScore = s;
    }

    public void setDieValue(int v){
        dieVal = v;
    }


}