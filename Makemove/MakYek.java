package Makemove;

import java.util.Scanner;

public class MakYek {
    private static final int ROWS = 8;
    private static final int COLUMNS = 8;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';


    private char[][] board;
    private int playerOneCount; // Count of Player X's pieces
    private int playerTwoCount; // Count of Player O's pieces



    public MakYek() {
        board = new char[ROWS][COLUMNS]; //
        initializeBoard();
        this.playerOneCount = 16; // Initialize count of Player X's pieces
        this.playerTwoCount = 16; // Initialize count of Player O's pieces
    }


    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (i == 0 || i == 2) {
                    board[i][j] = PLAYER_ONE;
                }
                else if (i == ROWS-3 || i == ROWS-1 ){
                    board[i][j] = PLAYER_TWO;
                }
                else{
                    board[i][j] = EMPTY;
                }
            }
        }
    }

/*
    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                /*System.out.print(board[i][j] + " ");
            }
                char piece = board[i][j];
                // Check if the piece is not a captured counter before printing
                if ((piece == PLAYER_ONE && playerOneCount != 16) || (piece == PLAYER_TWO && playerTwoCount != 16)) {
                    System.out.print(EMPTY + " ");
                } else {
                    System.out.print(piece + " ");
                }
            }
                System.out.println();
            }
        }
    }
    */

    // board printed
    public void printBoard() {
        if (playerOneCount == 16 && playerTwoCount == 16) {
            printFullBoard();
        } else {
            printPartialBoard();
        }
    }

    private void printFullBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printPartialBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                char piece = board[i][j];
