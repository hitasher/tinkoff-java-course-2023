package hw11;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {
    @Test
    @SneakyThrows
    public void testBehaviourChange() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(Delegate.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();
        int actualSum = ArithmeticUtils.sum(1, 1);
        assertThat(actualSum).isEqualTo(1);
    }

    private static final class Delegate {
        public static int mul(int a, int b) {
            return a * b;
        }
    }

    public static class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }
}
