import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGameDefensive {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void getScoreForFrameOutOfBound_throwException() throws Exception {
        game.play(new Frame(1,2));
        try {
            game.score(1);
            fail();
        } catch (OutOfBoundFrame e) {
            assertEquals("This Frame haven't been played yet!", e.getMessage());
        }
    }

    @Test
    public void getScoreForNegativeFrame_throwException() throws Exception {
        try {
            game.score(-1);
            fail();
        } catch (OutOfBoundFrame e) {
            assertEquals("Invalid Frame number!", e.getMessage());
        }
    }

    @Test
    public void playMoreThanTenFrames_throwException() throws Exception {
        try {
            for (int i = 0; i < 11; i++) {
                game.play(new Frame(2, 3));
            }
            fail();
        } catch (OutOfBoundFrame e) {
            assertEquals("You cannot play more than 10 Frames!", e.getMessage());
        }
    }

    @Test (expected = ScoreNotAvailableYet.class)
    public void getScoreJustAfterASpare_throwException() throws Exception {
        game.play(new Frame(5,5));
        game.score();
    }
}
