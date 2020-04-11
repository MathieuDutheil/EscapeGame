public class DefenderMode extends AbstractGame {


    @Override
    public void runGame() {

        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        String playerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");
        String computerGuess = "";
        String clew = "";
        initializeRange(0, 9);
        int numberOfTrials = 0;

        do {
            computerGuess = generateNextComputerCombination();
            System.out.println("L'Ordinateur a choisi : " + computerGuess);
            clew = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");
            updateRange(computerGuess, clew);

            numberOfTrials++;
        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
    }

}