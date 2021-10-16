package input_output_interfaces;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for allowing higher level classes to receive input from lower level classes.
 */
public interface InputBoundary {
    String getInput();
}
