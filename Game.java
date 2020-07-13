package edu.kit.informatik;

/**
 * This class contains the execution of the game and methodes to tests the input
 * through readLine and command line
 * 
 * @author Mohammed Anas Ejjalili
 * @version 1.0
 */
public class Game {

    private static final int NUMBER_OF_ARGS = 2;
    private StandardMode boardGame;

    /**
     * Initializes the board game with the corresponding mode
     * 
     * @param mode
     *            The choose game mode (args[0])
     * @param numberOfPawns
     *            The number of pawns of each player (args[1])
     */
    public void initializeMode(String mode, String numberOfPawns) {
        if (mode.equals("standard"))
            boardGame = new StandardMode(numberOfPawns);
        else if (mode.equals("flip"))
            boardGame = new FlipMode(numberOfPawns);
        else if (mode.equals("remove"))
            boardGame = new RemoveMode(numberOfPawns);

    }

    /**
     * Tests the arguments given through the command line to choose the mode and
     * number of pawns.
     * 
     * @param input
     *            The argument of command line
     * @return true if the command line is in the right form
     */
    public boolean testCommandLine(String[] input) {
        try {
            if (input.length != NUMBER_OF_ARGS) {
                Terminal.printError("Please give valid Arguments: Playmode and number of pieces");
                return false;
            }

            else if (!input[0].matches("standard|flip|remove")) {
                Terminal.printError("choose a valid playmode");
                return false;
            }

            else if (Integer.parseInt(input[1]) <= 27 || Integer.parseInt(input[1]) >= 33) {
                Terminal.printError("Please choose a number 27 < p < 33");
                return false;
            }
        } catch (NumberFormatException ex) {
            Terminal.printError("Please give a valid number of pieces");
            return false;
        }
        return true;
    }

    /**
     * tests the inputs from readLine during the game.
     * 
     * @return An array with the right inputs (command + value if available)
     */
    public String[] testInput() {
        String[] input = new String[2];
        boolean right = false;
        while (!right) {
            try {
                input = Terminal.readLine().split(" ", 2);
                if (input[0].matches("flip|token|print|quit") && input.length == 1)
                    right = true;
                else if (input[0].matches("throwin|remove") && input.length == 2 && Integer.parseInt(input[1]) <= 7
                        && Integer.parseInt(input[1]) >= 0)
                    right = true;
                else if (input[0].matches("state") && input.length == 2
                        && Integer.parseInt(input[1].split(";", 2)[0]) <= 7
                        && Integer.parseInt(input[1].split(";", 2)[0]) >= 0
                        && Integer.parseInt(input[1].split(";", 2)[1]) <= 7
                        && Integer.parseInt(input[1].split(";", 2)[1]) >= 0)
                    right = true;
                else
                    Terminal.printError("Please give a valid command");
            } catch (NumberFormatException ex) {
                Terminal.printError("Please give a valid parameter");
            } catch (ArrayIndexOutOfBoundsException ex) {
                Terminal.printError("Please give a valid parameter");
            }

        }
        return input;
    }

    /**
     * Executes the game after testing the command line and initializing the board.
     * 
     * @param args
     *            the argument of the command line
     */
    public void startGame(String[] args) {
        if (testCommandLine(args)) {
            initializeMode(args[0], args[1]);
            boolean running = true;
            while (running) {
                String[] input = testInput();
                switch (input[0]) {
                    case "throwin":
                        if (boardGame.isNotFinished())
                            boardGame.throwIn(input[1]);
                        else
                            Terminal.printError("Please choose another command, game ended");
                        break;
                    case "token":
                        boardGame.token();
                        break;
                    case "state":
                        boardGame.state(input[1]);
                        break;
                    case "print":
                        boardGame.printBoard();
                        break;
                    case "flip":
                        if (boardGame instanceof FlipMode) {
                            if (boardGame.isNotFinished())
                                ((FlipMode) boardGame).flip();
                            else
                                Terminal.printError("Please choose another command, game ended");
                        } else
                            Terminal.printError("This command is not allowed in this mode");
                        break;
                    case "remove":
                        if (boardGame instanceof RemoveMode) {
                            if (boardGame.isNotFinished())
                                ((RemoveMode) boardGame).remove(input[1]);
                            else
                                Terminal.printError("Please choose another command, game ended");
                        } else {
                            Terminal.printError("This command is not allowed in this mode");
                        }
                        break;
                    case "quit":
                        running = false;
                        break;
                    default:
                        Terminal.printError("Please give a valid command");
                }
            }

        }
    }
}
