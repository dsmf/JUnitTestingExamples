package org.example.junit.methodsource_vs_argumentsource;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StringUtil_viaArgumentsSource_Test {

    @Nested
    class ExtractLastTrimmedTests {

        @ParameterizedTest(name = "{0} => {1}")
        @ArgumentsSource(CommaSeparatedStringsArgs.class)
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
    class OtherStringUtilMethodTests {

        // more tests ...
    }

    // more tests ...

}


class CommaSeparatedStringsArgs implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext extensionContext) {
        return Stream.of(
                arguments("abc", "abc"),
                arguments("abc, def, ghi", "ghi"),
                arguments("abc, def,   \t ghi\n    ", "ghi")
        );
    }
}
