import org.junit.Test;

public class TestFrame {

    @Test (expected = InvalidFrameException.class)
    public void invalidFrame_sumOver10_throwsException() throws Exception {
        new Frame(34,5);
    }

    @Test (expected = InvalidFrameException.class)
    public void negativeFrame_throwException() throws Exception {
        new Frame(-3,4);
    }
}
