package hw11;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask1 {
    @SneakyThrows
    @Test
    public void testHelloByteBuddy() {
        var dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        String s = dynamicType.getDeclaredConstructor().newInstance().toString();
        assertThat(s).isEqualTo("Hello, ByteBuddy!");
    }
}
