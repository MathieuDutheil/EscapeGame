import java.util.Scanner;

public class EscapeGame {
    private int gameMode;
    private String secretCombination = "";
    private String playerInput;
    private final static int LENGTH_COMBINATION = 4;
    private Scanner sc = new Scanner(System.in);

    public void runGame() {
        System.out.println("Bienvenue dans Escape Game ONLINE");
        String restart;
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

            System.out.println("Voulez-vous jouer de nouveau : O = OUI ; N = NON.");
            do {
                restart = sc.nextLine(); // Manque contrôle sur cette saisie.
                if (!restart.equals("O") && !restart.equals("N")) {
                    System.out.println("Merci de saisir O ou N.");
                }
            } while (!restart.equals("O") && !restart.equals("N"));

        } while (restart.equals("O"));
    }

    public void selectGameMode() {
        gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 1, 3);
    }

    public void startChallengerMode() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        generateCombination();
        System.out.println(secretCombination);
        do {
            getPlayerInput();
            System.out.println(playerInput);
            System.out.println(compareInputWithCombination());

        } while (!playerInput.equals(secretCombination));
    }

    public void startDefenderMode() {
        System.out.println("Vous avez choisir le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");

        getPlayerInput();

    }

    public void startDualMode() {

    }

    private void generateCombination() {

        secretCombination = "";
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            secretCombination += Utilities.getRandomNumberInRange(0, 9);
        }

    }

    private void getPlayerInput() {
        do {
            System.out.println("Votre proposition ?");
            playerInput = sc.nextLine();

        } while (!isInputCorrect(playerInput));
    }

    private boolean isInputCorrect(String inputToTest) {

        if (inputToTest.length() != LENGTH_COMBINATION) {
            System.out.println("Attention votre saisie doit être de " + LENGTH_COMBINATION + " caractères.");

            return false;
        }

        for (int i = 0; i < inputToTest.length(); i++) {
            char c = inputToTest.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println(c);

                System.out.println("Attention votre  caractère n°" + (i + 1) + " n'est pas un chiffre.");
                return false;
            }
        }
        System.out.println("Saisie Correct");
        return true;
    }

    private String compareInputWithCombination() {

        String result = "";

        for (int i = 0; i < LENGTH_COMBINATION; i++) {

            if (playerInput.charAt(i) < secretCombination.charAt(i)) {
                result += "+";

            } else if (playerInput.charAt(i) > secretCombination.charAt(i)) {
                result += "-";

            } else result += "=";
        }
        return result;
    }
}

