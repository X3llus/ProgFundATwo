import java.util.*;
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
        words =  new ArrayList<>();
        getStrings();
        makeBoard();
    }

    private ArrayList<Integer> getXY() {
        Scanner keyIn = new Scanner(System.in);
        ArrayList<Integer> xy = new ArrayList<>();
        String[] firstSecond = {"rows", "columns"};
        for (int i = 0; i < 2;) {
            System.out.printf("Enter the number of %s (2-15): ", firstSecond[i]);
            try {
                int in = Integer.parseInt(keyIn.nextLine());
                if (in >= 2 && in <= 15) {
                    xy.add(in);
                    i++;
                } else {
                    System.out.println("Number must be between 2 and 15");
                }
            } catch(NumberFormatException e) {
                System.out.println("Only integers are allowed");
            }
        }
        return xy;
    }

    private void getStrings(){
        Scanner keyIn = new Scanner(System.in);
        String input;
        boolean hasSpace;

        for (int i = 0; i < this.board.length;) {
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
            } else if (hasDigit(input)) {
                System.out.println("Letters only");
            } else if (!hasSpace) {
                this.words.add(input);
                i++;
            }
        }
        System.out.println(this.words);
    }

    private void makeBoard() {
        Random rand = new Random();
        for (char[] chars : this.board) {
            for (int j = 0; j < chars.length; j++) {
                chars[j] = (char) (rand.nextInt(26) + 'A');
            }
        }
    }

    StringBuilder getWordSearchString() {
        StringBuilder toPrint = new StringBuilder();
        for (char[] chars : this.board) {
            for (char aChar : chars) {
                toPrint.append(aChar).append(" ");
            }
            toPrint.append("\n");
        }
        return toPrint;
    }

    private boolean hasDigit(String in) {
        String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for (String num : nums) {
            if (in.contains(num)) {
                return true;
            }
        }
        return false;
    }

}
