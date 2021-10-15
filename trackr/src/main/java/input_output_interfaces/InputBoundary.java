package input_output_interfaces;

import java.io.IOException;

/**
 * Interface for allowing higher level classes to receive input from lower level classes.
 */
public interface InputBoundary {
    String[] getInput() throws IOException;
}
