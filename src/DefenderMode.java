public class DefenderMode extends AbstractGame {


    @Override
    public void runGame() {
        String computerGuess = generateCombination();
        String clew = "";
        System.out.println("Vous avez choisi le mode Défenseur, l'ordinateur va devoir deviner votre combinaison secrète.");
        String playerCombination = getPlayerCombination("Quelle combinaison choisissez-vous ?");
        System.out.println("L'ordinateur va maintenant essayer de deviner votre combinaison secrète.");
        do {
            computerGuess = buildNextComputerAnswer(clew, computerGuess);
            System.out.println("L'Ordinateur a choisi : " + computerGuess);
            clew = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur");

        } while (!isPartyWon());
    }
}
