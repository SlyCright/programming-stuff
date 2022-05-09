package interview.problems;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProblemsSolver {

    public String recursiveReverse(String string) {
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

    public int recursiveFactorial(int number) {
        if (number > 1) {
            number *= recursiveFactorial(--number);
        }
        return number;
    }

}
