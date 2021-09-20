/**
 *  Helper class to check for a Connect 4 or pieces in a row
 *
 *  @author Oliver
 *  @version May 27, 2021
 */
public class Connect4
{
    private char[][] board;

    /**
     * Creates a clone of board to test if potential board states
     * @param brd - board of current game being played
     */
    public Connect4(char[][] brd)
    {
        //constructuer stuff
        board = brd;
    }

    /**
     * Checks if board position is valid on the board
     * @param row - row to check
     * @param col - col to check
     * @return if it is a valid position
     */
    public boolean isValid(int row, int col)
    {
//        System.out.println("diag: validating " + row + " " + col);
        if ((row <= 5 && row >= 0) && (col <= 6 && col >= 0))
        {
            return true;
        }
        return false;
    }

    /**
     * Place a description of your method here.
     * @param row - row of piece being placed
     * @param col - column of piece being placed
     * @param player - player whose piece is being placed
     * @return where it creats a connect 4 or not
     */
    public boolean isConnect(int row, int col, Player player)
    {
//        char piece = player.getPiece();

        // array of 7 [][][][][][][] and if 4 of one ch in a row, marks as 4!
        // one way to check for connect 4

        //  CHECK HORIZONTALLY
        char[] horizBoard = new char[7];
        for (int i = 0; i < 7; i++)
        {
            horizBoard[i] = board[row][i];
        }

//        for (char c : horizBoard)
//        {
//            System.out.print(c + " ");
//        }
        // if connect4, submit, otherwise DONT RETURN ANYTHING REEEEE
        if (hasRow(horizBoard, player, 4))
        {
            return true;
        } //otherwise, continue checking

        //  CHECK VERTICALLY
        char[] vertBoard = new char[7];
        for (int j = 0; j < 6; j++)
        {
            vertBoard[j] = board[j][col];
        }

//        System.out.print("| ");
//        for (char c : vertBoard)
//        {
//            System.out.print(c + " ");
//        }
            //basically copy paste from horiz lmao
        if (hasRow(vertBoard, player, 4))
        {
            return true;
        }

        //  CHECK DIAGONALLY
        char[] diagBoard = new char[7];
        char[] diagBoard2 = new char[7];
        for (int z = -3; z <= 3; z++)
        {
            if (isValid(row+z, col+z))
            {
                diagBoard[z+3] = board[row+z][col+z];
            }
        }

        for (int z = 3; z >= -3; z--)
        {
            if (isValid(row+z, col-z))
            {
                diagBoard2[z+3] = board[row+z][col-z];
            }
        }

//        System.out.print("| ");
//        for (char c : diagBoard)
//        {
//            System.out.print(c + " ");
//        }

        if (hasRow(diagBoard, player, 4) || hasRow(diagBoard2, player, 4))
        {
            return true;
        }

        return false;
    }

    /**
     * Checks if there is numRow pieces in a row (in an array)
     * @param arr - array to check in a row
     * @param p - player's piece
     * @param numRow - number of pieces in a row to check for
     * @return if it has numRow pieces in a row
     */
    private static boolean hasRow(char[] arr, Player p, int numRow)
    {
        int counter = 0;
        for (char c : arr)
        {
            if (c == p.getPiece())
            {
//                System.out.println("char checked is: " + c);
                counter++;
                if (counter == numRow)
                {
                    return true;
                }
            }
            else
            {
                counter = 0;
            }
        }

        return false;
    }

//    /**
//     * Testing for Connect4 Class
//     * @param args - standard testing method
//     */
//    public static void main(String[] args)
//    {
//        char[] bruh = {'o', 'o', 'o', '-', '-', '-', '-'};
//        Player p = new Player('o');
//
//        System.out.println(hasRow(bruh, p, 4));
//
//        for (int i = 0; i < 10; i++)
//        {
//            System.out.println((int) (Math.random() * (7)) + 1);
//        }
//    }
}