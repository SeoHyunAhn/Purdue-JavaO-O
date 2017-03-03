//import org.jdesktop.swingx.JXLoginPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zhangjunyi on 16/10/31.
 */
public class FoilMakerView {

    String labelStringUserName ="NeedUserName";
    String labelStringBottomMessage ="Welcome!";
    String password ="";
    String str, participant;
    public String question, answer, sampleAnswers1, sampleAnswers2, sampleAnswers3,maybeSuccess;
    String leaderstatement, leaderscore, clientfooled, leaderfooledcount,clientstatement, clientscore,leaderfooled, clientfooledcount;

    private JPanel mainPanel = null;
    public static final int FRAME_WIDTH = 400, FRAME_HEIGHT = 500;
    public static final int PANEL_WIDTH = 390, PANEL_HEIGHT = 490;
    private boolean isLeader = false;
    //******************************************************************************************************
    //*******************************   all the subview methods'variable  **********************************
    //******************************************************************************************************
    //Login_OR_RegisterView() this view is unnecessary Don't stretch it
    private JPanel login_OR_RegisterView_MainPanel = null;
    private JPanel login_OR_RegisterView_TopPanel = null;
    private JPanel login_OR_RegisterView_CenterPanel = null;
    public JPanel login_OR_RegisterView_BottomPanel = null;
    public JLabel login_OR_RegisterView_BottomLabel=null;

    private JButton login_OR_RegisterView_LoginButton = new JButton("Login");
    private JButton login_OR_RegisterView_RegisterButton = new JButton("Register");

    private JTextField login_OR_RegisterView_UserNameTextField = new JTextField("Enter the UserName");
    private JTextField login_OR_RegisterView_PassWordTextField = new JTextField("Enter the password");

    private JLabel login_OR_RegisterView_UserNameLabel = new JLabel("Username: ");
    private JLabel login_OR_RegisterView_PassWordLabel = new JLabel("Password: ");

    //registerView()
    private JPanel registerView_MainPanel = null;
    private JPanel registerView_TopPanel = null;
    private JPanel registerView_CenterPanel = null;
    private JPanel registerView_BottomPanel = null;

    private JTextField registerView_UserNameTextField = new JTextField("Enter the UserName");
    private JTextField registerView_PassWordTextField = new JTextField("Enter the password");

    private JLabel registerView_UserNameLabel = new JLabel("Username: ");
    private JLabel registerView_PassWordLabel = new JLabel("Password: ");
    private JLabel registerView_RegisterLabel = new JLabel("Register your account");

    private JButton registerView_RegisterButton = new JButton("Register");

    //start_JoinGameView()
    private JPanel start_JoinGameView_MainPanel = null;
    private JPanel start_JoinGameView_TopPanel = null;
    private JPanel start_JoinGameView_CenterPanel = null;
    private JPanel start_JoinGameView_BottomPanel = null;
    public JLabel start_JoinGameView_TopLabel=null;
    public JLabel start_JoinGameView_BottomLabel=null;

    private JButton start_JoinGameView_StartButton = new JButton("Start New Game");
    private JButton start_JoinGameView_JoinButton = new JButton("Join a Game");

    //start_A_NewGame_KeyView()
    private JPanel start_A_NewGame_KeyView_MainPanel = null;
    private JPanel start_A_NewGame_KeyView_TopPanel = null;
    private JPanel start_A_NewGame_KeyView_CenterPanel = null;
    private JPanel start_A_NewGame_KeyView_BottomPanel = null;
    public JLabel start_A_NewGame_KeyView_TopLabel=null;
    private JPanel getStart_A_NewGame_KeyView_BottomLabel=null;

    private JPanel start_A_NewGame_KeyView_ContainParticipantsPanel = null;

    private JLabel start_A_NewGame_KeyView_UpcenterLabel = new JLabel("Others should use this key to join your game");
    JButton start_A_NewGame_KeyView_Botton = new JButton("Start Game");
    public JTextArea start_A_NewGame_KeyView_TextArea = new JTextArea();
    public JTextField start_A_NewGame_KeyView_KeyTextField = new JTextField(3);

