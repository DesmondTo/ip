package duke.exception;

import java.io.IOException;

/** An exception that handles the possible exceptions thrown when the program runs. */
public class DukeException extends IOException {

    /**
     * A constructor for class DukeException.
     *
     * @param message The message to be displayed when this exception is caught.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Return a string representation of this exception.
     *
     * @return The string representation of this exception.
     */
    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", super.getMessage());
    }
}
