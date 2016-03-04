import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGame {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void noFramesPlayed_scoreZero() throws Exception {
        assertEquals(0, game.score());
    }

    @Test
    public void playAFullFrame_scoreIsSum() throws Exception {
        game.play(new Frame(3,4));
        int score = game.score();
        assertEquals(3 + 4, score);
    }

    @Test
    public void play2Frames_scoreIsSumOfAllPinsDown() throws Exception {
        game.play(new Frame(3,4));
        game.play(new Frame(1,2));
        int score = game.score();

        assertEquals(3 + 4 + 2 + 1, score);
    }

    @Test
    public void spare_scoreIsSumPlusSpareBonus() throws Exception {
        /*
        When a spare is played, the bonus for that frame is the number of pins knocked down by the next roll.
         */
        Frame spare = new Frame(3, 7);
        Frame secondFrame = new Frame(8, 0);
        game.play(spare);
        game.play(secondFrame);
        int bonus = 8;

        assertEquals(3 + 7 + bonus + 8, game.score());
    }

    @Test
    public void getScoreForASpecificFrame() throws Exception {
        game.play(new Frame(1,2));
        game.play(new Frame(3,4));

        assertEquals(1 + 2, game.score(0));
        assertEquals(1 + 2 + 3 + 4, game.score(1));
    }

    @Test
    @Ignore
    public void strike_scoreIsSumPlusStrikeBonus() throws Exception {
        /*
        When a strike is played the bonus is equal to the sum of pins down in the next 2 balls roll
         */
        Frame strike = new Frame(10,0);
        game.play(strike);

        game.play(new Frame(4,5));

        assertEquals(10 + 4 + 5, game.score());
    }

    @Test
    @Ignore
    public void testScenarioWithRealLifExample() throws Exception {
        // todo rewrite with frame specific scores (situation not possible described now (get score after spare/strike))
        game.play(new Frame(1,4));
        assertEquals(5, game.score());
        game.play(new Frame(4,5));
        assertEquals(14, game.score());
        game.play(new Frame(6,4));
        assertEquals(29, game.score());
        game.play(new Frame(5,5));
        assertEquals(49, game.score());
        game.play(new Frame(10,0));
        assertEquals(60, game.score());
        game.play(new Frame(5,5));
        assertEquals(49, game.score());

    }
}
