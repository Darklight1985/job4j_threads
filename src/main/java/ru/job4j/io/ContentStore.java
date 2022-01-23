package ru.job4j.io;

import java.io.IOException;
import java.util.function.Predicate;

public interface ContentStore {
    public String getContent() throws IOException;

    public String getContentWithoutUnicode(Predicate<Integer> filter) throws IOException;

    public void saveContent(String content) throws IOException;
}
