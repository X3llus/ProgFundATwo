import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordSearch {
    private char[][] board;
    private List<String> words;

    /**
     * Constructor for my class
     * sets up the search board
     * by getting a width and height
     * than a number of words
     */
    WordSearch() {
        List<Integer> xy = getXY();
        board = new char[xy.get(0)][xy.get(1)];
        words = new ArrayList<>();
        getStrings();
        makeBoard();
    }

    private ArrayList<Integer> getXY() {
        Scanner keyIn = new Scanner(System.in);
        ArrayList<Integer> xy = new ArrayList<>();
        String[] firstSecond = {"rows", "columns"};
        for (int i = 0; i < 2; ) {
            System.out.printf("Enter the number of %s (2-15): ", firstSecond[i]);
            try {
                int in = Integer.parseInt(keyIn.nextLine());
                if (in >= 2 && in <= 15) {
                    xy.add(in);
                    i++;
                } else {
                    System.out.println("Number must be between 2 and 15");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only integers are allowed");
            }
        }
        return xy;
    }

    private void getStrings() {
        Scanner keyIn = new Scanner(System.in);
        String input;
        boolean hasSpace;

        for (int i = 0; i < this.board.length; ) {
            System.out.printf("Please enter a word with %d or less characters: ", this.board[0].length);
            input = keyIn.nextLine();
            hasSpace = false;

            if (input.contains(" ")) {
                System.out.println("Spaces are not allowed, would you like to remove spaces? (y/n)");

                if (keyIn.nextLine().equalsIgnoreCase("y")) {
                    input = input.replace(" ", "");
                } else {
                    hasSpace = true;
                }
            }
            if (input.length() < 2 || input.length() > this.board[0].length) {
                System.out.printf("Words must be between 2 and %d characters\n", this.board[0].length);
            } else if (hasNotLetter(input)) {
                System.out.println("Letters only");
            } else if (!hasSpace) {
                this.words.add(input.toUpperCase());
                i++;
            }
        }
    }

    private void makeBoard() {
        Random rand = new Random();

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = (char) (rand.nextInt(26) + 'A');
            }
            for (int j = 0, k = rand.nextInt(this.board[0].length - this.words.get(i).length() + 1); j < this.words.get(i).length(); j++, k++) {
                this.board[i][k] = this.words.get(i).charAt(j);
            }
        }
    }

    StringBuilder getWordSearchString() {
        StringBuilder output = new StringBuilder();
        for (char[] chars : this.board) {
            for (char aChar : chars) {
                output.append(aChar).append(" ");
            }
            output.append("\n");
        }
        return output;
    }

    String getWordsString() {
        StringBuilder output = new StringBuilder();
        output.append("The words to find:\n");
        for (String word : this.words) {
            output.append(word).append("\n");
        }

        return output.toString();
    }

    private boolean hasNotLetter(String in) {
        Pattern accepted = Pattern.compile("[a-zA-Z]");
        Matcher match = accepted.matcher(in);
        return !match.find();

    }

    void printToFile() throws IOException {
        String toFile = getWordSearchString() + getWordsString();

        BufferedWriter file = new BufferedWriter(new FileWriter("./wordSearch.txt"));
        file.write(toFile);
        file.close();
    }

}
