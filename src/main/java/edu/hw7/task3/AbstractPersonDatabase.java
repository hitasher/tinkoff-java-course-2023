package edu.hw7.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> idToPerson = new HashMap<>();
    private final Map<String, List<Person>> nameToPersons = new HashMap<>();
    private final Map<String, List<Person>> addressToPersons = new HashMap<>();
    private final Map<String, List<Person>> phoneToPersons = new HashMap<>();

    @Override
    public void add(Person person) {
        idToPerson.put(person.id(), person);
        nameToPersons.computeIfAbsent(person.name(), i -> new ArrayList<>()).add(person);
        addressToPersons.computeIfAbsent(person.address(), i -> new ArrayList<>()).add(person);
        phoneToPersons.computeIfAbsent(person.phoneNumber(), i -> new ArrayList<>()).add(person);
    }

    @Override
    public void delete(int id) {
        Person deletedPerson = idToPerson.remove(id);
        if (deletedPerson == null) {
            return;
        }
        nameToPersons.get(deletedPerson.name()).remove(deletedPerson);
        addressToPersons.get(deletedPerson.address()).remove(deletedPerson);
        phoneToPersons.get(deletedPerson.phoneNumber()).remove(deletedPerson);
    }

    @Override
    public List<Person> findByName(String name) {
        return nameToPersons.getOrDefault(name, Collections.emptyList());
    }

    @Override
    public List<Person> findByAddress(String address) {
        return addressToPersons.getOrDefault(address, Collections.emptyList());
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return phoneToPersons.getOrDefault(phone, Collections.emptyList());
    }
}
