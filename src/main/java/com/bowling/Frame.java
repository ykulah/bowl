package com.bowling;

import java.util.LinkedList;

/**
 * Frame class is declared better readability of the code. It
 * is used as struct for storing frames in a game.
 *
 * @author  Yusuf Kulah
 */
public class Frame{
    public LinkedList<Integer> shots = new LinkedList<Integer>();

    /**
     * Helper function to identify the score of particular frame
     * is regular(not stike or spare).
     *
     * @author  Yusuf Kulah
     */
    public boolean isRegular(){

        if(shots.size() == 2){
            int firstRoll = shots.get(0);
            int secondRoll = shots.get(1);

            if(firstRoll < 10 && secondRoll < 10 && (firstRoll+secondRoll) < 10){
                return true;
            }
            return false;

        } else if(shots.size() == 1){
            int firstRoll = shots.get(0);

            if(firstRoll < 10){
                return true;
            }
            return false;
        }

        return false;
    }

    /**
     * In the rule of 10 pin bowling game if a frame is spare, it
     * will get the first roll of next frame as bonus points.
     *
     * Helper function to identify the score of particular frame
     * is spare. According to the result of this function, score
     * calculation strategy is updated.
     *
     *
     * @author  Yusuf Kulah
     */
    public boolean isSpare(){

        if(shots.size() < 2){
            return false;
        } else {
            int firstRoll = shots.get(0);
            int secondRoll = shots.get(1);

            if(firstRoll < 10 && secondRoll <= 10 && (firstRoll+secondRoll) == 10){
                return true;
            }
            return false;
        }

    }

    /**
     * In the rule of 10 pin bowling game if a frame is strike, it
     * will get the first and second roll of next frame as bonus points.
     *
     * Helper function to identify the score of particular frame
     * is strike. According to the result of this function, score
     * calculation strategy is updated.
     *
     *
     * @author  Yusuf Kulah
     */
    public boolean isStrike(){
        if(shots.get(0) == 10){
            return true;
        }
        return false;
    }
}
