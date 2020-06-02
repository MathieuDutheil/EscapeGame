import org.apache.log4j.Logger;

public class DefenderMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);

    @Override
    public void runGame() {
        LOGGER.info("runGame method started");
        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        String playerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");
        String computerGuess = "";
        String clue = "";
        int numberOfTrials = 0;
        LOGGER.debug("numberOfTrials = " + numberOfTrials);

        do {
            computerGuess = generateNextComputerCombination();
            System.out.println("L'Ordinateur a choisi : " + computerGuess);
           // clue = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");
            updateRange(computerGuess, clue);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error(e.toString());
            }

            numberOfTrials++;
            LOGGER.debug("numberOfTrials = " + numberOfTrials);
        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
        LOGGER.debug("maxNumberOfTrials = " + getMaxNumberOfTrials());
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