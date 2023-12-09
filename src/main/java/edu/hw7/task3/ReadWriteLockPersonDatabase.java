package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDatabase extends AbstractPersonDatabase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            super.add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public synchronized void delete(int id) {
        lock.writeLock().lock();
        try {
            super.delete(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return super.findByName(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return super.findByAddress(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return super.findByPhone(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
