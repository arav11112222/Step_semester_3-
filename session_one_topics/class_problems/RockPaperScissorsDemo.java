package session_one_topics.class_problems;

/**
 * Problem 1: Rock-Paper-Scissors Game
 * Plays N rounds, prints a round-by-round table, then a final summary with win %.
 * Uses a fixed sequence of moves for a reproducible demo (swap in java.util.Random
 * for the computer's move to make it genuinely random).
 */
public class RockPaperScissorsDemo {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        boolean playerWins = (playerMove.equals("Rock") && computerMove.equals("Scissors"))
                || (playerMove.equals("Paper") && computerMove.equals("Rock"))
                || (playerMove.equals("Scissors") && computerMove.equals("Paper"));

        return playerWins ? "Player Wins" : "Computer Wins";
    }

    public static void main(String[] args) {
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Paper", "Rock"};
        String[] computerMoves = {"Scissors", "Paper", "Rock", "Rock", "Paper"};

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("Round | Player Move | Computer Move | Result");

        for (int i = 0; i < playerMoves.length; i++) {
            String result = playRound(playerMoves[i], computerMoves[i]);
            System.out.println((i + 1) + " | " + playerMoves[i] + " | " + computerMoves[i] + " | " + result);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        double winPercent = (wins / (double) playerMoves.length) * 100;
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws
                + " | Win % = " + winPercent + "%");
    }
}
