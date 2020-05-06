import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Utilities {
    private static Scanner sc = new Scanner(System.in);
    private static org.apache.log4j.Logger logger = Logger.getLogger(AbstractGame.class);

    public static int getRandomNumberInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        if (min == max) {
            return max;
        }

        return (int) (Math.random() * ((max - min) + 1)) + min;
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
                logger.info(e.getMessage());
                isNonNumericalOutput = true;
                sc.nextLine();
            }
        } while (carac < lowLimit || carac > highLimit || isNonNumericalOutput);
        return carac;
    }

}


