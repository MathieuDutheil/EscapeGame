public class ChallengerMode extends AbstractGame {


    @Override
    public void runGame() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        initializeRange(0,9);
        String computerCombination = generateNextComputerCombination();
        int numberOfTrials = 0;
        do {
            String challengerPlayerCombination = getPlayerCombination("Essayer de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            compareGuessWithCombination(challengerPlayerCombination, computerCombination, "le Joueur");
            numberOfTrials++;
        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
    }
}
