import java.util.Scanner;

public class IHM {
    private static Scanner sc = new Scanner(System.in);

    public IHM() {
        runIHM();
    }

    public void runIHM() {
        int restart;
        do {
            int gameMode = Utilities.askAnInt("À quels modes du jeu souhaitez-vous jouer ? (1 : Challenger, 2 : Défenseur, 3 : Duel)", 0, 3);

            do {
                AbstractGame game;
                switch (gameMode) {
                    case 1:
                        game = new ChallengerMode();
                        break;
                    case 2:
                        game = new DefenderMode();
                        break;
                    default:
                        game = new DualMode();
                        break;
                }

                do {
                    System.out.println(game.getStateOfTheGame());
                    game.whoIsToPlay();
                    System.out.println(game.getWhoIsToPlay());
                    if (game.getWhoIsToPlay() == AbstractGame.Players.COMPUTER) {
                        System.out.println(game.computerTurn());

                    } else if (game.getWhoIsToPlay() == AbstractGame.Players.PLAYER) {
                        //playerIntro
                        System.out.println(game.playerTurn());

                        //ask
                        if (game.getWhatIsAsked() == AbstractGame.WhatIsAsked.COMBINATION) {
                            String combinationAsked = "";
                            do {
                                combinationAsked = sc.nextLine();
                            } while (!game.isCombinationCorrect(combinationAsked));

                            //what to do with combinationAsked
                            System.out.println(game.whatToDoWithAsk(combinationAsked));
                        }

                    }
                } while (!(game.getStateOfTheGame() == AbstractGame.StateOfTheGame.CLOSE_GAME));

                restart = Utilities.askAnInt("Voulez-vous : 1 - Rejouer au même mode, 2 - Lancer un autre mode, 3 - Quitter l'application.", 1, 3);
            } while (restart == 1);
        } while (restart == 2);
    }
}
