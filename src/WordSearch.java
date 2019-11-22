import java.util.*;

public class WordSearch {
    private char[][] board;
    private List<String> words;

    /**
     * Constructor for my class
     * sets up the search board
     * by getting a width and height
     * than a number of words
     */
    public WordSearch() {
        List<Integer> xy = getXY();
        board = new char[xy.get(1)][xy.get(0)];
        words =  new ArrayList<>();
        makeBoard();
        printBoard();
    }

    private static ArrayList<Integer> getXY() {
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

    public void makeBoard() {
        Random rand = new Random();
        for (char[] chars : this.board) {
            for (int j = 0; j < chars.length; j++) {
                chars[j] = (char) (rand.nextInt(26) + 'A');
            }
        }
    }

    public void printBoard() {
        StringBuilder toPrint = new StringBuilder();
        for (char[] chars : this.board) {
            for (char aChar : chars) {
                toPrint.append(aChar).append(" ");
            }
            toPrint.append("\n");
        }
        System.out.print(String.valueOf(toPrint));
    }


}
