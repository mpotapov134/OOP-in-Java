package factory;

import commands.Command;
import exceptions.CommandProductionException;
import exceptions.ConfigurationException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandFactory {
    private final HashMap<String, Class<Command>> commandClasses;
    private final HashMap<String, Command> producedCommands = new HashMap<>();

    public CommandFactory() throws ConfigurationException {
        FactoryConfigurator configurator = new FactoryConfigurator("factoryConf.txt");
        commandClasses = configurator.getCommandClasses();
    }

    public Command receiveCommand(String commandName) throws CommandProductionException {
        if (commandName == null || !commandClasses.containsKey(commandName))
            throw new CommandProductionException("Invalid command name.");

        if (!producedCommands.containsKey(commandName))
            produceNewCommand(commandName);
        return producedCommands.get(commandName);
    }

    private void produceNewCommand(String commandName) throws CommandProductionException {
        Command newCommand;
        try {
            newCommand = commandClasses.get(commandName).getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException
                 | InvocationTargetException e) {
            throw new CommandProductionException(e.getMessage(), e);
        }

        producedCommands.put(commandName, newCommand);
    }
}
