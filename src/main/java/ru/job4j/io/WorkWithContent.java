package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class WorkWithContent implements ContentStore {
    private final ParseFile parseFile;

    public WorkWithContent(ParseFile parseFile) {
        this.parseFile = parseFile;
    }

    @Override
    public String getContent() {
        Predicate<Character> filter = s -> s == s;
        return workContent(filter);
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
}
