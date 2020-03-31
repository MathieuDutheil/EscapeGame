import java.util.Scanner;

public class Game {
    private static final int LENGTH_COMBINATION = 4;
    private Scanner sc = new Scanner(System.in);
    private boolean partyWin = false;


    public void runGame() {
        System.out.println("Bienvenue dans Escape Game ONLINE");
        int restart;
        do {
            int gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 1, 3);
            do {
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
                restart = Utilities.askAnInt("Voulez-vous : 1 - Rejouer au même mode, 2 - Lancer un autre mode, 3 - Quitter l'application.)", 1, 3);
            }
            while (restart == 1);
        } while (restart == 2);
    }


    private void startChallengerMode() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        String challengerComputerCombination = generateCombination();

        do {
            String challengerPlayerCombination = getPlayerCombination("À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            compareGuessWithCombination(challengerPlayerCombination, challengerComputerCombination, "Le Joueur");
        } while (!partyWin);
    }

    public void startDefenderMode() {
        String computerGuess = "";
        int roundCounter = 0;
        String clew = "";
        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        String defenderPlayerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");

        do {
            computerGuess = buildNextComputerAnswer(roundCounter, clew, computerGuess);
            System.out.println("L'ordinateur a choisi : " + computerGuess);
            clew = compareGuessWithCombination(computerGuess, defenderPlayerCombination, "l'Ordinateur");
            roundCounter++;
        } while (!partyWin);

    }

    public void startDualMode() {
        System.out.println("Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison secrète de l'ordinateur avant qu'il ne devine la votre.");
        String dualPlayerCombination = getPlayerCombination("Quelle combinaison secrète choisissez-vous ?");
        System.out.println("L'ordinateur choisit une combinaison.");
        String dualComputerCombination = generateCombination();

        int roundCounter = 0;
        String playerGuess = "";
        String playerIndication = "";
        String computerGuess = "";


        do {
            playerGuess = getPlayerCombination("À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            compareGuessWithCombination(playerGuess, dualComputerCombination, "le Joueur");

            computerGuess = buildNextComputerAnswer(roundCounter, playerIndication, computerGuess);
            System.out.println("L'ordinateur a choisi : " + computerGuess);
            System.out.println("combinaison joueur = " + dualPlayerCombination);
            playerIndication = compareGuessWithCombination(computerGuess, dualPlayerCombination, "l'Ordinateur ");
            roundCounter++;
        } while (!partyWin);
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
        return true;
    }

    private String compareGuessWithCombination(String guess, String secret, String playerName) {

        String clew = "";
        partyWin = false;
        for (int i = 0; i < LENGTH_COMBINATION; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                clew += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                clew += "-";

            } else clew += "=";
        }
        System.out.println(clew);
        if (clew.equals("====")) {
            System.out.println("Bravo " + playerName + " a découvert la combinaison de son adversaire.");
            partyWin = true;
        }

        return clew;

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










