package hw11;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Modifier;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask3 {
    private final static String CLASS_NAME = "FibonacciCalculator";

    private final static String METHOD_NAME = "fibonacci";
    @SneakyThrows
    @Test
    public void shouldCreateNewClassWhichCanCalculateFibNumbers() {
        Class<?> fibonacciClass = createFibonacciClass();
        var method = fibonacciClass.getDeclaredMethod(METHOD_NAME, int.class);
        long calculatedNumber = (long) method.invoke(fibonacciClass.getDeclaredConstructor().newInstance(), 20);
        assertThat(calculatedNumber).isEqualTo(6765);
    }


    private Class<?> createFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(METHOD_NAME, long.class, Modifier.PUBLIC).withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public @NotNull ByteCodeAppender appender(@NotNull Target target) {
                    return (methodVisitor, context, methodDescription) -> {
                        Label l1 = new Label();
                        methodVisitor.visitCode();
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, l1);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.I2L);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitLabel(l1);
                        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_1);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, "(I)J");
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, "(I)J");
                        methodVisitor.visitInsn(Opcodes.LADD);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitEnd();
                        return new ByteCodeAppender.Size(5, 2);
                    };
                }

                @Override
                public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
    }
}
