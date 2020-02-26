import java.util.Scanner;

public class EscapeGame {
    private int gameMode;
    private String secretCombination = new String();
    private String playerGuess;
    private final static int LENGTH_COMBINATION = 4;
    private Scanner sc = new Scanner(System.in);

    public void runGame() {
        System.out.println("Bienvenue dans Escape Game ONLINE");
        selectGameMode();
        switch (gameMode) {
            case 1:
                startChallengerMode();
                break;
            case 2:
                startDefenderMode();
                break;
            default:
                startDualMode();
                break;

        }

    }


    public void selectGameMode() {
        gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 1, 3);
    }

    public void startChallengerMode() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        getCombination(LENGTH_COMBINATION);
        System.out.println(secretCombination);
        do {
            getPlayerGuess();


            Utilities.compareTwoString(playerGuess, secretCombination);
        } while (!playerGuess.equals(secretCombination));
    }

    public void startDefenderMode() {

    }

    public void startDualMode() {

    }

    private void getCombination(int length) {

        for (int i = 0; i < length; i++) {
            secretCombination += Utilities.getRandomNumberInRange(0, 9);
        }

    }

    private void getPlayerGuess() {
        do {
            playerGuess = sc.nextLine();

        }
        while (!isOutputNumerical(playerGuess));

    }

    private boolean isOutputNumerical(String outputToTest) { // miss try catch StringIndexOutOfBoundsException
        boolean isNumerical = false;
        for (int i = 0; i < outputToTest.length(); i++) {
            int valueToTest = Character.getNumericValue(outputToTest.charAt(i));
            if (valueToTest >= 0 && valueToTest <= 9) {
                isNumerical = true;
            } else {
                System.out.println("Merci de saisir un chiffre à l'index " + (i + 1) + " de votre saisie.");
                isNumerical = false;
                break;
            }

        }
        return isNumerical;
    }
}

