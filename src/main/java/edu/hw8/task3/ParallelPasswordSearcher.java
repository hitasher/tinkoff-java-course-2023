package edu.hw8.task3;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

public class ParallelPasswordSearcher extends AbstractPasswordSearcher {

    private static final int WORDS_PER_THREAD = 3;
    private final ConcurrentMap<String, String> passwordHashToUser;
    private final ConcurrentMap<String, String> userToPassword;
    private final ExecutorService executorService;
    private final CountDownLatch latch;

    public ParallelPasswordSearcher(Map<String, String> passwordHashToUser) {
        this.passwordHashToUser = new ConcurrentHashMap<>(passwordHashToUser);
        this.userToPassword = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool();
        this.latch = new CountDownLatch(passwordHashToUser.size());
    }

    @Override
    @SneakyThrows
    public Map<String, String> search() {
        for (int passwordLength = MIN_PASSWORD_LENGTH; passwordLength <= MAX_PASSWORD_LENGTH; ++passwordLength) {
            for (int i = 0; i < ALPHABET.length(); i += WORDS_PER_THREAD) {
                final int wordLengthCopy = passwordLength;
                final int startIndex = i;
                executorService.execute(() -> iterateOverAllPossiblePasswords(wordLengthCopy, startIndex));
            }
        }
        executorService.shutdown();
        latch.await();
        return userToPassword;
    }

    private void iterateOverAllPossiblePasswords(int wordLength, int startIndex) {
        int[] indexOfEachCharacter = new int[wordLength];
        Arrays.fill(indexOfEachCharacter, startIndex);
        while (indexOfEachCharacter[0] != (startIndex - WORDS_PER_THREAD) && !passwordHashToUser.isEmpty()) {
            StringBuilder password = new StringBuilder();
            for (Integer integer : indexOfEachCharacter) {
                password.append(ALPHABET_CHARACTERS.get(integer));
            }
            String generatedPass = password.toString();
            processPassword(generatedPass);
            for (int i = indexOfEachCharacter.length - 1;; --i) {
                if (i < 0) {
                    return;
                }
                indexOfEachCharacter[i]++;
                if (indexOfEachCharacter[i] == ALPHABET_CHARACTERS.size()) {
                    indexOfEachCharacter[i] = 0;
                } else {
                    break;
                }
            }

        }
    }

    private void processPassword(String generatedPassword) {
        String md5 = DigestUtils.md5Hex(generatedPassword);
        if (passwordHashToUser.containsKey(md5)) {
            String user = passwordHashToUser.get(md5);
            userToPassword.put(user, generatedPassword);
            passwordHashToUser.remove(md5);
            latch.countDown();
        }
    }
}
