package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState state;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        state = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        return playerIdx == state.getTurn();
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        if(action instanceof PigHoldAction) {
            if (state.getTurn() == 0) {
                state.setScore0(state.getScore0() + state.getRunningScore());
            } else if (state.getTurn() == 1) {
                state.setScore1(state.getScore1() + state.getRunningScore());
            }
            state.setRunningScore(0);

            int turn = (state.getTurn()+1)%players.length;

            state.setTurn(turn);
            return true;

        }
        else if(action instanceof PigRollAction){
            int roll = (int) (Math.random() * 6 + 1);

            state.setDieValue(roll);

            if (roll == 1){
                state.setRunningScore(0);
                int turn = (state.getTurn()+1) % players.length;
                state.setTurn(turn);
            }
            else{
                state.setRunningScore(state.getRunningScore() + roll);
            }
            return true;
        }

        return false;

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState game = new PigGameState();
        p.sendInfo(game);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if (state.getScore0() >= 50){
            return playerNames[0] + "wins and has a score of: " + state.getScore0();
        }
        else if (state.getScore1() >= 50){
            return playerNames[1] + "wins and has a score of: " + state.getScore1();
        }

        return null;
    }

}// class PigLocalGame
