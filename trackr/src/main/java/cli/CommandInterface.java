package cli;

import java.util.Scanner;

public class CommandInterface {

    public CommandInterface() {}

    /**
     * Startup script. Welcomes user and lists any upcoming events.
     */
    public static void Startup() {
        System.out.println("=-=-=-=-=-=-=-=-=-=\nWelcome to Trackr!\n=-=-=-=-=-=-=-=-=-=\n   Upcoming Events:\n");
        /*
        TODO: implement iteration over events in their reminder period
         */
        if (false) { //if there is at least 1 upcoming event:
            assert true; // display all upcoming events
        } else {
            System.out.println("None for now!");
        }
        System.out.println("Type 'help' for a list of commands or 'quit' to exit:");
    }
    public static String AwaitInput() {
        System.out.print("\n>");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

    }

    /**
     * Print list of commands
     */
    public static void Help() {
        System.out.println("help        shows help page\nquit       quit Trackr");
    }
    /**
     * Let the user know what they just typed was wack
     */
    public static void NotRecognized(String input) {
        System.out.println("'" + input + "' is not a recognized command. Use the 'help' command for a list of valid commands.");
    }
    /**
     * Takes user input and reacts accordingly.
     * @param input whatever the user types
     */
    public static void InputHandler(String input) {
        if (input.equals("")) {
            input = CommandInterface.AwaitInput();
        }
        while (!input.equals("quit")) {
            if (input.equals("add")) {
                assert true; //add birthday here. TODO: make layer 3 argument parser
            } else if (input.equals("help")) {
                Help();
                input = CommandInterface.AwaitInput();
            } else {
                NotRecognized(input);
                input = CommandInterface.AwaitInput();
            }
        }
    }
}
