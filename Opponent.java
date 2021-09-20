/**
 *  Opponent class to be the AI that plays against
 *  the player in 1 player mode
 *
 *  @author Oliver
 *  @version May 27, 2021
 */
public class Opponent extends Player
{
    private char piece;
    private Board board;
    private char[][] boardArr = new char[6][7];
    private Player user;

    /**
     * Creates a new default Opponent player.
     */
    public Opponent()
    {
        piece = 'o';
    }

    /**
     * If it isn't a default player, check to make sure symbols aren't the same
     * @param playerCh - character of the user/player
     * @param brd - board of the current game state
     * @param p1 - human player/user
     */
    public Opponent(char playerCh, Board brd, Player p1)
    {
        piece = 'o';
        if (playerCh == 'o')
        {
            piece = 'x';
        }

        board = brd;
        user = p1;

        for (int i = 0; i < 6; i++ )
        {
            for (int j = 0; j < 7; j++)
            {
                boardArr[i][j] = brd.getChar(i,j);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public int askInput()
    {
        int[] piecePos = new int[2];

        for (int col = 1; col < 8; col++)
        {
//            System.out.println("col for Opponent askInput is: " + col);
            //System.out.println(board.addPiece(this, col));

         // check if the board has a 4 or not
            //      or other checking stuff for 1,2,3 etc

            // check if opponent can get a connect 4
            piecePos = board.checkPiece(this, col);
            if (piecePos.length == 1)
            {
                board.clearSpace(piecePos[0], col - 1);
//                System.out.println("oppo has four in a row!");
                return col;
            }

            board.clearSpace(piecePos[0], col - 1);

        }

        for (int col = 1; col < 8; col++)
        {
            // check if user can get a connect 4
            piecePos = board.checkPiece(user, col);

            if (piecePos.length == 1)
            {
                board.clearSpace(piecePos[0], col - 1);
//                System.out.println("user will have connect4!");
                return col;
            }

            // remove piece from board with coords from piecePos[0] & [1]
            // if it doesnt work
            board.clearSpace(piecePos[0], col - 1);
        }

        // can return a random number after checking for 4 in a row
        return (int) (Math.random() * (7)) + 1;
    }

    /**
     * {@inheritDoc}
     */
    public char getPiece()
    {
        return piece;
    }
}