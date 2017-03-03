///**
// * Created by student on 10/21/16.
// */
//
//import javax.swing.*;
//import javax.swing.text.SimpleAttributeSet;
//import javax.swing.text.StyledDocument;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.AdjustmentEvent;
//import java.awt.event.AdjustmentListener;
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Vector;
//
//class sampel extends JFrame implements Runnable, ActionListener,AdjustmentListener{
//    private JButton startButton, exitButton;
//    private JScrollBar bar;
//    private JTextPane statePane;
//    private SimpleAttributeSet attributestate;
//    private StyledDocument docState;
//    private JLabel titleLB, stateLB;
//    private JOptionPane jopt;
//    private InetAddress ip;
//    private int clientOne, clientteo, ClientThree;
//    private String userone, usertwo, userthree;
//    private Vector vector;
//
//    private Point point;
//    private ServerSocket servSoc;
//    private Socket soc;
//    private boolean bStop;
//    private boolean bStart=false;
//    static ServerAbout cp;
//    static ServerThread st;// multiple threads (우리 때는 필요없는것
//
//    public Server(){
//        super("Server");
//    }
//    public void initalize(){
//        JPanel pnNorth =new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
//        stateLB=new JLabel("Server Stop...", JLabel.LEFT);
//        stateLB.setOpaque(false);
//        pnNorth.add(stateLB);
//
//        JPanel pnCenter=new JPanel(new BorderLayout());
//        titleLB=new JLabel("SERVER MESSAGE ", JLabel.CENTER);
//        statePane=new JTextPane();
//        statePane.setBorder(BorderFactory.createRaisedBevelBorder());
//        statePane.setEditable(false);
//
//        JScrollPane scroll=new JScrollPane(statePane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scroll.setOpaque(false);
//
//        bar=scroll.getVerticalScrollBar();
//        docState=statePane.getStyledDocument();
//        pnCenter.add(titleLB, "North");
//        pnCenter.add(scroll, "Center");
//
//        Box boxBottom=Box.createHorizontalBox();
//        JPanel pnBottom=new JPanel();
//
//        startButton=new JButton("START");
//        exitButton=new JButton(" EXIT");
//        exitButton.setEnabled(bStart);
//        pnBottom.add(startButton);
//        pnBottom.add(exitButton);
//
//        getContentPane().add(pnNorth, "North");
//        getContentPane().add(pnCenter, "Center");
//        getContentPane().add(pnBottom, "Bottom");
//
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        startButton.addActionListener(this);
//        exitButton.addActionListener(this);
//        Toolkit kit =getToolkit();
//        Dimension dmen=kit.getScreenSize();
//        setLocation((int)((dmen.width-getSize().width)/8, (int)((dmen.height-getSize().height)/8));
//        setSize(300, 300);
//        setResizable(false);
//        docState=statePane.getStyledDocument();
//        setVisible(true);
//    }
//    public void actionPerformed (ActionEvent ae) {
//        Object o = ae.getSource();
//        if (o.equals(startButton)) {
//            bStop = true;
//            Thread t = new Thread(this);
//            t.start();
//            startButton.setEnabled(!bStop);
//            exitButton.setEnabled(bStop);
//        } else if (o.equals(exitButton)) {
//            bStop = (!beforeExit());
//            closeServ();
//        }
//    }
//    public boolean beforeExit(){
//        JLabel content =new JLabel("END??");
//        int confirm=jopt.showConfirmDialog(null, content, "종료확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//        if (confirm==1)
//            return false;
//        return true;
//    }
//    public void adjustmentValueChanged(AdjustmentEvent ae){
//        bar.setValue(bar.getMaximum());
//        bar.removeAdjustmentListener(this);
//    }
//    public void run(){
//        cp=new ServerAbout();
//        try{
//            servSoc=new ServerSocket(0302);
//
//        }
//    }
//}