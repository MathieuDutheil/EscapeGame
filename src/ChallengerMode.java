import org.apache.log4j.Logger;

public class ChallengerMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ChallengerMode.class);
    private int numberOfTrials;

    public ChallengerMode() {
        setComputerCombination(generateNextComputerCombination());
        numberOfTrials = 0;
    }

    @Override
    public Enum.Players whoIsToPlay() {

        switch (getStateOfTheGame()) {
            case START:
                setWhoIsToPlay(Enum.Players.COMPUTER);
                break;

            case RUN:
                setWhoIsToPlay(Enum.Players.PLAYER);
                break;
        }

        return getWhoIsToPlay();
    }

    @Override
    public String computerTurn() {
        String whatToSay = "";
        if (getStateOfTheGame() == Enum.StateOfTheGame.START) {
            whatToSay = "Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.";
            setStateOfTheGame(Enum.StateOfTheGame.RUN);
        }
        return whatToSay;
    }

    @Override
    public String playerPrompt() {
        String whatToSay = "";
        if (getStateOfTheGame() == Enum.StateOfTheGame.RUN) {
            whatToSay = "Essayer de deviner la combinaison secrète de l'ordinateur. Votre proposition ?";

            if (isDeveloperMode()) {
                String newLine = System.getProperty("line.separator");
                whatToSay += newLine + "Le mode développeur est activé, la combinaison secrète de l'ordinateur est " + getComputerCombination();
            }
        }
        return whatToSay;
    }

    @Override
    public String playerTurn(String playerGuessCombination) throws CombinationIncorrectException {
        isCombinationCorrect(playerGuessCombination);
        String whatToDisplay = compareGuessWithCombination(playerGuessCombination, getComputerCombination());
        numberOfTrials++;
        if (numberOfTrials == getMaxNumberOfTrials()) {
            setStateOfTheGame(Enum.StateOfTheGame.END);
        }
        return whatToDisplay;
    }

    @Override
    public String endMessage() {
        String endMessage = "";
        if (isPartyWon()) {
            endMessage = "Bravo vous avez découvert la combinaison de l'ordinateur.";
        } else if (getNumberOfTrials() == getMaxNumberOfTrials()) {
            endMessage = "Vous avez perdu. Vous avez atteint le nombre d'essai maximum possible.";
        }
        return endMessage;
    }





}
