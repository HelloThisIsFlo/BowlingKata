import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Score> scores = new ArrayList<>(10);

    private Frame currentFrame;

    public void play(Frame frame) {
        checkForNumberOfFramesPlayed();
        currentFrame = frame;
        updateScores();
    }

    private void updateScores() {
        int scoreWithoutBonuses = getScoreWithoutBonuses();
        int score = scoreWithoutBonuses + getSpareBonus();
        scores.add(new Score(score, currentFrame.isSpare()));
    }

    private int getScoreWithoutBonuses() {
        int score = currentFrame.first + currentFrame.second;
        if (!firstRoll()) {
            score += getLastScoreValue();
        }
        return score;
    }

    private boolean firstRoll() {
        return scores.isEmpty();
    }

    private int getLastScoreValue() {
        Score lastScore = getLastScore();
        return lastScore.value;
    }

    private Score getLastScore() {
        return scores.get(scores.size() - 1);
    }

    private int getSpareBonus() {
        int bonus = 0;
        if (isPreviousSpare()) {
            bonus = currentFrame.first;
        }
        return bonus;
    }

    private boolean isPreviousSpare() {
        return !scores.isEmpty() && getLastScore().isSpare;
    }

    private void checkForNumberOfFramesPlayed() {
        if (scores.size() >= 10) {
            throw new MaxFrameIsTen();
        }
    }

    /**
     * @return Return the score for the last frame played
     */
    public int score() {
        return scores.isEmpty() ? 0 : scores.get(scores.size() - 1).value;
    }

    public int score(int frameNumber) {
        return scores.get(frameNumber).value;
    }

    private static class Score {

        private final int value;
        private final boolean isSpare;

        public Score(int value, boolean isSpare) {
            this.value = value;
            this.isSpare = isSpare;
        }
    }
}
