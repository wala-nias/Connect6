package edu.kit.informatik;

/**
 * The Player of Connectfour
 * 
 * @see StandardMode
 * 
 * @author ufeqj
 * @version 1.0
 */

public class Player {
    private static int playerCounter = 0;
    private String pawn;
    private int numberOfPawns;

    /**
     * The constructor for the Player class.
     * Creates a player with a number of pawns. The first one with the pawn's mark
     * "P2", the second with "P2."
     * 
     * @param numberOfPawns
     *            the number of pawns.
     * 
     */
    public Player(int numberOfPawns) {
        playerCounter++;
        this.numberOfPawns = numberOfPawns;
        if (playerCounter == 1)
            this.pawn = "P1";
        else if (playerCounter == 2)
            this.pawn = "P2";
    }

    /**
     * @return This pawn's name.
     */
    public String getPawn() {
        return pawn;
    }

    /**
     * Decrement the number of pawns
     */
    public void pawnDroped() {
        numberOfPawns--;
    }

    /**
     * @return number of remaining pawns of this player
     */
    public int getNumberOfPawns() {
        return numberOfPawns;
    }

}
