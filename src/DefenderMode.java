import org.apache.log4j.Logger;

public class DefenderMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(DefenderMode.class);
    private int numberOfTrials;

    public DefenderMode() {
        LOGGER.info("DefenderMode's constructor started");
        setStartMessage("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        numberOfTrials = 0;
        LOGGER.debug("numberOfTrials = " + numberOfTrials);
        setWhoIsToPlay(Enum.Players.PLAYER);
        LOGGER.info("DefenderMode's constructor terminated");
    }

    @Override
    public String playerPrompt() {
        LOGGER.trace("method playerPrompt started");
        String whatToSay = "";
        if (getStateOfTheGame() == Enum.StateOfTheGame.START) {
            whatToSay = "Quelle combinaison choisissez-vous ?";
        }
        LOGGER.debug("whatToSay = " + whatToSay);
        LOGGER.trace("method playerPrompt terminated");
        return whatToSay;
    }

    @Override
    public String playerTurn(String playerCombination) throws CombinationIncorrectException {
        LOGGER.trace("method playerTurn started");
        LOGGER.debug("playerCombination = " + playerCombination);
        isCombinationCorrect(playerCombination);
        setPlayerCombination(playerCombination);
        setStateOfTheGame(Enum.StateOfTheGame.RUN);
        setWhoIsToPlay(Enum.Players.COMPUTER);
        String whatToDisplay = "L'ordinateur va maintenant essayer de deviner votre combinaison secrète.";
        LOGGER.debug("whatToDisplay = " + whatToDisplay);
        LOGGER.trace("method playerTurn terminated");
        return whatToDisplay;
    }


    @Override
    public String computerTurn() {
        LOGGER.trace("method computerTurn started");
        String whatToSay = "";
        String computerGuess = "";
        String clue = "";

        if (getStateOfTheGame() == Enum.StateOfTheGame.RUN) {
            computerGuess = generateNextComputerCombination();
            LOGGER.debug("computerGuess = " + computerGuess);
            whatToSay = "L'ordinateur a choisi : " + computerGuess;
            clue = compareGuessWithCombination(computerGuess, getPlayerCombination());
            String newLine = System.getProperty("line.separator");
            whatToSay += newLine + clue;
            LOGGER.debug("whatToSay = " + whatToSay);
            updateRange(computerGuess, clue);
            numberOfTrials++;
            LOGGER.debug("numberOfTrials = " + numberOfTrials);
        }
        if (numberOfTrials == getMaxNumberOfTrials()) {
            endMessage();
            setStateOfTheGame(Enum.StateOfTheGame.END);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error(e.toString());
        }
        LOGGER.trace("method computerTurn started");
        return whatToSay;
    }


    @Override
    public void endMessage() {
        LOGGER.trace("method endMessage started");
        if (isPartyWon()) {
            setEndMessage("Bravo l'ordinateur a découvert la combinaison du joueur.");
        } else if (numberOfTrials == getMaxNumberOfTrials()) {
            setEndMessage("L'ordinateur a perdu. Il a atteint le nombre d'essai maximum possible.");
        }
        LOGGER.trace("method endMessage terminated");
    }




}