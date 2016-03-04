public class Game {

    private int score = 0;

    public void play(Frame frame) {
        score += frame.first + frame.second;
        if (frame.isSpare()) {
            score += 5;
        }
    }

    public int score() {
        return score;
    }

}
