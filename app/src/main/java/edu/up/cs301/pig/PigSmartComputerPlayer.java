package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

public class PigSmartComputerPlayer extends GameComputerPlayer {

    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(!(info instanceof PigGameState)){
            return;
        }

        //new piggamestate
        PigGameState gs = new PigGameState((PigGameState) info);

        boolean rollDie = true;

        int score;
        int opponentScore;
        if (playerNum == 0){
            score = gs.getScore0();
            opponentScore = gs.getScore1();
        }
        else{
            score = gs.getScore1();
            opponentScore = gs.getScore0();
        }

        //return if it's not their turn
        if (playerNum != gs.getTurn()){
            return;
        }

        //decision based on: # of points if hold, difference between this score and opponents score, remaining # of points needed to win
        int runningScore = gs.getRunningScore();
        if(runningScore + score >= 50){
            rollDie = false;
        }

        int diffScores = Math.abs(score - opponentScore);

        int offset = runningScore / (diffScores + 1);

        if(opponentScore > score){
            if(diffScores > 10){
                if(runningScore > diffScores){
                    rollDie = false;
                }
            }
            else{
                if(runningScore > 10){
                    rollDie = false;
                }
            }

        }
        else{
            if(runningScore > diffScores+offset){
                rollDie = false;
            }
        }


        if(!rollDie){
            PigHoldAction action = new PigHoldAction(this);
            game.sendAction(action);
            sleep(2000);
        }
        else{
            PigRollAction action = new PigRollAction(this);
            game.sendAction(action);
            sleep(2000);
        }

    }
}
