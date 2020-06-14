import org.apache.log4j.Logger;

public class DefenderMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);
    private int numberOfTrials;

    public DefenderMode() {
        LOGGER.info("DefenderMode's constructor started");
        setStartMessage("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        numberOfTrials = 0;
        setWhoIsToPlay(Enum.Players.PLAYER);
        LOGGER.info("DefenderMode's constructor terminated");
    }




    @Override
    public String playerPrompt() {
        String whatToSay = "";
        if (getStateOfTheGame() == Enum.StateOfTheGame.START) {

            whatToSay = "Quelle combinaison choisissez-vous ?";
        }
        return whatToSay;
    }

    @Override
    public String playerTurn(String playerCombination) throws CombinationIncorrectException {
        isCombinationCorrect(playerCombination);
        setPlayerCombination(playerCombination);
        setStateOfTheGame(Enum.StateOfTheGame.RUN);
        setWhoIsToPlay(Enum.Players.COMPUTER);
        return "L'ordinateur va maintenant essayer de deviner votre combinaison secrète.";
    }


    @Override
    public String computerTurn() {
        String whatToSay = "";
        String computerGuess = "";
        String clue = "";

        if (getStateOfTheGame() == Enum.StateOfTheGame.RUN) {
            computerGuess = generateNextComputerCombination();
            whatToSay = "L'ordinateur a choisi : " + computerGuess;
            clue = compareGuessWithCombination(computerGuess, getPlayerCombination());
            String newLine = System.getProperty("line.separator");
            whatToSay += newLine + clue;
            updateRange(computerGuess, clue);
            numberOfTrials++;
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
        return whatToSay;
    }


    @Override
    public void endMessage() {

        if (isPartyWon()) {
            setEndMessage("Bravo l'ordinateur a découvert la combinaison du joueur.");
        } else if (numberOfTrials == getMaxNumberOfTrials()) {
            setEndMessage("L'ordinateur a perdu. Il a atteint le nombre d'essai maximum possible.");
        }

    }




}