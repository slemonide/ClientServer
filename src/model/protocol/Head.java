package model.protocol;

/**
 *
 */
public class Head extends AbstractCommand {
    public Head(String arg) {
        super(arg);
    }

    @Override
    public String execute() {
        return "Head \"" + arg + "\" processed.\n";
    }
}
