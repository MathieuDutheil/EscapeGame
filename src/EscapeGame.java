import jdk.jshell.execution.Util;

public class EscapeGame {
    private int gameMode;
    private String secretCombination;
    private String playerStringGuess;
    private int playerGuess;

    public void selectGameMode() {
        gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 1, 3);
    }

    public void startChallengerMode() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        secretCombination = Utilities.getRandomStringNumberInRange(4);
        System.out.println(secretCombination);
        do {
            playerGuess = Utilities.askAnInt("Votre choix ?", 0, 100000);
            playerStringGuess = Integer.toString(playerGuess);
            Utilities.compareTwoString(playerStringGuess, secretCombination);
        } while (!playerStringGuess.equals(secretCombination));
    }

    public void startDefenderMode() {

    }

    public void startDualMode() {

    }

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
}
