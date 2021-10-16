package interface_adapters;

import database.DataAccess;
import database.DatabaseAccessFactory;
import input_output_interfaces.EventInOut;
import input_output_interfaces.InputBoundary;
import input_output_interfaces.OutputBoundary;
import usecases.EventManager;
import usecases.EventOutputData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * interface_adapters.BirthdayPresenter is an Interface Adapter class that receives inputs, decides what to do
 * with them, and sends them to the Use Case classes to be dealt with. It also receives
 * outputs from the Use Case classes and sends them to the Frameworks and Drivers to be
 * outputted.
 */
public class BirthdayPresenter {

    private final EventInOut em = new EventManager(DatabaseAccessFactory.createDatabaseAccessInterface());

    /**
     * Interacts with user to get input and send outputs about depending on what the user wants
     * to do.
     *
     * @param out An instance of interface_adapters.OutputBoundary that will output the information from the Use Case
     *            classes to the user.
     */
    public void run(InputBoundary in, OutputBoundary out) {
        try {
           String input = in.getInput();
           while (!input.equals("exit")) {
               String[] inputArray = input.split(" ");
               String command = inputArray[0];
               String[] args = Arrays.copyOfRange(inputArray, 1, inputArray.length);

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
            case "add" -> {
                EventOutputData.EventTypes eventType = getEventType(args[0]);
                String[] name = getFirstLastName(args[2]);
                String firstName = name[0];
                String lastName = name[1];

                String[] dateString = args[1].split("/");

                LocalDate date = LocalDate.of(Integer.parseInt(dateString[0]),
                        Integer.parseInt(dateString[1]),
                        Integer.parseInt(dateString[2]));

                LocalDate remindDeadline = date.minusDays(Integer.parseInt(args[3]));

                boolean success = em.add(eventType, firstName, lastName, date, remindDeadline);
                String successString = "unsuccessfully";
                if (success) {successString = "successfully";}
                String outString = "You have " + successString + " added a " + args[0] +
                        " event on " + args[1] + " for " + firstName + " " + lastName + ". You will be reminded " +
                        args[3] + " days beforehand.";

                out.sendOutput(outString);
            }
            case "remove" -> {
                EventOutputData.EventTypes eventType = getEventType(args[0]);
                String[] name = getFirstLastName(args[1]);
                String firstName = name[0];
                String lastName = name[1];

                boolean success = em.remove(eventType, firstName, lastName);
                String successString = "unsuccessfully";
                if (success) {successString = "successfully";}
                String outString = "You have " + successString + " removed a " + args[0] +
                        " event for " + firstName + " " + lastName + ".";

                out.sendOutput(outString);
            }
            case "view" -> {
                EventOutputData.EventTypes eventType = getEventType(args[0]);
                String[] name = getFirstLastName(args[1]);
                String firstName = name[0];
                String lastName = name[1];

                EventOutputData info = em.view(eventType, firstName, lastName);
                String dateString = info.getDate().toString().replace('-', '/');
                int days = (int) info.getRemindDeadline().until(info.getDate(), ChronoUnit.DAYS);

                String outString = "You have a " + args[0] +
                        " event on " + dateString + " for " + firstName + " " + lastName + ". You will be reminded " +
                        days + " days beforehand.";

                out.sendOutput(outString);
            }
            default -> out.sendOutput("Not a valid command");
        }
    }

    /**
     * Returns the eventType from the given input.
     *
     * @param input The given input from the user representing the event type.
     * @return      The event type in a form the use case classes can use.
     */
    private EventOutputData.EventTypes getEventType(String input) {
        if (input.equalsIgnoreCase("BIRTHDAY")) {
            return EventOutputData.EventTypes.BIRTHDAY;
        } else {
            return EventOutputData.EventTypes.ANNIVERSARY;
        }
    }

    /**
     * Returns the first and last names of an individual split between their fist and last names
     * in the form [<firstName>,<lastName>].
     *
     * @param input The given input from the user representing the name.
     * @return      The first and last names.
     */
    private String[] getFirstLastName(String input) {
        String lastName;
        String[] name = input.split("_");
        String firstName = name[0];
        if (name.length == 2) {
            lastName = name[1];
        } else {
            lastName = "";
        }

        return new String[] {firstName, lastName};
    }
}