//                if (piece == PLAYER_ONE || piece == PLAYER_TWO) {
//                    System.out.print(piece + " ");
//                } else {
//                    System.out.print(EMPTY + " ");
//                }
                if (piece == PLAYER_ONE && playerOneCount > 0) {
                    System.out.print(piece + " ");
                } else if (piece == PLAYER_TWO && playerTwoCount > 0) {
                    System.out.print(piece + " ");
                } else {
                    System.out.print(EMPTY + " ");
                }
            }
            System.out.println();
        }
    }




    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, char player) {


        // Check if the move is within the bounds of the board
        if (fromRow < 0 || fromRow >= ROWS || fromCol < 0 ||
                fromCol >= COLUMNS || toRow < 0 || toRow >= ROWS ||
                toCol < 0 || toCol >= COLUMNS) {


            System.out.println("Invalid move: Out of bounds.");
            return false;
        }


        // Check if the destination square is empty
        if (board[toRow][toCol] != EMPTY) {
            System.out.println("Invalid move: Destination square is not empty.");
            return false;
        }

        if (board[fromRow][fromCol] != player) {
            System.out.println("Invalid move: You can only move your own piece.");
            return false;
        }


        // Ensure the piece moves in a straight line (in the same row or column)
        if (fromRow != toRow && fromCol != toCol) {
            System.out.println("Invalid move: Piece must move in a straight line.");
            return false;
        }


        // Implement validation for move
        return true; // Placeholder, implement actual validation
    }


    /*  logic capture + count captured piece */


    private void checkSpecialCaptures(int row, int col, char player) {
        if (player == PLAYER_ONE) {
            if (hasCustodianship(row, col, player)) {
                System.out.println("Player X captured one O counter by custodianship.");
                playerTwoCount--;
            } else if (hasSpecialCapture(row, col, player)) {
                System.out.println("Player X captured three O counters in a single move by a combination of intervention and custodianship.");
                playerTwoCount -= 3;
            } else if (hasTwoCounterIntervention(row, col, player)) {
                System.out.println("Player X captured two O counters by intervention.");
                playerTwoCount -= 2;
            }
        } else if (player == PLAYER_TWO) {
            if (hasCustodianship(row, col, player)) {
                System.out.println("Player O captured one X counter by custodianship.");
                playerOneCount--;
            } else if (hasSpecialCapture(row, col, player)) {
                System.out.println("Player O captured three X counters in a single move by a combination of intervention and custodianship.");
                playerOneCount -= 3;
            } else if (hasTwoCounterIntervention(row, col, player)) {
                System.out.println("Player O captured two X counters by intervention.");
                playerOneCount -= 2;
            }
        } else {
            throw new RuntimeException("Invalid player.");
        }
    }


    private boolean hasTwoCounterIntervention(int row, int col, char player) {

        char opponent = (player == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;

        // Check if the move is within the bounds of the board
        if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) {
            return false;
        }

        // Check for two counter intervention in the horizontal direction
        if (col > 1 && board[row][col - 1] == opponent && board[row][col - 2] == opponent) {
            return true;
        }
        if (col < COLUMNS - 2 && board[row][col + 1] == opponent && board[row][col + 2] == opponent) {
            return true;
        }

        // Check for two counter intervention in the vertical direction
        if (row > 1 && board[row - 1][col] == opponent && board[row - 2][col] == opponent) {
            return true;
        }
        if (row < ROWS - 2 && board[row + 1][col] == opponent && board[row + 2][col] == opponent) {
            return true;
        }

        return false;
    }

    private boolean hasCustodianship(int row, int col, char player) {
        char opponent = (player == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;

        // Check if the move is within the bounds of the board
        if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) {
            return false;
        }

        // Check for custodianship in the horizontal direction
        if (col > 1 && board[row][col - 1] == opponent && board[row][col - 2] == player) {
            return true;
        }
        if (col < COLUMNS - 2 && board[row][col + 1] == opponent && board[row][col + 2] == player) {
            return true;
        }

        // Check for custodianship in the vertical direction
        if (row > 1 && board[row - 1][col] == opponent && board[row - 2][col] == player) {
            return true;
        }
        if (row < ROWS - 2 && board[row + 1][col] == opponent && board[row + 2][col] == player) {
            return true;
        }

        return false;
    }

    private boolean hasSpecialCapture(int row, int col, char player) {
        char opponent = (player == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;

        // Check if the move is within the bounds of the board
        if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) {
            return false;
        }

        // Check for special capture in the horizontal direction
        if (col > 0 && col < COLUMNS - 1 && board[row][col - 1] == opponent && board[row][col + 1] == opponent) {
            return true;
        }

        // Check for special capture in the vertical direction
        if (row > 0 && row < ROWS - 1 && board[row - 1][col] == opponent && board[row + 1][col] == opponent) {
            return true;
        }

        return false;
    }


    // capture logic // elimineer player_one of playe_two
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol, char player) {
        if (isValidMove(fromRow, fromCol, toRow, toCol, player)) {

            // Move the piece to the destination square
            char movedPiece = board[fromRow][fromCol];
            board[toRow][toCol] = movedPiece;
            board[fromRow][fromCol] = EMPTY;

            // Check for flanked opponents' pieces and eliminate if necessary
            eliminateFlankedPieces(toRow, toCol, movedPiece);

            // Check for special captures logic
            checkSpecialCaptures(toRow, toCol, player);

            // Print the updated position
            System.out.println("Updated Board:");
            printBoard();

            // Print the updated player counts
            System.out.println("Player X count: " + playerOneCount);
            System.out.println("Player O count: " + playerTwoCount);

            // Check for win condition
            if (isGameOver(player)) {
                System.out.println("Game over! Player " + player + " wins.");
                return true;
            }
        }
        return false;
    }



    private char getOpponent(char player) {
        return (player == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }





    private void eliminateFlankedPieces(int row, int col, char movedPlayer) {
        char opponentPlayer = (movedPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i != 0 && j != 0) || (i == 0 && j == 0)) {
                    continue; // Skip diagonals and the current position
                }

                int newRow = row + i;
                int newCol = col + j;

                if (!isValidPosition(newRow, newCol) || board[newRow][newCol] != opponentPlayer) {
                    continue; // Skip if not opponent's piece
                }

                // Check if flanked by player's pieces
                int oppositeRow = row - i;
                int oppositeCol = col - j;

                if (isValidPosition(oppositeRow, oppositeCol) && board[oppositeRow][oppositeCol] == movedPlayer) {
                    // Eliminate the opponent's piece
                    board[newRow][newCol] = EMPTY;
                    if (opponentPlayer == PLAYER_ONE) {
                        playerOneCount--;
                    } else {
                        playerTwoCount--;
                    }
                }
            }
        }
    }
/*



 */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    /*private boolean isGameOver(char player) {
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }

     */
    private boolean isGameOver(char player) {
        char opponent = (player == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
        return playerOneCount == 0 || playerTwoCount == 0 ||
                !hasValidMove(opponent);
    }

    private boolean hasValidMove(char player) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == player) {
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 || dj == 0) {
                                int toRow = i + di;
                                int toCol = j + dj;
                                if (isValidMove(i, j, toRow, toCol, player)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MakYek game = new MakYek();


        System.out.println("Initial Board:");
        game.printBoard();


        // Implement game loop
        char currentPlayer = PLAYER_ONE;


        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.println("Enter the row and column of the piece you want to move (from):");
            int fromRow = scanner.nextInt();
            int fromCol = scanner.nextInt();


            System.out.println("Enter the row and column of the square you want to move to (to):");
            int toRow = scanner.nextInt();
            int toCol = scanner.nextInt();


            game.makeMove(fromRow, fromCol, toRow, toCol, currentPlayer);
            //game.printBoard();


            currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
        }
    }


}