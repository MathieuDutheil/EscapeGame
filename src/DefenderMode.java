import org.apache.log4j.Logger;

public class DefenderMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);
    private int numberOfTrials;

    public DefenderMode() {
        numberOfTrials = 0;
    }


    @Override
    public Enum.Players whoIsToPlay() {

        switch (getStateOfTheGame()) {
            case START:
                setWhoIsToPlay(Enum.Players.PLAYER);
                break;

            case RUN:
                setWhoIsToPlay(Enum.Players.COMPUTER);
                break;
        }

        return getWhoIsToPlay();
    }

    @Override
    public String playerPrompt() {
        String whatToSay = "";
        if (getStateOfTheGame() == Enum.StateOfTheGame.START) {
            whatToSay = "Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.";
            String newLine = System.getProperty("line.separator");
            whatToSay += newLine + "Quelle combinaison choisissez-vous ?";
        }
        return whatToSay;
    }

    @Override
    public String playerTurn(String playerCombination) throws CombinationIncorrectException {
        isCombinationCorrect(playerCombination);
        setPlayerCombination(playerCombination);
        setStateOfTheGame(Enum.StateOfTheGame.RUN);
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
    public String endMessage() {
        String endMessage = "";
        if (isPartyWon()) {
            endMessage = "Bravo l'ordinateur a découvert la combinaison du joueur.";
        } else if (getNumberOfTrials() == getMaxNumberOfTrials()) {
            endMessage = "L'ordinateur a perdu. Il a atteint le nombre d'essai maximum possible.";
        }
        return endMessage;
    }




}