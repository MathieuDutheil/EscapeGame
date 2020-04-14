import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public abstract class AbstractGame {
    private int lengthCombination;
    private Scanner sc = new Scanner(System.in);
    private int[] minRange;
    private int[] maxRange;
    private boolean partyWon = false;
    private int maxNumberOfTrials;
    private boolean developperMode;


    public boolean isPartyWon() {
        return partyWon;
    }

    public int getMaxNumberOfTrials() {
        return maxNumberOfTrials;
    }

    public abstract void runGame();

    void loadProperties() {

        String path = new File("src/config.properties").getAbsolutePath();
        System.out.println(path);
        final Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            prop.load(input);
            lengthCombination = Integer.parseInt(prop.getProperty("LENGTH_COMBINATION"));
            maxNumberOfTrials = Integer.parseInt(prop.getProperty("LIMITED_NUMBER_OF_TRIALS"));
            developperMode = Boolean.parseBoolean(prop.getProperty("DEVELOPPER_MODE"));

        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

        String clew = "";
        int nbGoodResponses = 0;
        for (int i = 0; i < lengthCombination; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                clew += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                clew += "-";

            } else {
                clew += "=";
                nbGoodResponses++;
            }
        }
        System.out.println(clew);
        if (nbGoodResponses == lengthCombination) {
            System.out.println("Bravo " + playerName + " a découvert la combinaison de son adversaire.");
            partyWon = true;
        }

        return clew;

    }

    void initializeRange(int min, int max) {
        minRange =  new int[lengthCombination];
        maxRange = new int[lengthCombination];
        for (int i = 0; i < lengthCombination; i++) {
            minRange[i] = min;
            maxRange[i] = max;
        }

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
        return nextAnswer;
    }

}









