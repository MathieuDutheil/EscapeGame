import org.apache.log4j.Logger;

import java.util.Arrays;

public class DualMode extends AbstractGame {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(DualMode.class);
    private final static int NB_PLAYERS = 2;
    private final Enum.Players[] playersOrder = new Enum.Players[NB_PLAYERS];
    private int indexPlayer;
    String newLine = System.getProperty("line.separator");

    public DualMode() {
        LOGGER.info("DualMode's constructor started");
        LOGGER.debug("NB_PLAYERS = " + NB_PLAYERS);
        indexPlayer = 0;
        LOGGER.debug("indexPlayer = " + indexPlayer);
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
        LOGGER.debug("playersOrder = " + Arrays.toString(playersOrder));
        setStartMessage(startMessageBuild);
        LOGGER.info("DualMode's constructor terminated");
    }

    @Override
    public Enum.Players getWhoIsToPlay() {
        LOGGER.trace("Getter getWhoIsToPlay started");
        indexPlayer = indexPlayer % NB_PLAYERS;
        LOGGER.debug("indexPlayer = " + indexPlayer);
        Enum.Players whoIsToPlay = playersOrder[indexPlayer];
        LOGGER.debug("whoIsToPlay = " + whoIsToPlay);
        LOGGER.trace("Getter getWhoIsToPlay terminated");
        return whoIsToPlay;
    }


    @Override
    public String computerTurn() {
        LOGGER.trace("method computerTurn started");
        String whatToSay = "";
        String computerGuess = "";
        String clue = "";
        switch (getStateOfTheGame()) {

            case START:
                whatToSay = "L'ordinateur a choisi une combinaison secrète.";
                if (playersOrder[1] == Enum.Players.COMPUTER) {
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
        LOGGER.debug("whatToSay = " + whatToSay);
        indexPlayer++;
        LOGGER.debug("indexPlayer = " + indexPlayer);
        LOGGER.trace("method computerTurn terminated");
        return whatToSay;
    }

    @Override
    public String playerPrompt() {
        LOGGER.trace("method playerPrompt started");
        String whatToSay = "";
        switch (getStateOfTheGame()) {

            case START:
                whatToSay = "Quelle combinaison secrète choisissez-vous ?";
                break;
            case RUN:

                whatToSay = "À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?";
                if (isDeveloperMode()) {
                    whatToSay += newLine + "Le mode développeur est activé, la combinaison secrète de l'ordinateur est " + getComputerCombination();
                }
                break;
        }
        LOGGER.debug("whatToSay = " + whatToSay);
        LOGGER.trace("method playerPrompt terminated");
        return whatToSay;
    }

    @Override
    public String playerTurn(String playerCombination) throws CombinationIncorrectException {
        String whatToDisplay = "";
        LOGGER.trace("method playerTurn started");
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
        LOGGER.debug("whatToDisplay = " + whatToDisplay);
        indexPlayer++;
        LOGGER.debug("indexPlayer = " + indexPlayer);
        LOGGER.trace("method playerTurn terminated");
        return whatToDisplay;
    }

    @Override
    public void endMessage() {
        LOGGER.trace("method endMessage started");
        switch (getWhoIsToPlay()) {
            case COMPUTER:
                setEndMessage("Bravo l'ordinateur a découvert la combinaison du joueur. Sa combinaison secrète était " + getComputerCombination() + ".");
                break;
            case PLAYER:
                setEndMessage("Bravo vous avez découvert la combinaison de l'ordinateur.");
        }
        LOGGER.trace("method endMessage terminated");
    }


}