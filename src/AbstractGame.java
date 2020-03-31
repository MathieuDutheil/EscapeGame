import java.util.Scanner;

public abstract class AbstractGame {
    private static final int LENGTH_COMBINATION = 4;
    private Scanner sc = new Scanner(System.in);


    private boolean partyWin = false;

    public boolean isPartyWin() {
        return partyWin;
    }

    public abstract void runGame();
    
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

            computerGuess = buildNextComputerAnswer(playerIndication, computerGuess);
            System.out.println("L'ordinateur a choisi : " + computerGuess);
            System.out.println("combinaison joueur = " + dualPlayerCombination);
            playerIndication = compareGuessWithCombination(computerGuess, dualPlayerCombination, "l'Ordinateur ");
            roundCounter++;
        } while (!partyWin);
    }


    String generateCombination() {

        String generateCombination = "";
        for (int i = 0; i < LENGTH_COMBINATION; i++) {
            generateCombination += Utilities.getRandomNumberInRange(0, 9);
        }
        return generateCombination;
    }

    String getPlayerCombination(String intro) {
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

    String compareGuessWithCombination(String guess, String secret, String playerName) {

        String clew = "";
        int clews = 0;
        for (int i = 0; i < LENGTH_COMBINATION; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                clew += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                clew += "-";

            } else {
                clew += "=";
                clews++;
            }
        }
        System.out.println(clew);
        if (clews == LENGTH_COMBINATION) {
            System.out.println("Bravo " + playerName + " a découvert la combinaison de son adversaire.");
            partyWin = true;
        }

        return clew;

    }

    String buildNextComputerAnswer(String indication, String guess) {
        String nextAnswer = "";
        if (indication == "") {
            return generateCombination();
        }

        for (int i = 0; i < indication.length(); i++) {
            if (indication.charAt(i) == '+') {
                nextAnswer += Utilities.getRandomNumberInRange((guess.charAt(i) - '0'), 9);

            } else if (indication.charAt(i) == '-') {
                nextAnswer += Utilities.getRandomNumberInRange(0, (guess.charAt(i) - '0'));

            } else if (indication.charAt(i) == '=') {
                nextAnswer += ((guess.charAt(i) - '0'));

            }
        }
        return nextAnswer;
    }

}









