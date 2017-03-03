import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Created by student on 10/14/16.
 */
public class TTTView extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusMessage = new JLabel("Welcome");
    private TicTacToe controller;

    public TTTView(TicTacToe controller) {
        this.controller = controller;
        setUpGUI();
        this.pack();
        this.setVisible(true);
    }

    private void setUpGUI() {
        Container contentPane;
        this.setSize(200, 200);
        contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(statusMessage, BorderLayout.SOUTH);

        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("?");
                buttons[i][j].addActionListener(this);
                tempPanel.add(buttons[i][j]);
            }
            contentPane.add(tempPanel, BorderLayout.CENTER);
        }
    }

    public void setStatusMessage(String msg) {

    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clicked == buttons[i][j]) {
                    controller.cellPicked(i, j);
                }
            }
        }
    }
    public void changeLabel(int i, int j, char sym){
        buttons[i][j].setText(""+sym);
    }
}
