package interview.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProblemsSolverTest {

    @Test
    public void recursiveReverseTest() {
        //Given
        String givenString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expectedString = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
        //When
        String resultString = ProblemsSolver.recursiveReverse(givenString);
        //Then
        assertThat(resultString).isEqualTo(expectedString);
    }

    @Test
    public void recursiveFactorialTest() {
        //Given
        int given = 10;
        int expected = 3628800; // 10!=3628800
        //When
        int result = ProblemsSolver.recursiveFactorial(given);
        //Then
        assertThat(result).isEqualTo(expected);
    }

}
