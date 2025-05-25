// Multiply numbers without using '*' operator

public class MultiplyNumbers {

    public static int multiply(int a, int b) {
        int absA = Math.abs(a);
        int absB = Math.abs(b);

        int result = 0;
        for (int i = 0; i < absA; i++) {
            result += absB;
        }

        boolean negative = a < 0 && b >= 0;
        if (b < 0 && a >= 0) {
            negative = true;
        }

        return negative ? -result : result;
    }

    public static void main(String[] args) {
        System.out.println(multiply(2, 3));
        System.out.println(multiply(-2, -3));
        System.out.println(multiply(-2, 3));
        System.out.println(multiply(2, -3));
        System.out.println(multiply(-2, 0));
    }
}
