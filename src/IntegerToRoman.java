// url: https://leetcode.com/problems/integer-to-roman/

public class IntegerToRoman {
    public String intToRoman(int num) {
        int[] romanValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<romanValues.length && num > 0; i++){
            while(num >= romanValues[i]){
                num-=romanValues[i];
                sb.append(romans[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();
        String romanNumber = integerToRoman.intToRoman(3749);
        System.out.printf(romanNumber);
    }
}
