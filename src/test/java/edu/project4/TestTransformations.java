package edu.project4;

import edu.project4.model.Point;
import edu.project4.transformations.CosineTransformation;
import edu.project4.transformations.FishEyeTransformation;
import edu.project4.transformations.HandkerchiefTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.PolarTransformation;
import edu.project4.transformations.SinusoidalTransformation;
import edu.project4.transformations.SphereTransformation;
import edu.project4.transformations.SwirlTransformation;
import edu.project4.transformations.TangentTransformation;
import edu.project4.transformations.Transformation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestTransformations {
    private final static Point POINT = new Point(0, 1);
    private static Stream<Arguments> testPoints() {
        return Stream.of(
            Arguments.of(new Point(0, 0)),
            Arguments.of(new Point(130, 3928)),
            Arguments.of(new Point(21301, -39381)),
            Arguments.of(new Point(-1389, 3198)),
            Arguments.of(new Point(-983248923, -9824))
        );
    }
    @ParameterizedTest
    @MethodSource("testPoints")
    void cosineTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new CosineTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }

    @ParameterizedTest
    @MethodSource("testPoints")
    void fishEyeTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new FishEyeTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }

    @ParameterizedTest
    @MethodSource("testPoints")
    void handkerchief_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new HandkerchiefTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }

    @ParameterizedTest
    @MethodSource("testPoints")
    void linearTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new LinearTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }

    @ParameterizedTest
    @MethodSource("testPoints")
    void polarTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new PolarTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }
    @ParameterizedTest
    @MethodSource("testPoints")
    void sinusoidalTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new SinusoidalTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }

    @ParameterizedTest
    @MethodSource("testPoints")
    void sphereTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new SphereTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }

    @ParameterizedTest
    @MethodSource("testPoints")
    void swirlTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new SwirlTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }

    @ParameterizedTest
    @MethodSource("testPoints")
    void tangentTransformation_ShouldNotThrowAnyExceptions() {
        Transformation transformation = new TangentTransformation();
        assertDoesNotThrow(() -> transformation.apply(POINT));
    }



}
