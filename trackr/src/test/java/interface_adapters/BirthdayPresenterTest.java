package interface_adapters;

import input_output_interfaces.InputBoundary;
import input_output_interfaces.OutputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                "You have successfully added a Birthday event on 2022/01/01 for James Jameson." +
                        " You will be reminded 7 days beforehand.",
                "You have a Birthday event on 2022/01/01 for James Jameson." +
                        " You will be reminded 7 days beforehand.",
                "You have successfully removed a Birthday event for James Jameson."
                };

        FakeUserInterface userInterface = new FakeUserInterface(
                new String[] {
                        "add Birthday 2022/01/01 James_Jameson 7",
                        "view Birthday James_Jameson",
                        "remove Birthday James_Jameson",
                        "exit"
                }, 3);

        birthdayPresenter.run(userInterface, userInterface);
        assertArrayEquals(expectedOutput, userInterface.testOutputs);
        assertEquals(3, userInterface.testOutputIndex);
        }

    /**
     * Class that implements InputBoundary, OutputBoundary. Used only for testing purposes since the test
     * needs an InputBoundary and OutputBoundary to get inputs from and send outputs to.
     */
    public static class FakeUserInterface implements InputBoundary, OutputBoundary {
        private final String[] testInputs;
        private final String[] testOutputs;
        private int testInputIndex;
        private int testOutputIndex;

        public FakeUserInterface(String[] testInputs,int numTestOutputs) {
            this.testInputs = testInputs;
            this.testOutputs = new String[numTestOutputs];
        }

        @Override
        public String getInput() {
            return this.testInputs[testInputIndex++];
        }

        @Override
        public void sendOutput(String output) {
            this.testOutputs[testOutputIndex++] = output;
        }
    }
}