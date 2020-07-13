package edu.kit.informatik;

/**
 * This is a subclass of the StandardMode. It inherits all the methods and
 * attributes from its parent class. It adds also a method that flips the whole
 * board
 * 
 * @author Mohammed Anas Ejjalili
 * @version 1.0
 *
 */
public class FlipMode extends StandardMode {

    /**
     * The constructor for the flip mode.
     * 
     * @param numberOfPawns
     *            The number of pawns of each player
     */
    public FlipMode(String numberOfPawns) {
        super(numberOfPawns);
    }

    /**
     * this method flips the whole board
     */
    public void flip() {
        for (int i = 0; i < 7; i++)
            reverseColumn(i);
        if (!testGame()) {
            Terminal.printLine("OK");
            switchPlayer();
        }
    }

    /**
     * An overloaded method from the parent class.It takes the pawns and throw it in
     * the column.
     * 
     * @param pawn
     *            The pawn mark in the cell
     * @param column
     *            The column's number
     */
    private void dropIn(String pawn, int column) {
        for (int i = 7; i >= 0; i--) {
            Cell[] array = board.getColumn(column);
            if (array[i].getPawn() == "**") {
                board.getCell(i, column).setPawn(pawn);
                break;
            }
        }
    }

    /**
     * Flips a single column. it makes a copy of the pawns of this column, reset it
     * and drop the element in a reversed order
     * 
     * @param column
     *            The column's number
     */
    public void reverseColumn(int column) {
        String[] temp = new String[8];
        for (int i = 0; i <= 7; i++) {
            if (board.getColumn(column)[i].getPawn().matches("P1|P2"))
                temp[i] = board.getColumn(column)[i].getPawn();
            else
                temp[i] = "**";
        }
        board.resetColumn(column);
        for (int j = 0; j <= 7; j++)
            if (!temp[j].equals("**"))
                dropIn(temp[j], column);
    }

}
