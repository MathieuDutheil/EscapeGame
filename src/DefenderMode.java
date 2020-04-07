public class DefenderMode extends AbstractGame {


    @Override
    public void runGame() {

        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        String playerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");
        String computerGuess = generateCombination();
        String clew = "";
        initializeRange(0, 9);

        do {
            computerGuess = buildNextComputerGuess(clew, computerGuess);
            System.out.println("L'Ordinateur a choisi : " + computerGuess);
            clew = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");
        } while (!isPartyWon());
    }

}