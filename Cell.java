package edu.kit.informatik;

/**
 * The Cell of a board
 * 
 * @author Mohammed Anas Ejjalili
 * @version 1.0
 *
 */
public class Cell {
    private final int rowNb;
    private final int columnNb;
    private String pawn;

    /**
     * The constructor for the Cell class.
     * Creates a cell located at {@code row:column} in the board
     * 
     * @param row
     *            The new cell's row number.
     * @param column
     *            The new cell's column number.
     */
    public Cell(int row, int column) {
        this.rowNb = row;
        this.columnNb = column;
        //each cell has at the beginning the default pawn mark "**"
        this.pawn = "**";
    }

    /**
     * @return the pawn in this Cell ("**","P1","P2")
     */
    public String getPawn() {
        return pawn;
    }

    /**
     * Sets the pawn of the cell (from the pawn of the current player)
     * 
     * @param pawn
     *            Pawn to be set
     */
    public void setPawn(String pawn) {
        this.pawn = pawn;
    }

    /**
     * @return The row's number of this cell
     */
    public int getRow() {
        return rowNb;
    }

    /**
     * @return The column's number of this cell
     */
    public int getColumn() {
        return columnNb;
    }

}
