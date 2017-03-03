import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zhangjunyi on 16/10/31.
 */
public class FoilmakerViewqwem extends JFrame {
    public static final int FRAME_WIDTH = 400, FRAME_HEIGHT = 600;
    public static final int PANEL_WIDTH = 390, PANEL_HEIGHT = 590;


    private JLabel usernameLabel = new JLabel("FoliMaker!");
    private JLabel bottomMessageLabel = new JLabel("Welcome!");

    private JPanel mainPanel = null;

    //Login_OR_RegisterView()
    private JPanel login_OR_RegisterView_MainPanel = null;
    private JPanel login_OR_RegisterView_TopPanel = null;
    private JPanel login_OR_RegisterView_CenterPanel = null;
    private JPanel login_OR_RegisterView_BottomPanel = null;

    private JButton login_OR_RegisterView_LoginButton = new JButton("Login");
    private JButton login_OR_RegisterView_RegisterButton = new JButton("Register");

    private JTextField login_OR_RegisterView_UserNameTextField = new JTextField("Enter the username");
    private JTextField login_OR_RegisterView_PassWordTextField = new JTextField("Enter the password");

    private JLabel login_OR_RegisterView_UserNameLabel = new JLabel("name");
    private JLabel login_OR_RegisterView_PassWordLabel = new JLabel("password");

    //registerView()
    private JPanel registerView_MainPanel = null;
    private JPanel registerView_TopPanel = null;
    private JPanel registerView_CenterPanel = null;
    private JPanel registerView_BottomPanel = null;

    private JTextField registerView_UserNameTextField = new JTextField("Enter the username");
    private JTextField registerView_PassWordTextField = new JTextField("Enter the password");

    private JLabel registerView_UserNameLabel = new JLabel("name");
    private JLabel registerView_PassWordLabel = new JLabel("password");
    private JLabel registerView_RegisterLabel = new JLabel("Register your account");

    private JButton registerView_RegisterButton = new JButton("Register");


    //start_JoinGameView()
    private JPanel start_JoinGameView_MainPanel = null;
    private JPanel start_JoinGameView_TopPanel = null;
    private JPanel start_JoinGameView_CenterPanel = null;
    private JPanel start_JoinGameView_BottomPanel = null;

    private JButton start_JoinGameView_StartButton = new JButton("Start New Game");
    private JButton start_JoinGameView_JoinButton = new JButton("Join a Game");

    //start_A_NewGame_KeyView()
    private JPanel start_A_NewGame_KeyView_MainPanel = null;
    private JPanel start_A_NewGame_KeyView_TopPanel = null;
    private JPanel start_A_NewGame_KeyView_CenterPanel = null;
    private JPanel start_A_NewGame_KeyView_BottomPanel = null;

    private JPanel start_A_NewGame_KeyView_ContainParticipantsPanel = null;

    private JLabel start_A_NewGame_KeyView_UpcenterLabel = new JLabel("Others should use this key to join your game");
    private JButton start_A_NewGame_KeyView_Botton = new JButton("Start Game");
    private JTextArea start_A_NewGame_KeyView_TextArea = new JTextArea();
    private JTextField start_A_NewGame_KeyView_KeyTextField = new JTextField(3);

    //joinGame_EnterKeyToJoinView
    private JPanel joinGame_EnterKeyToJoinView_MainPanel = null;
    private JPanel joinGame_EnterKeyToJoinView_TopPanel = null;
    private JPanel joinGame_EnterKeyToJoinView_CenterPanel = null;
    private JPanel joinGame_EnterKeyToJoinView_BottomPanel = null;

    private JLabel joinGame_EnterKeyToJoinView_CenterLabel = new JLabel("Enter the game key to join a game");
    private JTextField joinGame_EnterKeyToJoinView_CenterTextField = new JTextField(3);
    private JButton joinGame_EnterKeyToJoinView_CenterJoinGameBottom = new JButton("Join Game");

    //joinGame_WaitingForLeaderView()
    private JPanel joinGame_WaitingForLeaderView_MainPanel = null;
    private JPanel joinGame_WaitingForLeaderView_TopPanel = null;
    private JPanel joinGame_WaitingForLeaderView_CenterPanel = null;
    private JPanel joinGame_WaitingForLeaderView_BottomPanel = null;

    private JLabel joinGame_WaitingForLeaderView_CenterLabel = new JLabel("Waiting for leader...");

