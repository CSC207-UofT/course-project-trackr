import cli.CommandInterface;

public class Main {
    public static void main(String[] args) {
        CommandInterface.Startup();
        String input = ""; //non-null input
        CommandInterface.InputHandler(input);
    }
}
