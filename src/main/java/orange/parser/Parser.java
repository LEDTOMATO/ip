package orange.parser;

import orange.command.*;

/**
 * Parses user input into commands.
 */
public class Parser {
    private static final int COMMAND_INDEX = 0;
    private static final int ARGUMENTS_INDEX = 1;
    private static final int MIN_PARTS_WITH_ARGS = 2;
    private static final int EXPECTED_DEADLINE_PARTS = 2;
    private static final int EXPECTED_EVENT_PARTS = 3;

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param fullCommand the full user input string
     * @return the corresponding Command object
     * @throws Exception if the command is invalid
     */
    public static Command parse(String fullCommand) throws Exception {
        validateInput(fullCommand);

        String[] parts = splitCommand(fullCommand);
        String commandWord = extractCommandWord(parts);

        return createCommand(commandWord, parts);
    }

    /**
     * Validates that the input command is not null or empty.
     *
     * @param fullCommand the command to validate
     * @throws Exception if command is null or empty
     */
    private static void validateInput(String fullCommand) throws Exception {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new Exception("Command cannot be empty.");
        }
    }

    /**
     * Splits the command into command word and arguments.
     *
     * @param fullCommand the full command string
     * @return array with command word and arguments
     */
    private static String[] splitCommand(String fullCommand) {
        return fullCommand.trim().split(" ", 2);
    }

    /**
     * Extracts and normalizes the command word.
     *
     * @param parts the split command parts
     * @return the command word in lowercase
     */
    private static String extractCommandWord(String[] parts) {
        return parts[COMMAND_INDEX].toLowerCase();
    }

    /**
     * Creates the appropriate Command object based on command word.
     *
     * @param commandWord the command word
     * @param parts the split command parts
     * @return the corresponding Command object
     * @throws Exception if command is invalid
     */
    private static Command createCommand(String commandWord, String[] parts) throws Exception {
        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return createMarkCommand(parts);

        case "unmark":
            return createUnmarkCommand(parts);

        case "todo":
            return createTodoCommand(parts);

        case "deadline":
            return parseDeadline(parts);

        case "event":
            return parseEvent(parts);

        case "delete":
            return createDeleteCommand(parts);

        case "find":
            return createFindCommand(parts);

        case "cheer":
            return new CheerCommand();

        default:
            throw new Exception("What do you mean? 🎵");
        }
    }

    /**
     * Creates a MarkCommand from the parsed input.
     *
     * @param parts the split command parts
     * @return MarkCommand
     * @throws Exception if task number is missing
     */
    private static Command createMarkCommand(String[] parts) throws Exception {
        validateHasArguments(parts, "Please specify the task number to mark.");
        int taskNumber = parseTaskNumber(parts[ARGUMENTS_INDEX]);
        return new MarkCommand(taskNumber);
    }

    /**
     * Creates an UnmarkCommand from the parsed input.
     *
     * @param parts the split command parts
     * @return UnmarkCommand
     * @throws Exception if task number is missing
     */
    private static Command createUnmarkCommand(String[] parts) throws Exception {
        validateHasArguments(parts, "Please specify the task number to unmark.");
        int taskNumber = parseTaskNumber(parts[ARGUMENTS_INDEX]);
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Creates an AddTodoCommand from the parsed input.
     *
     * @param parts the split command parts
     * @return AddTodoCommand
     * @throws Exception if description is missing
     */
    private static Command createTodoCommand(String[] parts) throws Exception {
        validateHasArguments(parts, "Write details for todo task pls.");
        String description = parts[ARGUMENTS_INDEX].trim();
        return new AddTodoCommand(description);
    }

    /**
     * Creates a DeleteCommand from the parsed input.
     *
     * @param parts the split command parts
     * @return DeleteCommand
     * @throws Exception if task number is missing
     */
    private static Command createDeleteCommand(String[] parts) throws Exception {
        validateHasArguments(parts, "Please specify the task number to delete.");
        int taskNumber = parseTaskNumber(parts[ARGUMENTS_INDEX]);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Creates a FindCommand from the parsed input.
     *
     * @param parts the split command parts
     * @return FindCommand
     * @throws Exception if keyword is missing
     */
    private static Command createFindCommand(String[] parts) throws Exception {
        validateHasArguments(parts, "Please specify a keyword to search for.");
        String keyword = parts[ARGUMENTS_INDEX].trim();
        return new FindCommand(keyword);
    }

    /**
     * Parses a deadline command.
     *
     * @param parts the split command parts
     * @return AddDeadlineCommand
     * @throws Exception if format is invalid
     */
    private static Command parseDeadline(String[] parts) throws Exception {
        validateHasArguments(parts, "Write details for deadline task pls.");

        String[] deadlineParts = parts[ARGUMENTS_INDEX].split(" /by ");
        validateDeadlineParts(deadlineParts);

        String description = deadlineParts[0].trim();
        String deadline = deadlineParts[1].trim();

        return new AddDeadlineCommand(description, deadline);
    }

    /**
     * Parses an event command.
     *
     * @param parts the split command parts
     * @return AddEventCommand
     * @throws Exception if format is invalid
     */
    private static Command parseEvent(String[] parts) throws Exception {
        validateHasArguments(parts, "Write details for event task pls.");

        String[] eventParts = parts[ARGUMENTS_INDEX].split(" /from | /to ");
        validateEventParts(eventParts);

        String description = eventParts[0].trim();
        String startDate = eventParts[1].trim();
        String endDate = eventParts[2].trim();

        return new AddEventCommand(description, startDate, endDate);
    }

    /**
     * Validates that command has arguments.
     *
     * @param parts the split command parts
     * @param errorMessage the error message to throw if invalid
     * @throws Exception if no arguments provided
     */
    private static void validateHasArguments(String[] parts, String errorMessage) throws Exception {
        if (parts.length < MIN_PARTS_WITH_ARGS || parts[ARGUMENTS_INDEX].trim().isEmpty()) {
            throw new Exception(errorMessage);
        }
    }

    /**
     * Validates that deadline parts are correctly formatted.
     *
     * @param deadlineParts the split deadline parts
     * @throws Exception if format is invalid
     */
    private static void validateDeadlineParts(String[] deadlineParts) throws Exception {
        if (deadlineParts.length < EXPECTED_DEADLINE_PARTS) {
            throw new Exception("Please use format: deadline [description] /by yyyy-MM-dd");
        }
    }

    /**
     * Validates that event parts are correctly formatted.
     *
     * @param eventParts the split event parts
     * @throws Exception if format is invalid
     */
    private static void validateEventParts(String[] eventParts) throws Exception {
        if (eventParts.length < EXPECTED_EVENT_PARTS) {
            throw new Exception("Please use format: event [description] /from yyyy-MM-dd /to yyyy-MM-dd");
        }
    }

    /**
     * Parses a string to an integer task number.
     *
     * @param taskNumberString the string to parse
     * @return the parsed task number
     * @throws Exception if parsing fails
     */
    private static int parseTaskNumber(String taskNumberString) throws Exception {
        try {
            return Integer.parseInt(taskNumberString.trim());
        } catch (NumberFormatException e) {
            throw new Exception("Invalid task number format.");
        }
    }
}