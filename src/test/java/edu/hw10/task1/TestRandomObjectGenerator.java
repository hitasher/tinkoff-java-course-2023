package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestRandomObjectGenerator {

    private final static RandomObjectGenerator GENERATOR = new RandomObjectGenerator();

    @Test
    void testRecord() {
        User user = GENERATOR.nextObject(User.class);
        assertThat(user.name).isNotNull().hasSizeGreaterThanOrEqualTo(5);
        assertThat(user.age).isGreaterThan(17).isLessThan(80);
        assertThat(user.job).isNull();
    }

    @Test
    void testFactoryMethod() {
        Car car = GENERATOR.nextObject(Car.class, "createCar");
        assertThat(car.getName()).isNotNull();
        assertThat(car.getPrice()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1000000);
        assertThat(car.getMileage()).isGreaterThanOrEqualTo(0);
    }

    public record User(
        @NotNull @Min(5) @Max(10) String name,
        @Min(18) @Max(80) int age,
        String job,
        boolean isSuperuser
    ) {}

    @Getter
    public static class Car {
        private final String name;
        private final double price;
        private final long mileage;

        private Car(String name, double price, int mileage) {
            this.name = name;
            this.price = price;
            this.mileage = mileage;
        }

        public static Car createCar(@NotNull String name, @Min(0) @Max(1000000) double price, @Min(0) int mileage) {
            return new Car(name, price, mileage);
        }

    }

}
