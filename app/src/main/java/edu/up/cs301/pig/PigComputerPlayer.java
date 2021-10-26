package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        if(!(info instanceof PigGameState)){
            return;
        }

        //new piggamestate
        PigGameState gs = new PigGameState((PigGameState) info);

        //return if it's not their turn
        if (playerNum != gs.getTurn()){
            return;
        }

        //generate random number
        int rand = (int)(Math.random()*2);

        if(rand == 0){
            PigHoldAction action = new PigHoldAction(this);
            game.sendAction(action);
            sleep(2000);
        }
        else{
            PigRollAction action = new PigRollAction(this);
            game.sendAction(action);
            sleep(2000);
        }
    }//receiveInfo

}
