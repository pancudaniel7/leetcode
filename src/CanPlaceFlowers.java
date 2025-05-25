// url: https://leetcode.com/problems/can-place-flowers/description/?envType=study-plan-v2&envId=leetcode-75

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int goodPlotsCount = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            boolean emptyLeft = (i == 0) || flowerbed[i - 1] == 0;
            boolean emptyRight = (i == flowerbed.length - 1) || flowerbed[i + 1] == 0;

            if (emptyLeft && emptyRight && flowerbed[i] != 1) {
                flowerbed[i] = 1;
                goodPlotsCount++;
                if (goodPlotsCount >= n) {
                    return true;
                }
            }
        }

        return goodPlotsCount >= n;
    }

    public static void main(String[] args) {
        CanPlaceFlowers canPlaceFlowers = new CanPlaceFlowers();
//        boolean result = canPlaceFlowers.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1);
//        boolean result = canPlaceFlowers.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2);
//        boolean result = canPlaceFlowers.canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2);
//          boolean result = canPlaceFlowers.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1);
//        boolean result = canPlaceFlowers.canPlaceFlowers(new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 2);
//        boolean result = canPlaceFlowers.canPlaceFlowers(new int[]{0, 0, 1, 0, 1}, 1);
        boolean result = canPlaceFlowers.canPlaceFlowers(new int[]{0, 0, 1, 0, 0}, 1);
        System.out.println(result);
    }
}
