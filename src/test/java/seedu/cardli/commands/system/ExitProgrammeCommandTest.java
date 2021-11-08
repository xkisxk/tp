package seedu.cardli.commands.system;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitProgrammeCommandTest {
    @Test
    public void execute_argument_expectExtraArgumentMessage() {
        String input = "bye hello world";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new ExitProgrammeCommand(arguments);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("There should not be any arguments.", output);
    }
}
