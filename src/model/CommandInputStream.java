package model;

import model.protocol.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents the communication link
 */
public class CommandInputStream extends DataInputStream {
    /**
     * Creates a DataInputStream that uses the specified
     * underlying InputStream.
     *
     * @param in the specified input stream
     */
    public CommandInputStream(InputStream in) {
        super(in);
    }

    public String readString() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        boolean hitSpace = false;
        while (!hitSpace) {
            char aChar = readChar();
            hitSpace = Character.isWhitespace(aChar);
            if (!hitSpace) {
                stringBuffer.append(aChar);
            }
        }

        return new String(stringBuffer);
    }

    public AbstractCommand readCommand() throws IOException {
        switch (readString()) {
            case "HEAD":
                return new Head(readString());
            case "GET":
                return new Get(readString());
            case "POST":
                return new Post(readString());
            case "DONE":
                return new Done(readString());
            default:
                throw new IOException("Unknown command.");
        }
    }
}
