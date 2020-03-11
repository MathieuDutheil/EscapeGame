import java.util.Scanner;

public class EscapeGame {
    private int gameMode;
    private String secretCombination = "";
    private String playerCombination;
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
                restart = sc.nextLine();
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
            getPlayerCombination();
            System.out.println(playerCombination);
            System.out.println(compareGuessWithCombination(playerCombination, secretCombination));

        } while (!playerCombination.equals(secretCombination));
    }

    public void startDefenderMode() {
        String computerGuess = "";
        int roundCounter = 0;

        String playerInput = "";

        System.out.println("Vous avez choisir le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        getPlayerCombination();
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");

        do {
            System.out.println("computerGuess = " + computerGuess);
            System.out.println("roundCounter = " + roundCounter);
            System.out.println("playerInput = " + playerInput);
            System.out.println("playerCombination = " + playerCombination);
            System.out.println(computerGuess);
            computerGuess = buildNextComputerAnswer(roundCounter, playerInput, computerGuess);
            System.out.println(computerGuess);
            System.out.println("L'ordinateur a choisi : " + computerGuess);
            System.out.println("Merci d'indiquer pour chaque caractère de votre combinaison si la valeur du caractère proposé par l'ordinateur est supérieur (avec un +), inférieur (avec un -) ou égale (avec un =).");
            System.out.println("Exemple : Votre combinaison est 1234. Si l'ordinateur choisit la combinaison 3210. Vous devrez indiquez : +=--");

            do {

                playerInput = sc.nextLine();

            } while (!playerInput.equals(compareGuessWithCombination(computerGuess, playerCombination)));


            roundCounter++;
        } while (!computerGuess.equals(playerCombination));

    }

    public void startDualMode() {

    }

    private void generateCombination() {

        secretCombination = "";
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            secretCombination += Utilities.getRandomNumberInRange(0, 9);
        }

    }

    private void getPlayerCombination() {
        do {
            System.out.println("Votre proposition ?");
            playerCombination = sc.nextLine();

        } while (!isCombinationCorrect(playerCombination));
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
        System.out.println("Saisie Correct");
        return true;
    }

    private String compareGuessWithCombination(String guess, String secret) {

        String result = "";

        for (int i = 0; i < LENGTH_COMBINATION; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                result += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                result += "-";

            } else result += "=";
        }
        return result;
    }

    private String buildNextComputerAnswer(int counter, String indication, String guess) {
        String nextAnswer = "";
        switch (counter) {
            case 0:
                System.out.println(indication);
                System.out.println(guess);
                nextAnswer = "5555";
                break;
            case 1:
                System.out.println("indication = " + indication);
                System.out.println("guess = " + guess);
                for (int i = 0; i < indication.length(); i++) {
                    if (indication.charAt(i) == '+') {

                        nextAnswer += ((guess.charAt(i) - '0') + ('2' - '0'));
                        System.out.println(nextAnswer);

                    } else if (indication.charAt(i) == '-') {
                        nextAnswer += ((guess.charAt(i) - '0') - ('2' - '0'));
                        System.out.println(nextAnswer);

                    } else if (indication.charAt(i) == '=') {
                        nextAnswer += ((guess.charAt(i) - '0'));
                        System.out.println(nextAnswer);
                    }
                }

                break;

            default:
                for (int i = 0; i < indication.length(); i++) {
                    if (indication.charAt(i) == '+') {

                        nextAnswer += ((guess.charAt(i) - '0') + ('1' - '0'));
                        System.out.println(nextAnswer);

                    } else if (indication.charAt(i) == '-') {
                        nextAnswer += ((guess.charAt(i) - '0') - ('1' - '0'));
                        System.out.println(nextAnswer);

                    } else if (indication.charAt(i) == '=') {
                        nextAnswer += ((guess.charAt(i) - '0'));
                        System.out.println(nextAnswer);
                    }
                }
                break;


        }
        return nextAnswer;
    }
}




