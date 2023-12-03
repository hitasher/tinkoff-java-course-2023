package edu.hw8.task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;

public class SingleThreadPasswordSearcher extends AbstractPasswordSearcher {
    private final Map<String, String> passwordHashToUser;
    private final Map<String, String> userToPassword;

    public SingleThreadPasswordSearcher(Map<String, String> passwordHashToUser) {
        this.passwordHashToUser = passwordHashToUser;
        this.userToPassword = new HashMap<>();
    }

    @Override
    public Map<String, String> search() {
        while (!passwordHashToUser.isEmpty()) {
            for (int passwordLength = MIN_PASSWORD_LENGTH; passwordLength <= MAX_PASSWORD_LENGTH; ++passwordLength) {
                iterateOverAllPossiblePasswords(passwordLength);
            }
        }
        return userToPassword;
    }

    private void iterateOverAllPossiblePasswords(int wordLength) {
        int[] indexOfEachCharacter = new int[wordLength];
        Arrays.fill(indexOfEachCharacter, 0);
        while (!passwordHashToUser.isEmpty()) {
            StringBuilder password = new StringBuilder();
            for (Integer integer : indexOfEachCharacter) {
                password.append(ALPHABET_CHARACTERS.get(integer));
            }
            String generatedPassword = password.toString();
            processPassword(generatedPassword);
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
        }
    }
}
