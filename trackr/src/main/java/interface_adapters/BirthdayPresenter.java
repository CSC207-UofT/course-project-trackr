package interface_adapters;

import usecases.EventManager;

import java.io.IOException;
import java.util.Arrays;

/**
 * interface_adapters.BirthdayPresenter is an Interface Adapter class that receives inputs, decides what to do
 * with them, and sends them to the Use Case classes to be dealt with. It also receives
 * outputs from the Use Case classes and sends them to the Frameworks and Drivers to be
 * outputted.
 */
public class BirthdayPresenter {
    private EventManager eventManager;

    /**
     * Constructs an instance of interface_adapters.BirthdayPresenter
     *
     * @param em The EventManager for this interface_adapters.BirthdayPresenter
     */
    public BirthdayPresenter(EventManager em) {
        this.eventManager = em;
    }

    /**
     * Interacts with user to get input and send outputs about depending on what the user wants
     * to do.
     *
     * @param out An instance of interface_adapters.OutputBoundary that will output the information from the Use Case
     *            classes to the user.
     */
    public void run(InputBoundary in, OutputBoundary out) {
        try {
           String[] input = in.getInput();
           while (!input[0].equals("exit")) {
               String command = input[0];
               String[] args = Arrays.copyOfRange(input, 1, input.length);

               executeCommand(command, args, out);
               input = in.getInput();
           }
        } catch (IOException e) {
            out.sendOutput("Oops! Something went wrong");
        }

    }

    /**
     * Decides what (Use Case) to do based on the given user input.
     * If the input is not in a valid format then an error message
     * is outputted to the user.
     *
     * @param command The user given command.
     * @param args    The arguments/parameters for the given command.
     * @param out     The instance interface_adapters.OutputBoundary that output what is given.
     */
    public void executeCommand(String command, String[] args, OutputBoundary out) {
        if (command.equals("add")) {
            //TODO: implement behaviour using em.addEvent()
        } else if (command.equals("remove")) {
            //TODO: implement behaviour using em.removeEvent()
        } else if (command.equals("view")) {
            //TODO: implement behaviour, NEEDED: EventManager.viewInfo() or something along those lines
        } else {
            out.sendOutput("Not a valid command");
        }
    }
}
