import java.util.Scanner;

/**
 *  Player class to keep player information
 *  and deal with user input
 *
 *  @author Oliver
 *  @version May 27, 2021
 */
public class Player
{
    private char piece;

    /**
     * Creates a default player with piece “x”
     */
    public Player()
    {
        piece = 'x';
    }

    /**
     * Creates a player with piece “ch”
     * @param ch - character piece
     */
    public Player(char ch)
    {
        piece = ch;
    }

    /**
     * Asks user what column they would like to place piece at
     * @return column user specifies piece to be placed
     */
    public int askInput()
    {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        System.out.println("Which column would you like to place your piece in?"
            + " (columns are 1 to 7)");
        String input = scan.nextLine();

        //System.out.println("input is: " + input);
        //System.out.println("isValid is: " + isValid(column));

        while (!isValid(input))
        {
            System.out.println("Please enter a valid column" +
                " (columns are 1 to 7)");
            input = scan.nextLine();
        }

        int column = Integer.parseInt(input);

        return column;
    }

    /**
     * Returns piece character
     * @return player's piece
     */
    public char getPiece()
    {
        return piece;
    }

    /**
     * Tests if the column's input is valid
     * @param col - column prompted by user
     * @return if the input is valid or not
     */
    private boolean isValid(String str)
    {
        try
        {
            Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        int col = Integer.parseInt(str);

        if (col >= 1 && col <= 7)
        {
            return true;
        }
        return false;
    }
}