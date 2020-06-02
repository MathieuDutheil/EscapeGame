import org.apache.log4j.Logger;

public class ChallengerMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ChallengerMode.class);


    public ChallengerMode() {
        setComputerCombination(generateNextComputerCombination());
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
        setNumberOfTrials(getNumberOfTrials() + 1);
        if (getNumberOfTrials() == getMaxNumberOfTrials()) {
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


    @Override
    public void runGame() {
        LOGGER.info("runGame method started");
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");//Done
        String computerCombination = generateNextComputerCombination();//Done
        int numberOfTrials = 0; //Done
        LOGGER.debug("numberOfTrials = " + numberOfTrials);
        do {
            //String challengerPlayerCombination = getPlayerCombination("Essayer de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            //compareGuessWithCombination(challengerPlayerCombination, computerCombination, "le Joueur");
            numberOfTrials++;
            LOGGER.debug("numberOfTrials = " + numberOfTrials);
        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
        LOGGER.debug("maxNumberOfTrials = " + getMaxNumberOfTrials());
        LOGGER.info("runGame method finished");
    }


}
