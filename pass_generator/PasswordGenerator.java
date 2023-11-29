import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one character from each character set
        password.append(getRandomChar(UPPER, random));
        password.append(getRandomChar(LOWER, random));
        password.append(getRandomChar(DIGITS, random));
        password.append(getRandomChar(SPECIAL, random));

        // Generate the remaining characters
        for (int i = 4; i < length; i++) {
            String charSet = getRandomCharSet(random);
            password.append(getRandomChar(charSet, random));
        }

        // Shuffle the password characters
        String shuffledPassword = shuffle(password.toString(), random);

        return shuffledPassword;
    }

    private static char getRandomChar(String charSet, SecureRandom random) {
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }

    private static String getRandomCharSet(SecureRandom random) {
        int randomType = random.nextInt(4);
        switch (randomType) {
            case 0:
                return UPPER;
            case 1:
                return LOWER;
            case 2:
                return DIGITS;
            case 3:
                return SPECIAL;
            default:
                return "";
        }
    }

    private static String shuffle(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = characters[index];
            characters[index] = characters[i];
            characters[i] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        int passwordLength = 12; // Change this to the desired password length
        String generatedPassword = generatePassword(passwordLength);
        System.out.println("Generated Password: " + generatedPassword);
    }
}
