import jdk.jshell.execution.Util;

public class EscapeGame {
    private int gameMode;
    private String secretCombination;
    private String playerGuess;
    private final static int LENGTH_COMBINATION = 4;

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
        secretCombination = askACombination(LENGTH_COMBINATION);
        System.out.println(secretCombination);
        do {
            playerGuess = Utilities.askAStringNumber();

            Utilities.compareTwoString(playerGuess, secretCombination);
        } while (!playerGuess.equals(secretCombination));
    }

    public void startDefenderMode() {

    }

    public void startDualMode() {

    }
    public static String askACombination(int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(Utilities.getRandomNumberInRange(0, 9));
        }
        return buffer.toString();
    }


}

