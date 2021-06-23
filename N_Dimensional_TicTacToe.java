package tictactoe;
import java.util.Scanner;


// @author sir Tony Nasr
// N-Dimensional TicTacToe Board

public class TicTacToe {

    static Scanner get = new Scanner(System.in);
    static final String black  = "\u001B[30m";
    static final String red    = "\u001B[31m";
    static final String green  = "\u001B[32m";
    static final String yellow = "\u001B[33m";
    static final String blue   = "\u001B[34m";

    public static void main (String[] args) {
        int n; char choice;
        System.out.print(yellow + "Enter The Dimension (N*N) Of The board N= ");
        do { // Put Protection on The size
            n = get.nextInt();
            if (n < 3) System.out.print(red + "ERROR!! Please Enter at least 2!");
        } while (n < 3);
        do {// Put Protection on The Choice
            System.out.print("Enter The Player To Start: ");
            choice = get.next().charAt(0);
            choice = Character.toUpperCase(choice);
            if (choice != 'X' && choice != 'O')
                System.out.println(red + "ERROR!! Please Enter Either X or O !");
        } while (choice != 'X' && choice != 'O');

        char grid [][] = new char [n][n]; // create the tictactoe data holder
        int res[][] = new int [2][n + 2]; // First array [0] for X data, Second [1] for O data
        draw(n, grid);

        do {
            input(n, grid, choice);
            draw(n, grid);
            if (choice == 'X')  choice = 'O'; else choice = 'X';
            if (check(n, grid, res, choice) == 1) {
                System.out.println("\n" + blue + "X won !"); break;
            }
            if (check(n, grid, res, choice) == 0)  {
                System.out.println("\n" + blue + "O won !"); break;
            }

        } while (check(n, grid, res, choice) == -1);
    }

    public static void input (int n, char grid[][], char choice) {
        int row, col;
        System.out.println("Player " + blue + choice + yellow + " Enter The Corresponding: ");
        do { // Put Protection on The cell position choice
            System.out.print(green + "ROW   : ");
            row = get.nextInt();
            System.out.print(green + "COLUMN: ");
            col = get.nextInt();
            if (row < 1 || col < 1 || row > n || col > n || grid[row - 1][col - 1] == 'X' || grid[row - 1][col - 1] == 'O' )
                System.out.println(red + "ERROR!! Please MAke Sure The Cell is empty and the entered data is between 1 and " + n);
        } while (row < 1 || col < 1 || row > n || col > n || grid[row - 1][col - 1] == 'X' || grid[row - 1][col - 1] == 'O' );
        grid[row - 1][col - 1] = choice;
    }

    public static void draw (int n, char grid[][]) {
        for (int row = 0; row < n + 1; row++) {
            for (int col = 0; col <= n; col++)
                System.out.print(blue + " --"); // for style :):)
            System.out.println();
            for (int col = 0; col < n && row < n; col++)
                System.out.print("  " + green + grid[row][col] + red + "|"); // print the tictactoe board
            System.out.println();
        }
    }

    public static int check (int n, char grid[][], int res[][], char choice) {
        int x = 0, o = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'X') { x++; res[0][j]++; }
                if (grid[i][j] == 'O') { o++; res[1][j]++; }
                // check for rows and olumns
                if (i == j) { // check for first diagonal
                    if (grid[i][j] == 'X') res[0][n]++;
                    if (grid[i][j] == 'O') res[1][n]++;
                }

                if (j == n - i - 1) { // check for second diagonal
                    if (grid[i][j] == 'X') res[0][n + 1]++;
                    if (grid[i][j] == 'O') res[1][n + 1]++;
                }

            }
            if (x == n) return 1; // return if X won on a row
            if (o == n) return 0; // return if O won on a row
            x = 0; o = 0;
        }

        for (int m = 0; m < n + 2; m++) {
            if (res[0][m] == n) return 1;
            if (res[1][m] == n) return 0; // check data for col and diag

        }
        for (int m = 0; m < n + 2; m++) { // empty data on failure for next loop
            res[0][m] = 0; res[1][m] = 0;
        }

        return -1;
    }

}


