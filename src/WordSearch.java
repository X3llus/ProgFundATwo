import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(Arrays.toString(getBoardDimensions()));
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

    public void setBoard() {

    }

    public int[] getBoardDimensions() {
        int[] out = {
                this.board.length,
                this.board[0].length
        };
        return out;
    }


}
