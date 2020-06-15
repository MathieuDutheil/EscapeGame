import org.apache.log4j.Logger;

public class ChallengerMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ChallengerMode.class);
    private int numberOfTrials;

    public ChallengerMode() {
        LOGGER.info("ChallengerMode's constructor started");
        setStartMessage("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        setComputerCombination(generateNextComputerCombination());
        numberOfTrials = 0;
        LOGGER.debug("numberOfTrials = " + numberOfTrials);
        setStateOfTheGame(Enum.StateOfTheGame.RUN);
        setWhoIsToPlay(Enum.Players.PLAYER);
        LOGGER.info("ChallengerMode's constructor terminated");
    }


    @Override
    public String computerTurn() {
        return null;
    }


    @Override
    public String playerPrompt() {
        LOGGER.trace("method playerPrompt started");
        String whatToSay = "";
        whatToSay = "Essayer de deviner la combinaison secrète de l'ordinateur. Votre proposition ?";

        if (isDeveloperMode()) {
            String newLine = System.getProperty("line.separator");
            whatToSay += newLine + "Le mode développeur est activé, la combinaison secrète de l'ordinateur est " + getComputerCombination();
        }
        LOGGER.debug("whatToSay = " + whatToSay);
        LOGGER.trace("method playerPrompt terminated");
        return whatToSay;
    }

    @Override
    public String playerTurn(String playerGuessCombination) throws CombinationIncorrectException {
        LOGGER.trace("method playerTurn started");
        LOGGER.debug("playerGuessCombination = " + playerGuessCombination);
        isCombinationCorrect(playerGuessCombination);
        String whatToDisplay = compareGuessWithCombination(playerGuessCombination, getComputerCombination());
        LOGGER.debug("whatToDisplay = " + whatToDisplay);
        numberOfTrials++;
        LOGGER.debug("numberOfTrials = " + numberOfTrials);
        if (numberOfTrials == getMaxNumberOfTrials()) {
            endMessage();
            setStateOfTheGame(Enum.StateOfTheGame.END);
        }

        LOGGER.trace("method playerTurn started");
        return whatToDisplay;
    }

    @Override
    public void endMessage() {
        LOGGER.trace("method endMessage started");
        if (isPartyWon()) {
            setEndMessage("Bravo vous avez découvert la combinaison de l'ordinateur.");
        } else if (numberOfTrials == getMaxNumberOfTrials()) {
            setEndMessage("Vous avez perdu. Vous avez atteint le nombre d'essai maximum possible. La combinaison de l'ordinateur était " + getComputerCombination()+".");
        }
        LOGGER.trace("method endMessage terminated");
    }
}
