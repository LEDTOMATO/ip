package orange.task;

/**
 * Represents a place with location, rating, and notes.
 */
public class Place extends Task {
    private String location;
    private int rating;
    private String notes;

    /**
     * Constructs a Place with description, location, rating, and notes.
     *
     * @param description place name
     * @param location address or area
     * @param rating rating from 1-5
     * @param notes additional notes
     */
    public Place(String description, String location, int rating, String notes) {
        super(description, TaskType.PLACE);
        this.location = location;
        this.rating = rating;
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public int getRating() {
        return rating;
    }

    public String getNotes() {
        return notes;
    }

    /**
     * Checks if location contains a valid Singapore postal code.
     *
     * @return true if contains valid 6-digit postal code, false otherwise
     */
    public boolean hasValidPostalCode() {
        // Singapore postal codes are exactly 6 digits
        return location.matches(".*\\b\\d{6}\\b.*");
    }

    @Override
    public String toString() {
        String stars = "★".repeat(rating) + "☆".repeat(5 - rating);
        String postalIndicator = hasValidPostalCode() ? " 📍" : "";
        return super.toString() + " | " + location + postalIndicator + " | " + stars
                + (notes.isEmpty() ? "" : " | " + notes);
    }

    @Override
    public String toFileString() {
        return "P | " + (isDone ? "1" : "0") + " | " + description
                + " | " + location + " | " + rating + " | " + notes;
    }
}