import java.util.Scanner;

public class TicTacToe2D
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String gameBoard[][] = new String[row][col];
        String activePlayer = "X";
        int playerMove = 0;
        int playerSpace = 0;
        int turnCounter = 0;
        boolean validMove = false;
        boolean gameWon = false;
        boolean gameUnwinnable = false;
        boolean gameOver = false;
        boolean playAgain = false;


        do
        {
            clearBoard(gameBoard);
            activePlayer = "X";
            turnCounter = 0;
            validMove = false;
            gameWon = false;
            gameUnwinnable = false;
            gameOver = false;
            playAgain = false;

            do
            {
                printBoard(gameBoard);
                do
                {
                    //playerMove is the user input, playerSpace is the array index.
                    playerMove = SafeInput.getRangedInt(input, "Player " + activePlayer + ", please enter the number of the space you would like to play", 1, 9);
                    playerSpace = playerMove - 1;
                    validMove = validMoveCheck(gameBoard, playerMove);
                }
                while (!validMove);

                gameBoard[playerSpace] = activePlayer;
                turnCounter++;

                //check for win
                if (turnCounter > 4)
                {
                    gameWon = gameEndings(gameBoard, activePlayer);
                    gameUnwinnable = gameStalemate(gameBoard, activePlayer);
                    if (gameWon || gameUnwinnable)
                    {
                        gameOver = true;
                    }
                }

                if(!gameOver)
                {
                    if (activePlayer.equals("X"))
                    {
                        activePlayer = "O";
                    }
                    else
                    {
                        activePlayer = "X";
                    }
                }
            }
            while (!gameOver);

            printBoard(gameBoard);

            if(gameWon)
            {
                System.out.println("Player " + activePlayer + " has won!");
            }
            else if(gameUnwinnable)
            {
                System.out.println("There is no possible win. The game is a draw.");
            }

            playAgain = SafeInput.getYesNoConfirm(input, "Would you like to play again?");
        }
        while (playAgain);
    }

    private static void clearBoard(String[] gameBoard)
    {
        for (int x = 0; x < gameBoard.length; x++)
        {
            int spaceNumber = x + 1;
            String spaceName = "" + spaceNumber;
            gameBoard[x]= spaceName;
        }
    }

    private static void printBoard(String[] gameBoard)
    {
        for (int x = 0; x < 3; x++)
        {
            int leftColumn = (x * 3);
            int middleColumn = (x * 3) + 1;
            int rightColumn = (x * 3) + 2;
            if(x == 0)
            {
                System.out.print("\n");
            }
            System.out.printf("%1s|%1s|%1s\n", gameBoard[leftColumn], gameBoard[middleColumn], gameBoard[rightColumn]);
            if(x < 2)
            {
                System.out.print("-+-+-\n");
            }
            else
            {
                System.out.print("\n");
            }
        }
    }

    private static boolean validMoveCheck(String[] gameBoard, int move)
    {
        boolean legalMove = false;
        String spaceName = "" + move;

        //playerMove, which is loaded to move, is the array address and not the user entry.
        if(spaceName.equals(gameBoard[move - 1]))
        {
            legalMove = true;
        }
        else
        {
            legalMove = false;

            //This converts the move back into the user entry when printing.
            System.out.println("Space " + (move) + " has already been claimed.");
        }
        return legalMove;
    }

    private static boolean gameEndings (String[] gameBoard, String player)
    {
        boolean gameEnded = false;


        for (int x = 0; x < 3; x++)
        {
            //Check horizontal win
            if (gameBoard[(x * 3)].equals(player) && gameBoard[(x * 3) + 1].equals(player) && gameBoard[(x * 3) + 2].equals(player))
            {
                gameEnded = true;
            }
            //Check vertical win
            else if(gameBoard[x].equals(player) && gameBoard[(x + 3)].equals(player) && gameBoard[(x + 6)].equals(player))
            {
                gameEnded = true;
            }
        }

        //check diagonals

        if(gameBoard[0].equals(player) && gameBoard[4].equals(player) && gameBoard[8].equals(player))
        {
            gameEnded = true;
        }
        else if(gameBoard[2].equals(player) && gameBoard[4].equals(player) && gameBoard[6].equals(player))
        {
            gameEnded = true;
        }

        return gameEnded;
    }

    private static boolean gameStalemate(String[] gameBoard, String player)
    {
        int xCount = 0;
        int oCount = 0;
        boolean rowDraw = false;
        boolean columnDraw = false;
        boolean leftDiagonalDraw = false;
        boolean rightDiagonalDraw = false;
        boolean gameDraw = false;

        //Check for row win possibilities
        for (int x = 0; x < 3; x++)
        {
            xCount = 0;
            oCount = 0;

            if(gameBoard[(x * 3)].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[x * 3].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[(x * 3) + 1].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[(x * 3) + 1].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[(x * 3) + 2].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[(x * 3) + 2].equals("O"))
            {
                oCount++;
            }

            if(oCount == 0 || xCount == 0)
            {
                rowDraw = false;
                break;
            }
            else if(x == 2)
            {
                rowDraw = true;
            }
        }

        //check for column win possibilities
        for (int x = 0; x < 3; x++)
        {
            xCount = 0;
            oCount = 0;

            if(gameBoard[x].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[x].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[(x + 3)].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[(x + 3)].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[(x + 6)].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[(x + 6)].equals("O"))
            {
                oCount++;
            }

            if(oCount == 0 || xCount == 0)
            {
                columnDraw = false;
                break;
            }
            else if(x == 2)
            {
                columnDraw = true;
            }
        }

        //check for left diagonal draw
        for (int x = 0; x < 1; x++)
        {
            xCount = 0;
            oCount = 0;

            if(gameBoard[0].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[0].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[4].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[4].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[8].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[8].equals("O"))
            {
                oCount++;
            }

            if(oCount == 0 || xCount == 0)
            {
                leftDiagonalDraw = false;
            }
            else
            {
                leftDiagonalDraw = true;
            }
        }

        //check for right diagonal draw
        for (int x = 0; x < 1; x++)
        {
            xCount = 0;
            oCount = 0;

            if (gameBoard[2].equals("X"))
            {
                xCount++;
            }
            else if (gameBoard[2].equals("O"))
            {
                oCount++;
            }
            if (gameBoard[4].equals("X"))
            {
                xCount++;
            } else if (gameBoard[4].equals("O"))
            {
                oCount++;
            }
            if (gameBoard[6].equals("X"))
            {
                xCount++;
            }
            else if (gameBoard[6].equals("O"))
            {
                oCount++;
            }

            if (oCount == 0 || xCount == 0)
            {
                rightDiagonalDraw = false;
            }
            else
            {
                rightDiagonalDraw = true;
            }
        }


        if(rowDraw && columnDraw && leftDiagonalDraw && rightDiagonalDraw)
        {
            gameDraw = true;
        }

        return gameDraw;
    }
}
