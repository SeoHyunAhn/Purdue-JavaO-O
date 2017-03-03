/**
 * Created by student on 11/7/16.
 */
public class MatrixMultiplier {
    public static void main(String[] args) {
        int M=4, N=6, L=1000;
        int [][] a=null,b=null,c=null;
        a=new int[M][N];
        initialize(a,M,N);
//        initialize(b,N,L);
        b=createIdentityMatrix(6);
//        c=matrixMultiply(a,b,M,N,L);
        c=matrixMultiply(a,b,M,N,N);

        for (int row=0; row<M; row++) {
            for (int col = 0; col < N; col++) {
                if (c[row][col]!=a[row][col]){
                    System.out.println("Array not in match "+row+" , "+ col);
                }
            }
        }
        double q=3.0, w=2.0,e=5.0;
        PairOfDoubles result= solveQuadratic(q,w,e);
        System.out.println("The roots are : " +result.value1+ "  ,  "+result.value2);

    }

    private static PairOfDoubles solveQuadratic(double a, double b, double c){
        double root1,root2;
        PairOfDoubles result;

        double commonPart=Math.sqrt(b*b+4*a*c);
        root1=(-1*b +commonPart/(2*a));
        root2=(-1*b -commonPart/(2*a));

        return new PairOfDoubles(root1,root2);
    }

    public static void initialize(int[][] matrix, int numRows, int numCol){
        matrix=new int[numRows][numCol];
        for (int row=0; row<numRows; row++){
            for (int col=0; col<numCol; col++)
                matrix[row][col] = (int) (Math.random()*1000);
        }
    }

    public static int[][] createIdentityMatrix(int n){
        int[][] result=new int[n][n];
        for (int row=0; row<n; row++){
            for (int col=0; col<n; col++) {
                if (row==col)
                    result[row][col]=1;
                else
                    result[row][col]=0;
            }

        }
        return result;
    }

    public static int[][] matrixMultiply(int[][] a, int[][] b, int i, int j, int k){
        int[][] result=new int[i][k];
        int value;

        for (int row=0; row<a.length; row++){
            for (int col=0; col<k; col++){
              value  =0;
                for (int t=0; t<b.length; t++){
                    System.out.println("row:  "+ row+"col:   "+col+"t"+t);
                    value+=a[row][t]*b[t][col];
                }
                result[row][col] = value;
            }
        }
        return result;
    }
}

class PairOfDoubles{
    double value1;
    double value2;
    public PairOfDoubles(double a, double b){
        value1=a;
        value2=b;
    }
}
// if one is calling a method that is returning more than one value back