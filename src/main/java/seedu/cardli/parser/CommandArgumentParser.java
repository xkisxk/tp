package seedu.cardli.parser;

/**
 * Interface to parse arguments for commands.
 */
public interface CommandArgumentParser {

    /**
     * Parses the given String arguments into a String[].
     * @param arguments the given String arguments to be parsed
     * @return the split parameters to be used in the command
     */
    String[] parseArguments(String arguments);
}
