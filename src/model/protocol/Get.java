package model.protocol;

/**
 *
 */
public class Get extends AbstractCommand {
    public Get(String arg) {
        super(arg);
    }

    @Override
    public String execute() {
        return arg + " Gotten\n";
    }
}