import cli.CommandInterface;

public class Main {
    public static void main(String[] args) {
        CommandInterface ci = new CommandInterface();
        ci.Startup();
        String[] input = new String[0]; //non-null input
        input[0] = "";
        ci.InputHandler(input[0]);
    }
}
