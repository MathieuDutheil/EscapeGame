public class EscapeGame {
    private int gameMode;
    private int secretCombination;

    public void selectGameMode() {
        gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 1, 3);
    }

    public void startChallengerMode() {
    gameMode = Utilities.getRandomNumberInRange(0,9999);
        System.out.println(gameMode);
    }

    public void startDefenderMode() {

    }

    public void startDualMode() {

    }

    public void runGame() {
        System.out.println("Bienvenue dans Escape Game ONLINE");
        selectGameMode();
        switch (gameMode) {
            case 1:
                startChallengerMode();
                break;
            case 2:
                startDefenderMode();
                break;
            default:
                startDualMode();
                break;

        }

    }
}
