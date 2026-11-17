
package lt.vcd;

public class DigitSum {

    // Recursive method to calculate sum of digits
    public static int sumOfDigits(int x) {

        // Base case
        if (x < 10) {
            return x;
        }

        // Recursive case
        return (x % 10) + sumOfDigits(x / 10);
    }

    public static void main(String[] args) {

        int number = 1342;

        int result = sumOfDigits(number);

        System.out.println("Sum of digits: " + result);
    }
}
