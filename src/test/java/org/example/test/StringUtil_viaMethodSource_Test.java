package org.example.test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StringUtil_viaMethodSource_Test {



    @Nested
    class ExtractLastTrimmedTests {

        @ParameterizedTest(name = "{0} => {1}")
        // @MethodSource
        // normally @MethodSource without a specified method name
        // JUnit searches for a method with the same name as the test
        // but this does not work with @Nested and results in the error
        // "Could not find factory method"
        // The error remains even if we explicitly specify the method name:
        // @MethodSource("extractLastTrimmedArgs")
        // With current JUnit versions it works with a fully qualified name though:
        @MethodSource(value = "org.example.test.StringUtil_viaMethodSource_Test#extractLastTrimmedArgs")
            // BUT: this is ugly and probably not very refactoring safe (class / method renaming...)
            // for a better solution see StringUtilTest_viaArgumentsSource
        void extractLastTrimmed(final String str, final String expectedStr) {
            final String result = StringUtil.extractLastTrimmed(str);
            assertThat(result).isEqualTo(expectedStr);
        }

        @ParameterizedTest
        @NullAndEmptySource
        void extractLastTrimmed_NullOrEmpty(final String str) {
            final String result = StringUtil.extractLastTrimmed(str);
            assertThat(result).isNull();
        }

    }

    @Nested
    class AnotherStringUtilMethodTests {

        // more tests ...
    }

    // more tests ...



    public static Stream<? extends Arguments> extractLastTrimmedArgs() {
        return Stream.of(
                arguments("abc", "abc"),
                arguments("abc, def, ghi", "ghi"),
                arguments("abc, def,   \t ghi\n    ", "ghi")
        );
    }
}