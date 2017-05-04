package model.protocol;

/**
 * A command that could be sent over the communication link
 */
public abstract class AbstractCommand {
    protected String arg;

    public AbstractCommand(String arg) {
        this.arg = arg;
    }

    public abstract String execute();
}