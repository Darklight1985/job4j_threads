package ru.job4j.io;
import java.io.*;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return new File(file.getName());
    }
}
