public class DualMode extends AbstractGame {

    @Override
    public void runGame() {
        System.out.println("Vous avez choisi le mode : Duel, vous allez devoir trouver la combinaison secrète de l'ordinateur avant qu'il ne devine la votre.");
        String playerCombination = getPlayerCombination("Quelle combinaison secrète choisissez-vous ?");
        System.out.println("L'ordinateur choisit une combinaison.");
        String computerCombination = generateCombination();
        
        String playerGuess = "";
        String clew = "";
        String computerGuess = "";


        do {
            playerGuess = getPlayerCombination("À votre tour de tenter de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            compareGuessWithCombination(playerGuess, computerCombination, "le Joueur");

            computerGuess = buildNextComputerAnswer(clew, computerGuess);
            System.out.println("L'ordinateur a choisi : " + computerGuess);
            System.out.println("combinaison joueur = " + playerCombination);
            clew = compareGuessWithCombination(computerGuess, playerCombination, "l'Ordinateur ");
          
        } while (!isPartyWon());
    }
}
