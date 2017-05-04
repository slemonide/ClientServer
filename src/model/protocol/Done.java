package model.protocol;

/**
 *
 */
public class Done extends AbstractCommand {
    public Done(String arg) {
        super(arg);
    }

    @Override
    public String execute() {
        return "All done.\n";
    }
}
