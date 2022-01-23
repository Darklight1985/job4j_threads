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

    public String getContentWithoutUnicode() throws IOException {
        String output = "";
        int data;
        try (BufferedInputStream buff= new BufferedInputStream(new FileInputStream(file))) {
            while ((data = buff.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        }
        return output;
    }
}
