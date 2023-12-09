package edu.hw7.task3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestPersonDatabase {

    private static Stream<Arguments> test() {
        return Stream.of(
            Arguments.of(new SynchronizedPersonDatabase(), 1),
            Arguments.of(new SynchronizedPersonDatabase(), 3),
            Arguments.of(new SynchronizedPersonDatabase(), 5),
            Arguments.of(new ReadWriteLockPersonDatabase(), 1),
            Arguments.of(new ReadWriteLockPersonDatabase(), 2),
            Arguments.of(new ReadWriteLockPersonDatabase(), 7)
        );
    }

    @ParameterizedTest
    @MethodSource
    void test(AbstractPersonDatabase personDatabase, int numberOfThreads)
        throws InterruptedException, ExecutionException {
        final int delay = 50;
        final int id = 1;
        final String name = "Dmitry";
        final String address = "Moscow";
        final String phone = "88005553535";
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Person person = new Person(id, name, address, phone);
        executorService.execute(() -> personDatabase.add(person));
        Thread.sleep(delay);
        Future<List<Person>> futureByName = executorService.submit(() -> personDatabase.findByName(name));
        Future<List<Person>> futureByAddress = executorService.submit(() -> personDatabase.findByAddress(address));
        Future<List<Person>> futureByPhone = executorService.submit(() -> personDatabase.findByPhone(phone));
        executorService.shutdown();
        assertThat(futureByName.get()).containsExactly(person);
        assertThat(futureByAddress.get()).containsExactly(person);
        assertThat(futureByPhone.get()).containsExactly(person);

        executorService = Executors.newFixedThreadPool(numberOfThreads);
        executorService.execute(() -> personDatabase.delete(id));
        Thread.sleep(delay);
        futureByName = executorService.submit(() -> personDatabase.findByName(name));
        futureByAddress = executorService.submit(() -> personDatabase.findByAddress(address));
        futureByPhone = executorService.submit(() -> personDatabase.findByPhone(phone));
        executorService.shutdown();
        assertThat(futureByName.get().isEmpty()).isTrue();
        assertThat(futureByAddress.get().isEmpty()).isTrue();
        assertThat(futureByPhone.get().isEmpty()).isTrue();
    }
}
