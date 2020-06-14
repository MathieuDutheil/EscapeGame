import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Utilities {
    private static Scanner sc = new Scanner(System.in);
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(Utilities.class);

    public static int getRandomNumberInRange(int min, int max) {
        LOGGER.trace("method getRandomNumberInRange started");
        LOGGER.debug("min = " + min + " ,max = " + max);
        if (min > max) {
            int oldMin = min;
            min = max;
            max = oldMin;
            LOGGER.debug("min = " + min + " ,max = " + max);
        }

        if (min == max) {
            LOGGER.debug("min = " + min + " ,max = " + max);
            LOGGER.trace("method getRandomNumberInRange terminated : min = max");
            return max;
        }
        LOGGER.trace("method getRandomNumberInRange terminated");
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }


    public static int askAnInt(String intro, int lowLimit, int highLimit) {
        LOGGER.trace("method askAnInt started");
        LOGGER.debug("lowLimit = " + lowLimit + " ,highLimit = " + highLimit);
        int intAsked = 0;
        boolean isNonNumericalInput;
        do {
            try {
                isNonNumericalInput = false;
                System.out.println(intro);
                intAsked = sc.nextInt();
                if (intAsked < lowLimit || intAsked > highLimit) {
                    LOGGER.warn("user input for intAsked out of boundaries = " + intAsked);
                    System.out.println("Attention la saisie attendue doit être comprise entre " + lowLimit + " et " + highLimit + ", recommencez la saisie.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Merci de saisir un chiffre.");
                LOGGER.error(ex.toString());
                isNonNumericalInput = true;
                sc.nextLine();
            }
        } while (intAsked < lowLimit || intAsked > highLimit || isNonNumericalInput);
        LOGGER.debug("intAsked = " + intAsked);
        LOGGER.trace("method askAnInt finished");
        return intAsked;
    }

}


