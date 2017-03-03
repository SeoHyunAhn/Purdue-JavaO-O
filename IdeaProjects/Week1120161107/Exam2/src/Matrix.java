/**
 * Created by student on 11/6/16.
 */
public class Matrix {
    public String isSkewSymmetric(int[][] mat){
        int rowCount;

     //check if null or empty, if return null
        if (mat==null || mat.length<1)
            return "NULL";
        for (int i=0; i<mat.length; i++){
            if (mat[i]==null || mat[i].length<1)
                return "NULL"; // integer matrix이기에 첫 로우만 해도 됨
        }
        //check if not rectangular
        rowCount=mat.length;
        for (int i=0; i<mat.length; i++){
            if (mat[i].length !=rowCount)
                return "RECTANGULAR";
        }
        //check if diagonal entries are all zero
        for (int i=0; i<rowCount; i++){
            if (mat[i][i]!=0)
                return "FALSE";
        }
        //check if off diagonal parit add to zero
        for (int row=0; row<rowCount; row++){
            for (int col=0; col<row; col++){
                if (mat[row][col]+mat[col][row]!=0)
                    return "FALSE";
            }
        }

        return "TRUE";
    }
}


/*
   String as=null;
        String RECTANGULAR = "RECTANGULAR";
        String notTrue="FALSE";
        String tRUE= "TRUE";
        String nULL="NULL";
        String isEmpty="";
        if (mat==null|| mat.length<1){
            return nULL;
        }
        for (int i=0; i<mat.length; i++){
            for (int j=0; j<mat[0].length; j++){
                if (mat.length!=mat[j].length){
                    return RECTANGULAR;
                }
//                 if (i+j <mat.length) {
                    if (mat[i][j] != -mat[j][i]) {
                        return notTrue;
                    }
//                }
                if (isEmpty.equals(mat[i][j])){
                    return nULL;
                }
            }
        }
        return tRUE;
 */