// url: https://leetcode.com/problems/string-compression/?envType=study-plan-v2&envId=leetcode-75

public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);

        while (absDividend > 0) {
            absDividend -= absDivisor;
            
        }

        return 0;
    }

    public static void main(String[] args) {
        DivideTwoIntegers divideTwoIntegers = new DivideTwoIntegers();
        divideTwoIntegers.divide(10, 3);
    }
}
