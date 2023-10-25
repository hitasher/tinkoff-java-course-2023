package edu.hw2.task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.function.Supplier;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestCallingInfo {

    private static CallingInfo method1() {
        return CallingInfo.callingInfo();
    }

    private static CallingInfo method2() {
        return method1();
    }

    private static CallingInfo method3() {
        return method2();
    }

    private static Stream<Supplier<CallingInfo>> testCallingInfo() {
        return Stream.of(
            TestCallingInfo::method1,
            TestCallingInfo::method2,
            TestCallingInfo::method3
        );
    }

    @ParameterizedTest
    @MethodSource
    void testCallingInfo(Supplier<CallingInfo> supplier) {
        assertThat(supplier.get())
            .extracting("methodName", "className")
            .containsExactly("method1", "edu.hw2.task4.TestCallingInfo");
    }

}
