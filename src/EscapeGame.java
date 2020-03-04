import java.util.Scanner;

public class EscapeGame {
    private int gameMode;
    private String secretCombination;
    private String playerGuess;
    private final static int LENGTH_COMBINATION = 4;
    private Scanner sc = new Scanner(System.in);

    public void runGame() {
        System.out.println("Bienvenue dans Escape Game ONLINE");
        char restart;
        do {
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

            System.out.println("Voulez-vous jouer de nouveau : Y = OUI ; N = NON.");
            restart = sc.next().charAt(0);

        } while (restart == 'Y');
    }

    public void selectGameMode() {
        gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 1, 3);
    }

    public void startChallengerMode() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        generateCombination();
        System.out.println(secretCombination);
        do {
            getPlayerGuess();
            System.out.println(playerGuess);
            compareGuessWithCombination();

        } while (!playerGuess.equals(secretCombination));
    }

    public void startDefenderMode() {
        System.out.println("Vous avez choisir le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        System.out.println("Votre combinaison secrète ?");
        getPlayerGuess();

    }

    public void startDualMode() {

    }

    private void generateCombination() {

        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            secretCombination += Utilities.getRandomNumberInRange(0, 9);
        }

    }

    private void getPlayerGuess() {
        do {
            playerGuess = "";  // Vraiment utile ???
            System.out.println("Votre proposition ?");
            playerGuess = sc.nextLine();

        } while (!isOutputCorrect(playerGuess));
    }

    private boolean isOutputCorrect(String outputToTest) {

        if (outputToTest.length() != LENGTH_COMBINATION) {
            System.out.println("Attention votre saisie doit se limiter à " + LENGTH_COMBINATION + " caractères.");

            return false;
        }

        for (int i = 0; i < outputToTest.length(); i++) {
            char c = outputToTest.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println(c);

                System.out.println("Attention votre  caractère n°" + (i + 1) + " n'est pas un chiffre.");
                return false;
            }
        }
        System.out.println("Saisie Correct");
        return true;
    }

    private void compareGuessWithCombination() {


        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            if (playerGuess.charAt(i) == secretCombination.charAt(i))
                System.out.print("=");

            if (playerGuess.charAt(i) < secretCombination.charAt(i)) {
                System.out.print("+");
            }
            if (playerGuess.charAt(i) > secretCombination.charAt(i)) {
                System.out.print("-");
            }
        }
        System.out.println("");
    }
}