    //joinGame_EnterKeyToJoinView
    private JPanel joinGame_EnterKeyToJoinView_MainPanel = null;
    private JPanel joinGame_EnterKeyToJoinView_TopPanel = null;
    private JPanel joinGame_EnterKeyToJoinView_CenterPanel = null;
    private JPanel joinGame_EnterKeyToJoinView_BottomPanel = null;
    private JLabel joinGame_EnterKeyToJoinView_BottomLabel=null;
    private JLabel joinGame_EnterKeyToJoinView_TopLabel=null;

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
    private JPanel launchGame_WhatIsTheWordForView_BottomPanel = new JPanel(new BorderLayout());

    private JPanel launchGame_WhatIsTheWordForView_InnerPanel1 = new JPanel(new FlowLayout());
    private JPanel launchGame_WhatIsTheWordForView_InnerPanel2 = new JPanel(new FlowLayout());
    private JPanel launchGame_WhatIsTheWordForView_InnerPanel3 = new JPanel(new FlowLayout());

    private JLabel launchGame_WhatIsTheWordForView_WhatIsTheWordForLabel = new JLabel("  What is the word for");
    private JTextArea launchGame_WhatIsTheWordForView_TextArea = new JTextArea("A group of zebras");
    private JTextField launchGame_WhatIsTheWordForView_TextField = new JTextField();
    private JButton launchGame_WhatIsTheWordForView_SubmitSuggestionButton = new JButton("Submit Suggestion");

    //pick_SubmitOptionsView()
    private JLabel pick_SubmitOptionsView_label1 = new JLabel("Pick your option below");
    private String[] pick_SubmitOptionsView_options = {"A zippy-do", "A zig zag", "A dazzle"};
    private JButton pick_SubmitOptionsView_SubmitButton = new JButton("Submit Option");

    private JPanel pick_SubmitOptionsView_MainPanel = new JPanel(new BorderLayout());
    private JPanel pick_SubmitOptionsView_TopPanel = new JPanel();
    private JPanel pick_SubmitOptionsView_CenterPanel = new JPanel(new BorderLayout());
    private JPanel pick_SubmitOptionsView_BottomPanel = new JPanel(new BorderLayout());

    private JPanel pick_SubmitOptionsView_InnerPanel1 = new JPanel();
    private JPanel pick_SubmitOptionsView_InnerPanel2 = new JPanel(new GridBagLayout());
    private JPanel pick_SubmitOptionsView_InnerPanel3 = new JPanel(new FlowLayout());

    private JRadioButton[] pick_SubmitOptionsView_RadioButton;

    //receiveResultsView()
    private JButton receiveResultsView_NextRoundButton = new JButton("Next Round");

    private JTextArea receiveResultsView_TextArea1 = new JTextArea("You were fooled by Alice");
    private JTextArea receiveResultsView_TextArea2 = new JTextArea();

    private JPanel receiveResultsView_MainPanel = new JPanel(new BorderLayout());
    private JPanel receiveResultsView_TopPanel = new JPanel();
    private JPanel receiveResultsView_CenterPanel = new JPanel(new GridBagLayout());
    private JPanel receiveResultsView_RoundResultsPanel = new JPanel(new FlowLayout());
    private JPanel receiveResultsView_OverAllPanel = new JPanel(new FlowLayout());
    private JPanel receiveResultsView_BottomPanel = new JPanel(new BorderLayout());


    JFrame jframe = new JFrame();



    FoilMakerController controller;
    private JLabel Start_A_NewGame_KeyView_BottomLabel;

    //娑眉铫ü
    //  FoilMakerController controller;

    public FoilMakerView(FoilMakerController controller){
        this.controller=controller;
        login_OR_RegisterView();
        registerView();
        start_JoinGameView(str);
        start_A_NewGame_KeyView();
        joinGame_EnterKeyToJoinView();
        joinGame_WaitingForLeaderView();
    }

