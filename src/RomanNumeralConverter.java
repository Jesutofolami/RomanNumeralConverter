import java.util.Scanner;

public class RomanNumeralConverter {

    public int convert(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Roman numeral cannot be null or empty");
        }

        int total = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = getValue(roman.charAt(i));

            if (currentValue == -1) {
                throw new IllegalArgumentException("Invalid character: " + roman.charAt(i));
            }

            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }

            prevValue = currentValue;
        }

        return total;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RomanNumeralConverter converter = new RomanNumeralConverter();

        System.out.println(" ROMAN NUMERAL CONVERTER ");
        System.out.println("Enter Roman numerals to convert to numbers.");
        System.out.println("Type 'exit' or 'quit' to stop.\n");

        while (true) {
            System.out.print("Enter a Roman numeral: ");
            String input = scanner.nextLine().trim();

            // Check if user wants to exit
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Please enter a Roman numeral.\n");
                continue;
            }

            try {
                int result = converter.convert(input.toUpperCase());
                System.out.println(input.toUpperCase() + " = " + result);
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            }
        }

        scanner.close();
    }
}