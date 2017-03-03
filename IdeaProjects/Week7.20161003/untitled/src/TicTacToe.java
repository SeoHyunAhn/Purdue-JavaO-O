/**
 * Created by student on 10/14/16.
 */
public class TicTacToe {
    private TTTModel model;
    private TTTView view;

    public TicTacToe(){
        model=new TTTModel(this);
        view=new TTTView(this);
    }
    public void cellClicked(int row, int col){
        model.cellPicked();
        view.changeLabel(row, col, model.getPlayerSymbol());
    }
    public static void main(String[] args) {
        TicTacToe game;
        game=new TicTacToe();
        game.view.setStatusMessage("Player"+game.model.getPlayerSymbol()+"'s Turn");
    }
}
