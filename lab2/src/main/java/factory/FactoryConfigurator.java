package factory;

import commands.Command;
import exceptions.ConfigurationException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class FactoryConfigurator {
    private final HashMap<String, Class<Command>> commandClasses = new HashMap<>();

    public FactoryConfigurator(String configFileName) throws ConfigurationException {
        configure(configFileName);
    }

    public HashMap<String, Class<Command>> getCommandClasses() {
        return commandClasses;
    }

    private void configure(String configFileName) throws ConfigurationException {
        InputStream in = FactoryConfigurator.class.getResourceAsStream("/" + configFileName);
        if (in == null)
            throw new ConfigurationException("Configuration file not found.");

        Scanner configScanner = new Scanner(in);
        while (configScanner.hasNext()) {
            String[] configPair = configScanner.next().split("\\s*=\\s*");
            addCommandClass(configPair);
        }
    }

    private void addCommandClass(String[] configPair) throws ConfigurationException {
        if (configPair.length != 2)
            throw new ConfigurationException("Bad format for config file. Expected format:\n"
                    + "<command name>=<name of the corresponding command class.");

        try {
            commandClasses.put(configPair[0], (Class<Command>) Class.forName(configPair[1]));
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new ConfigurationException("Invalid class for a command: " + configPair[1]);
        }
    }
}
