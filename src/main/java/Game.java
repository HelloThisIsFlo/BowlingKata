public class Game {

    private int score = 0;
    private int framesPlayed = 0;

    public void play(Frame frame) {
        incrementFramesPlayed();
        score += frame.first + frame.second;
        if (frame.isSpare()) {
            score += 5;
        }
    }

    private void incrementFramesPlayed() {
        framesPlayed++;
        if (framesPlayed > 10) {
            throw new CannotPlayMoreThanTenFrameException();
        }
    }

    public int score() {
        return score;
    }
}
