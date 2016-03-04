/**
 * Represent a round, called "frame" in Bowling
 */
public class Frame {

    public final int first;
    public final int second;

    public Frame(int first, int second) throws InvalidFrameException {
        this.first = first;
        this.second = second;

        checkFrameValid();
    }

    private void checkFrameValid() throws InvalidFrameException {
        if (first + second > 10) {
            throw new InvalidFrameException("A frame can not knock more than 10 pins");
        }
    }

    public boolean isSpare() {
        return first + second == 10;
    }

}
