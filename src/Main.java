public class Main {

    public static void main(String[] args) {

        int restart;
        do {
            int gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 1, 3);
            do {
                switch (gameMode) {
                    case 1:
                        Game game = new ChallengerMode();
                        game.runGame();
                        break;
                    case 2:
                        game = new DefenderMode();
                        game.runGame();
                        break;
                    default:
                        game = new DualMode();
                        game.runGame();
                        break;
                }

                restart = Utilities.askAnInt("Voulez-vous : 1 - Rejouer au même mode, 2 - Lancer un autre mode, 3 - Quitter l'application.)", 1, 3);
            }
            while (restart == 1);
        } while (restart == 2);
    }
}

