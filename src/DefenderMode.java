public class DefenderMode extends AbstractGame {


    @Override
    public void runGame() {

        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        String playerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");
        String computerGuess = "";
        String clue = "";
        int numberOfTrials = 0;

        do {
            computerGuess = generateNextComputerCombination();
            System.out.println("L'Ordinateur a choisi : " + computerGuess);
            clue = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");
            updateRange(computerGuess, clue);

            numberOfTrials++;
        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
    }

}