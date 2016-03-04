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
    public void zeroPinsdown_scoreZero() throws Exception {
        game.play(new Frame(0,0));
        int score = game.score();
        assertEquals(0, score);
    }

    @Test
    public void xPinsdown_scoreX() throws Exception {
        int pinsDown = 3;
        game.play(new Frame(pinsDown,0));
        int score = game.score();
        assertEquals(pinsDown, score);
    }

    @Test
    public void playAFullFrame_scoreIsSum() throws Exception {
        game.play(new Frame(3,4));
        int score = game.score();
        assertEquals(3 + 4, score);
    }

    @Test (expected = InvalidFrameException.class)
    public void invalidFrame_ThrowInvalidFrameException() throws Exception {
        new Frame(5,6);
    }

    @Test
    public void play2Frames_scoreIsSumOfAllPinsDown() throws Exception {
        game.play(new Frame(3,4));
        game.play(new Frame(1,2));
        int score = game.score();

        assertEquals(3 + 4 + 2 + 1, score);
    }

    @Test
    public void spare_scoreIsSumPlusBonus5() throws Exception {
        game.play(new Frame(3,7));
        int score = game.score();

        assertEquals(3 + 7 + 5, score);
    }

    @Test (expected = CannotPlayMoreThanTenFrameException.class)
    public void playMoreThanTenFrames_throwException() throws Exception {
        for (int i = 0; i < 11; i++) {
            game.play(new Frame(2,3));
        }
    }

    @Test
    public void testScenarioWithRealLifExample() throws Exception {
        game.play(new Frame(1,4));
        assertEquals(5, game.score());
        game.play(new Frame(4,5));
        assertEquals(14, game.score());
        game.play(new Frame(6,4));
        assertEquals(29, game.score());
        game.play(new Frame(5,5));
        assertEquals(49, game.score());
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
}
