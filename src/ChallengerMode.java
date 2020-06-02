import org.apache.log4j.Logger;

public class ChallengerMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ChallengerMode.class);


    public ChallengerMode() {
        setComputerCombination(generateNextComputerCombination());
    }

    @Override
    public void runGame() {
        LOGGER.info("runGame method started");// Done
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");//Done
        String computerCombination = generateNextComputerCombination();//Done
        int numberOfTrials = 0; //Done
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

    @Override
    public Enum.Players whoIsToPlay() {
        if (getStateOfTheGame() == Enum.StateOfTheGame.START) {
            setWhoIsToPlay(Enum.Players.COMPUTER);
        } else if (getWhoIsToPlay() == Enum.Players.PLAYER) {
            setWhoIsToPlay(Enum.Players.COMPUTER);
        } else if (getWhoIsToPlay() == Enum.Players.COMPUTER) {
            setWhoIsToPlay(Enum.Players.PLAYER);
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
            setWhatIsAsked(Enum.WhatIsAsked.COMBINATION);
        } else if (getStateOfTheGame() == Enum.StateOfTheGame.END) {
            setWhatIsAsked(Enum.WhatIsAsked.NULL);
            setStateOfTheGame(Enum.StateOfTheGame.CLOSE_GAME);
            if (isPartyWon()) {
                whatToSay = "Bravo vous avez découvert la combinaison de l'ordinateur.";
            } else if (getNumberOfTrials()>= getMaxNumberOfTrials()){
                whatToSay = "Vous avez perdu. Vous avez dépassé le nombre d'essai possible.";
            }
        }
        return whatToSay;
    }

    @Override
    public String whatToDoWithAsk(String ask) {
        String whatToDisplay = compareGuessWithCombination(ask, getComputerCombination());
        setNumberOfTrials(getNumberOfTrials() + 1);
        if (getNumberOfTrials()>= getMaxNumberOfTrials()) {
            setStateOfTheGame(Enum.StateOfTheGame.END);
        }
        return whatToDisplay;
    }


}
