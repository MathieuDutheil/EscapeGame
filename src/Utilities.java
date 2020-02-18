import java.util.InputMismatchException;
import java.util.Scanner;


public class Utilities {

    private static Scanner sc = new Scanner(System.in);
    private static String newLine = System.getProperty("line.separator");

    public static String getRandomStringNumberInRange(int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i <= (length - 1); i++) {
            buffer.append(getRandomNumberInRange(0, 9));
        }
        String stringNumber = buffer.toString();
        return stringNumber;
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int) (Math.random() * ((max - min) + 1)) + min;
    }


    public static String askAStringNumber() {
        String stringNumber;
        do {
            stringNumber = sc.nextLine();

        } while (isStringNumericalOutput(stringNumber));
        return stringNumber;
    }

    public static boolean isStringNumericalOutput(String stringToTest) {
        boolean bool = true;
        try {
            for (int i = 0; i <= (stringToTest.length() - 1); i++) {

                int i1 = Integer.parseInt(String.valueOf(stringToTest.charAt(i))); //try/catch a rajouter sur NumberFormatException

                if (i1 >= 0 & i1 <= 9) {
                    bool = false;
                }
            }
        } catch (NumberFormatException e) {

            System.out.println("Merci de saisir un chiffre");
        }


        return bool;
    }


    public static int askAnInt(String intro, int lowLimit, int highLimit) {

        int carac = 0;
        boolean isNonNumericalOutput;
        do {
            try {
                isNonNumericalOutput = false;
                System.out.println(intro);
                carac = sc.nextInt();
                if (carac < lowLimit || carac > highLimit) {
                    System.out.println("Attention la saisie attendue doit être comprise entre " + lowLimit + " et " + highLimit + ", recommencez la saisie.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Merci de saisir un chiffre.");
                isNonNumericalOutput = true;
                sc.nextLine();
            }
        } while (carac < lowLimit || carac > highLimit || isNonNumericalOutput);
        return carac;
    }


    public static void compareTwoString(String firstString, String secondString) {
        for (int i = 0; i <= ((firstString.length()) - 1); i++) {
            compareTwoChar(firstString.charAt(i), secondString.charAt(i));
        }
        System.out.println(newLine);
    }


    public static void compareTwoChar(char a, char b) {
        if (a == b) {
            System.out.print("=");
        }
        if (a > b) {
            System.out.print("+");
        }
        if (a < b) {
            System.out.print("-");
        }

    }


}

