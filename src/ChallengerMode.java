import org.apache.log4j.Logger;

public class ChallengerMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ChallengerMode.class);

    @Override
    public void runGame() {
        LOGGER.info("runGame method started");
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        String computerCombination = generateNextComputerCombination();
        int numberOfTrials = 0;
        LOGGER.debug("numberOfTrials = " + numberOfTrials);
        do {
            String challengerPlayerCombination = getPlayerCombination("Essayer de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            compareGuessWithCombination(challengerPlayerCombination, computerCombination, "le Joueur");
            numberOfTrials++;
            LOGGER.debug("numberOfTrials = " + numberOfTrials);
        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
        LOGGER.debug("maxNumberOfTrials = " + getMaxNumberOfTrials());
        LOGGER.info("runGame method finished");
    }
}
