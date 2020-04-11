import java.util.Scanner;

public abstract class AbstractGame {
    private static final int LENGTH_COMBINATION = 4;
    private Scanner sc = new Scanner(System.in);
    private int minRange[] = new int[LENGTH_COMBINATION];
    private int maxRange[] = new int[LENGTH_COMBINATION];
    private boolean partyWon = false;
    private static final int MAX_NUMBER_OF_TRIALS = 5;
    private static boolean DEVELOPPER_MODE = false;

    public boolean isPartyWon() {
        return partyWon;
    }

    public static int getMaxNumberOfTrials() {
        return MAX_NUMBER_OF_TRIALS;
    }

    public abstract void runGame();

    String generateCombination() {

        String combination = "";
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            combination += Utilities.getRandomNumberInRange(0, 9);
        }
        return combination;
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

        if (inputToTest.length() != LENGTH_COMBINATION) {
            System.out.println("Attention votre saisie doit être de " + LENGTH_COMBINATION + " caractères.");

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
        for (int i = 0; i < LENGTH_COMBINATION; i++) {

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
        if (nbGoodResponses == LENGTH_COMBINATION) {
            System.out.println("Bravo " + playerName + " a découvert la combinaison de son adversaire.");
            partyWon = true;
        }

        return clew;

    }

    void initializeRange(int min, int max) {
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            minRange[i] = min;
            maxRange[i] = max;
        }

    }

    void updateRange(String oldGuess, String indication) {
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
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
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            nextAnswer += Utilities.getRandomNumberInRange(minRange[i], maxRange[i]);
        }
        return nextAnswer;
    }

}









