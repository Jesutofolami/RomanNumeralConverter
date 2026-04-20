import java.util.Scanner;

public class RomanNumeralConverter {

    public int convert(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Roman numeral cannot be null or empty");
        }


        validateRomanRules(roman);

        int total = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = getValue(roman.charAt(i));

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

    private void validateRomanRules(String roman) {
        int consecutiveCount = 1;

        for (int i = 1; i <= roman.length(); i++) {
            if (i < roman.length() && roman.charAt(i) == roman.charAt(i - 1)) {
                consecutiveCount++;

                char currentChar = roman.charAt(i);

                // V, L, D can never repeat
                if (currentChar == 'V' || currentChar == 'L' || currentChar == 'D') {
                    throw new IllegalArgumentException(
                            "Invalid Roman numeral: '" + currentChar + "' cannot be repeated"
                    );
                }

                // I, X, C, M cannot repeat more than 3 times
                if (consecutiveCount > 3) {
                    throw new IllegalArgumentException(
                            "Invalid Roman numeral: Cannot repeat '" + currentChar +
                                    "' more than 3 times"
                    );
                }
            } else {
                consecutiveCount = 1;
            }
        }

        // Check for invalid subtraction patterns
        String[] invalidPatterns = {
                "IL", "IC", "ID", "IM", "VX", "VL", "VC", "VD", "VM",
                "XD", "XM", "LC", "LD", "LM", "DM"
        };

        for (String pattern : invalidPatterns) {
            if (roman.contains(pattern)) {
                throw new IllegalArgumentException(
                        "Invalid Roman numeral: Invalid subtraction pattern '" + pattern + "'"
                );
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RomanNumeralConverter converter = new RomanNumeralConverter();


        while (true) {
            System.out.print("Enter a Roman numeral: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

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