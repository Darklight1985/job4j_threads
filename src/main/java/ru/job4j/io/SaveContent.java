package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveContent {
    private final ParseFile parseFile;

    public SaveContent(ParseFile parseFile) {
        this.parseFile = parseFile;
    }

    public void saveContent(String content) throws IOException {
        try (BufferedOutputStream buff =
                     new BufferedOutputStream(new FileOutputStream(parseFile.getFile()))) {
            for (int i = 0; i < content.length(); i += 1) {
                buff.write(content.charAt(i));
            }
        }
    }
}
