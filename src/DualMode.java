import org.apache.log4j.Logger;

public class DualMode extends AbstractGame {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);
    private final static int NB_PLAYERS = 2;
    private final Enum.Players[] playersOrder = new Enum.Players[NB_PLAYERS];
    private int indexPlayer;
    String newLine = System.getProperty("line.separator");

    public DualMode() {
        LOGGER.info("DualMode's constructor started");
        indexPlayer = 0;
        setComputerCombination(generateNextComputerCombination());
        String startMessageBuild = "Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison secrète de l'ordinateur avant qu'il ne devine la votre.";
        switch (Utilities.getRandomNumberInRange(0, 1)) {
            case 0:
                playersOrder[0] = Enum.Players.PLAYER;
                playersOrder[1] = Enum.Players.COMPUTER;
                startMessageBuild += newLine + "C'est le Joueur qui commencera à deviner.";
                break;

            default:
                playersOrder[0] = Enum.Players.COMPUTER;
                playersOrder[1] = Enum.Players.PLAYER;
                startMessageBuild += newLine + "C'est l'ordinateur qui commencera à deviner.";
                break;
        }
        setStartMessage(startMessageBuild);
        LOGGER.info("DualMode's constructor terminated");
    }

    @Override
    public Enum.Players getWhoIsToPlay() {
        indexPlayer = indexPlayer % NB_PLAYERS;

        return playersOrder[indexPlayer];
    }


    @Override
    public String computerTurn() {
        String whatToSay = "";
        String computerGuess = "";
        String clue = "";
        switch (getStateOfTheGame()) {

            case START:
                if (playersOrder[1] == Enum.Players.COMPUTER) {
                    whatToSay = "L'ordinateur a choisi une combinaison secrète.";
                    setStateOfTheGame(Enum.StateOfTheGame.RUN);
                }
                break;

            case RUN:
                computerGuess = generateNextComputerCombination();
                whatToSay = "L'ordinateur tente de deviner votre combinaison. Il choisit : " + computerGuess + ".";
                clue = compareGuessWithCombination(computerGuess, getPlayerCombination());
                whatToSay += newLine + clue;
                updateRange(computerGuess, clue);
                break;
        }
        indexPlayer++;
        return whatToSay;
    }

    @Override
    public String playerPrompt() {
        String whatToSay = "";
        String newLine = System.getProperty("line.separator");
        switch (getStateOfTheGame()) {

            case START:
                whatToSay += newLine + "L'ordinateur a choisi une combinaison.";
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
                whatToDisplay = "Vous avez choisi comme combinaison secrète : " + playerCombination + ".";
                if (playersOrder[1] == Enum.Players.PLAYER) {
                    setStateOfTheGame(Enum.StateOfTheGame.RUN);
                }
                break;
            case RUN:
                isCombinationCorrect(playerCombination);
                whatToDisplay = compareGuessWithCombination(playerCombination, getComputerCombination());
                break;
        }
        indexPlayer++;
        return whatToDisplay;
    }

    @Override
    public void endMessage() {

        switch (getWhoIsToPlay()) {
            case COMPUTER:
                setEndMessage("Bravo l'ordinateur a découvert la combinaison du joueur.");
                break;
            case PLAYER:
                setEndMessage("Bravo vous avez découvert la combinaison de l'ordinateur.");
        }

    }


}