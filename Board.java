/**
 *  Board class to deal with the locations of pieces
 *  and adding / removing pieces
 *
 *  @author Oliver
 *  @version May 27, 2021
 */
public class Board
{
    /**
     * Empty character that fills the board
     */
    public static final char emptyCh = '-';
    private char[][] board;
    private boolean continueGame;
    private Connect4 con;

    /**
     * Creates the empty board (2D array serves as location)
     */
    public Board()
    {
        board = new char[6][7]; //row, col
        this.clearBoard();
        continueGame = true;
    }

    /**
     * Prints the board to console
     */
    public void printBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (j == 0)
                {
                    System.out.print("|| " + board[i][j] + " |");
                }
                else if (j == board.length)
                {
                    System.out.print("| " + board[i][j] + " ||");
                }
                else
                {
                    System.out.print("| " + board[i][j] + " |");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Adds piece to board
     * @param player - player's piece
     * @param column - column to place piece
     * @return array of row, col where piece was placed
     */
    public int[] addPiece(Player player, int column)
    {
        int col = column - 1; // 1 to 7 to indices
        int row = 0;
        if (board[0][col] != '-')
        {
            System.out.println("Column is full, please pick another column");
            int inp = player.askInput();
            addPiece(player, inp);
        }
        else
        {
            while (row + 1 < 6 && board[row+1][col] == '-')
            {
                //System.out.println("in loop!");
                row++;
            }
            //System.out.println("row is: " + row);
            board[row][col] = player.getPiece();
        }

        //check for connect 4 later
        con = new Connect4(board);

        if (con.isConnect(row, col, player))
        {
//            System.out.println("4 in a row bruh");
            continueGame = false;
        }
//        System.out.println("row=" + row + " col=" + col);
//        System.out.println(this.getChar(row, col));

        // for Opponent to know where final piece is placed
        int[] rowCol = {row, col};
        return rowCol;
    }

    /**
     * Adds a piece to the board, but doesn't end game when finds a connect4
     * @param player - player whose piece is to be added
     * @param column - column piece is to be placed
     * @return coords of piece or just {row} if it completes a connect4
     */
    public int[] checkPiece(Player player, int column)
    {
        int col = column - 1; // 1 to 7 to indices
        int row = 0;
        if (board[0][col] != '-')
        {
            int inp = 1;
            for (int i = 1; i < 7; i++)
            {
                inp = i;
                if (board[0][i] != '-')
                {
                    break;
                }
            }
            addPiece(player, inp);
        }
        else
        {
            while (row + 1 < 6 && board[row+1][col] == '-')
            {
                row++;
            }
            board[row][col] = player.getPiece();
        }

        //check for connect 4 later
        con = new Connect4(board);

        if (con.isConnect(row, col, player))
        {
//            System.out.println("board: " + "user 4 in a row");
            return new int[] {row};
        }
//        System.out.println("row=" + row + " col=" + col);
//        System.out.println(this.getChar(row, col));

        // for Opponent to know where final piece is placed
        int[] rowCol = {row, col};
        return rowCol;
    }

    /**
     * Clears the board of all pieces
     */
    public void clearBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                board[i][j] = emptyCh;
            }
        }
    }

    /**
     * Clears a certain space on the board
     * @param row - row of space
     * @param col - column of space
     */
    public void clearSpace(int row, int col)
    {
        board[row][col] = emptyCh;
    }

    /**
     * Checks if the board is full
     * @return whether the board is full
     */
    public boolean checkTie()
    {
        int count = 0;

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j] == emptyCh)
                {
                    count++;
                }
            }
        }

        return (count == 0);
    }

    /**
     * Getter method for whether to continue the game or not
     * @return whether to continue
     */
    public boolean getContinue()
    {
        return continueGame;
    }

    /**
     * Setter method for whether to continue the game or not
     * @param b - whether to continue
     */
    public void setContinue(boolean b)
    {
        continueGame = b;
    }

    /**
     * Getter method for a character in the board
     * @param row - row of space to check
     * @param col - column of space to check
     * @return piece at space specified
     */
    public char getChar(int row, int col)
    {
        return board[row][col];
    }

//    public static void main(String[] args)
//    {
//        Board bruh = new Board();
//        Player p1 = new Player();
//
//        bruh.addPiece(p1, p1.askInput());
//        bruh.printBoard();
//
//        bruh.addPiece(p1, p1.askInput());
//        bruh.printBoard();
//    }
}