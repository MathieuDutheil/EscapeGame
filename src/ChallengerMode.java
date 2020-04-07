public class ChallengerMode extends AbstractGame {


    @Override
    public void runGame() {
        System.out.println("Vous avez choisi le mode Challenger, vous allez devoir deviner la combinaison secrète généré par l'ordinateur.");
        String computerCombination = generateCombination();

        do {
            String challengerPlayerCombination = getPlayerCombination("Essayer de deviner la combinaison secrète de l'ordinateur. Votre proposition ?");
            compareGuessWithCombination(challengerPlayerCombination, computerCombination, "le Joueur");
        } while (!isPartyWon());
    }
}
