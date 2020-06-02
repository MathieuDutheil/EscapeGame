import org.apache.log4j.Logger;

public class DualMode extends AbstractGame {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);
    @Override
    public void runGame() {
        LOGGER.info("runGame method started");
        System.out.println("Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison secrète de l'ordinateur avant qu'il ne devine la votre.");
       // String playerCombination = getPlayerCombination("Quelle combinaison secrète choisissez-vous ?");
        System.out.println("L'Ordinateur choisit une combinaison.");
        String computerCombination = generateNextComputerCombination();
        String playerGuess = "";
        String clue = "";
        String computerGuess = computerCombination;

        do {

           // playerGuess = getPlayerCombination("À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            //compareGuessWithCombination(playerGuess, computerCombination, "le Joueur");

            if (!isPartyWon()) {
                System.out.println("L'Ordinateur a choisi : " + computerGuess);
                //System.out.println("combinaison joueur = " + playerCombination);
               // clue = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");
                updateRange(computerGuess, clue);
                computerGuess = generateNextComputerCombination();
            }
        } while (!isPartyWon());
        LOGGER.info("runGame method finished");
    }

    @Override
    public Enum.Players whoIsToPlay() {
        return null;
    }

    @Override
    public String computerTurn() {
        return null;
    }

    @Override
    public String playerPrompt() {
        return null;
    }

    @Override
    public String playerTurn(String ask) {
        return null;
    }

    @Override
    public String endMessage() {
        return null;
    }
}
