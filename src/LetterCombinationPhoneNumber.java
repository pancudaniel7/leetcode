// url: https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationPhoneNumber {

    private final String[] LETTERS = new String[]{
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return output;

        backtracking(output, new StringBuilder(), digits, 0);
        return output;
    }

    private void backtracking(List<String> output, StringBuilder current, String digits, int index) {
        if (index == digits.length()) {
            output.add(current.toString());
            return;
        }

        String letters = LETTERS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtracking(output, current, digits, index + 1);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationPhoneNumber letterCombinationPhoneNumber = new LetterCombinationPhoneNumber();
//        List<String> output = letterCombinationPhoneNumber.letterCombinations("23");
        List<String> output = letterCombinationPhoneNumber.letterCombinations("234");

        for (String combination : output) {
            System.out.printf("%s ", combination);
        }
    }
}
