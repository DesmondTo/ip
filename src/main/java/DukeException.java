import java.io.IOException;

public class DukeException extends IOException {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", super.getMessage());
    }
}
