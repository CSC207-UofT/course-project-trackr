package interface_adapters;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BirthdayPresenterTest {
    BirthdayPresenter birthdayPresenter;

    @BeforeEach
    public void setUp() {
        birthdayPresenter = new BirthdayPresenter();
    }

    @Test
    public void runTest() {
        String[] expectedOutput = new String[]{
                "You have added a birthday on 2022/01/01 for James Jameson." +
                        "You will be reminded 7 days before.",
                "You have a birthday event for James Jameson on 2022/01/01." +
                        "You will be reminded 7 days before.",
                "You have removed a birthday on 2022/01/01 for James Jameson"
                };
        }
        FakeUserInterface userInterface = new FakeUserInterface(
                new String[][] {
                        new String[]{"add", "2022/01/01", "James Jameson", "7"},
                        new String[]{"view", "James Jameson"},
                        new String[]{"remove", "2022/01/01", "James Jameson"}
                }, 5);

    /**
     * Class that implements InputBoundary, OutputBoundary. Used only for testing purposes since the test
     * needs an InputBoundary and OutputBoundary to get inputs from and send outputs to.
     */
    public static class FakeUserInterface implements BirthdayPresenter.InputBoundary, BirthdayPresenter.OutputBoundary {
        private String[][] testInputs;
        private String[] testOutputs;
        private int testInputIndex;
        private int testOutputIndex;

        public FakeUserInterface(String[][] testInputs,int numTestOutputs) {
            this.testInputs = testInputs;
            this.testOutputs = new String[numTestOutputs];
        }

        @Override
        public String[] getInput() throws IOException {
            return this.testInputs[testInputIndex++];
        }

        @Override
        public void sendOutput(String output) {
            this.testOutputs[testOutputIndex++] = output;
        }
    }
}