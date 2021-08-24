package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** A class that handles task-listing command. */
public class ListCommand extends Command{

    /**
     * Execute the task-listing command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        int currentIndex = 1;
        for (Task task : tasks.getTasks()) {
            if (task != null)
                result.append("\n\t ")
                        .append(currentIndex)
                        .append(".")
                        .append(task);
            currentIndex ++;
        }
        System.out.println("\tHere are the tasks in your list:" + result);
    }

    /**
     * Return a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
