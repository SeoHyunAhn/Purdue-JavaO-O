import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by student on 10/3/16.
 */
public class Connect4 {
    private char[][] board;
    private int column; //가로
    private int row; //세로
    private char red = 'O';
    private char yellow = 'X';
    //private char EmptySpace = '\0';
    public Connect4(){
        this.board = new char[6][7];
        for (int i = 0; i < board.length; i++)
            for (int j=0; j< board[i].length; j++)
                board[i][j] = ' ';

    }

    public char[][] getBoard() {
        char [][]copy = new char[this.board.length][this.board[0].length];
        for (int i = 0; i < board.length; i++)
            for (int j=0; j< board[i].length; j++)
                copy[i][j]=board[i][j];
        return copy;
    }
    public void printScreen() {
        for (int row = 0; row < board.length; ++row) {
            System.out.println("----------------------");
            System.out.print("| ");
            for (int col = 0; col < board[row].length; ++col)
                System.out.print(board[row][col] + "| ");
            System.out.println();
        }
        System.out.println("----------------------");

    }

    public int putPiece(int column, char color) {
        getBoard();
        int row;
        for (row = 5; row >= 0; --row)
            if ((board[row][column]) ==' '){
                board[row][column] = color;
                return row;
            }
        if (board[0][column]!=' '){
            return -1;}
        return row;
    }

    public char checkAlignment(int row, int column) {
        //y-axis
        int count = 0;
        for (int row1 = 1; row1 < 6; ++row1) {
            if ((board[row1][column]) != ' ' && board[row1][column] == board[row1 - 1][column]) {
                ++count;
                if (count >= 3)
                    return board[row1][column];
            } else
                count = 0;
        }

        //x-axis
        for (int column1 = 1; column1 < 7; column1++) {
            if ((board[row][column1]) != ' ' && board[row][column1] == board[row][column1 - 1]) {
                ++count;
                if (count >= 3)
                    return board[row][column1];
            } else
                count = 0;
        }

        //Diagonal
        int checkO = 0;
        int checkX = 0;
        int trow = row;
        int tcol = column;
        //int sum = row + column;
        {
            while (trow != 0 && tcol != 0) {
                trow--;
                tcol--;
            }
            for (int i = 0; i < 7; i++) {
                if (board[trow + i][tcol + i] == 'O') {
                    checkO++;
                    checkX = 0;
                }
                if (board[trow + i][tcol + i] == 'X') {
                    checkX++;
                    checkO = 0;
                }
                if (board[trow + i][tcol + i] == ' ') {
                    checkO = 0;
                    checkX = 0;
                }
                if (checkO >= 4) {
                    return board[row][column];
                }
                if (checkX >= 4) {
                    return board[row][column];
                }
                if (trow + i == 5 || tcol + i == 6) {
                    i = 7;
                }
            }
        }
         checkO = 0;
         checkX = 0;
         trow = row;
         tcol = column;
        {
            while (trow != 5 && tcol != 0) {
                trow++;
                tcol--;
            }
            for (int i = 0; i < 7; i++) {
                if ((trow - i) == -1 || (tcol+i)== 6)
                    break;
                if (board[trow - i][tcol + i] == 'O') {
                    checkO++;
                    checkX = 0;
                }
                if (board[trow - i][tcol + i] == 'X') {
                    checkX++;
                    checkO = 0;
                }
                if (board[trow - i][tcol + i] == ' ') {
                    checkO = 0;
                    checkX = 0;
                }
                if (checkO >= 4) {
                    return board[row][column];
                }
                if (checkX >= 4) {
                    return board[row][column];
                }

            }
        }return ' ';
    }



    public void play() {
        getBoard();
        while(true) {
            printScreen();
            System.out.print("Current player: 'O' \nChoose a column: ");
            Scanner scanner = new Scanner(System.in);
            int column2 = scanner.nextInt();
            if (column2 < 1 || column2 > 7) {
                System.out.println("Column should be from 1 to 7");
                continue;
            }
            putPiece(column2, 'O');
            printScreen();
            char ch = 0;
            System.out.print("Current player: 'X' \nChoose a column: ");
            int column3 = scanner.nextInt();
            putPiece(column3, 'X');
            for (int row = 1; row < 6; row++) {
                for (int column = 1; column < 7; column++) {
                    if (checkAlignment(row, column) != ' ') {
                        ch = board[row][column];
                        System.out.println(ch);
                        if (board[row][column] == 'O') {
                            System.out.print("!!! Winner is player 'O'");
                            return;
                        } else if (board[row][column] == 'X') {
                            System.out.print("!!! Winner is player 'X'");
                            return;
                        }
                    }
                }
            }
        }
    }


}