package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Contact(@NotNull String firstName, String lastName) implements Comparable<Contact> {
    public Contact(String firstName) {
        this(firstName, null);
    }

    @Override
    public int compareTo(@NotNull Contact otherContact) {
        if (lastName == null && otherContact.lastName == null) {
            return firstName.compareTo(otherContact.firstName);
        } else if (lastName == null) {
            return -1;
        } else if (otherContact.lastName == null) {
            return 1;
        }
        return lastName.compareTo(otherContact.lastName);
    }
}
