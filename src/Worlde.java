import java.util.Locale;
import java.util.Scanner;

public class Worlde {

    public static final String correctWord = "train";
    public static final int guessLength = 5;
    public static int guessNumber = 0;

    public static void main(String[] args) {

        String[][] gameBoard = {{" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}};

        System.out.println("""
                Welcome to a Wordle Remake!\s
                To play, enter a five letter word.\s
                Correct letters in the right spot will be capitalized.\s
                Correct letters in the wrong spot will be lowercase.
                Wrong letters will be viewed as *.""");

        printBoard(gameBoard);

        while (guessNumber <= 6){
            Scanner input = new Scanner(System.in);
            String wordChoice = input.next().toLowerCase(Locale.ROOT);
            if (wordChoice.length() != 5){
                System.out.println("Word must be 5 letters.");
                continue;
            }
            if (!checkWin(wordChoice) && guessNumber==5) {
                printBoard(updateBoard(gameBoard, wordChoice));
                System.out.println("You lost. Try again.");
                break;
            }
            printBoard(updateBoard(gameBoard, wordChoice));
            if (checkWin(wordChoice)){
                System.out.println("Congrats! You win.");
                break;
            }
            int guessLeft = 6 - guessNumber;
            System.out.println("Guesses left: " + guessLeft);
        }
    }

    public static void printBoard(String[][] gameBoard){
        for (String[] row: gameBoard){
            for (String box: row){
                System.out.print("[" + box + "] ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isInWord(String letter){
        return correctWord.contains(letter);
    }

    public static boolean isInSpot(String letter, int position){
        return correctWord.charAt(position) == letter.charAt(0);
    }

    public static String[][] updateBoard(String[][] gameBoard, String word){
        for (int i = 0; i < guessLength; i++){
            if (isInWord(word.substring(i, i+1)) && isInSpot(word.substring(i, i+1), i)){
                gameBoard[guessNumber][i] = word.substring(i, i+1).toUpperCase(Locale.ROOT);
            }
            else if (isInWord(word.substring(i, i+1))){
                gameBoard[guessNumber][i] = word.substring(i, i+1);
            }
            else gameBoard[guessNumber][i] = "*";
        }
        guessNumber++;
        return gameBoard;
    }

    public static boolean checkWin(String word){
        return word.equals(correctWord);
    }

}
