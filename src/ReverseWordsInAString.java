// url: https://leetcode.com/problems/reverse-words-in-a-string/description/?envType=study-plan-v2&envId=leetcode-75

import java.util.Objects;
import java.util.Stack;

public class ReverseWordsInAString {

    public String reverseWords(String s) {
        String[] sArray = s.split(" ");
        Stack stack = new Stack();
        for (int i = 0; i < sArray.length; i++) {
            if (!Objects.equals(sArray[i], "")){
                stack.push(sArray[i]);
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < sArray.length; i++) {
            if (!Objects.equals(sArray[i], "")){
                output.append(stack.pop());
                if (i < sArray.length - 1) output.append(" ");
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {
        ReverseWordsInAString reverseVowelsOfAString = new ReverseWordsInAString();
//        String result = reverseVowelsOfAString.reverseWords("the sky is blue");
        String result = reverseVowelsOfAString.reverseWords("a good   example");
        System.out.printf(result);
    }
}
