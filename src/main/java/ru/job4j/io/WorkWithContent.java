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
        String output = "";
        int data;
        try (BufferedInputStream buff =
                     new BufferedInputStream(new FileInputStream(parseFile.getFile()))) {
            while ((data = buff.read()) > 0) {
                if (filter.test((char) data)) {
                    output += data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public void saveContent(String content) throws IOException {
        try (BufferedOutputStream buff =
                     new BufferedOutputStream(new FileOutputStream(parseFile.getFile()))) {
            for (int i = 0; i < content.length(); i += 1) {
                buff.write(content.charAt(i));
            }
        }
    }
}
