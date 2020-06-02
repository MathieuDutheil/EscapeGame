import org.apache.log4j.Logger;

import java.util.Scanner;

public class IHM {
    private static Scanner sc = new Scanner(System.in);
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(IHM.class);

    public IHM() throws CombinationIncorrectException {
        runIHM();
    }

    public void runIHM() throws CombinationIncorrectException {
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
                    switch (game.whoIsToPlay()) {
                        case COMPUTER:
                            System.out.println(game.computerTurn());
                            break;

                        case PLAYER:
                            System.out.println(game.playerPrompt());
                            break;
                    }

                    if (game.getWhatIsAsked() == Enum.WhatIsAsked.COMBINATION) {                      // switch bon choix ?
                        String combinationAsked = "";
                        combinationAsked = sc.nextLine();
                        try {
                            System.out.println(game.playerTurn(combinationAsked));
                        } catch (CombinationIncorrectException ex) {
                            LOGGER.error(ex.toString());
                            System.out.println(ex.getMessage());
                        }
                    }

                } while (!(game.getStateOfTheGame() == Enum.StateOfTheGame.END));

                System.out.println(game.endMessage());

                restart = Utilities.askAnInt("Voulez-vous : 1 - Rejouer au même mode, 2 - Lancer un autre mode, 3 - Quitter l'application.", 1, 3);
            } while (restart == 1);
        } while (restart == 2);
    }
}
