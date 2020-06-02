import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;


public abstract class AbstractGame {

    // Variable
    private Enum.Players whoIsToPlay;
    private Enum.StateOfTheGame stateOfTheGame;
    private Enum.WhatIsAsked whatIsAsked;
    private static boolean propertyLoaded = false;
    private static int lengthCombination = 4;
    private static int maxNumberOfTrials = 5;
    private static boolean developerMode = false;
    private int numberOfTrials = 0;
    private String computerCombination = "";
    private Scanner sc = new Scanner(System.in);
    private final int[] minRange;
    private final int[] maxRange;
    private boolean partyWon = false;
    private final static int MIN_LENGTH_COMBINATION = 1;
    private final static int MAX_LENGTH_COMBINATION = 10;
    private final static int MIN_NUMBER_OF_TRIALS = 3;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);

    // Constructor
    public AbstractGame() {
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
    }

    // Getter
    public boolean isPartyWon() {
        return partyWon;
    }

    protected int getMaxNumberOfTrials() {
        return maxNumberOfTrials;
    }

    public Enum.Players getWhoIsToPlay() {
        return whoIsToPlay;
    }

    public Enum.StateOfTheGame getStateOfTheGame() {
        return stateOfTheGame;
    }

    public int getNumberOfTrials() {
        return numberOfTrials;
    }

    public Enum.WhatIsAsked getWhatIsAsked() {
        return whatIsAsked;
    }

    public String getComputerCombination() {
        return computerCombination;
    }

    // Setter
    protected void setComputerCombination(String computerCombination) {
        this.computerCombination = computerCombination;
    }

    protected void setStateOfTheGame(Enum.StateOfTheGame stateOfTheGame) {
        this.stateOfTheGame = stateOfTheGame;
    }

    public void setWhoIsToPlay(Enum.Players whoIsToPlay) {
        this.whoIsToPlay = whoIsToPlay;
    }

    public void setNumberOfTrials(int numberOfTrials) {
        this.numberOfTrials = numberOfTrials;
    }

    public void setWhatIsAsked(Enum.WhatIsAsked whatIsAsked) {
        this.whatIsAsked = whatIsAsked;
    }

    void loadProperties() {
        LOGGER.trace("method loadProperties started");
        String path = new File("src/config.properties").getAbsolutePath();
        LOGGER.debug("path = " + path);
        System.out.println(path);

        final Properties prop = new Properties();

        try (InputStream input = new FileInputStream(path)) {

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
    public abstract Enum.Players whoIsToPlay();

    public abstract String computerTurn();

    public abstract String playerPrompt();

    public abstract String playerTurn(String ask) throws CombinationIncorrectException;

    public abstract String endMessage();

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


    String generateNextComputerCombination() {
        LOGGER.trace("method generateNextComputerCombination started");
        String nextComputerCombination = "";
        for (int i = 0; i < lengthCombination; i++) {
            nextComputerCombination += Utilities.getRandomNumberInRange(minRange[i], maxRange[i]);
        }
        if (developerMode) {
            System.out.println("Combinaison = " + nextComputerCombination);
            //voir pour implémenter LOGGER ici
            //voir comment virer le System.out
        }

        LOGGER.debug("nextComputerCombination = " + nextComputerCombination);
        LOGGER.trace("method generateNextComputerCombination finished");
        return nextComputerCombination;
    }


    String compareGuessWithCombination(String guess, String secret) {

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

        if (nbGoodResponses == lengthCombination) {
            partyWon = true;
            stateOfTheGame = Enum.StateOfTheGame.END;
        }
        return clue;
    }

    void updateRange(String oldGuess, String indication) {
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


    public abstract void runGame();

    String getPlayerCombination(String intro) {
        LOGGER.trace("method getPlayerCombination started");
        String playerCombination = "";
        //   do {
        System.out.println(intro);
        playerCombination = sc.nextLine();

        // } while (!isCombinationCorrect(playerCombination));
        LOGGER.debug("playerCombination = " + playerCombination);
        LOGGER.trace("method getPlayerCombination finished");
        return playerCombination;
    }
}










