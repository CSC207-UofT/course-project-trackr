package cli;
import interface_adapters.BirthdayPresenter;
import input_output_interfaces.*;

import java.io.IOException;
import java.util.Scanner;

public class CommandInterface implements InputBoundary, OutputBoundary {

    public CommandInterface() {}

    /**
     * Startup script. Welcomes user and lists any upcoming events.
     */
    public void Startup() {
        System.out.println("=-=-=-=-=-=-=-=-=-=\nWelcome to Trackr!\n=-=-=-=-=-=-=-=-=-=\n");
        DisplayEvents();
        System.out.println("Type 'help' for a list of commands or 'quit' to exit:");
    }
    @Override
    public String getInput() {
        System.out.print("\n>");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    @Override
    public void sendOutput(String out) {
        System.out.println(out);
    }
    public void DisplayEvents() {
        /*
        TODO: implement iteration over events in their reminder period
         */
        sendOutput("Upcoming events:\n");
        if (false) { //if there is at least 1 upcoming event:
            assert true; // display all upcoming events
        } else { //otherwise say this
            System.out.println("None for now!");
        }
    }
    /**
     * Print list of commands
     * TODO: add add command here once implemented
     */
    public void Help() {
        System.out.println("help        shows help page\nquit       quit Trackr");
    }
    /**
     * Let the user know what they just typed was wack
     */
    public void NotRecognized(String input) {
        System.out.println("'" + input + "' is not a recognized command. Use the 'help' command for a list of valid commands.");
    }
    /**
     * Takes user input and reacts accordingly.
     * @param input whatever the user types
     */
    public void InputHandler(String input) {
        String[] inputArray;
        if (input.equals("")) {
            input = getInput();
        }
        while (!input.equals("quit")) {
            inputArray = input.split(" ");
            if (inputArray[0].equals("add")) {
            AddHandler(inputArray);
            input = getInput();
            } else if (input.equals("help")) {
                Help();
                input = getInput();
            } else if (input.equals("list")) {
                DisplayEvents();
                input = getInput();
            } else {
                NotRecognized(input);
                input = getInput();
            }

        }
    }
    public void AddHandler(String[] inputArray) {
        String date = null;
        String personName = null;
        String interval = null;
        // not enough arguments case
        if (inputArray.length < 7) {
            System.out.println("Error: not enough information. See 'help' for more details.");
            return;
        }
        // get arguments in any order by iterating
        for (int i = 1; i < inputArray.length; i++) {
            //if all fields have been filled, create the event
            if (date != null && personName != null && interval != null) {
                String[] args = new String[2];
                args[0] = date;
                args[1] = personName;
                args[2] = interval;
                //TODO: create birthday event
                //BirthdayPresenter.executeCommand("add", args, );
                System.out.println(date + personName + interval);
                return;
            } else if (inputArray[i].equals("-d")) {
                date = inputArray[i+1];
            } else if (inputArray[i].equals("-p")) {
                personName = inputArray[i+1];
            } else if (inputArray[i].equals("-i")) {
                interval = inputArray[i+1];
            }
        }

    }
}
