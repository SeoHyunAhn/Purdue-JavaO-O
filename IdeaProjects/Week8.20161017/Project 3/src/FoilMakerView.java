import com.intellij.refactoring.changeClassSignature.TypeParameterInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoilMakerView extends JFrame implements ActionListener {
    String username, password, question, answer, sampleAnswers1, sampleAnswers2, sampleAnswers3;
    String leaderstatement, leaderscore, clientfooled,  leaderfooledcount,  clientstatement,  clientscore,  leaderfooled,  clientfooledcount;

        FoilMakerController controller;
    //Field
    JPanel mainPanel;
    JLabel statusMessageLabel, topMessageLabel;
    private final int FRAME_WIDTH = 400, FRAME_HEIGHT = 600;
    private final int STATUS_PANEL_HIEGHT = 60;
    int MainPanel_WIDTH = FRAME_WIDTH - 10, MainPanel_Height = FRAME_HEIGHT - STATUS_PANEL_HIEGHT;
    int PanelWidget_Width = MainPanel_WIDTH - 20, panelWidget_Height = 40;

    private static enum ViewNames {LOGINVIEW}

    //loginpanel
    JPanel loginViewPanel = null;
    JTextField loginPanelUsername = null;
    JPasswordField loginPanelPassword = null;
    JButton loginPanelLoginButton = null, loginPanelRegisterButton = null;


    public FoilMakerView(FoilMakerController controller) {
        this.controller = controller;
        //여기다 하나하나 더하고 만약 뭐뭐시 다음으로 넘어가는 것을 더한다
    }

    private void setupFrame() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setSize(new Dimension(MainPanel_WIDTH, MainPanel_Height));
        statusMessageLabel = new JLabel("Welcome");
        topMessageLabel = new JLabel("FoilMaker!");
        contentPane.add(statusMessageLabel, BorderLayout.SOUTH);
        contentPane.add(topMessageLabel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);

        this.setTitle("FoilMaker");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.repaint();
    }

    private void createLoginView() {
        JPanel tempPanel1, tempPanel2;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.weightx = 1.0;

        loginViewPanel = new JPanel(new GridLayout());
        loginViewPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        loginViewPanel.setSize(new Dimension(MainPanel_WIDTH, MainPanel_Height));

        loginPanelUsername = new JTextField("Enter user name", 20);
        loginPanelPassword = new JPasswordField("ENter Password", 20);
        loginPanelLoginButton = new JButton("Login");
        loginPanelLoginButton.addActionListener(this);
        loginPanelRegisterButton = new JButton("Register");
        loginPanelRegisterButton.addActionListener(this);

        tempPanel1 = new JPanel(new GridLayout(2, 2));
        tempPanel1.add(new JLabel("User Name"));
        tempPanel1.add(loginPanelUsername);
        tempPanel1.add(new JLabel("Password"));
        tempPanel1.add(loginPanelPassword);

        tempPanel2 = new JPanel(new GridLayout(0, 2));
        tempPanel2.add(loginPanelLoginButton);
        tempPanel2.add(loginPanelRegisterButton);

        constraints.weightx = 0.25;
        constraints.gridx = 0;
        constraints.gridy = 0;

        loginViewPanel.add(new JPanel(), constraints);
        mainPanel.add(loginViewPanel, ViewNames.LOGINVIEW.toString());
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            ButtonLoginRegister(e);
        }
    }

    public void setStatusMessage(String str) {
        statusMessageLabel.setText(str);
    }
    public void questionMaker(String question, String answer){
        this.question=question;
        this.answer=answer;
    }
    public void getuseranswer(String sampleAnswers1, String sampleAnswers2, String sampleAnswers3){
        this.sampleAnswers1=sampleAnswers1;
        this.sampleAnswers2=sampleAnswers2;
        this.sampleAnswers3=sampleAnswers3;
    }
    //                  statement, score, fooled?, fooledcount, statement, score, fooled?, fooledcount
    public void recievedResults(String leaderstatement, String leaderscore, String clientfooled, String leaderfooledcount, String clientstatement, String clientscore, String leaderfooled, String clientfooledcount ){
        this.leaderstatement=leaderstatement;
        this.leaderscore=leaderscore;
        this.clientfooled=clientfooled;
        this.leaderfooledcount=leaderfooledcount;
        this.clientstatement=clientstatement;
        this.clientscore=clientscore;
        this.leaderfooled=leaderfooled;
        this.clientfooledcount=clientfooledcount;
    }

    public void ButtonLoginRegister(ActionEvent e) {
        Object source = e.getSource();
        username = loginPanelUsername.getText();
        password = String.valueOf(loginPanelPassword.getPassword());
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String a = button.getText();
            if (a.equals(loginPanelLoginButton)) {
                controller.loginUser(username, password);
            } else if (a.equals(loginPanelRegisterButton)) {
                controller.registerUser(username, password);
//            } else if (a.equals(NewGame))
//                controller.newGameLeader();
//            else if (a.equals(JoinGame))
//                controller.joinGame();
//        }
            }
        }
    }
}

