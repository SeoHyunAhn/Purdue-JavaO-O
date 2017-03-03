/**
 * Created by student on 10/14/16.
 */
public class TTTModel {
    private char[][] cells=new char[3][3];
    private int nextPlayer;
    TicTacToe controller;
    private char[] symbols= {'X', 'O'};

    public TTTModel(TicTacToe controller){
        this.controller=controller;
        for(int i=0; i<3;i++)
            for (int j=0; j<3; j++)
                cells[i][j]=' ';
        nextPlayer=0;
    }
    public char getPlayerSymbol(){
        return symbols[nextPlayer];
    }
    public void cellPicked(int row, int col){
        cells[row][col]=symbols[nextPlayer];
        nextPlayer=(nextPlayer+1)%2;
    }
}
