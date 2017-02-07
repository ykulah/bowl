import com.bowling.Game;
import com.bowling.Frame;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by i306775 on 06/02/2017.
 */

public class GameTest {

    @Test
    public void testStrikeSuccess() {
        Frame tFrame = new Frame();
        tFrame.shots.add(10);
        tFrame.shots.add(0);

        assertEquals(tFrame.isStrike(), true);
    }

    @Test
    public void testStrikeFail() {
        Frame tFrame = new Frame();
        tFrame.shots.add(7);
        tFrame.shots.add(2);

        assertEquals(tFrame.isStrike(), false);
    }

    @Test
    public void testSpareSuccess() {
        Frame tFrame = new Frame();
        tFrame.shots.add(8);
        tFrame.shots.add(2);

        assertEquals(tFrame.isSpare(), true);
    }

    @Test
    public void testSpareFail() {
        Frame tFrame = new Frame();
        tFrame.shots.add(7);
        tFrame.shots.add(2);

        assertEquals(tFrame.isSpare(), false);
    }

    @Test
    public void testRegularSuccess() {
        Frame tFrame = new Frame();
        tFrame.shots.add(2);
        tFrame.shots.add(2);

        assertEquals(tFrame.isRegular(), true);
    }

    @Test
    public void testRegularFail() {
        Frame tFrame = new Frame();
        tFrame.shots.add(8);
        tFrame.shots.add(2);

        assertEquals(tFrame.isRegular(), false);
    }

    @Test
    public void allStrikeGame() {
        Game tGame = new Game();

        for(int i =0; i<12; i++){
            tGame.roll(10);
        }
        assertEquals(tGame.score(), 300);
    }

    @Test
    public void allZeroGame() {
        Game tGame = new Game();

        for(int i =0; i<20; i++){
            tGame.roll(0);
        }
        assertEquals(tGame.score(), 0);
    }

    @Test
    public void regularGame() {
        Game tGame = new Game();

        for(int i =0; i<10; i++){
            tGame.roll(0);
            tGame.roll(5);
        }
        assertEquals(tGame.score(), 50);
    }

    @Test
    public void testSingleRoll() {
        Game tGame = new Game();
        int ball = 8;

        tGame.roll(ball);

        assertEquals( tGame.getFrames()[0].shots.size(), 1);
    }

    @Test
    public void testDoubleRoll() {
        Game tGame = new Game();
        int ball1 = 8;
        int ball2 = 1;

        tGame.roll(ball1);
        tGame.roll(ball2);

        assertEquals( tGame.getFrames()[0].shots.size(), 2);
    }
}
