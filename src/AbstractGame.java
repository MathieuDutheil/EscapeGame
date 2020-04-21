import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public abstract class AbstractGame {

    private int lengthCombination = 4;
    private Scanner sc = new Scanner(System.in);
    private int[] minRange;
    private int[] maxRange;
    private boolean partyWon = false;
    private int maxNumberOfTrials = 5;
    private boolean developperMode = false;

    public AbstractGame() {
        loadProperties();
        minRange = new int[lengthCombination];
        //System.out.println(Arrays.toString(minRange));
        // Output : [0, 0, 0, 0]
        //Arrays.fill(minRange, 0); Est-ce que je mets cette ligne quand même ?

        maxRange = new int[lengthCombination];
        Arrays.fill(maxRange, 9);
        //System.out.println(Arrays.toString(maxRange));
        // Output :  [9, 9, 9, 9]

        //int minRange[] = new int[4] {0};
        //array creation with both dimension expression and initialization is illegal

    }


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

        } catch (FileNotFoundException | InputMismatchException | NumberFormatException ex) {
            System.out.println(ex.getMessage());

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
        System.out.println(developperMode);
        if (developperMode) {
            System.out.println("Combinaison = " + nextAnswer);
        }
        return nextAnswer;
    }

}









