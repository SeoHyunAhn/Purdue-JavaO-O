/**
 * Created by student on 10/3/16.
 */
public class Lab07 {
    public boolean isSymmetric(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < row; col++) {
                if (matrix[row][col] != matrix[col][row]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isDiagonal(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < row; col++) {
                if (row == col && (col != 0 && row != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isIdentity(int[][] matrix) {
        if (isSymmetric(matrix)==false)
            return false;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < row; col++) {
                if (row == col) {
                    if (row == 1 && col == 1) {
                        return true;
                    }}
                else{
                    if (row == 0 && col == 0) {
                        return true;
                        }
                    }}
            }
            return false;
        }
    public boolean isUpperTriangular(int[][] matrix){
        if (isSymmetric(matrix)==false)
            return false;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < row; col++) {
                if (matrix[row][col]!=0)
                    return false;}
                }
                return true;
                }
                /*
                while (row<matrix.length){
                    for (int i=0; i<col; i++)
                    if (row==i) {
                        if (col < i)
                            matrix[row][col] = 1;
                        else
                            matrix[row][col] = 0;
                    }
                    System.out.printf("%d", matrix[row][col]);
                    return true;
                    }}}return false;
            }*/
    public boolean isTridiagonal(int[][] matrix) {
        if (isSymmetric(matrix) == false)
            return false;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < row; col++) {
                if (row == col) {
                    if (row != 0 && col != 0)
                        return true;
                }
                if ((row + 1) == col)
                    if (row != 0 && col != 0)
                        return true;
                if ((col + 1) == row)
                    if (row != 0 && col != 0)
                        return true;
            }
        }
        return false;
    }

    /**
     * Created by student on 10/9/16.
     */
    public static class Game {
        Game game1=new Game();
        game1.Connect4;
    }
}

