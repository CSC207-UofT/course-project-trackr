package cli

import java.util.Scanner

class CommandInterface : InputBoundary, OutputBoundary {
    /**
     * Startup script. Welcomes user and lists any upcoming events.
     */
    fun Startup() {
        System.out.println("=-=-=-=-=-=-=-=-=-=\nWelcome to Trackr!\n=-=-=-=-=-=-=-=-=-=\n")
        DisplayEvents()
        System.out.println("Type 'help' for a list of commands or 'quit' to exit:")
    }

    @get:Override
    val input: String
        get() {
            System.out.print("\n>")
            val sc = Scanner(System.`in`)
            return sc.nextLine()
        }

    @Override
    fun sendOutput(out: String?) {
        System.out.println(out)
    }

    fun DisplayEvents() {
        sendOutput("Upcoming events:\n")
        //        if (false) { //if there is at least 1 upcoming event:
//            assert true; // display all upcoming events
//        } else { //otherwise say this
        sendOutput("None for now!")
        //        }
    }

    /**
     * Let the user know what they just typed was wack
     */
    //    public void NotRecognized(String input) {
    //        System.out.println("'" + input + "' is not a recognized command. Use the 'help' command for a list of valid commands.");
    //    }
    //    /**
    //     * Takes user input and reacts accordingly.
    //     * @param input whatever the user types
    //     */
    //    public void InputHandler(String input) {
    //        String[] inputArray;
    //        if (input.equals("")) {
    //            input = getInput();
    //        }
    //        while (!input.equals("quit")) {
    //            inputArray = input.split(" ");
    //            if (inputArray[0].equals("add")) {
    //            AddHandler(inputArray);
    //            input = getInput();
    //            } else if (input.equals("help")) {
    //                Help();
    //                input = getInput();
    //            } else if (input.equals("list")) {
    //                DisplayEvents();
    //                input = getInput();
    //            } else {
    //                NotRecognized(input);
    //                input = getInput();
    //            }
    //
    //        }
    //    }
    fun AddHandler(inputArray: Array<String>) {
        var date: String? = null
        var personName: String? = null
        var interval: String? = null
        // not enough arguments case
        if (inputArray.size < 7) {
            System.out.println("Error: not enough information. See 'help' for more details.")
            return
        }
        // get arguments in any order by iterating
        for (i in 1 until inputArray.size) {
            //if all fields have been filled, create the event
            if (date != null && personName != null && interval != null) {
                //TODO: create birthday event
                //BirthdayPresenter.executeCommand("add", args, );
                System.out.println(date + personName + interval)
                return
            } else if (inputArray[i].equals("-d")) {
                date = inputArray[i + 1]
            } else if (inputArray[i].equals("-p")) {
                personName = inputArray[i + 1]
            } else if (inputArray[i].equals("-i")) {
                interval = inputArray[i + 1]
            }
        }
    }

    init {
        Startup()
    }
}