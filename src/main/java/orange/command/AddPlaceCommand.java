package orange.command;

import orange.storage.Storage;
import orange.task.Place;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Command to add a place/restaurant.
 */
public class AddPlaceCommand extends Command {
    private String description;
    private String location;
    private int rating;
    private String notes;

    /**
     * Constructs an AddPlaceCommand.
     *
     * @param description place name
     * @param location address
     * @param rating rating (1-5)
     * @param notes additional notes
     */
    public AddPlaceCommand(String description, String location, int rating, String notes) {
        this.description = description;
        this.location = location;
        this.rating = rating;
        this.notes = notes;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (rating < 1 || rating > 5) {
            throw new Exception("Rating must be between 1 and 5");
        }

        Place place = new Place(description, location, rating, notes);
        tasks.addTask(place);
        storage.save(tasks.getTasks());

        String postalWarning = place.hasValidPostalCode()
                ? ""
                : "\n  💡 Tip: Include a 6-digit postal code for easier reference!";

        return "Got it. I've added this place:\n  " + place + "\n"
                + "Now you have " + tasks.getSize() + " items in the list."
                + postalWarning;
    }
}