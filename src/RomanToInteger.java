// url: https://leetcode.com/problems/roman-to-integer/

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int output = 0;
        int i = s.length() - 1;
        while (i > -1) {
            int value = romanMap.get(s.charAt(i));
            if (i < s.length() - 1 && value < romanMap.get(s.charAt(i + 1))) {
                output -= value;
            } else {
                output += value;
            }
            i--;
        }
        return output;
    }

    public static void main(String[] args) {
        String romanNumber = "XLII";
        RomanToInteger romanToInteger = new RomanToInteger();
        int output = romanToInteger.romanToInt(romanNumber);
        System.out.println(output);
    }
}
