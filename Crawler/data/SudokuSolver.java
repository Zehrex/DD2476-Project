1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/SudokuSolver.java
public class SudokuSolver {

    private final char NO_VALUE;
    private final int ROW_START, ROW_END, COLUMN_START, COLUMN_END, SECTION_SIZE, OPTION_START, OPTIONS_END, OPTIONS_SIZE;

    {
        NO_VALUE = '.';

        ROW_START = 0;
        ROW_END = 9;
        COLUMN_START = 0;
        COLUMN_END = 9;
        SECTION_SIZE = 3;
        OPTION_START = 0;
        OPTIONS_END = 9;
        OPTIONS_SIZE = 9;
    }

    private char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        solve(ROW_START);
    }

    private boolean solve(int startingRow) {
        for(int row = startingRow; row < ROW_END; row ++) {
            for(int column = COLUMN_START; column < COLUMN_END; column ++) {
                if(board[row][column] == NO_VALUE) {
                    boolean[] notAvailable = notAvailable(row, column);

                    for(int k = OPTION_START; k < OPTIONS_END; k ++) {
                        if(!notAvailable[k]) {
                            board[row][column] = (char) (k + '1');

                            if (solve(row)) {
                                return true;
                            }

                            board[row][column] = NO_VALUE;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    private boolean[] notAvailable(int row, int column) {
        boolean[] notAvailable = new boolean[OPTIONS_SIZE];
        char c;

        for (int index = OPTION_START; index < OPTIONS_END; index ++) {
            c = board[row][index];
            if (c != NO_VALUE) {
                notAvailable[c - '1'] = true;
            }

            c = board[index][column];
            if (c != NO_VALUE) {
                notAvailable[c - '1'] = true;
            }

            c = board[(row / SECTION_SIZE) * SECTION_SIZE + index / SECTION_SIZE][(column / SECTION_SIZE) * SECTION_SIZE + index % SECTION_SIZE];
            if (c != NO_VALUE) {
                notAvailable[c - '1'] = true;
            }
        }

        return notAvailable;
    }

}
