import org.apache.log4j.Logger;

public class DualMode extends AbstractGame {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);
    private static int draw;
    private static boolean isRunStarted;

    public DualMode() {
        setComputerCombination(generateNextComputerCombination());
        draw = Utilities.getRandomNumberInRange(0, 1);
        System.out.println(draw);
        isRunStarted = false;
    }

    @Override
    public Enum.Players whoIsToPlay() {

        switch (getStateOfTheGame()) {
            case START:
                setWhoIsToPlay(Enum.Players.PLAYER);
                break;

            case RUN:
                if (!isRunStarted) {
                    if (draw == 0) {
                        setWhoIsToPlay(Enum.Players.PLAYER);
                        isRunStarted = true;
                    } else if (draw == 1) {
                        setWhoIsToPlay(Enum.Players.COMPUTER);
                        isRunStarted = true;
                    }
                } else {
                    switch (getWhoIsToPlay()) {
                        case PLAYER:
                            setWhoIsToPlay(Enum.Players.COMPUTER);
                            break;
                        case COMPUTER:
                            setWhoIsToPlay(Enum.Players.PLAYER);
                            break;
                    }
                }
                break;
        }
        return getWhoIsToPlay();
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
        }
        return whatToSay;
    }

    @Override
    public String playerPrompt() {
        String whatToSay = "";
        String newLine = System.getProperty("line.separator");
        switch (getStateOfTheGame()) {

            case START:

                whatToSay = "Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison secrète de l'ordinateur avant qu'il ne devine la votre.";
                whatToSay += newLine + "Déterminons qui commencera la partie.";
                whatToSay += newLine + "Le lancer de pièce a donné " + draw + ".";
                if (draw == 0) {
                    whatToSay += newLine + "C'est le Joueur qui commencera à deviner.";
                } else {
                    whatToSay += newLine + "C'est l'ordinteur qui commencera à deviner.";
                }
                whatToSay += newLine + "L'ordinateur choisit une combinaison.";
                whatToSay += newLine + "Quelle combinaison secrète choisissez-vous ?";
                break;
            case RUN:

                whatToSay = "À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?";
                if (isDeveloperMode()) {
                    whatToSay += newLine + "Le mode développeur est activé, la combinaison secrète de l'ordinateur est " + getComputerCombination();
                }
                break;
        }


        return whatToSay;
    }

    @Override
    public String playerTurn(String playerCombination) throws CombinationIncorrectException {
        String whatToDisplay = "";
        switch (getStateOfTheGame()) {
            case START:
                isCombinationCorrect(playerCombination);
                setPlayerCombination(playerCombination);
                whatToDisplay = "Commençons la partie.";
                setStateOfTheGame(Enum.StateOfTheGame.RUN);
                break;
            case RUN:
                isCombinationCorrect(playerCombination);
                whatToDisplay = compareGuessWithCombination(playerCombination, getComputerCombination());
                break;
        }

        return whatToDisplay;
    }

    @Override
    public String endMessage() {
        String endMessage = "";
        switch (whoIsToPlay()) {
            case COMPUTER:
                endMessage = "Bravo l'ordinateur a découvert la combinaison du joueur.";
                break;
            case PLAYER:
                endMessage = "Bravo vous avez découvert la combinaison de l'ordinateur.";
        }
        return endMessage;
    }



}