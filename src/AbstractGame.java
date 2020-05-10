import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;


public abstract class AbstractGame {

    private static boolean propertyLoaded = false;
    private static int lengthCombination = 4;
    private static int maxNumberOfTrials = 5;
    private static boolean developperMode = false;
    private Scanner sc = new Scanner(System.in);
    private final int[] minRange;
    private final int[] maxRange;
    private boolean partyWon = false;
    private final static int MIN_LENGTH_COMBINATION = 1;
    private final static int MAX_LENGTH_COMBINATION = 10;
    private final static int MIN_NUMBER_OF_TRIALS = 3;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);

    public AbstractGame() {
        if (!propertyLoaded) {
            loadProperties();
            propertyLoaded = true;
        }
        minRange = new int[lengthCombination];
        maxRange = new int[lengthCombination];
        Arrays.fill(maxRange, 9);
    }


    public boolean isPartyWon() {
        return partyWon;
    }

    protected int getMaxNumberOfTrials() {
        return maxNumberOfTrials;
    }

    public abstract void runGame();

    void loadProperties() {
        LOGGER.trace("method loadProperties started");
        String path = new File("src/config.properties").getAbsolutePath();
        System.out.println(path);

        final Properties prop = new Properties();

        try (InputStream input = new FileInputStream(path)) {

            prop.load(input);

            try {
                int checkLengthCombination = Integer.parseInt(prop.getProperty("LENGTH_COMBINATION"));
                if (checkLengthCombination >= MIN_LENGTH_COMBINATION && checkLengthCombination <= MAX_LENGTH_COMBINATION) {
                    lengthCombination = checkLengthCombination;
                    LOGGER.debug("lengthCombination = " + lengthCombination);
                }
            } catch (NumberFormatException ex) {
                LOGGER.warn(ex.toString());
                LOGGER.debug("lengthCombination = " + lengthCombination);
            }

            try {
                int checkNumberOfTrials = Integer.parseInt(prop.getProperty("MAX_NUMBER_OF_TRIALS"));
                if (checkNumberOfTrials >= MIN_NUMBER_OF_TRIALS) {
                    maxNumberOfTrials = checkNumberOfTrials;
                    LOGGER.debug("maxNumberOfTrials = " + maxNumberOfTrials);
                }
            } catch (NumberFormatException ex) {
                LOGGER.warn(ex.toString());
                LOGGER.debug("maxNumberOfTrials = " + maxNumberOfTrials);

            }

            developperMode = Boolean.parseBoolean(prop.getProperty("DEVELOPPER_MODE"));
            LOGGER.debug("developperMode = " + developperMode);

        } catch (FileNotFoundException ex) {
            LOGGER.error(ex.toString());

        } catch (final IOException ex) {
            LOGGER.error(ex.toString());
        }
        LOGGER.trace("method loadProperties finished");
    }

    String getPlayerCombination(String intro) {
        String playerCombination = "";
        do {
            System.out.println(intro);
            playerCombination = sc.nextLine();

        } while (!isCombinationCorrect(playerCombination));
        return playerCombination;
    }

    private boolean isCombinationCorrect(String inputToTest) {

        if (inputToTest.length() != lengthCombination) {
            System.out.println("Attention votre saisie doit être de " + lengthCombination + " caractères.");

            return false;
        }

        for (int i = 0; i < inputToTest.length(); i++) {
            char c = inputToTest.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println("Attention votre  caractère n°" + (i + 1) + " n'est pas un chiffre.");
                return false;
            }
        }
        return true;
    }

    String compareGuessWithCombination(String guess, String secret, String playerName) {

        String clue = "";
        int nbGoodResponses = 0;
        for (int i = 0; i < lengthCombination; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                clue += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                clue += "-";

            } else {
                clue += "=";
                nbGoodResponses++;
            }
        }
        System.out.println(clue);
        if (nbGoodResponses == lengthCombination) {
            System.out.println("Bravo " + playerName + " a découvert la combinaison de son adversaire.");
            partyWon = true;
        }

        return clue;

    }


    void updateRange(String oldGuess, String indication) {
        for (int i = 0; i < lengthCombination; i++) {
            if (indication.charAt(i) == '+') {
                minRange[i] = ((oldGuess.charAt(i) - '0') + 1);

            } else if (indication.charAt(i) == '-') {
                maxRange[i] = ((oldGuess.charAt(i) - '0') - 1);

            } else if (indication.charAt(i) == '=') {
                minRange[i] = (oldGuess.charAt(i) - '0');
                maxRange[i] = (oldGuess.charAt(i) - '0');

            }
        }
    }

    String generateNextComputerCombination() {

        String nextAnswer = "";
        for (int i = 0; i < lengthCombination; i++) {
            nextAnswer += Utilities.getRandomNumberInRange(minRange[i], maxRange[i]);
        }
        if (developperMode) {
            System.out.println("Combinaison = " + nextAnswer);
        }
        return nextAnswer;
    }

}









