///**
// * Created by student on 10/21/16.
// */
//public class TTTMODEL {
//    public char[][]cells;
//    private char symbols[]={'X', 'O'};
//    public static enum STATUS {inplay, draw, player0wins, player1winds}
//    private STATUS currentstatus=STATUS.inplay;
//    public STATUS checkCurrentstatus(){
//        STATUS status=STATUS.inplay;
//        int row, col, rowstep, colstep;
//        //check each col
//        rowstep=0;
//        colstep=1;
//        col=0;
//        for (row=0; row<3; row++){
//            status=checkLine(row, col, rowstep, colstep);
//            if (status!=STATUS.inplay)
//                return status;
//        }
//        //each row
//        colstep=0;
//        rowstep=1;
//        row=0;
//        for (col=0; col<3; col++){
//            status=checkLine(row, col, rowstep, colstep);
//            if (status!=STATUS.inplay)
//                return status;
//        }
//        //check both diagonals
//        row=0;
//        col=0;
//        rowstep=1;
//        colstep=1;
//        status=checkLine(row, col, rowstep, colstep);
//        if (status!=STATUS.inplay)
//            return status;
//        //2nd
//        row=2;
//        col=0;
//        rowstep=-1;
//        colstep=1;
//        status=checkLine(row, col, rowstep, colstep);
//        if (status!=STATUS.inplay)
//            return status;
//        //check when draw
//        for (row=0; row<3; row++)
//            for (col=0; col<3;col++){
//                if (cells[row][col]==' ')
//                    return STATUS.inplay;
//            }
//        return STATUS.draw;
//    }
//    /*
//    * This method returns the status of the game with respect to a dingle line of three cells which may be a row of thee colums
//    * */
//    private STATUS checkLine(int startrow, int startcol, int rowStep, int colStep) {
//        char c1, c2, c3;
//        int row, col;
//        row = startrow;
//        col = startcol;
//        c1 = cells[startrow][startcol];
//        row += rowStep;
//        col += colStep;
//        c2 = cells[row][col];
//        row += rowStep;
//        col += colStep;
//        c3 = cells[row][col];
//        row += rowStep;
//        col += colStep;
//
//        if (c1==c2 &&c2==c3) {
//            if (c1 == symbols[0])
//                return STATUS.player0wins;
//            else if (c1==symbols[1])
//                return STATUS.player1winds;
//        }
//        return STATUS.inplay;
//    }
//    public void startNewGame(){
//
//    }
//}
