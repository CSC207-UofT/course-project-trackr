import cli.CommandInterface;

public class Main {
    public static void main(String[] args) {
        String test = "add -d 2019/01/01 -p Dave -i 7";
        String[] arrtest = test.split(" ");
        for (String a: arrtest) {

        System.out.println(a);
        }
        CommandInterface.Startup();
        String input = ""; //non-null input
        CommandInterface.InputHandler(input);
    }
}
