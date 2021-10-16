package interface_adapters;

import entities.Person;
import input_output_interfaces.InputBoundary;
import input_output_interfaces.OutputBoundary;
import usecases.EventManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * interface_adapters.BirthdayPresenter is an Interface Adapter class that receives inputs, decides what to do
 * with them, and sends them to the Use Case classes to be dealt with. It also receives
 * outputs from the Use Case classes and sends them to the Frameworks and Drivers to be
 * outputted.
 */
public class BirthdayPresenter {
    private EventInputBoundary eventInputBoundary;

    /**
     * Interacts with user to get input and send outputs about depending on what the user wants
     * to do.
     *
     * @param eib An instance of interface_adapters.EventInputBoundary that will output the information from the Use Case
     *            classes to the user.
     */
    public BirthdayPresenter(EventInputBoundary eib) {
        this.eventInputBoundary = eib;
    }

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
        switch (command) {
            case "add":
                String[] dateString = args[0].split("/");
                LocalDate date = LocalDate.of(Integer.parseInt(dateString[0]),
                        Integer.parseInt(dateString[1]),
                        Integer.parseInt(dateString[2]));
                String firstName = args[1];
                String lastName = args[2];
                LocalDate remindDeadline = date.minusDays(Integer.parseInt(args[3]));

                eventInputBoundary.addEvent(firstName, lastName, "Birthday", date, remindDeadline);

                break;

            case "remove":
                String firstNameRemove = args[1];
                String lastNameRemove = args[2];
                eventInputBoundary.removeEvent("Birthday", firstNameRemove, lastNameRemove);
                break;

            case "view":
                String firstNameView = args[1];
                String lastNameView = args[2];
                //TODO: implement behaviour, NEEDED: EventManager.viewInfo() or something along those lines
                break;

            default:
                out.sendOutput("Not a valid command");
                break;
        }
    }
}