    //launchGame_WhatIsTheWordForView()
    private JPanel launchGame_WhatIsTheWordForView_MainPanel = null;
    private JPanel launchGame_WhatIsTheWordForView_TopPanel = null;
    private JPanel launchGame_WhatIsTheWordForView_CenterPanel = null;
    private JPanel launchGame_WhatIsTheWordForView_BottomPanel = null;

    private JPanel launchGame_WhatIsTheWordForView_InnerPanel1 = null;
    private JPanel launchGame_WhatIsTheWordForView_InnerPanel2 = null;


    private JLabel launchGame_WhatIsTheWordForView_WhatIsTheWordForLabel = new JLabel("  What is the word for");
    private JTextArea launchGame_WhatIsTheWordForView_TextArea = new JTextArea("A group of zebras");
    private JTextField launchGame_WhatIsTheWordForView_TextField = new JTextField();
    private JButton launchGame_WhatIsTheWordForView_SubmitSuggestionButton = new JButton("Submit Suggestion");



    JFrame jframe = new JFrame();

    String password;
    String username;
    public void setupJFrame(){
        jframe.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        jframe.setLocation(400,400);
        this.setTitle("FoilMaker");
        jframe.add(mainPanel);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void mainPanelView(){
        mainPanel = new JPanel();
        CardLayout layout = new CardLayout();

        mainPanel.setLayout(layout);
        mainPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        mainPanel.add(login_OR_RegisterView_MainPanel,"login_OR_RegisterView_MainPanel");
        mainPanel.add(start_JoinGameView_MainPanel,"start_JoinGameView_MainPanel");
        //     mainPanel.add(start_A_NewGame_KeyView_MainPanel,"start_A_NewGame_KeyView_MainPanel");
        //      mainPanel.add(joinGame_EnterKeyToJoinView_MainPanel,"joinGame_EnterKeyToJoinView_MainPanel");
        //      mainPanel.add(joinGame_WaitingForLeaderView_MainPanel,"joinGame_WaitingForLeaderView_MainPanel");
        //      mainPanel.add(launchGame_WhatIsTheWordForView_MainPanel,"launchGame_WhatIsTheWordForView_MainPanel");

        login_OR_RegisterView_LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(mainPanel,"start_JoinGameView_MainPanel");
            }
        });

        layout.show(mainPanel,"login_OR_RegisterView_MainPanel");


    }

    //public void login_OR_RegisterView()
    public void login_OR_RegisterView() {
        login_OR_RegisterView_MainPanel = new JPanel(new BorderLayout());
        login_OR_RegisterView_TopPanel = new JPanel(new FlowLayout());
        login_OR_RegisterView_TopPanel.add(usernameLabel);

        login_OR_RegisterView_CenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        login_OR_RegisterView_CenterPanel.add(login_OR_RegisterView_UserNameLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        login_OR_RegisterView_CenterPanel.add(login_OR_RegisterView_UserNameTextField, c);
        c.gridx = 0;
        c.gridy = 1;
        login_OR_RegisterView_CenterPanel.add(login_OR_RegisterView_PassWordLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        login_OR_RegisterView_CenterPanel.add(login_OR_RegisterView_PassWordTextField, c);
        c.gridx = 0;
        c.gridy = 2;
        login_OR_RegisterView_CenterPanel.add(login_OR_RegisterView_LoginButton, c);
        c.gridx = 1;
        c.gridy = 2;
        login_OR_RegisterView_CenterPanel.add(login_OR_RegisterView_RegisterButton, c);
        login_OR_RegisterView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        login_OR_RegisterView_BottomPanel = new JPanel(new BorderLayout());
        login_OR_RegisterView_BottomPanel.add(bottomMessageLabel);


        login_OR_RegisterView_MainPanel.add(login_OR_RegisterView_TopPanel, BorderLayout.NORTH);
        login_OR_RegisterView_MainPanel.add(login_OR_RegisterView_CenterPanel, BorderLayout.CENTER);
        login_OR_RegisterView_MainPanel.add(login_OR_RegisterView_BottomPanel, BorderLayout.SOUTH);
        login_OR_RegisterView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

        password = login_OR_RegisterView_PassWordTextField.getText();
        username = login_OR_RegisterView_UserNameTextField.getText();
    }

    //public void registerView()
    public void registerView(){
        registerView_MainPanel = new JPanel(new BorderLayout());
        registerView_TopPanel = new JPanel(new FlowLayout());
        registerView_TopPanel.add(usernameLabel);

        registerView_CenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.gridx = 1;
        c.gridy = 0;
        registerView_CenterPanel.add(registerView_RegisterLabel,c);
        c.gridx = 0;
        c.gridy = 1;
        registerView_CenterPanel.add(registerView_UserNameLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        registerView_CenterPanel.add(registerView_UserNameTextField, c);
        c.gridx = 0;
        c.gridy = 2;
        registerView_CenterPanel.add(registerView_PassWordLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        registerView_CenterPanel.add(registerView_PassWordTextField, c);
        c.gridx = 1;
        c.gridy = 3;
        registerView_CenterPanel.add(registerView_RegisterButton,c);


        registerView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        registerView_BottomPanel = new JPanel(new BorderLayout());
        registerView_BottomPanel.add(bottomMessageLabel);


        registerView_MainPanel.add(registerView_TopPanel, BorderLayout.NORTH);
        registerView_MainPanel.add(registerView_CenterPanel, BorderLayout.CENTER);
        registerView_MainPanel.add(registerView_BottomPanel, BorderLayout.SOUTH);
        registerView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

        password = registerView_PassWordTextField.getText();
        username = registerView_UserNameTextField.getText();
    }

    //public void start_JoinGameView()

    public void start_JoinGameView(){
        start_JoinGameView_MainPanel = new JPanel(new BorderLayout());
        start_JoinGameView_TopPanel = new JPanel(new FlowLayout());
        start_JoinGameView_TopPanel.add(usernameLabel);

        start_JoinGameView_CenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        start_JoinGameView_CenterPanel.add(start_JoinGameView_StartButton,c);
        c.gridx = 1;
        c.gridy = 0;
        start_JoinGameView_CenterPanel.add(start_JoinGameView_JoinButton,c);
        start_JoinGameView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        start_JoinGameView_BottomPanel = new JPanel(new BorderLayout());
        start_JoinGameView_BottomPanel.add(bottomMessageLabel);

        start_JoinGameView_MainPanel.add(start_JoinGameView_TopPanel,BorderLayout.NORTH);
        start_JoinGameView_MainPanel.add(start_JoinGameView_CenterPanel,BorderLayout.CENTER);
        start_JoinGameView_MainPanel.add(start_JoinGameView_BottomPanel,BorderLayout.SOUTH);
        start_JoinGameView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    //public void start_A_NewGame_KeyView()
    public void start_A_NewGame_KeyView(){
        start_A_NewGame_KeyView_MainPanel = new JPanel(new BorderLayout());
        start_A_NewGame_KeyView_TopPanel = new JPanel(new FlowLayout());
        start_A_NewGame_KeyView_TopPanel.add(usernameLabel);

        start_A_NewGame_KeyView_CenterPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.gridy=0;
        c.gridx=0;
        start_A_NewGame_KeyView_CenterPanel.add(start_A_NewGame_KeyView_UpcenterLabel,c);
        c.gridy=1;
        c.gridx=0;
        start_A_NewGame_KeyView_CenterPanel.add(start_A_NewGame_KeyView_KeyTextField,c);
        c.gridy=2;
        c.gridx=0;

        start_A_NewGame_KeyView_ContainParticipantsPanel = new JPanel(new FlowLayout());
        start_A_NewGame_KeyView_ContainParticipantsPanel.setBorder(BorderFactory.createTitledBorder("Participants"));
        start_A_NewGame_KeyView_ContainParticipantsPanel.setPreferredSize(new Dimension(300,200));
        start_A_NewGame_KeyView_ContainParticipantsPanel.setBackground(Color.white);
        start_A_NewGame_KeyView_TextArea.setBackground(Color.lightGray);
        start_A_NewGame_KeyView_TextArea.setPreferredSize(new  Dimension(290,170));
        start_A_NewGame_KeyView_ContainParticipantsPanel.add(start_A_NewGame_KeyView_TextArea);
        c.gridy=2;
        c.gridx=0;
        start_A_NewGame_KeyView_CenterPanel.add(start_A_NewGame_KeyView_ContainParticipantsPanel,c);


        c.gridy=3;
        c.gridx=0;
        start_A_NewGame_KeyView_CenterPanel.add(start_A_NewGame_KeyView_Botton,c);
        start_A_NewGame_KeyView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());


        start_A_NewGame_KeyView_BottomPanel = new JPanel(new BorderLayout());
        start_A_NewGame_KeyView_BottomPanel.add(bottomMessageLabel);

        start_A_NewGame_KeyView_MainPanel.add(start_A_NewGame_KeyView_TopPanel,BorderLayout.NORTH);
        start_A_NewGame_KeyView_MainPanel.add(start_A_NewGame_KeyView_CenterPanel,BorderLayout.CENTER);
        start_A_NewGame_KeyView_MainPanel.add(start_A_NewGame_KeyView_BottomPanel,BorderLayout.SOUTH);
        start_A_NewGame_KeyView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    //public void joinGame_EnterKeyToJoinView()
    public void joinGame_EnterKeyToJoinView(){
        joinGame_EnterKeyToJoinView_MainPanel = new JPanel(new BorderLayout());
        joinGame_EnterKeyToJoinView_TopPanel = new JPanel(new FlowLayout());
        joinGame_EnterKeyToJoinView_TopPanel.add(usernameLabel);

        joinGame_EnterKeyToJoinView_CenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 0;
        joinGame_EnterKeyToJoinView_CenterPanel.add(joinGame_EnterKeyToJoinView_CenterLabel,c);
        c.gridx = 0;
        c.gridy = 1;
        joinGame_EnterKeyToJoinView_CenterPanel.add(joinGame_EnterKeyToJoinView_CenterTextField,c);
        c.gridx = 0;
        c.gridy = 2;
        joinGame_EnterKeyToJoinView_CenterPanel.add(joinGame_EnterKeyToJoinView_CenterJoinGameBottom,c);

        joinGame_EnterKeyToJoinView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        joinGame_EnterKeyToJoinView_BottomPanel = new JPanel(new BorderLayout());
        joinGame_EnterKeyToJoinView_BottomPanel.add(bottomMessageLabel);

        joinGame_EnterKeyToJoinView_MainPanel.add(joinGame_EnterKeyToJoinView_TopPanel,BorderLayout.NORTH);
        joinGame_EnterKeyToJoinView_MainPanel.add(joinGame_EnterKeyToJoinView_CenterPanel,BorderLayout.CENTER);
        joinGame_EnterKeyToJoinView_MainPanel.add(joinGame_EnterKeyToJoinView_BottomPanel,BorderLayout.SOUTH);
        joinGame_EnterKeyToJoinView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

    }

    // public void joinGame_WaitingForLeaderView()
    public void joinGame_WaitingForLeaderView(){
        joinGame_WaitingForLeaderView_MainPanel= new JPanel(new BorderLayout());
        joinGame_WaitingForLeaderView_TopPanel = new JPanel(new FlowLayout());
        joinGame_WaitingForLeaderView_TopPanel.add(usernameLabel);

        joinGame_WaitingForLeaderView_CenterPanel = new JPanel(new GridBagLayout());
        joinGame_WaitingForLeaderView_CenterPanel.add(joinGame_WaitingForLeaderView_CenterLabel);

        joinGame_WaitingForLeaderView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        joinGame_WaitingForLeaderView_BottomPanel = new JPanel(new BorderLayout());
        joinGame_WaitingForLeaderView_BottomPanel.add(bottomMessageLabel);

        joinGame_WaitingForLeaderView_MainPanel.add(joinGame_WaitingForLeaderView_TopPanel,BorderLayout.NORTH);
        joinGame_WaitingForLeaderView_MainPanel.add(joinGame_WaitingForLeaderView_CenterPanel,BorderLayout.CENTER);
        joinGame_WaitingForLeaderView_MainPanel.add(joinGame_WaitingForLeaderView_BottomPanel,BorderLayout.SOUTH);
        joinGame_WaitingForLeaderView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    public void launchGame_WhatIsTheWordForView(){
        launchGame_WhatIsTheWordForView_MainPanel = new JPanel(new BorderLayout());
        launchGame_WhatIsTheWordForView_TopPanel = new JPanel(new FlowLayout());
        launchGame_WhatIsTheWordForView_TopPanel.add(usernameLabel);

        launchGame_WhatIsTheWordForView_CenterPanel = new JPanel(new BorderLayout());
    }

    public static void main(String[] args) {
        FoilmakerViewqwem n = new FoilmakerViewqwem();
        n.login_OR_RegisterView();
        n.start_JoinGameView();
        n.mainPanelView();
        n.setupJFrame();
    }

}