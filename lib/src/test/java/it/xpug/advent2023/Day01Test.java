package it.xpug.advent2023;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {

    private Integer day01(Stream<String> input) {
        Function<Integer, Integer> timesTen = n -> n*10;
        Function<Integer, Integer> id = n -> n;
        Function<Pair<Integer, Integer>, Integer> sum = nn -> nn.left() + nn.right();

        Function<String, Integer> processLine =
                Pair.split(this::firstDigitInString, this::lastDigitInString)
                        .andThen(Pair.cross(timesTen, id))
                        .andThen(sum);

        return input.map(processLine).reduce(0, (a, b) -> a+b);
    }

    private Integer firstDigitInString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isDigit(s.charAt(i))) {
                return s.charAt(i) - '0';
            }
            if (s.startsWith("two")) {
                return 2;
            }
        }
        throw new IllegalStateException("Not found");
    }

    private Integer lastDigitInString(String s) {
        var digit = s.chars().boxed().toList()
                .reversed().stream()
                .filter(Character::isDigit).findFirst().orElseThrow();

        return digit - '0';
    }

    @Test
    void acceptance1() {
        var input = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;

        assertThat(day01(Arrays.stream(input.split("\n")))).isEqualTo(142);
    }

    @Test
    @Disabled("WIP")
    void acceptance2() {
        var input = """
two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
""";

        assertThat(day01(Arrays.stream(input.split("\n")))).isEqualTo(281);
    }

    @Test
    void real() throws IOException {
        var result = day01(Files.lines(Path.of("src/main/resources/day01.txt")));
        System.out.println(result);
    }

    @Test
    void testFirstDigitInString() {
        assertThat(firstDigitInString("1abc")).isEqualTo(1);
        assertThat(firstDigitInString("sss2abc")).isEqualTo(2);
    }

    @Test
    void testFirstNumberInWords() {
        assertThat(firstDigitInString("two1nine")).isEqualTo(2);
    }

    @Test
    void testLastDigitInString() {
        assertThat(lastDigitInString("1a4bc")).isEqualTo(4);
        assertThat(lastDigitInString("sss2abc")).isEqualTo(2);
    }

}