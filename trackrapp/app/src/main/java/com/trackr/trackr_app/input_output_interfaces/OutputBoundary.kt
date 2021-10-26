package input_output_interfaces

/**
 * Interface for allowing higher level classes to send output to lower level classes.
 */
interface OutputBoundary {
    fun sendOutput(output: String?)
}