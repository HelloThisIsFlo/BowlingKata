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
        game.play(0);
        int score = game.score();
        assertEquals(0, score);
    }

    @Test
    public void xPinsdown_scoreX() throws Exception {
        int pinsDown = 3;
        game.play(pinsDown);
        int score = game.score();
        assertEquals(pinsDown, score);
    }

    @Test
    public void representARoundWithFrames() throws Exception {
        Frame firstRound = new Frame(0,0);
        game.playWithFrames(firstRound);
        int score = game.score();
        assertEquals(0, score);
    }
}
