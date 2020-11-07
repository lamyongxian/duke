package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

public class FindCommand extends Command{

    public FindCommand(String[] args) {
        super(args);
    }

    @Override
    public CommandType getType() {
        return CommandType.FIND;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {

        if (super.args == null || super.args.length < 2) { // at least 1 command argument, else error
            throw new DukeException("Must provide keyword for find command leh!",
                    DukeException.DukeError.MISSING_ARGUMENT);
        }

        String keyword = String.valueOf(super.args[1]);

        List<Task> tasks = taskManager.findTasks(keyword);

        ui.echo("OK, this is what I found:");
        int i = 0;
        for (Task t: tasks) {
            ui.echo(String.format("%d.%s", ++i, t.toString()));
        }

        return true;
    }
}