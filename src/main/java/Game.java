public class Game {

    private int score = 0;

    public void play(int pinsDown) {
        score = pinsDown;
    }

    public void playWithFrames(Frame frame) {
        score = frame.first;
    }

    public int score() {
        return score;
    }

}
