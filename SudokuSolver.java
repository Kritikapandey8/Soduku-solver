public class SudokuSolver {
    private int[][] board;

    public SudokuSolver(int[][] board) {
        this.board = board;
    }

    public boolean solve() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(i, j, num)) {
                            board[i][j] = num;
                            if (solve()) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, int num) {
        // Check the row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check the region
        int regionRow = row - row % 3;
        int regionCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[regionRow + i][regionCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[9][9];
        System.out.println("Enter the Sudoku puzzle (0 for empty cells):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("Enter cell (" + (i + 1) + ", " + (j + 1) + "): ");
                board[i][j] = Integer.parseInt(System.console().readLine());
            }
        }
        SudokuSolver solver = new SudokuSolver(board);
        if (solver.solve()) {
            solver.printBoard();
        } else {
            System.out.println("No solution exists");
        }
    }
}