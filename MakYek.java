import java.util.Scanner;
public class MakYek {
    private static final int ROWS = 8;
    private static final int COLUMNS = 8;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';


    private char[][] board;


    public MakYek() {
        board = new char[ROWS][COLUMNS]; //
        initializeBoard();
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


    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
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


        // Ensure the piece moves in a straight line (in the same row or column)
        if (fromRow != toRow && fromCol != toCol) {
            System.out.println("Invalid move: Piece must move in a straight line.");
            return false;
        }


        // Implement validation for move
        return true; // Placeholder, implement actual validation
    }




    // capture logic // elimineer player_one of playe_two

    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol, char player) {
        if (isValidMove(fromRow, fromCol, toRow, toCol, player)) {
            // Check if there is a piece of the opponent's color in the middle
            int middleRow = toRow - fromRow;
            int middleCol = toCol - fromCol;

            for (int i = fromRow; i < toRow; i++) {
                for (int j = fromCol; j < toCol; j++) {
                    if (board[i][j] != EMPTY) return false;
                }
                printBoard();
                return true;
            }

            if (board[middleRow][middleCol] != EMPTY && board[middleRow][middleCol] != player) {
                // Capture the opponent's piece
                board[middleRow][middleCol] = EMPTY;
            }
        }
        return false;
    }


    // Move the piece to the destination square
//            board[toRow][toCol] = board[fromRow][fromCol];
//            board[fromRow][fromCol] = EMPTY;


    // Check for win condition
//            if (isGameOver(player)) {
//                System.out.println("Game over! Player " + player + " wins.");
//                System.exit(0); // Exit the game
//            }
//        } else {
//            System.out.println("Invalid move. Try again.");
//        }
//    }




    private boolean isGameOver(char player) {
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
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
            game.printBoard();


            // Check for win condition or switch player
            // Implement win condition check


            currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
        }
    }


}