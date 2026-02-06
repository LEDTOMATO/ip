package orange.task;

/**
 * Represents the type of a task.
 */
public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String symbol;

    /**
     * Constructs a TaskType with the given symbol.
     *
     * @param symbol the symbol representing the task type
     */
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the symbol of the task type.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }
}