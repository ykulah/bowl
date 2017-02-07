package com.bowling;

import java.util.LinkedList;

/**
 * Created by i306775 on 05/02/2017.
 */


public class Game {

    private boolean bonusRound = false;
    private boolean isLastRound = false;

    public Frame[] getFrames() {
        return frames;
    }

    private Frame[] frames = new Frame[10];
    private int frameIdx = 0;


    /**
     * This is the iterative function of the game. User can
     * pass rounds by calling this function. Special cases
     * are also handled, such as spare and strike.
     *
     * @author  Yusuf Kulah
     */
    public void roll(int i){

        if(frameIdx < 9){
            //first 9 rounds
            if (frames[frameIdx] == null){
                Frame f =  new Frame();
                f.shots.add(i);
                frames[frameIdx] = f;

                if(frames[frameIdx].isStrike()){
                    frameIdx++;
                    System.out.println(score());
                }
            } else {
                frames[frameIdx].shots.add(i);
                frameIdx++;
                System.out.println(score());

            }
        } else if(frameIdx == 9 && !bonusRound) {
            // Last round
            if (frames[frameIdx] == null){
                Frame f =  new Frame();
                f.shots.add(i);
                frames[frameIdx] = f;

            } else {
                isLastRound = true;
                frames[frameIdx].shots.add(i);

                if(frames[frameIdx].isSpare() || frames[frameIdx].isStrike()){
                    bonusRound = true;
                } else {
                    System.out.println(score());
                }
            }
        } else if(frameIdx == 9 && bonusRound){
            // bonus round
            frames[frameIdx].shots.add(i);
            System.out.println(score());
        }
    }

    /**
     * Score() function is used to calculate score, when it is applicable. In
     * some cases(spare and strike), score cannot be evaluated, because the points
     * of next round(s) is/are required.
     *
     * If the score cannot be evaluated, the score from previous round is displayed
     * in terminal.
     *
     * @author  Yusuf Kulah
     */

    public int score(){
        int currentScore = 0;
        for (int idx = 0; idx < frameIdx; idx++){
            if (frames[idx].isRegular()){
                // normal shot
                if(frames[idx].shots.size()==2){
                    currentScore += frames[idx].shots.get(0);
                    currentScore += frames[idx].shots.get(1);
                } else if(frames[idx].shots.size()==1){
                    currentScore += frames[idx].shots.get(0);
                }
            }
            else if(frames[idx].isSpare()){
                // spare
                try{
                    Frame next = frames[idx + 1];
                    currentScore += next.shots.get(0);
                    currentScore += frames[idx].shots.get(0);
                    currentScore += frames[idx].shots.get(1);
                }  catch (NullPointerException e){
                }
            }
            else if(frames[idx].isStrike()){
                try{
                    //check next frame
                    Frame next = frames[idx+1];
                    if(next.isRegular()){
                        // next frame after strike
                        currentScore += 10;
                        currentScore += next.shots.get(0);
                        currentScore += next.shots.get(1);
                    } else if(next.isStrike()){
                        // strike - strike
                        try{
                            Frame nextNext = frames[idx+2];
                            if(nextNext.isRegular()){
                                currentScore += 20;
                                currentScore += nextNext.shots.get(0);

                            } else if(nextNext.isStrike()){
                                // strike - strike - strike
                                currentScore += 30;
                            } else if (nextNext.isSpare()){
                                // strike - strike - spare
                                currentScore += 20;
                                currentScore += nextNext.shots.get(0);
                            }
                        } catch (ArrayIndexOutOfBoundsException e){
                            if (idx == 8 && frames[9] != null){
                                currentScore += 10;
                                currentScore += frames[9].shots.get(0);
                                currentScore += frames[9].shots.get(1);
                            }
                        } catch (NullPointerException e){
                        }

                    } else if(next.isSpare()){
                        // strike - spare
                        currentScore += 10;
                        currentScore += next.shots.get(0);
                        currentScore += next.shots.get(1);
                    }
                } catch (NullPointerException e){
                }
            }
        }

        if(isLastRound){
            if(bonusRound) {
                currentScore += frames[9].shots.get(0);
                currentScore += frames[9].shots.get(1);
                currentScore += frames[9].shots.get(2);
            } else {
                currentScore += frames[9].shots.get(0);
                currentScore += frames[9].shots.get(1);
            }
        }

        return currentScore;
    }

}

