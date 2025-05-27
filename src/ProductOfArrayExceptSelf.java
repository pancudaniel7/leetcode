// url: https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=leetcode-75

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];

        output[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            output[i] = nums[i - 1] * output[i - 1];
        }

        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i]*=rightProduct;
            rightProduct*=nums[i];
        }

        return output;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf product = new ProductOfArrayExceptSelf();
//        int[] result = product.productExceptSelf(new int[]{1, 2, 3, 4});
//        int[] result = product.productExceptSelf(new int[]{1, -2, 3, 4});
//        int[] result = product.productExceptSelf(new int[]{1, 0, 3, 4});
        int[] result = product.productExceptSelf(new int[]{-1, 1, 0, -3, 3});

//        int[] result = product.productExceptSelf(new int[]{0, 0});
        for (int i = 0; i < result.length; i++) {
            System.out.printf("%s ", result[i]);
        }

    }
}
