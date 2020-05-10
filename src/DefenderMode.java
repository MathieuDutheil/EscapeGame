import org.apache.log4j.Logger;

public class DefenderMode extends AbstractGame {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AbstractGame.class);

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numberOfTrials++;
        } while (!isPartyWon() && numberOfTrials != getMaxNumberOfTrials());
    }

}