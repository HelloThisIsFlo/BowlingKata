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
            throw new OutOfBoundFrame("You cannot play more than 10 Frames!");
        }
    }

    /**
     * @return Return the score for the last frame played
     */
    public int score() throws ScoreNotAvailableYet {
        return scores.isEmpty() ? 0 : score(scores.size() - 1);
    }

    public int score(int frameNumber) throws ScoreNotAvailableYet {
        checkIfFrameOutOfBound(frameNumber);
        checkIfAwaitingFutureRoll(frameNumber);
        return scores.get(frameNumber).value;
    }

    private void checkIfFrameOutOfBound(int frameNumber) {
        if (scores.size() <= frameNumber) {
            throw new OutOfBoundFrame("This Frame haven't been played yet!");
        } else if (frameNumber < 0) {
            throw new OutOfBoundFrame("Invalid Frame number!");
        }
    }

    private void checkIfAwaitingFutureRoll(int frameNumber) throws ScoreNotAvailableYet {
        if (isLastScoreAvailable(frameNumber)) {
            checkIfLastFrameIsSpare();
        }
    }

    private void checkIfLastFrameIsSpare() throws ScoreNotAvailableYet {
        Score lastScore = getLastScore();
        if (lastScore.isSpare) {
            throw new ScoreNotAvailableYet();
        }
    }

    private boolean isLastScoreAvailable(int frameNumber) {
        return frameNumber == scores.size() - 1;
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
