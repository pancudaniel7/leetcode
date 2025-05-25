// url: https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description/?envType=study-plan-v2&envId=leetcode-75

import java.util.ArrayList;
import java.util.List;

public class KidsWithTheGreatestNumberOfCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;

        for (int candy : candies) {
            if (maxCandies < candy) {
                maxCandies = candy;
            }
        }

        List<Boolean> output = new ArrayList<>(candies.length);
        for (int candy : candies) {
            output.add(candy + extraCandies >= maxCandies);
        }

        return output;
    }

    public static void main(String[] args) {
        int[] candies = new int[]{2, 3, 5, 1, 3};
        int extraCandies = 3;

        KidsWithTheGreatestNumberOfCandies kidsWithTheGreatestNumberOfCandies = new KidsWithTheGreatestNumberOfCandies();
        List<Boolean> result = kidsWithTheGreatestNumberOfCandies.kidsWithCandies(candies, extraCandies);

        for (Boolean item : result) {
            System.out.printf("%s ", item);
        }
    }
}
