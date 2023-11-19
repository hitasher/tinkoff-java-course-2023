package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

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
        int counter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readBlock(bufferedReader, line);
                readBlock(bufferedReader, bufferedReader.readLine());
                ++counter;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return counter;
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String blockKey = readBlock(bufferedReader, line);
                if (blockKey.equals(key)) {
                    return true;
                }
                readBlock(bufferedReader, bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readBlock(bufferedReader, line);
                String blockValue = readBlock(bufferedReader, bufferedReader.readLine());
                if (blockValue.equals(value)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String get(Object key) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String blockKey = readBlock(bufferedReader, line);
                String blockValue = readBlock(bufferedReader, bufferedReader.readLine());
                if (blockKey.equals(key)) {
                    return blockValue;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Nullable
    @Override
    public String put(@NotNull String key, @NotNull String value) {
        String oldValue = get(key);
        if (oldValue != null) {
            remove(key);
        }
        long keySize = key.lines().count();
        if (key.endsWith("\n")) {
            ++keySize;
        }
        writeLineToTheEndOfFile(String.valueOf(keySize));
        writeLineToTheEndOfFile(key);
        long valueSize = value.lines().count();
        if (value.endsWith("\n")) {
            ++valueSize;
        }
        writeLineToTheEndOfFile(String.valueOf(valueSize));
        writeLineToTheEndOfFile(value);
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
        Set<String> keys = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                keys.add(readBlock(bufferedReader, line));
                readBlock(bufferedReader, bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return keys;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        List<String> values = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readBlock(bufferedReader, line);
                values.add(readBlock(bufferedReader, bufferedReader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> entries = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String key = readBlock(bufferedReader, line);
                String value = readBlock(bufferedReader, bufferedReader.readLine());
                entries.add(Map.entry(key, value));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entries;
    }

    private String readBlock(BufferedReader bufferedReader, String line) throws IOException {
        long blockSize = Long.parseLong(line);
        StringBuilder blockBuilder = new StringBuilder();
        for (int i = 0; i < blockSize - 1; ++i) {
            blockBuilder.append(bufferedReader.readLine()).append('\n');
        }
        blockBuilder.append(bufferedReader.readLine());
        return blockBuilder.toString();
    }

    private void writeLineToTheEndOfFile(@NotNull String string) {
        try {
            Files.writeString(path, string + "\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
