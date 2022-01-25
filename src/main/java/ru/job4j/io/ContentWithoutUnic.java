package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ContentWithoutUnic implements ContentStore {
    private final ParseFile parseFile;

    public ContentWithoutUnic(ParseFile parseFile) {
        this.parseFile = parseFile;
    }

    public String workContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        int data;
        try (BufferedInputStream buff =
                     new BufferedInputStream(new FileInputStream(parseFile.getFile()))) {
            while ((data = buff.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    @Override
    public String getContent() {
        Predicate<Character> filter = s -> s < 0x80;
        return workContent(filter);
    }
}
