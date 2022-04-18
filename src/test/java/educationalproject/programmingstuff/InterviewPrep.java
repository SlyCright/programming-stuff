package educationalproject.programmingstuff;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterviewPrep {

    @Test
    public void recursiveReverseTest() {

        //Given
        String givenString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expectedString = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

        //When
        String resultString = recursiveReverse(givenString);

        //Then
        assertThat(resultString).isEqualTo(expectedString);

    }

    private String recursiveReverse(String string) {

        int lastIndex = string.length() - 1;

        if (lastIndex >= 1) {
            char firstChar = string.charAt(0);
            char lastChar = string.charAt(lastIndex);
            string = string.substring(1, lastIndex);
            string = recursiveReverse(string);
            string = lastChar + string + firstChar;
        }

        return string;
    }

    @Test
    public void recursiveFactorialTest() {
        //Given
        int given = 10;
        int expected = 3628800; // 10!=3628800

        //When
        int result = recursiveFactorial(given);

        //Then
        assertThat(result).isEqualTo(expected);

    }

    private int recursiveFactorial(int number) {
        System.out.println("number = " + number);
        if (number > 1) {
            number *= recursiveFactorial(--number);
            System.out.println("number = " + number);
        }

        return number;
    }


}
