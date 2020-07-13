package edu.kit.informatik;

/**
 * Contains the playing board, the both players and performs the standard
 * methods used in all game modes
 * 
 * @author Mohammed Anas Ejjalili
 * @version 1.0
 */
public class StandardMode {

    /**
     * protected to be accessed from the subclasses (Flipmode|Removemode)
     */
    protected Board board;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player currentPlayer;
    private Player nextPlayer;
    private Player winner;
    private boolean notFinished;

    /**
     * The constructor for the standard mode. It initializes a board with two
     * players
     * 
     * @param numberOfPawns
     *            The number of pawns for each player(args[1])
     */
    public StandardMode(String numberOfPawns) {
        this.board = new Board();
        this.firstPlayer = new Player(Integer.parseInt(numberOfPawns));
        this.secondPlayer = new Player(Integer.parseInt(numberOfPawns));
        this.currentPlayer = firstPlayer;
        this.nextPlayer = secondPlayer;
        this.notFinished = true;
    }

    /**
     * Gets the player corresponding to the given pawn
     * 
     * @param pawn
     *            The pawn mark of the player
     * @return The player with the corresponding pawn mark
     */
    public Player getPlayer(String pawn) {
        if (pawn.equals("P1"))
            return this.firstPlayer;
        else if (pawn.equals("P2"))
            return this.secondPlayer;
        else
            return null;
    }

    /**
     * Gets the board (Grid)
     * 
     * @return the board of the game
     */
    public Board getboard() {
        return this.board;
    }

    /**
     * Prints the board content.
     */
    public void printBoard() {
        for (int i = 0; i < 8; i++)
            Terminal.printLine(this.board.getRowString(i));
    }

    /**
     * Shows the state of the cell with the given coordinate (column;row)
     * 
     * @param input
     *            the coordinate of the cell separated with ";"
     * 
     */
    public void state(String input) {

        String[] coordinate = input.split(";", 2);
        int column = Integer.parseInt(coordinate[0]);
        int row = Integer.parseInt(coordinate[1]);
        Terminal.printLine(board.getCell(row, column).getPawn());
    }

    /**
     * @return the Winner of the game| null if there is no winner
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Sets the Winner of the game
     * 
     * @param pawn
     *            The pawn mark of the winner
     */
    public void setWinner(String pawn) {
        this.winner = getPlayer(pawn);
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer
     *            the player with the right turn
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * switch the player after a valid turn
     */
    public void switchPlayer() {
        if (this.currentPlayer.getPawn().equals("P1")) {
            this.currentPlayer = this.secondPlayer;
            setNextPlayer();
        }

        else {
            this.currentPlayer = this.firstPlayer;
            setNextPlayer();
        }

    }

    /**
     * Sets the next player from the current player.This method helps to check if
     * the next player has no pawns anymore
     */
    public void setNextPlayer() {
        if (this.currentPlayer.getPawn().equals("P1"))
            this.nextPlayer = this.secondPlayer;
        if (this.currentPlayer.getPawn().equals("P2"))
            this.nextPlayer = this.firstPlayer;
    }

    /**
     * Check if game ended
     * 
     * @return true if it's not finished
     */
    public boolean isNotFinished() {
        return notFinished;
    }

    /**
     * Set the variable false if the game ended
     * 
     * @param notFinished
     *            (@code false) if game ended
     */
    public void setNotFinished(boolean notFinished) {
        this.notFinished = notFinished;
    }

    /**
     * Prints the number of the remaining pawns of the current player
     */
    public void token() {
        Terminal.printLine(currentPlayer.getNumberOfPawns());
    }

    /**
     * print draw
     */
    public void draw() {
        Terminal.printLine("draw");
    }

    /**
     * Check the rows, columns and diagonals and count the ranges containing 4 of
     * the same pawn's mark aligned of each player. It also sets the winner with the
     * highest number of ranged lines, and prints draw in case the same number of
     * lines are aligned.
     * 
     * @return true if the four are connected or in draw case.
     */
    public boolean fourAreConnected() {
        int counterP1 = 0;
        int counterP2 = 0;
        for (int i = 0; i < 8; i++) {
            if (board.getColumnString(i).contains("P1 P1 P1 P1") || board.getRowString(i).contains("P1 P1 P1 P1")) {
                counterP1++;
            } else if (board.getColumnString(i).contains("P2 P2 P2 P2")
                    || board.getRowString(i).contains("P2 P2 P2 P2")) {
                counterP2++;
            }
        }
        for (int j = 0; j <= 14; j++) {
            if (board.getLeftDiagonalString(j).contains("P1P1P1P1")
                    || board.getRightDiagonalString(j).contains("P1P1P1P1")) {
                counterP1++;
            } else if (board.getLeftDiagonalString(j).contains("P2P2P2P2")
                    || board.getRightDiagonalString(j).contains("P2P2P2P2")) {
                counterP2++;
            }
        }
        if (counterP1 > counterP2) {
            setWinner("P1");
            return true;
        } else if (counterP1 < counterP2) {
            setWinner("P2");
            return true;
        } else if (counterP1 != 0 && counterP1 == counterP2) {
            // in this case the winner is null
            setWinner("draw");
            return true;
        }

        return false;
    }

    /**
     * Check if the game ended. It ends game if four are connected, board is full,
     * the players dropped all their pawns, or in draw cases.
     * 
     * @return true if the game is ended
     */
    public boolean testGame() {
        if (fourAreConnected() && getWinner() != null) {
            showWinner();
            setNotFinished(false);
            return true;
        } else if (!fourAreConnected() && board.boardIsFull()) {
            draw();
            setNotFinished(false);
            return true;
        } else if (!fourAreConnected() && nextPlayer.getNumberOfPawns() == 0) {
            draw();
            setNotFinished(false);
            return true;
        } else if (fourAreConnected() && getWinner() == null) {
            draw();
            setNotFinished(false);
            return true;
        }
        return false;
    }

    /**
     * Throws a pawn if game did't end and then switch to the next player
     * 
     * @param columnNb
     *            The column's number as a String
     */
    public void throwIn(String columnNb) {

        int column = Integer.parseInt(columnNb);
        if (board.columnIsFull(column))
            Terminal.printError("Please choose a valid column, this column is full");
        else {
            dropIn(column);
            if (!testGame()) {
                Terminal.printLine("OK");
                switchPlayer();
            }
        }
    }

    /**
     * Set the pawn's mark of the current player's in the first empty cell of the
     * selected column number. And decrement the number of pawns after a drop.
     * 
     * @param column
     *            The column's number
     */
    private void dropIn(int column) {
        Cell[] array = board.getColumn(column);
        for (int i = 7; i >= 0; i--) {
            if (array[i].getPawn() == "**") {
                board.getCell(i, column).setPawn(currentPlayer.getPawn());
                currentPlayer.pawnDroped();
                break;
            }
        }
    }

    /**
     * show and print the pawn mark of the winner
     */
    public void showWinner() {
        Terminal.printLine(winner.getPawn() + " wins");
    }

}
