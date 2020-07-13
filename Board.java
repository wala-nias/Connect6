package edu.kit.informatik;

/**
 * The board of the game.
 * 
 * @author Mohammed Anas Ejjalili
 * @version 1.0
 *
 */
public class Board {
    private final Cell[][] board;

    /**
     * The Constructor for the board class. Creates a Board with 8*8 Cells (@see
     * Cell)
     */
    public Board() {
        this.board = new Cell[8][8];
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                board[row][column] = new Cell(row, column);
            }
        }
    }

    /**
     * Gets the Cell with the given coordinate
     * 
     * @param row
     *            The row's index
     * @param column
     *            The column's index
     * @return The cell with the corresponding coordinate
     */
    public Cell getCell(int row, int column) {
        return board[row][column];
    }

    /**
     * Gets the column (array of cells) from the given column index. The same
     * numbering as in the assignment.
     * 
     * @param column
     *            The column's index
     * @return An array with the cells in this column
     */
    public Cell[] getColumn(int column) {
        Cell[] array = new Cell[8];
        for (int j = 0; j < 8; j++) {
            array[j] = new Cell(j, column);
        }
        for (int i = 0; i < 8; i++) {
            array[i] = board[i][column];
        }
        return array;
    }

    /**
     * Resets all the column's fields with "**".
     * 
     * @param column
     *            The column's index
     */
    public void resetColumn(int column) {
        for (int i = 0; i <= 7; i++) {
            board[i][column].setPawn("**");
        }
    }

    /**
     * Gets the cell's content from the whole column in a String.
     * 
     * @param column
     *            The column's index
     * @return A String with the cells content(pawns or "**")
     */
    public String getColumnString(int column) {
        String columnToString = "";
        for (int i = 0; i < 8; i++)
            columnToString += board[i][column].getPawn() + " ";
        return columnToString;
    }

    /**
     * Gets the row (array of cells) from the given row index. The same numbering as
     * in the assignment .
     * 
     * @param row
     *            The row's number
     * @return An array with the cells of this row.
     */
    public Cell[] getRow(int row) {
        Cell[] array = new Cell[8];
        for (int i = 0; i < 8; i++)
            array[i] = board[row][i];
        return array;
    }

    /**
     * Gets the cell's content from the whole row in a String.
     * 
     * @param row
     *            The row's index
     * @return A String with the cells content(pawns or "**")
     */
    public String getRowString(int row) {
        String rowToString = "";
        for (int i = 0; i < 8; i++)
            rowToString += board[row][i].getPawn() + " ";
        return rowToString;
    }

    /**
     * Gets the cell's content from the whole right diagonal in a String. The right
     * diagonals starting from the left side have the index from 0-7. The right
     * diagonals starting from the bottom from 8-14.
     * 
     * @param diagonalNb
     *            The index of the diagonal.
     * @return A String with the cells content(pawns or "**")
     */
    public String getRightDiagonalString(int diagonalNb) {
        String diagonal = "";
        // Diagonal that begins from the left side of the board
        if (diagonalNb < 8) {
            int i = diagonalNb;
            int j = 0;
            while (i >= 0) {
                diagonal += board[i][j].getPawn();
                i--;
                j++;
            }
            return diagonal;
            // Diagonal that begins from the lower side of the board
        } else if (diagonalNb >= 8) {
            int i = 7;
            int j = diagonalNb - 7;
            while (j <= 7) {
                diagonal += board[i][j].getPawn();
                i--;
                j++;
            }
            return diagonal;
        }
        return diagonal;

    }

    /**
     * Gets the cell's content from the whole left diagonal in a String The left
     * diagonals starting from the left side have the index from 0-7. The left
     * diagonals starting from the top side have the index from 8-14.
     * 
     * @param diagonalNb
     *            The number of the diagonal
     * @return A String with the cells content(pawns or "**")
     */
    public String getLeftDiagonalString(int diagonalNb) {
        String diagonal = "";
        // Diagonal that begins from the left side of the board
        if (diagonalNb < 8) {
            int i = diagonalNb;
            int j = 0;
            while (i <= 7) {
                diagonal += board[i][j].getPawn();
                i++;
                j++;
            }
            return diagonal;
            // Diagonal that begins from the top side of the board
        } else if (diagonalNb >= 8) {
            int i = 0;
            int j = diagonalNb - 7;
            while (j <= 7) {
                diagonal += board[i][j].getPawn();
                i++;
                j++;
            }
            return diagonal;
        }

        return diagonal;

    }

    /**
     * Checks if the column with the given index is full
     * 
     * @param column
     *            The column's index
     * @return true only if this column is full
     */
    public boolean columnIsFull(int column) {
        Cell[] array = getColumn(column);
        if (array[0].getPawn() != "**")
            return true;
        return false;
    }

    /**
     * Checks if the whole board is full
     * 
     * @return (@code true) only if the board is full
     */
    public boolean boardIsFull() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (board[i][j].getPawn() == "**")
                    return false;
        return true;
    }

}
