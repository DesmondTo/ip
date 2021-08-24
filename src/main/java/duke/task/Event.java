package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** A task class which falls under deadline category. */
public class Event extends Task {

    protected LocalDate at;

    /**
     * A constructor for class Deadline.
     *
     * @param description The task description.
     * @param at The date of the task being carried out.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Return a string representation in the format to be written in tasks.txt file.
     *
     * @return The string representation in the format to be written in tasks.txt file.
     */
    @Override
    public String toDataString() {
        return String.format("EVENT %s | %s", super.toDataString(), this.at);
    }

    /**
     * Return a string representation of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}