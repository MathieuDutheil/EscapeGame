public class Trash {
    /* TRASH TRASH TRASH TRASH TRASH
    String compareGuessWithCombination(String guess, String secret, String playerName) {
        LOGGER.trace("method compareGuessWithCombination started");
        LOGGER.debug("guess = " + guess + ", secret = " + secret);
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
        System.out.println(clue);
        if (nbGoodResponses == lengthCombination) {
            System.out.println("Bravo " + playerName + " a découvert la combinaison de son adversaire.");
            partyWon = true;
            LOGGER.debug("partyWon = " + partyWon);
        }

        LOGGER.trace("method compareGuessWithCombination finished");
        return clue;

    }

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
    public void runGame() {
        LOGGER.info("runGame method started");
        System.out.println("Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison secrète de l'ordinateur avant qu'il ne devine la votre.");
        // String playerCombination = getPlayerCombination("Quelle combinaison secrète choisissez-vous ?");
        System.out.println("L'Ordinateur choisit une combinaison.");
        String computerCombination = generateNextComputerCombination();
        String playerGuess = "";
        String clue = "";
        String computerGuess = computerCombination;

        do {

            // playerGuess = getPlayerCombination("À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            //compareGuessWithCombination(playerGuess, computerCombination, "le Joueur");

            if (!isPartyWon()) {
                System.out.println("L'Ordinateur a choisi : " + computerGuess);
                //System.out.println("combinaison joueur = " + playerCombination);
                // clue = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");
                updateRange(computerGuess, clue);
                computerGuess = generateNextComputerCombination();
            }
        } while (!isPartyWon());
        LOGGER.info("runGame method finished");
    }




    }
    */
}