    public FoilMakerView () {

    }


    //public void setupJFrame()
    public void setupJFrame(){
        jframe.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        jframe.setTitle("FoilMaker");
        jframe.add(mainPanel);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.repaint();

    }

    //public void mainPanelView()
    public void mainPanelView() {
        String stringLogin_OR_RegisterView_MainPanel = "login_OR_RegisterView_MainPanel";
        String stringStart_JoinGameView_MainPanel = "start_JoinGameView_MainPanel";
        String stringStart_A_NewGame_KeyView_MainPanel = "start_A_NewGame_KeyView_MainPanel";
        String stringJoinGame_EnterKeyToJoinView_MainPanel = "JoinGame_EnterKeyToJoinView_MainPanel";
        String stringJoinGame_WaitingForLeaderView_MainPanel = "joinGame_WaitingForLeaderView_MainPanel";

        mainPanel = new JPanel();
        CardLayout layout = new CardLayout();

        mainPanel.setLayout(layout);
        mainPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        mainPanel.add(login_OR_RegisterView_MainPanel, stringLogin_OR_RegisterView_MainPanel);
        mainPanel.add(start_JoinGameView_MainPanel, stringStart_JoinGameView_MainPanel);

        mainPanel.add(start_A_NewGame_KeyView_MainPanel, stringStart_A_NewGame_KeyView_MainPanel);
        mainPanel.add(joinGame_EnterKeyToJoinView_MainPanel, stringJoinGame_EnterKeyToJoinView_MainPanel);
        mainPanel.add(joinGame_WaitingForLeaderView_MainPanel, stringJoinGame_WaitingForLeaderView_MainPanel);
//        mainPanel.add(launchGame_WhatIsTheWordForView_MainPanel,"launchGame_WhatIsTheWordForView_MainPanel");

        login_OR_RegisterView_LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelStringUserName=login_OR_RegisterView_UserNameTextField.getText();
                password=login_OR_RegisterView_PassWordTextField.getText();
                controller.loginUser(labelStringUserName, password);
                if (controller.parts[2].equals("INVALIDMESSAGEFPRMAT")) {
                    login_OR_RegisterView_BottomLabel.setText("Invalid format");
                } else if (controller.parts[2].equals("UNKNOWNUSER")) {
                    login_OR_RegisterView_BottomLabel.setText("Invalid user");
                } else if (controller.parts[2].equals("INVALIDUSERPASSWORD")) {
                    login_OR_RegisterView_BottomLabel.setText("Invalid Password");
                } else if (controller.parts[2].equals("USERALREADYLOGGEDIN")) {
                    login_OR_RegisterView_BottomLabel.setText("User already logged in");
                } else if (controller.parts[2].equals("SUCCESS")) {
                    login_OR_RegisterView_BottomLabel.setText(labelStringUserName +"logged in");
                }
////                System.out.println(labelStringBottomMessage+"     +++++=");
//                System.out.println(login_OR_RegisterView_BottomLabel.getText());
                if (possibleSuccess(controller.parts[2]).equals("SUCCESS") ) {
                    start_JoinGameView(controller.doit);
                    mainPanel.add(start_JoinGameView_MainPanel, stringStart_JoinGameView_MainPanel);
                    layout.show(mainPanel, stringStart_JoinGameView_MainPanel);
                    start_JoinGameView_TopLabel.setText(labelStringUserName);
//                    login_OR_RegisterView_BottomLabel.setText("Welcome");
                }
                else {
                    layout.show(mainPanel, stringLogin_OR_RegisterView_MainPanel);
                }

            }
        });
//        while (true) {
//            login_OR_RegisterView_LoginButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    controller.userRequest("LOGIN", labelStringUserName, password);
//                    controller.sendMessage(controller.requestUser);
//                }
//            });
//            controller.loginUser(labelStringUserName, password);
//            if (controller.getRegister()) {
//                layout.show(mainPanel, stringStart_JoinGameView_MainPanel);
//                break;
//            }
//        }
        login_OR_RegisterView_RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelStringUserName=login_OR_RegisterView_UserNameTextField.getText();
                password=login_OR_RegisterView_PassWordTextField.getText();
                controller.registerUser(labelStringUserName,password);
