package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ContactParser {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Pattern LONG_NAME_PATTERN = Pattern.compile("^[A-Z][a-z]*\\s[A-Z][a-z]*$");
    private final static Pattern SHORT_NAME_PATTERN = Pattern.compile("^[A-Z][a-z]*$");

    private ContactParser() {
    }

    public static ArrayList<Contact> parseContacts(List<String> names, String order) {
        Objects.requireNonNull(order);
        LOGGER.trace("Parsing {}", names);
        ArrayList<Contact> contacts = new ArrayList<>();
        if (names == null) {
            return contacts;
        }
        for (final String name : names) {
            if (isNameLong(name)) {
                LOGGER.trace("Name \"{}\" matches long name pattern ", name);
                String firstName = name.substring(0, name.indexOf(' '));
                String lastName = name.substring(name.indexOf(' ') + 1);
                contacts.add(new Contact(firstName, lastName));
            } else if (isNameShort(name)) {
                LOGGER.trace("Name \"{}\" matches short name pattern ", name);
                contacts.add(new Contact(name));
            } else {
                throw new IllegalArgumentException(String.format("Provided name \"%s\" is invalid", name));
            }
        }
        if (Objects.equals(order, "ASC")) {
            contacts.sort(Comparator.naturalOrder());
        } else if (Objects.equals(order, "DESC")) {
            contacts.sort(Comparator.reverseOrder());
        } else {
            throw new IllegalArgumentException("Provided order is invalid");
        }
        return contacts;
    }

    private static boolean isNameLong(String name) {
        Matcher matcher = LONG_NAME_PATTERN.matcher(name);
        return matcher.matches();
    }

    private static boolean isNameShort(String name) {
        Matcher matcher = SHORT_NAME_PATTERN.matcher(name);
        return matcher.matches();
    }
}
