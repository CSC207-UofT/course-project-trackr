import cli.CommandInterface;
import interface_adapters.BirthdayPresenter;

public class Main {
    public static void main(String[] args) {
        CommandInterface cli = new CommandInterface();
        BirthdayPresenter bp = new BirthdayPresenter();
        bp.run(cli, cli);
    }
}