//
                if (controller.parts[2].equals("INVALIDMESSAGEFPRMAT")) {
                    login_OR_RegisterView_BottomLabel.setText("Invalid format");
                } else if (controller.parts[2].equals("INVALIDUSERNAME"))
                    login_OR_RegisterView_BottomLabel.setText("Username is empty");
                else if (controller.parts[2].equals("INVALIDUSERPASSWORD"))
                    login_OR_RegisterView_BottomLabel.setText("Password\tempty");
                else if (controller.parts[2].equals("USERALREADYEXISTS"))
                    login_OR_RegisterView_BottomLabel.setText("User already exist");
                else if (controller.parts[2].equals("SUCCESS")) {
                    login_OR_RegisterView_BottomLabel.setText(labelStringUserName + " logging in");
                }
                if (possibleSuccess(controller.parts[2]).equals("SUCCESS")) {
                    start_JoinGameView(controller.doit);
                    mainPanel.add(start_JoinGameView_MainPanel, stringStart_JoinGameView_MainPanel);
                    layout.show(mainPanel, stringStart_JoinGameView_MainPanel);
                    start_JoinGameView_TopLabel.setText(labelStringUserName);
//
//                    login_OR_RegisterView_BottomLabel.setText("Welcome");
                }
                else {
                    layout.show(mainPanel, stringLogin_OR_RegisterView_MainPanel);
                }
            }
        });
        start_JoinGameView_StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.newGameLeader();
                //change button able to see here
                //   already set the button enable
                if (controller.parts[2].equals("SUCCESS")) {

                    layout.show(mainPanel, stringStart_A_NewGame_KeyView_MainPanel);
                    start_A_NewGame_KeyView_TopLabel.setText(labelStringUserName);
                    start_A_NewGame_KeyView_KeyTextField.setText(controller.hello[3]);
                    start_A_NewGame_KeyView_Botton.setEnabled(false);
                    start_JoinGameView_BottomLabel.setText("Created Game. Press <StartGame> to Start");


                    start_A_NewGame_KeyView_TextArea.append(controller.participantGame());

//                    start_A_NewGame_KeyView_Botton.setEnabled(true);


                }else {
                    layout.show(mainPanel, stringStart_JoinGameView_MainPanel);
                }
            }
