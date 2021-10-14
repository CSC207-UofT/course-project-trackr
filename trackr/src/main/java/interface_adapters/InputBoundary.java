package interface_adapters;

import java.io.IOException;

/**
 * Interface for allowing Use Case classes to receive input from Interface Adapters
 */
public interface InputBoundary {
    String[] getInput() throws IOException;
}
