// url: https://leetcode.com/problems/zigzag-conversion/

import java.sql.SQLOutput;

public class ZigZagConversion {
    static class Solution {

        public String convert(String s, int numRows) {
            if(numRows == 1) {
                return s;
            }

            StringBuilder[] rows = new StringBuilder[numRows];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = new StringBuilder();
            }

            boolean goingDown = false;
            int index = 0;

            for (char c : s.toCharArray()) {
                rows[index].append(c);

                if (index == 0 || index == numRows - 1) {
                    goingDown = !goingDown;
                }

                index += goingDown ? 1 : -1;
            }

            StringBuilder result = new StringBuilder();
            for (StringBuilder row : rows) {
                result.append(row.toString());
            }

            return result.toString();
        }


        public static void main(String[] args) {
            int numRows = 3;
            String input = "PAYPALISHIRING";
            Solution solution = new Solution();
            String output = solution.convert(input, numRows);
            System.out.println(output);
        }
    }
}
