import org.apache.log4j.Logger;

public class DefenderMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);

    @Override
    public void runGame() {
        LOGGER.info("runGame method started");
        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");// done
        //String playerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");//
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");// done
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
            whatToSay = "L'Ordinateur a choisi : " + computerGuess;
            clue = compareGuessWithCombination(computerGuess, getPlayerCombination());
            String newLine = System.getProperty("line.separator");
            whatToSay += newLine + clue;
            updateRange(computerGuess, clue);
        }
        if (getNumberOfTrials() == getMaxNumberOfTrials()) {
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