import java.util.*;

/**
 *  Main game flow of Connect 4 is being done here,
 *  culmination of all the other classes :D
 *
 *  @author Oliver
 *  @version May 27, 2021
 */
public class Game
{
    private static LinkedList<Player> pList = new LinkedList<Player>();
    private static Board board = new Board();

    /**
     * Plays a turn of a certain player
     * @param player - player that has a turn
     */
    public static void playRound(Player player)
    {
        int input = player.askInput();
        board.addPiece(player, input);

        if (player instanceof Opponent)
        {
            System.out.println(player.getPiece() + " chose column " + input);
        }

        board.printBoard();

        if (board.checkTie() == true)
        {
            board.setContinue(false);
        }

        System.out.println();
    }

    /**
     * Overall gameflow and riveting connect 4 gameplay
     * @param args - standard main argument
     */
    public static void main(String[] args)
    {
        // add opponent / beginning game stuff
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);

        System.out.println("What character do you want your game piece to be?"
            + " (please enter a single character)");
        String reqPiece = scan.nextLine();

        Player p1 = new Player(reqPiece.charAt(0));
        pList.add(p1);

        System.out.println();

        System.out.println("Would you like to play 1 player or 2 player mode?"
            + " (please answer '1' or '2') ");
        String input = scan.nextLine().toLowerCase();

        boolean valid = false;

        while (!valid)
        {
            if (input.equals("'1'") || input.equals("1"))
            {
                Player opp = new Opponent(p1.getPiece(), board, p1);
                pList.add(opp);
                valid = true;
            }
            else if (input.equals("'2'") || input.equals("2"))
            {
                Player p2;
                if (p1.getPiece() == 'o')
                {
                    p2 = new Player('x');
                }
                else
                {
                    p2 = new Player('o');
                }
                pList.add(p2);
                valid = true;
            }
            else {
                System.out.println("Would you like to play 1 player " +
                    "or 2 player mode?" + " (please answer '1' or '2') ");
                input = scan.nextLine().toLowerCase();
            }
        }
        System.out.println();

        int curr = 0;

        boolean playMore = true;

        while (playMore)
        {
            Player currPlayer = pList.get(curr);

            while(board.getContinue())
            {
                currPlayer = pList.get(curr);

                System.out.println(currPlayer.getPiece() + "'s turn!");

                playRound(currPlayer);

                // implement circular linked list w/ 2 items
                if (curr == 0)
                {
                    curr++;
                }
                else
                {
                    curr = 0;
                }
            }

            System.out.println("Congratulations player " +
                currPlayer.getPiece() + " for winning!\n");

            System.out.println("Would you like to play again? ('y' or 'n')");
            input = scan.nextLine().toLowerCase();

            boolean validResponse = false;

            while (!validResponse)
            {
                if (input.equals("n") || input.equals("'n'"))
                {
                    playMore = false;
                    validResponse = true;
                }
                else if (input.equals("y") || input.equals("'y'"))
                {
                     board.setContinue(true);
                     board.clearBoard();
                     validResponse = true;
                }
                else
                {
                    System.out.println("Please enter a valid response "
                        + "('y' or 'n')");
                    input = scan.nextLine().toLowerCase();
                }
            }
        }

        System.out.println();
        System.out.println("Thanks for playing! :D");
    }
}