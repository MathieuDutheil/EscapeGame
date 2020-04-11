public class DualMode extends AbstractGame {

    @Override
    public void runGame() {

        System.out.println("Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison secrète de l'ordinateur avant qu'il ne devine la votre.");
        String playerCombination = getPlayerCombination("Quelle combinaison secrète choisissez-vous ?");
        System.out.println("L'Ordinateur choisit une combinaison.");
        initializeRange(0,9);
        String computerCombination = generateNextComputerCombination();
        String playerGuess = "";
        String clew = "";
        String computerGuess = computerCombination;
        int numberOfTrials = 0;

        do {

            playerGuess = getPlayerCombination("À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            compareGuessWithCombination(playerGuess, computerCombination, "le Joueur");


            System.out.println("L'Ordinateur a choisi : " + computerGuess);
            System.out.println("combinaison joueur = " + playerCombination);
            clew = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");
            updateRange(computerGuess, clew);
            computerGuess = generateNextComputerCombination();

            numberOfTrials++;

        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
    }
}
