import java.util.Scanner;

public class TicTacToe2D
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String gameBoard[][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String activePlayer = "X";
        int playerMove = 0;
        int playerRow = 0;
        int playerCol = 0;
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
                    playerRow= SafeInput.getRangedInt(input, "Player " + activePlayer + ", please enter the row of the space you would like to play", 1, 3) - 1;
                    playerCol = SafeInput.getRangedInt(input, "Player " + activePlayer + ", please enter the column of the space you would like to play", 1, 3) - 1;
                    validMove = validMoveCheck(playerRow, playerCol);
                }
                while (!validMove);

                gameBoard[playerRow][playerCol] = activePlayer;
                turnCounter++;

                //check for win
                if (turnCounter > 4)
                {
                    gameWon = gameEndings(activePlayer);
                    gameUnwinnable = gameStalemate(activePlayer);
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

    private static void clearBoard(String[][] gameBoard)
    {
        for(int x = 0; x < ROW; x++)
        {
            for(int y = 0; y < COL; y++)
            {
                gameBoard[x][y] = " ";
            }
        }
    }

    private static void printBoard(String[][] gameBoard)
    {
        for(int x = 0; x < ROW; x++)
        {
            for(int y = 0; y < COL; y++)
            {
                if(y < 2)
                {
                    System.out.print(" " + gameBoard[x][y] + " |");
                }
                else
                {
                    System.out.print(" " + gameBoard[x][y] + " \n");
                }
            }

            if(x < 2)
            {
                System.out.print("---+---+---\n");
            }
        }
    }

    private static boolean validMoveCheck(int row, int col)
    {
        boolean legalMove = false;

        //playerMove, which is loaded to move, is the array address and not the user entry.
        if(gameBoard[row][col].equals(" "))
        {
            legalMove = true;
        }
        else
        {
            legalMove = false;

            //This converts the move back into the user entry when printing.
            System.out.println("Row " + (row + 1) + " column " + (col + 1) + " has already been claimed.");
        }
        return legalMove;
    }

    private static boolean gameEndings (String player)
    {
        boolean gameEnded = false;


        for(int x = 0; x < 3; x++)
        {
            //Check horizontal win
            if(gameBoard[x][0].equals(player) && gameBoard[x][1].equals(player) && gameBoard[x][2].equals(player))
            {
                gameEnded = true;
            }
            //Check vertical win
            else if(gameBoard[0][x].equals(player) && gameBoard[1][x].equals(player) && gameBoard[2][x].equals(player))
            {
                gameEnded = true;
            }
        }

        //check diagonals

        if(gameBoard[0][0].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][2].equals(player))
        {
            gameEnded = true;
        }
        else if(gameBoard[0][2].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][0].equals(player))
        {
            gameEnded = true;
        }

        return gameEnded;
    }

    private static boolean gameStalemate(String player)
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

            if(gameBoard[x][0].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[x][0].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[x][1].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[x][1].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[x][2].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[x][2].equals("O"))
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

            if(gameBoard[0][x].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[0][x].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[1][x].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[1][x].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[2][x].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[2][x].equals("O"))
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

            if(gameBoard[0][0].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[0][0].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[1][1].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[1][1].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[2][2].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[2][2].equals("O"))
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

            if(gameBoard[0][2].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[0][2].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[1][1].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[1][1].equals("O"))
            {
                oCount++;
            }
            if(gameBoard[2][0].equals("X"))
            {
                xCount++;
            }
            else if(gameBoard[2][0].equals("O"))
            {
                oCount++;
            }

            if(oCount == 0 || xCount == 0)
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
