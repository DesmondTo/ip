package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** A task class which falls under deadline category. */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * A constructor for class Deadline.
     *
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Return a string representation in the format to be written in tasks.txt file.
     *
     * @return The string representation in the format to be written in tasks.txt file.
     */
    @Override
    public String toDataString() {
        return String.format("DEADLINE %s | %s", super.toDataString(), this.by);
    }

    /**
     * Return a string representation of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}