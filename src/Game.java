import java.util.Scanner;

public class Game {
    private int gameMode;
    private static final int LENGTH_COMBINATION = 4;
    private String secretCombination = "";
    private String playerCombination;
    private Scanner sc = new Scanner(System.in);
    private String intro;
    private String mode;
    private String computerCombination;



    public void runGame() {
        System.out.println("Bienvenue dans Escape Game ONLINE");
        String restart = "";

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

    private void startChallengerMode() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        String challengerSecretCombination = generateCombination();
        String challengerPlayerCombination;
        do {
            challengerPlayerCombination = getPlayerCombination("Votre proposition ?");
        } while (compareGuessWithCombination(challengerPlayerCombination, challengerSecretCombination));
    }

    public void startDefenderMode() {
        String computerGuess = "";
        int roundCounter = 0;
        String playerInput = "";
        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        playerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");

        do {
            computerGuess = buildNextComputerAnswer(roundCounter, playerInput, computerGuess);
            System.out.println(computerGuess);
            System.out.println("L'ordinateur a choisi : " + computerGuess);
            //playerInput = compareGuessWithCombination(computerGuess, playerCombination);
            System.out.println(playerInput);

            roundCounter++;
        } while (!computerGuess.equals(playerCombination));

    }

    public void startDualMode() {
        System.out.println("Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison de l'ordinateur avant qu'il ne devine la votre.");
        System.out.println("Le joueur choisit une combinaison.");
        playerCombination = getPlayerCombination("Quelle combinaison secrète choisissez-vous ?");
        System.out.println("L'ordinateur choisit une combinaison.");
        secretCombination = generateCombination();

        int roundCounter = 0;
        String playerGuess = "";
        String playerIndication = "";
        String computerGuess = "";


        do {
            playerGuess = getPlayerCombination("Votre proposition ?");
            System.out.println(playerGuess);
            System.out.println(compareGuessWithCombination(playerGuess, secretCombination));

            computerGuess = buildNextComputerAnswer(roundCounter, playerIndication, computerGuess);
            System.out.println(computerGuess);
            System.out.println("L'ordinateur a choisi : " + computerGuess);
            System.out.println("Merci d'indiquer pour chaque caractère de votre combinaison si la valeur du caractère proposé par l'ordinateur est supérieur (avec un +), inférieur (avec un -) ou égale (avec un =).");
            do {
                System.out.println("combinaison joueur = " + playerCombination);
                playerIndication = sc.nextLine();
            } while (!playerIndication.equals(compareGuessWithCombination(computerGuess, playerCombination)));


            roundCounter++;
        } while (!playerGuess.equals(secretCombination) && !computerGuess.equals(playerCombination));
    }


    private String generateCombination() {

        String generateCombination = "";
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            generateCombination += Utilities.getRandomNumberInRange(0, 9);
        }
        return generateCombination;
    }

    private String getPlayerCombination(String intro) {
        String getPlayerCombination = "";
        do {
            System.out.println(intro);
            getPlayerCombination = sc.nextLine();

        } while (!isCombinationCorrect(getPlayerCombination));
        return getPlayerCombination;
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
        System.out.println("Saisie Correcte");
        return true;
    }

    private boolean compareGuessWithCombination(String guess, String secret) {

        String result = "";

        for (int i = 0; i < LENGTH_COMBINATION; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                result += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                result += "-";

            } else result += "=";
        }
        System.out.println(result);
        return !guess.equals(secret);

    }

    private String buildNextComputerAnswer(int counter, String indication, String guess) {
        String nextAnswer = "";
        switch (counter) {
            case 0:
                nextAnswer = generateCombination();
                break;

            default:
                for (int i = 0; i < indication.length(); i++) {
                    if (indication.charAt(i) == '+') {

                        nextAnswer += ((guess.charAt(i) - '0') + ('1' - '0'));


                    } else if (indication.charAt(i) == '-') {
                        nextAnswer += ((guess.charAt(i) - '0') - ('1' - '0'));


                    } else if (indication.charAt(i) == '=') {
                        nextAnswer += ((guess.charAt(i) - '0'));

                    }
                }
                break;


        }
        return nextAnswer;
    }
}










