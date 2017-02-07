package com.bowling;

import java.util.Scanner;

/**
 * Created by i306775 on 05/02/2017.
 */

public class Main {

    public static void main(String[] args) {
        Game myGame = new Game();

        Scanner s = new Scanner(System.in);
        System.out.println("Game initialized.");
        for (int i = 0; i < 10; i++){

            int rand1 = generateRandomNumber(10);

            System.out.println("Hit enter to roll");
            if(s.nextLine().trim().isEmpty()){
                System.out.println("Round: "+ (i+1) +" Roll: 1 Rolled: "+rand1);
                myGame.roll(rand1);
            }

            if(rand1 != 10){
                int rand2  = generateRandomNumber(10 - rand1);

                if(s.nextLine().trim().isEmpty()){
                    System.out.println("Round "+ (i+1) +" Roll: 2 Rolled: "+rand2);
                    myGame.roll(rand2);
                }

                if(i == 9 && (rand1 + rand2 == 10)){

                    rand1 = generateRandomNumber(10);
                    System.out.println("Hit enter to roll for bonus round");
                    if(s.nextLine().trim().isEmpty()){
                        System.out.println("Round: "+ (i+1) +" Roll: 3 Rolled: "+rand1);
                        myGame.roll(rand1);
                    }
                }
            } else if(i == 9 && rand1 == 10){
                rand1 = generateRandomNumber(10);
                System.out.println("Hit enter to roll for bonus round");
                if(s.nextLine().trim().isEmpty()){
                    System.out.println("Round: "+ (i+1) +" Roll: 3 Rolled: "+rand1);
                    myGame.roll(rand1);
                }
            }
        }
        System.out.println("Final score: " + myGame.score());
    }


    /**
     * Generate random numbers for rolls.
     *
     * @author  Yusuf Kulah
     */
    public static int generateRandomNumber(int max){
        int min = 0;
        return  min + (int) (Math.random() * ((max - min) + 1));
    }
}
