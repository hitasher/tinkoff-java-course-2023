package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private static final String DELIMITER = ":";
    private final Path path;

    public DiskMap(@NotNull Path path) {
        this.path = path;
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("File with provided path can't be created.");
        }
    }

    @Override
    public int size() {
        try {
            return Files.readAllLines(path).size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            return bufferedReader.readLine() == null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsKey(Object key) {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.anyMatch(line -> line.split(DELIMITER)[0].equals(key));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.anyMatch(line -> line.split(DELIMITER)[1].equals(value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(Object key) {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                .map(line -> line.split(DELIMITER))
                .filter(pair -> pair.length > 1 && pair[0].equals(key))
                .findFirst()
                .map(pair -> pair[1])
                .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @Override
    public String put(@NotNull String key, @NotNull String value) {
        String oldValue = get(key);
        if (oldValue != null) {
            remove(key);
        }
        String line = key + DELIMITER + value + "\n";
        try {
            Files.writeString(path, line, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        Set<Entry<String, String>> entries = entrySet();
        String value = get(key);
        if (value == null) {
            return null;
        }
        clear();
        entries.stream()
            .filter(entry -> !entry.getKey().equals(key))
            .forEach(entry -> put(entry.getKey(), entry.getValue()));
        return value;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        try {
            new FileOutputStream(path.toFile()).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                .map(line -> line.split(DELIMITER))
                .filter(pair -> pair.length > 0)
                .map(pair -> pair[0])
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Collection<String> values() {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                .map(line -> line.split(DELIMITER))
                .filter(pair -> pair.length > 1)
                .map(pair -> pair[1])
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                .map(line -> line.split(DELIMITER))
                .filter(pair -> pair.length > 1)
                .map(pair -> Map.entry(pair[0], pair[1]))
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
