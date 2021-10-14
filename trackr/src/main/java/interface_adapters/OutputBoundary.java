package interface_adapters;

/**
 * Interface for allowing Interface Adapters classes to receive output from Use Case classes.
 */
public interface OutputBoundary {
    String sendOutput(String output);
}
