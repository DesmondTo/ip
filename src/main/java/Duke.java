import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Initialize string array to store the list
    private static final ArrayList<Task> TASKS = new ArrayList<>();
    private static int numOfTask = 0;

    enum Command {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE
    }

    enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private static void printAddOrDelete(boolean isAdd, Task task, int numOfTask) {
        Printer.prettyPrint(String.format("%s. I've %s this task:\n\t %s\n\tNow you have %d tasks in the list.",
                isAdd ? "Got it" : "Noted",
                isAdd ? "added" : "deleted",
                task.toString(),
                numOfTask));
    }

    private static String[] extractCommand(String[] command) throws EmptyDescriptionException, IncompleteDescriptionException {
        if (command.length < 2 || command[1].trim().isEmpty())
            throw new EmptyDescriptionException(String.format("The description of a %s cannot be empty.", command[0]));
        String[] description = command[1].split(" /by | /at ", 2);
        if (!command[0].equals("todo") &&
                (description.length < 2 || description[0].trim().isEmpty() || description[1].trim().isEmpty()))
            throw new IncompleteDescriptionException(String.format("The description of a %s is incomplete.", command[0]));
        return description;
    }

    private static void addThenPrint(String[] command, ArrayList<Task> tasks, int numOfTask) {
        try {
            String[] descriptions = extractCommand(command);
            Task task = null;

            switch (TaskType.valueOf(command[0].toUpperCase())) {
            case TODO:
                task = new Todo(descriptions[0]);
                break;
            case EVENT:
                task = new Event(descriptions[0], descriptions[1]);
                break;
            case DEADLINE:
                task = new Deadline(descriptions[0], descriptions[1]);
                break;
            }

            if (task != null) {
                TASKS.add(task);
                printAddOrDelete(true, task, ++numOfTask);
            }
        } catch (EmptyDescriptionException | IncompleteDescriptionException e) {
            Printer.prettyPrint(e.toString());
        }
    }

    public static void main(String[] args) {
        // Greet the user
        Printer.prettyPrint("Welcome to\n" +
                Printer.logo +
                "\tI'm Desmond,\n" +
                "\thow may I serve you?\n");

        // Initialize scanner to get user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Execute based on command (user input)
        // Exit when user commands "bye"
        while (!input.equals("bye")) {
            String[] command = input.split(" ", 2);
            try {
                switch (Command.valueOf(command[0].toUpperCase())) {
                case LIST:
                    Printer.prettyPrint("Here are the tasks in your list:\n" +
                            Printer.listTask(TASKS, numOfTask));
                    break;
                case DONE:
                    TASKS.get(Integer.parseInt(command[1]) - 1).markAsDone();
                     break;
                case DELETE:
                    printAddOrDelete(false, TASKS.get(Integer.parseInt(command[1]) - 1), --numOfTask);
                    TASKS.remove(Integer.parseInt(command[1]) - 1);
                    break;
                case TODO:
                    // Fallthrough
                case EVENT:
                    // Fallthrough
                case DEADLINE:
                    addThenPrint(command, TASKS, numOfTask++);
                    break;
                default:
                    throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (UnknownCommandException e) {
                Printer.prettyPrint(e.toString());
            }
            input = scanner.nextLine();
        }
        Printer.prettyPrint("Bye (*´▽｀)ノシ. Have a good day!\n");
    }
}
