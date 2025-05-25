// url: https://leetcode.com/problems/reverse-vowels-of-a-string/?envType=study-plan-v2&envId=leetcode-75

import java.util.*;

public class ReverseVowelsOfAString {

    public String reverseVowels(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        List<Character> currentVowels = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                currentVowels.add(s.charAt(i));
            }
        }

        currentVowels = currentVowels.reversed();
        StringBuilder output = new StringBuilder(s);

        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                output.setCharAt(i, currentVowels.get(j++));
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {
        ReverseVowelsOfAString reverseVowelsOfAString = new ReverseVowelsOfAString();
        String result = reverseVowelsOfAString.reverseVowels("IceCreAm");
        System.out.println(result);
    }
}
