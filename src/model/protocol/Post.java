package model.protocol;

/**
 *
 */
public class Post extends AbstractCommand {
    public Post(String arg) {
        super(arg);
    }

    @Override
    public String execute() {
        return arg + " Posted\n";
    }
}
