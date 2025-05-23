// url: https://leetcode.com/problems/container-with-most-water/description/

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class ContainerWithTheMostWater {

    public int maxArea(int[] height) {
        int output = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int leftValue = height[left];
            int rightValue = height[right];

            int maxHeight = max(leftValue, rightValue) - abs(leftValue - rightValue);
            int length = right - left;

            int currentOutput = maxHeight * length;
            output = max(currentOutput, output);

            if (leftValue < rightValue) {
                left++;
            } else {
                right--;
            }
        }

        return output;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        int output = new ContainerWithTheMostWater().maxArea(height);
        System.out.println(output);
    }
}
