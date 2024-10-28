import java.util.Scanner;

public class NumberPyramidPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows for the pyramid: ");
        int n = scanner.nextInt(); // Number of rows

        // Generate the pyramid pattern
        for (int i = 1; i <= n; i++) {
            // Print spaces for alignment
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }

            // Print numbers
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }

            // Move to the next line
            System.out.println();
        }

        scanner.close(); // Close the scanner
    }
}
