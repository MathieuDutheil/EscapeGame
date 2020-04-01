import java.util.Scanner;

public abstract class AbstractGame {
    private static final int LENGTH_COMBINATION = 4;
    private Scanner sc = new Scanner(System.in);


    private boolean partyWin = false;

    public boolean isPartyWin() {
        return partyWin;
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
        int goodResponse = 0;
        for (int i = 0; i < LENGTH_COMBINATION; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                clew += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                clew += "-";

            } else {
                clew += "=";
                goodResponse++;
            }
        }
        System.out.println(clew);
        if (goodResponse == LENGTH_COMBINATION) {
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
                nextAnswer += Utilities.getRandomNumberInRange((guess.charAt(i) - '0') + 1, 9);

            } else if (indication.charAt(i) == '-') {
                nextAnswer += Utilities.getRandomNumberInRange(0, (guess.charAt(i) - '0') - 1);

            } else if (indication.charAt(i) == '=') {
                nextAnswer += ((guess.charAt(i) - '0'));

            }
        }
        return nextAnswer;
    }

}









