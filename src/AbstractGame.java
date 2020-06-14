import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public abstract class AbstractGame {

    // Variable
    private Enum.Players whoIsToPlay;
    private Enum.StateOfTheGame stateOfTheGame;
    private static boolean propertyLoaded = false;
    private static int lengthCombination = 4;
    private static int maxNumberOfTrials = 5;
    private static boolean developerMode = false;
    private String computerCombination = "";
    private String playerCombination = "";
    private final int[] minRange;
    private final int[] maxRange;
    private boolean partyWon = false;
    private final static int MIN_LENGTH_COMBINATION = 1;
    private final static int MAX_LENGTH_COMBINATION = 10;
    private final static int MIN_NUMBER_OF_TRIALS = 3;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);
    private String startMessage = "";
    private String endMessage = "";

    public AbstractGame() {
        LOGGER.info("AbstractGame's constructor started");
        if (!propertyLoaded) {
            loadProperties();
            propertyLoaded = true;
            LOGGER.info("propertyLoaded = " + propertyLoaded);
        }
        minRange = new int[lengthCombination];
        LOGGER.debug("minRange = " + Arrays.toString(minRange));
        maxRange = new int[lengthCombination];
        Arrays.fill(maxRange, 9);
        LOGGER.debug("maxRange = " + Arrays.toString(maxRange));
        stateOfTheGame = Enum.StateOfTheGame.START;
        LOGGER.debug("stateOfTheGame = " + stateOfTheGame);
        LOGGER.info("AbstractGame's constructor terminated");
    }

    // Getter
    public boolean isPartyWon() {
        LOGGER.trace("Getter isPartyWon started");
        LOGGER.debug("partyWon = " + partyWon);
        LOGGER.trace("Getter isPartyWon started");
        return partyWon;
    }

    protected int getMaxNumberOfTrials() {
        LOGGER.trace("Getter getMaxNumberOfTrials started");
        LOGGER.debug("maxNumberOfTrials = " + maxNumberOfTrials);
        LOGGER.trace("Getter getMaxNumberOfTrials started");
        return maxNumberOfTrials;
    }

    public String getStartMessage() {
        LOGGER.trace("Getter getStartMessage started");
        LOGGER.debug("startMessage = " + startMessage);
        LOGGER.trace("Getter getStartMessage terminated");
        return startMessage;
    }

    protected Enum.Players getWhoIsToPlay() {
        LOGGER.trace("Getter getWhoIsToPlay started");
        LOGGER.debug("whoIsToPlay = " + whoIsToPlay);
        LOGGER.trace("Getter getWhoIsToPlay terminated");
        return whoIsToPlay;
    }

    protected Enum.StateOfTheGame getStateOfTheGame() {
        LOGGER.trace("Getter getStateOfTheGame started");
        LOGGER.debug("stateOfTheGame = " + stateOfTheGame);
        LOGGER.trace("Getter getStateOfTheGame terminated");
        return stateOfTheGame;
    }

    protected String getComputerCombination() {
        LOGGER.trace("Getter getComputerCombination started");
        LOGGER.debug("computerCombination = " + computerCombination);
        LOGGER.trace("Getter getComputerCombination terminated");
        return computerCombination;
    }

    public boolean isDeveloperMode() {
        LOGGER.trace("Getter isDeveloperMode started");
        LOGGER.debug("developerMode = " + developerMode);
        LOGGER.trace("Getter isDeveloperMode terminated");
        return developerMode;
    }

    public String getPlayerCombination() {
        LOGGER.trace("Getter getPlayerCombination started");
        LOGGER.debug("playerCombination = " + playerCombination);
        LOGGER.trace("Getter getPlayerCombination terminated");
        return playerCombination;
    }

    public String getEndMessage() {
        LOGGER.trace("Getter getEndMessage started");
        LOGGER.debug("endMessage = " + endMessage);
        LOGGER.trace("Getter getEndMessage terminated");
        return endMessage;
    }

    // Setter
    protected void setComputerCombination(String computerCombination) {
        LOGGER.trace("Setter setComputerCombination started");
        this.computerCombination = computerCombination;
        LOGGER.debug("computerCombination = " + computerCombination);
        LOGGER.trace("Setter setComputerCombination terminated");
    }

    protected void setStateOfTheGame(Enum.StateOfTheGame stateOfTheGame) {
        LOGGER.trace("Setter setStateOfTheGame started");
        this.stateOfTheGame = stateOfTheGame;
        LOGGER.debug("stateOfTheGame = " + stateOfTheGame);
        LOGGER.trace("Setter setStateOfTheGame terminated");
    }

    protected void setStartMessage(String startMessage) {

        LOGGER.trace("Setter setStartMessage started");
        this.startMessage = startMessage;
        LOGGER.debug("startMessage = " + startMessage);
        LOGGER.trace("Setter setStartMessage terminated");
    }

    protected void setWhoIsToPlay(Enum.Players whoIsToPlay) {
        LOGGER.trace("Setter setWhoIsToPlay started");
        this.whoIsToPlay = whoIsToPlay;
        LOGGER.debug("whoIsToPlay = " + whoIsToPlay);
        LOGGER.trace("Setter setWhoIsToPlay terminated");
    }

    protected void setPlayerCombination(String playerCombination) {
        LOGGER.trace("Setter setPlayerCombination started");
        this.playerCombination = playerCombination;
        LOGGER.debug("playerCombination = " + playerCombination);
        LOGGER.trace("Setter setPlayerCombination terminated");
    }

    public void setEndMessage(String endMessage) {

        LOGGER.trace("Setter setEndMessage started");
        this.endMessage = endMessage;
        LOGGER.debug("endMessage = " + endMessage);
        LOGGER.trace("Setter setEndMessage terminated");
    }

    //loadProperties
    void loadProperties() {
        LOGGER.trace("method loadProperties started");

        final Properties prop = new Properties();

        try (InputStream input = getClass().getResourceAsStream("config.properties")) {

            try {

                prop.load(input);

                try {
                    int checkLengthCombination = Integer.parseInt(prop.getProperty("LENGTH_COMBINATION"));
                    if (checkLengthCombination >= MIN_LENGTH_COMBINATION && checkLengthCombination <= MAX_LENGTH_COMBINATION) {
                        lengthCombination = checkLengthCombination;
                        LOGGER.debug("lengthCombination = " + lengthCombination);
                    }
                } catch (NumberFormatException ex) {
                    LOGGER.warn(ex.toString());
                    LOGGER.debug("lengthCombination put to default value = " + lengthCombination);
                }

                try {
                    int checkNumberOfTrials = Integer.parseInt(prop.getProperty("MAX_NUMBER_OF_TRIALS"));
                    if (checkNumberOfTrials >= MIN_NUMBER_OF_TRIALS) {
                        maxNumberOfTrials = checkNumberOfTrials;
                        LOGGER.debug("maxNumberOfTrials = " + maxNumberOfTrials);
                    }
                } catch (NumberFormatException ex) {
                    LOGGER.warn(ex.toString());
                    LOGGER.debug("maxNumberOfTrials put to default value = " + maxNumberOfTrials);

                }

                developerMode = Boolean.parseBoolean(prop.getProperty("DEVELOPER_MODE"));
                LOGGER.debug("developerMode = " + developerMode);
            } catch (FileNotFoundException ex) {
                LOGGER.warn(ex.toString());
                LOGGER.debug("lengthCombination put to default value = " + lengthCombination);
                LOGGER.debug("maxNumberOfTrials put to default value = " + maxNumberOfTrials);
                LOGGER.debug("developerMode put to default value = " + developerMode);

            }
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
            LOGGER.debug("lengthCombination put to default value = " + lengthCombination);
            LOGGER.debug("maxNumberOfTrials put to default value = " + maxNumberOfTrials);
            LOGGER.debug("developerMode put to default value = " + developerMode);

        }
        LOGGER.trace("method loadProperties finished");
    }

    //Abstract Method

    public abstract String computerTurn();

    public abstract String playerPrompt();

    public abstract String playerTurn(String ask) throws CombinationIncorrectException;

    public abstract void endMessage();

    //Method
    boolean isCombinationCorrect(String combination) throws CombinationIncorrectException {
        LOGGER.trace("method isCombinationCorrect started");
        LOGGER.debug("combination = " + combination);
        if (combination.length() != lengthCombination) {
            LOGGER.warn("combination's length is not good = " + combination.length() + ", lengthCombination = " + lengthCombination);
            throw new CombinationIncorrectException("Attention votre saisie doit être de " + lengthCombination + " caractères.");
        }

        for (int i = 0; i < combination.length(); i++) {
            char c = combination.charAt(i);
            if (c < '0' || c > '9') {
                LOGGER.warn("character n°" + (i + 1) + " is not a number = " + combination.charAt(i));
                throw new CombinationIncorrectException("Attention votre  caractère n°" + (i + 1) + " n'est pas un chiffre.");
            }
        }
        LOGGER.trace("method isCombinationCorrect finished");
        return true;
    }

    protected String generateNextComputerCombination() {
        LOGGER.trace("method generateNextComputerCombination started");
        String nextComputerCombination = "";
        for (int i = 0; i < lengthCombination; i++) {
            nextComputerCombination += Utilities.getRandomNumberInRange(minRange[i], maxRange[i]);
        }

        LOGGER.debug("nextComputerCombination = " + nextComputerCombination);
        LOGGER.trace("method generateNextComputerCombination finished");
        return nextComputerCombination;
    }


    protected String compareGuessWithCombination(String guess, String secret) {
        LOGGER.trace("method compareGuessWithCombination started");
        LOGGER.debug("guess = " + guess + " ,secret = " + secret);
        String clue = "";
        int nbGoodResponses = 0;
        for (int i = 0; i < lengthCombination; i++) {

            if (guess.charAt(i) < secret.charAt(i)) {
                clue += "+";

            } else if (guess.charAt(i) > secret.charAt(i)) {
                clue += "-";

            } else {
                clue += "=";
                nbGoodResponses++;
            }
        }
        LOGGER.debug("clue = " + clue);
        LOGGER.debug("nbGoodResponses = " + nbGoodResponses);
        if (nbGoodResponses == lengthCombination) {
            partyWon = true;
            LOGGER.debug("partyWon = " + partyWon);
            endMessage();
            stateOfTheGame = Enum.StateOfTheGame.END;
            LOGGER.debug("stateOfTheGame = " + stateOfTheGame);
        }
        LOGGER.trace("method compareGuessWithCombination terminated");
        return clue;
    }

    protected void updateRange(String oldGuess, String indication) {
        LOGGER.trace("method updateRange started");
        LOGGER.debug("oldGuess = " + oldGuess + " ,indication = " + indication);
        for (int i = 0; i < lengthCombination; i++) {
            if (indication.charAt(i) == '+') {
                minRange[i] = ((oldGuess.charAt(i) - '0') + 1);

            } else if (indication.charAt(i) == '-') {
                maxRange[i] = ((oldGuess.charAt(i) - '0') - 1);

            } else if (indication.charAt(i) == '=') {
                minRange[i] = (oldGuess.charAt(i) - '0');
                maxRange[i] = (oldGuess.charAt(i) - '0');

            }
        }
        LOGGER.debug("minRange = " + Arrays.toString(minRange));
        LOGGER.debug("maxRange = " + Arrays.toString(maxRange));
        LOGGER.trace("method updateRange finished");
    }


}










