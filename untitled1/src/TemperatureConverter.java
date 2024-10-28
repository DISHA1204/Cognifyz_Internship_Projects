import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Temperature Converter!");
        System.out.print("Enter the temperature you want to convert: ");
        double temperature = scanner.nextDouble();

        System.out.print("Convert to (1 for Celsius, 2 for Fahrenheit): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                double celsius = convertFahrenheitToCelsius(temperature);
                System.out.printf("%.2f Fahrenheit is %.2f Celsius.\n", temperature, celsius);
                break;
            case 2:
                double fahrenheit = convertCelsiusToFahrenheit(temperature);
                System.out.printf("%.2f Celsius is %.2f Fahrenheit.\n", temperature, fahrenheit);
                break;
            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
        }

        scanner.close();
    }

    private static double convertFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private static double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
}