//3144f
        });
        start_JoinGameView_JoinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(mainPanel, stringJoinGame_EnterKeyToJoinView_MainPanel);
            }
        });
        joinGame_EnterKeyToJoinView_CenterJoinGameBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String joinGameTk=joinGame_EnterKeyToJoinView_CenterTextField.getText();
                controller.joinGame(joinGameTk);
                if (controller.parts[2].equals("USERNOTLOGGEDIN")) {
                    joinGame_EnterKeyToJoinView_BottomLabel.setText("Invalid Token");
                } else if (controller.parts[2].equals("GAMEKEYNOTFOUND")) {
                    joinGame_EnterKeyToJoinView_BottomLabel.setText("Invalid Game Token");
                } else if (controller.parts[2].equals("FAILURE")) {
                    joinGame_EnterKeyToJoinView_BottomLabel.setText("Game already started");
                } else if (controller.parts[2].equals("SUCCESS")) {
                    joinGame_EnterKeyToJoinView_BottomLabel.setText("Game joined: waiting for leader");
                }    //NEWPARTICIPANT--<username>--<score>
                if (controller.parts[2].equals("SUCCESS")) {

                    layout.show(mainPanel, stringJoinGame_WaitingForLeaderView_MainPanel);
                    joinGame_EnterKeyToJoinView_TopLabel.setText(labelStringUserName);
                    joinGame_EnterKeyToJoinView_BottomLabel.setText("Created Game. Press <StartGame> to Start");
                    start_A_NewGame_KeyView_TextArea.append(participant);
                    jframe.pack();
                    jframe.repaint();

                }else {
                    layout.show(mainPanel, stringStart_JoinGameView_MainPanel);
                }

            }
        });

        layout.show(mainPanel, stringLogin_OR_RegisterView_MainPanel);

    }


    //******************************************************************************************************
    //*******************************   All the subpanelview methods  **********************************
    // ******************************************************************************************************
    //public void login_OR_RegisterView()
    public void login_OR_RegisterView() {

        login_OR_RegisterView_MainPanel = new JPanel(new BorderLayout());
        login_OR_RegisterView_MainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        login_OR_RegisterView_TopPanel = new JPanel(new FlowLayout());


        login_OR_RegisterView_TopPanel.add(new JLabel(labelStringUserName));
        // login_OR_RegisterView_TopPanel.setPreferredSize(new Dimension(PANEL_WIDTH,20));

        //login_OR_RegisterView_TopPanel.setBackground(Color.BLUE);

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
        login_OR_RegisterView_BottomLabel= new JLabel(labelStringBottomMessage);
        login_OR_RegisterView_BottomPanel.add(login_OR_RegisterView_BottomLabel);
        //  login_OR_RegisterView_BottomPanel.setBackground(Color.PINK);


        login_OR_RegisterView_MainPanel.add(login_OR_RegisterView_TopPanel, BorderLayout.NORTH);
        login_OR_RegisterView_MainPanel.add(login_OR_RegisterView_CenterPanel, BorderLayout.CENTER);
        login_OR_RegisterView_MainPanel.add(login_OR_RegisterView_BottomPanel, BorderLayout.SOUTH);
        //login_OR_RegisterView_MainPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        login_OR_RegisterView_MainPanel.setBounds(0,0,PANEL_WIDTH,PANEL_HEIGHT);
        login_OR_RegisterView_MainPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

        password = login_OR_RegisterView_PassWordTextField.getText();
        labelStringUserName = login_OR_RegisterView_UserNameTextField.getText();

    }

    //public void registerView() this view is unnecessary Don't stretch it
    public void registerView(){
        registerView_MainPanel = new JPanel(new BorderLayout());
        registerView_TopPanel = new JPanel(new FlowLayout());
        registerView_TopPanel.add(new JLabel(labelStringUserName));

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
        registerView_BottomPanel.add(new JLabel(labelStringBottomMessage));


        registerView_MainPanel.add(registerView_TopPanel, BorderLayout.NORTH);
        registerView_MainPanel.add(registerView_CenterPanel, BorderLayout.CENTER);
        registerView_MainPanel.add(registerView_BottomPanel, BorderLayout.SOUTH);
        registerView_MainPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));


        //password = registerView_PassWordTextField.getText();
        //labelStringUserName = registerView_UserNameTextField.getText();
    }

    //public void start_JoinGameView()
    public void start_JoinGameView(String str){

        start_JoinGameView_MainPanel = new JPanel(new BorderLayout());
        start_JoinGameView_TopPanel = new JPanel(new FlowLayout());
        start_JoinGameView_TopLabel= new JLabel();
        start_JoinGameView_TopPanel.add(start_JoinGameView_TopLabel);

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
        start_JoinGameView_BottomLabel=new JLabel(str);
        start_JoinGameView_BottomPanel.add(start_JoinGameView_BottomLabel);

        start_JoinGameView_MainPanel.add(start_JoinGameView_TopPanel,BorderLayout.NORTH);
        start_JoinGameView_MainPanel.add(start_JoinGameView_CenterPanel,BorderLayout.CENTER);
        start_JoinGameView_MainPanel.add(start_JoinGameView_BottomPanel,BorderLayout.SOUTH);
        start_JoinGameView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    //public void start_A_NewGame_KeyView()
    public void start_A_NewGame_KeyView(){
        start_A_NewGame_KeyView_MainPanel = new JPanel(new BorderLayout());
        start_A_NewGame_KeyView_TopPanel = new JPanel(new FlowLayout());
        start_A_NewGame_KeyView_TopLabel=new JLabel(labelStringUserName);

        start_A_NewGame_KeyView_TopPanel.add(start_A_NewGame_KeyView_TopLabel);
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
        start_A_NewGame_KeyView_Botton.setEnabled(false);
        start_A_NewGame_KeyView_CenterPanel.add(start_A_NewGame_KeyView_Botton,c);
        start_A_NewGame_KeyView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());


        start_A_NewGame_KeyView_BottomPanel = new JPanel(new BorderLayout());
        Start_A_NewGame_KeyView_BottomLabel=new JLabel(str);
        start_A_NewGame_KeyView_BottomPanel.add(Start_A_NewGame_KeyView_BottomLabel);

        start_A_NewGame_KeyView_MainPanel.add(start_A_NewGame_KeyView_TopPanel,BorderLayout.NORTH);
        start_A_NewGame_KeyView_MainPanel.add(start_A_NewGame_KeyView_CenterPanel,BorderLayout.CENTER);
        start_A_NewGame_KeyView_MainPanel.add(start_A_NewGame_KeyView_BottomPanel,BorderLayout.SOUTH);
        start_A_NewGame_KeyView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    //public void joinGame_EnterKeyToJoinView()
    public void joinGame_EnterKeyToJoinView(){
        joinGame_EnterKeyToJoinView_MainPanel = new JPanel(new BorderLayout());
        joinGame_EnterKeyToJoinView_TopPanel = new JPanel(new FlowLayout());
        joinGame_EnterKeyToJoinView_TopLabel=new JLabel(labelStringUserName);
        joinGame_EnterKeyToJoinView_TopPanel.add(joinGame_EnterKeyToJoinView_TopLabel);

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
        joinGame_EnterKeyToJoinView_BottomLabel=new JLabel();
        joinGame_EnterKeyToJoinView_BottomPanel.add(joinGame_EnterKeyToJoinView_BottomLabel);

        joinGame_EnterKeyToJoinView_MainPanel.add(joinGame_EnterKeyToJoinView_TopPanel,BorderLayout.NORTH);
        joinGame_EnterKeyToJoinView_MainPanel.add(joinGame_EnterKeyToJoinView_CenterPanel,BorderLayout.CENTER);
        joinGame_EnterKeyToJoinView_MainPanel.add(joinGame_EnterKeyToJoinView_BottomPanel,BorderLayout.SOUTH);
        joinGame_EnterKeyToJoinView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    //public void joinGame_WaitingForLeaderView()
    public void joinGame_WaitingForLeaderView(){
        joinGame_WaitingForLeaderView_MainPanel= new JPanel(new BorderLayout());
        joinGame_WaitingForLeaderView_TopPanel = new JPanel(new FlowLayout());
        joinGame_WaitingForLeaderView_TopPanel.add(new JLabel(labelStringUserName));

        joinGame_WaitingForLeaderView_CenterPanel = new JPanel(new GridBagLayout());
        joinGame_WaitingForLeaderView_CenterPanel.add(joinGame_WaitingForLeaderView_CenterLabel);

        joinGame_WaitingForLeaderView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        joinGame_WaitingForLeaderView_BottomPanel = new JPanel(new BorderLayout());

        joinGame_WaitingForLeaderView_BottomPanel.add(new JLabel(labelStringBottomMessage));

        joinGame_WaitingForLeaderView_MainPanel.add(joinGame_WaitingForLeaderView_TopPanel,BorderLayout.NORTH);
        joinGame_WaitingForLeaderView_MainPanel.add(joinGame_WaitingForLeaderView_CenterPanel,BorderLayout.CENTER);
        joinGame_WaitingForLeaderView_MainPanel.add(joinGame_WaitingForLeaderView_BottomPanel,BorderLayout.SOUTH);
        joinGame_WaitingForLeaderView_MainPanel.setSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    //public void launchGame_WhatIsTheWordForView()
    public void launchGame_WhatIsTheWordForView(){
        launchGame_WhatIsTheWordForView_MainPanel = new JPanel(new BorderLayout());
        launchGame_WhatIsTheWordForView_TopPanel = new JPanel(new FlowLayout());
        launchGame_WhatIsTheWordForView_TopPanel.add(new JLabel(labelStringUserName));

        launchGame_WhatIsTheWordForView_CenterPanel = new JPanel(new BorderLayout());
        launchGame_WhatIsTheWordForView_CenterPanel.add(launchGame_WhatIsTheWordForView_WhatIsTheWordForLabel,BorderLayout.NORTH);
        launchGame_WhatIsTheWordForView_TextArea.setPreferredSize(new Dimension(380,220));
        launchGame_WhatIsTheWordForView_TextArea.setBorder(BorderFactory.createEtchedBorder());
        launchGame_WhatIsTheWordForView_TextArea.setBackground(Color.lightGray);

        launchGame_WhatIsTheWordForView_InnerPanel1.add(launchGame_WhatIsTheWordForView_TextArea);

        launchGame_WhatIsTheWordForView_InnerPanel2.setBorder(BorderFactory.createTitledBorder("Your Suggestion"));
        launchGame_WhatIsTheWordForView_InnerPanel2.setPreferredSize(new Dimension(380,250));
        launchGame_WhatIsTheWordForView_TextField.setColumns(28);
        launchGame_WhatIsTheWordForView_InnerPanel2.add(launchGame_WhatIsTheWordForView_TextField);
        launchGame_WhatIsTheWordForView_InnerPanel1.add(launchGame_WhatIsTheWordForView_InnerPanel2);
        launchGame_WhatIsTheWordForView_CenterPanel.add(launchGame_WhatIsTheWordForView_InnerPanel1,BorderLayout.CENTER);

        launchGame_WhatIsTheWordForView_InnerPanel3.add(launchGame_WhatIsTheWordForView_SubmitSuggestionButton);
        launchGame_WhatIsTheWordForView_CenterPanel.add(launchGame_WhatIsTheWordForView_InnerPanel3,BorderLayout.SOUTH);
        launchGame_WhatIsTheWordForView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        launchGame_WhatIsTheWordForView_BottomPanel.add(new JLabel(labelStringBottomMessage));
        launchGame_WhatIsTheWordForView_MainPanel.add(launchGame_WhatIsTheWordForView_TopPanel,BorderLayout.NORTH);
        launchGame_WhatIsTheWordForView_MainPanel.add(launchGame_WhatIsTheWordForView_CenterPanel,BorderLayout.CENTER);
        launchGame_WhatIsTheWordForView_MainPanel.add(launchGame_WhatIsTheWordForView_BottomPanel,BorderLayout.SOUTH);
    }

    //public void pick_SubmitOptionsView()
    public void pick_SubmitOptionsView(){
        pick_SubmitOptionsView_TopPanel.add(new JLabel(labelStringUserName));

        pick_SubmitOptionsView_InnerPanel1.add(pick_SubmitOptionsView_label1);
        pick_SubmitOptionsView_CenterPanel.add(pick_SubmitOptionsView_InnerPanel1,BorderLayout.NORTH);

        pick_SubmitOptionsView_RadioButton = new JRadioButton[pick_SubmitOptionsView_options.length];
        ButtonGroup group = new ButtonGroup();
        for(int i = 0; i < pick_SubmitOptionsView_options.length; i++){
            pick_SubmitOptionsView_RadioButton[i] = new JRadioButton(pick_SubmitOptionsView_options[i]);
            group.add(pick_SubmitOptionsView_RadioButton[i]);
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = GridBagConstraints.RELATIVE;
            c.gridx = 0;
            c.anchor = GridBagConstraints.WEST;
            pick_SubmitOptionsView_InnerPanel2.add(pick_SubmitOptionsView_RadioButton[i],c);
        }
        pick_SubmitOptionsView_CenterPanel.add(pick_SubmitOptionsView_InnerPanel2,BorderLayout.CENTER);

        pick_SubmitOptionsView_InnerPanel3.add(pick_SubmitOptionsView_SubmitButton);
        pick_SubmitOptionsView_CenterPanel.add(pick_SubmitOptionsView_InnerPanel3,BorderLayout.SOUTH);
        pick_SubmitOptionsView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        pick_SubmitOptionsView_BottomPanel.add(new JLabel(labelStringBottomMessage));

        pick_SubmitOptionsView_MainPanel.add(pick_SubmitOptionsView_TopPanel,BorderLayout.NORTH);
        pick_SubmitOptionsView_MainPanel.add(pick_SubmitOptionsView_CenterPanel,BorderLayout.CENTER);
        pick_SubmitOptionsView_MainPanel.add(pick_SubmitOptionsView_BottomPanel,BorderLayout.SOUTH);
    }

    //receiveResultsView()
    public void receiveResultsView(){
        receiveResultsView_TopPanel.add(new JLabel(labelStringUserName));

        receiveResultsView_TextArea1.setPreferredSize(new Dimension(360,140));
        receiveResultsView_TextArea1.setBackground(Color.PINK);
        receiveResultsView_RoundResultsPanel.add(receiveResultsView_TextArea1);
        receiveResultsView_RoundResultsPanel.setPreferredSize(new Dimension(370,170));
        receiveResultsView_RoundResultsPanel.setBackground(Color.white);
        receiveResultsView_RoundResultsPanel.setBorder(BorderFactory.createTitledBorder("Round Result"));

        receiveResultsView_TextArea2.setPreferredSize(new Dimension(352,200));
        receiveResultsView_TextArea2.setBackground(Color.YELLOW);
        JScrollPane scrollPane = new JScrollPane(receiveResultsView_TextArea2);
        receiveResultsView_OverAllPanel.add(scrollPane);
        receiveResultsView_OverAllPanel.setBackground(Color.white);
        receiveResultsView_OverAllPanel.setPreferredSize(new Dimension(370,230));
        receiveResultsView_OverAllPanel.setBorder(BorderFactory.createTitledBorder("Overall Results"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 0;
        receiveResultsView_CenterPanel.add(receiveResultsView_RoundResultsPanel,c);
        c.gridx = 0;
        c.gridy = 1;
        receiveResultsView_CenterPanel.add(receiveResultsView_OverAllPanel,c);
        c.gridx = 0;
        c.gridy = 2;
        receiveResultsView_CenterPanel.add(receiveResultsView_NextRoundButton,c);
        receiveResultsView_CenterPanel.setBorder(BorderFactory.createEtchedBorder());

        receiveResultsView_BottomPanel.add(new JLabel(labelStringBottomMessage));

        receiveResultsView_MainPanel.add(receiveResultsView_TopPanel,BorderLayout.NORTH);
        receiveResultsView_MainPanel.add(receiveResultsView_CenterPanel,BorderLayout.CENTER);
        receiveResultsView_MainPanel.add(receiveResultsView_BottomPanel,BorderLayout.SOUTH);
    }

    public String setStatusMessage(String msg) {
        labelStringBottomMessage=msg;
        System.out.println(labelStringBottomMessage);
        return labelStringBottomMessage;
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
    //            statement, score, fooled?, fooledcount, statement, score, fooled?, fooledcount
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

    public String possibleSuccess(String str){
        this.maybeSuccess = str;

//        System.out.println("possibleSuccess"+maybeSuccess);
        return maybeSuccess;
    }
    public String participant(String str){
        this.participant=str;
        return participant;
    }
//
//    public void defineAllViews(){
//
//        login_OR_RegisterView();
//
//        registerView();
//        start_JoinGameView();
//        start_A_NewGame_KeyView();
//        joinGame_EnterKeyToJoinView();
//        joinGame_WaitingForLeaderView();
//
//    }

    public static void main(String[] args) {
        FoilMakerView n = new FoilMakerView(new FoilMakerController());
//        n.defineAllViews();
        n.mainPanelView();
        n.setupJFrame();
    }

}