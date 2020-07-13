package edu.kit.informatik;

/**
 * This is a subclass of the StandardMode. It inherits all the methods
 * and attributes from its parent class. It has an additional method to remove a
 * cell from the last row.
 * 
 * @author Mohammed Anas Ejjalili
 * @version 1.0
 *
 */
public class RemoveMode extends StandardMode {

    /**
     * The constructor for the remove mode.
     * @param numberOfPawns
     *            The number of pawns of each player
     */
    public RemoveMode(String numberOfPawns) {
        super(numberOfPawns);
    }

    /**
     * Remove the last element of the column and switch the player after a valid
     * turns if the game still not ended
     * 
     * @param columnNb
     *            The column's number
     */
    public void remove(String columnNb) {
        try {
            int column = Integer.parseInt(columnNb);
            if (lastElementRemoved(column) && !testGame()) {
                Terminal.printLine("OK");
                switchPlayer();
            }
        } catch (NumberFormatException ex) {
            Terminal.printError("Give a valid column index");
        }
    }
  
    /**
     * Remove the last pawn of the current player of this column and shift the rest of the
     * column.
     * 
     * @param column
     *            The column's number
     * @return true if the element is successfully removed
     */
    public boolean lastElementRemoved(int column) {
        if (board.getColumn(column)[7].getPawn().equals(getCurrentPlayer().getPawn())) {
            for (int i = 6; i >= 0; i--) {
                board.getCell(i + 1, column).setPawn(board.getCell(i, column).getPawn());
            }
            if (board.getColumn(column)[0].getPawn().matches("P1|P2")) {
                for (int j = 0; j <= 7; j++) {
                    if (board.getCell(j, column).getPawn().matches("P1|P2")) {
                        board.getCell(j, column).setPawn("**");
                        break;
                    }
                }
            }
            return true;
        } else {
            Terminal.printError("Please choose a column with your pawn");
            return false;
        }

    }

}
