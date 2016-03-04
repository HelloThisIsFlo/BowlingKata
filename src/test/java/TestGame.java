import org.junit.Before;
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
}
