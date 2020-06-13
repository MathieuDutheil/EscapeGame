import org.apache.log4j.Logger;

public class ChallengerMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ChallengerMode.class);
    private int numberOfTrials;

    public ChallengerMode() {
        this.setStartMessage("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        setComputerCombination(generateNextComputerCombination());
        numberOfTrials = 0;
        setStateOfTheGame(Enum.StateOfTheGame.RUN);
        setWhoIsToPlay(Enum.Players.PLAYER);
    }


    @Override
    public String computerTurn() {
        return null;
    }


    @Override
    public String playerPrompt() {
        String whatToSay = "";
        whatToSay = "Essayer de deviner la combinaison secrète de l'ordinateur. Votre proposition ?";

        if (isDeveloperMode()) {
            String newLine = System.getProperty("line.separator");
            whatToSay += newLine + "Le mode développeur est activé, la combinaison secrète de l'ordinateur est " + getComputerCombination();
        }

        return whatToSay;
    }

    @Override
    public String playerTurn(String playerGuessCombination) throws CombinationIncorrectException {
        isCombinationCorrect(playerGuessCombination);
        String whatToDisplay = compareGuessWithCombination(playerGuessCombination, getComputerCombination());
        numberOfTrials++;
        if (numberOfTrials == getMaxNumberOfTrials()) {
            endMessage();
            setStateOfTheGame(Enum.StateOfTheGame.END);
        }
        return whatToDisplay;
    }

    @Override
    public void endMessage() {

        if (isPartyWon()) {
            setEndMessage("Bravo vous avez découvert la combinaison de l'ordinateur.");
        } else if (numberOfTrials == getMaxNumberOfTrials()) {
            setEndMessage("Vous avez perdu. Vous avez atteint le nombre d'essai maximum possible.");
        }

    }


}
