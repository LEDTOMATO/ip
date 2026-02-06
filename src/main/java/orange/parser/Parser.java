package orange.parser;

import orange.command.*;

/**
 * Parses user input into commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param fullCommand the full user input string
     * @return the corresponding Command object
     * @throws Exception if the command is invalid
     */
    public static Command parse(String fullCommand) throws Exception {
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if (parts.length < 2) {
                    throw new Exception("Please specify the task number to mark.");
                }
                return new MarkCommand(Integer.parseInt(parts[1]));

            case "unmark":
                if (parts.length < 2) {
                    throw new Exception("Please specify the task number to unmark.");
                }
                return new UnmarkCommand(Integer.parseInt(parts[1]));

            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new Exception("Write details for todo task pls.");
                }
                return new AddTodoCommand(parts[1].trim());

            case "deadline":
                return parseDeadline(parts);

            case "event":
                return parseEvent(parts);

            case "delete":
                if (parts.length < 2) {
                    throw new Exception("Please specify the task number to delete.");
                }
                return new DeleteCommand(Integer.parseInt(parts[1]));

            case "find":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new Exception("Please specify a keyword to search for.");
                }
                return new FindCommand(parts[1].trim());

            default:
                throw new Exception("What do you mean? 🎵");
        }
    }

    /**
     * Parses a deadline command.
     *
     * @param parts the split command parts
     * @return AddDeadlineCommand
     * @throws Exception if format is invalid
     */
    private static Command parseDeadline(String[] parts) throws Exception {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new Exception("Write details for deadline task pls.");
        }

        String[] deadlineParts = parts[1].split(" /by ");
        if (deadlineParts.length < 2) {
            throw new Exception("Please use format: deadline [description] /by yyyy-MM-dd");
        }

        return new AddDeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
    }

    /**
     * Parses an event command.
     *
     * @param parts the split command parts
     * @return AddEventCommand
     * @throws Exception if format is invalid
     */
    private static Command parseEvent(String[] parts) throws Exception {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new Exception("Write details for event task pls.");
        }

        String[] eventParts = parts[1].split(" /from | /to ");
        if (eventParts.length < 3) {
            throw new Exception("Please use format: event [description] /from yyyy-MM-dd /to yyyy-MM-dd");
        }

        return new AddEventCommand(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
    }
}