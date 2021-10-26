import cli.CommandInterface
import interface_adapters.BirthdayPresenter

object Main {
    fun main(args: Array<String?>?) {
        val cli = CommandInterface()
        val bp = BirthdayPresenter()
        bp.run(cli, cli)
    }
}