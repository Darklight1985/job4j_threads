package ru.job4j.io;

import java.io.IOException;
import java.util.function.Predicate;

public interface ContentStore {
    public String getContent() throws IOException;
}
