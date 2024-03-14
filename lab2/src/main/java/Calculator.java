import commands.Command;
import context.Context;
import exceptions.CommandException;
import exceptions.CommandProductionException;
import exceptions.ConfigurationException;
import factory.CommandFactory;

public class Calculator {
    private Context context;
    private final CommandFactory factory;

    public Calculator() throws ConfigurationException {
        context = new Context();
        factory = new CommandFactory();
    }

    public void execute(String commandName, String[] args) throws CommandProductionException,
            CommandException {
        Command command = factory.receiveCommand(commandName);
        command.execute(context, args);
    }

    public void restart() {
        context = new Context();
    }
}
