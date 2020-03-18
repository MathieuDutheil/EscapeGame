import java.util.Scanner;

abstract class AbstractMode {

    private static final int LENGTH_COMBINATION = 4;
    private String intro;
    private String mode;

    private String computerCombination;
    private String playerCombination;
    private Scanner sc = new Scanner(System.in);

    public AbstractMode() {
        this.intro = "Vous avez choisi le mode ";
    }


    @Override
    public String toString() {
        return intro + mode;
    }

    private String getPlayerCombination() {
        String getPlayerCombination = "";
        do {
            System.out.println("Votre proposition ?");
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


    protected void setMode(String mode) {
        this.mode = mode;
    }
}



