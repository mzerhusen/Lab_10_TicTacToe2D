import java.util.Scanner;

public class SafeInput
{
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }
        while(retString.length() == 0);

        return retString;

    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return an integer response that is not zero length
     */
    public static int getInt(Scanner pipe, String prompt)
    {
        int returnValue = 0;
        String invalidValue;
        boolean isNumber = false;

        do
        {
            System.out.print(prompt + ": ");
            if(pipe.hasNextInt())
            {
                returnValue = pipe.nextInt();
                pipe.nextLine();
                isNumber = true;
            }
            else
            {
                invalidValue = pipe.nextLine();
                System.out.println(invalidValue + " is not a valid entry, please enter a number.");
            }

        }
        while (!isNumber);

        return returnValue;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a double response that is not zero length
     */
    public static double getDouble(Scanner pipe, String prompt)
    {
        double userNumber = 0.0;
        String invalidInput;
        boolean validInput = false;

        do
        {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble())
            {
                userNumber = pipe.nextDouble();
                pipe.nextLine();
                validInput = true;
            }
            else
            {
                invalidInput = pipe.nextLine();
                System.out.println(invalidInput + " is not a valid entry. Please enter a number.");
            }
        }
        while(!validInput);

        return userNumber;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param lowerBound is the minimum acceptable input
     * @param upperBound is the maximum acceptable input
     * @return an integer response that is in a specified range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int lowerBound, int upperBound)
    {
        int userNumber = 0;
        String invalidInput;
        boolean validInput = false;

        do
        {
            System.out.print(prompt + " [" + lowerBound + "-" + upperBound + "]:");
            if (pipe.hasNextInt())
            {
                userNumber = pipe.nextInt();
                pipe.nextLine();
                if (userNumber >= lowerBound && userNumber <= upperBound)
                {
                    validInput = true;
                }
                else
                {
                    System.out.println(userNumber + " is not in the range [" + lowerBound + "-" + upperBound + "], please enter a number in that range.");
                }
            }
            else
            {
                invalidInput = pipe.nextLine();
                System.out.println(invalidInput + " is not a valid entry. Please enter a number.");
            }
        }
        while(!validInput);

        return userNumber;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param lowerBound is the minimum acceptable input
     * @param upperBound is the maximum acceptable input
     * @return a double response that is in a specified range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double lowerBound, double upperBound)
    {
        double userNumber = 0.0;
        String invalidInput;
        boolean validInput = false;

        do
        {
            System.out.print(prompt + " [" + lowerBound + "-" + upperBound + "]:");
            if (pipe.hasNextDouble())
            {
                userNumber = pipe.nextDouble();
                pipe.nextLine();
                if (userNumber >= lowerBound && userNumber <= upperBound)
                {
                    validInput = true;
                }
                else
                {
                    System.out.println(userNumber + " is not in the range [" + lowerBound + "-" + upperBound + "], please enter a number in that range.");
                }
            }
            else
            {
                invalidInput = pipe.nextLine();
                System.out.println(invalidInput + " is not a valid entry. Please enter a number.");
            }
        }
        while(!validInput);

        return userNumber;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a boolean response that yes = true and no = false
     */
    public static boolean getYesNoConfirm(Scanner pipe, String prompt)
    {
        String userAnswer;
        boolean validInput = false;
        boolean userInput = false;

        do
        {
            System.out.print(prompt + ": ");
            userAnswer = pipe.nextLine();
            userAnswer = userAnswer.toUpperCase();
            if(userAnswer.equals("Y") || userAnswer.equals("N"))
            {
                validInput = true;
                if(userAnswer.equals("Y"))
                {
                    userInput = true;
                }
                else
                {
                    userInput = false;
                }
            }
            else
            {
                System.out.println(userAnswer + " is not a valid response, please enter Y for Yes or N for No.");
            }

        }
        while(!validInput);

        return userInput;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx regular expression format you want returned
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String userAnswer;
        boolean validInput = false;

        do
        {
            System.out.print(prompt + ": ");
            userAnswer = pipe.nextLine();
            if(userAnswer.matches(regEx))
            {
                validInput = true;
            }
            else
            {
                System.out.print(userAnswer + "is not a valid entry ");
            }
        }
        while(!validInput);

        return userAnswer;
    }



}