import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";
    private static final String specialCharacters = "!@#$%^&*()-_=+[]{}\\|;:'\",.<>/?";

    public static String generatePassword(int length, String characterSet) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            char randomChar = characterSet.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the length of the password: ");
        int length = scanner.nextInt();

        System.out.println("Choose the character set to use:");
        System.out.println("1. Uppercase letters");
        System.out.println("2. Lowercase letters");
        System.out.println("3. Numbers");
        System.out.println("4. Special characters");
        System.out.println("5. All of the above");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String characterSet = "";
        switch (choice) {
            case 1:
                characterSet = uppercaseLetters;
                break;
            case 2:
                characterSet = lowercaseLetters;
                break;
            case 3:
                characterSet = numbers;
                break;
            case 4:
                characterSet = specialCharacters;
                break;
            case 5:
                characterSet = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;
                break;
            default:
                System.out.println("Invalid choice. Using default character set: all of the above");
                characterSet = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;
                break;
        }

        String password = generatePassword(length, characterSet);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }
}
