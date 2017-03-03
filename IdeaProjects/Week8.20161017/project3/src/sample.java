//import com.intellij.codeInsight.template.postfix.templates.SoutPostfixTemplate;
//import com.intellij.util.io.socketConnection.ClientSocketConnection;
//
//import java.net.ServerSocket;
//import java.net.Socket;
//
///**
// * Created by student on 10/21/16.
// */
//public class sample {
//    ServerSocket serverSocket;
//    Socket clientSocket;
//    int portnumber
//    try{
//        System.out.println("Creating socket");
//        serverSocket=new ServerSocket(portnumber);
//
//        System.out.println("Listening");
//        clientSocket=serverSocket.accept();
//        System.out.println("Got a request"+ clientSocket.getPort());
//
//    }
//}
//Jlavel.jtextfield,jpasswaordfield. borderlayout-->cardlayout
//read userdatabase
//같은 컴이면 localhost를 사용
//bob이나 앨리스는 이미만들어져서 거부
//parse string
//위에 이런건 보통 모듈에 넣는 것이 빠르다

import java.awt.*;
import javax.swing.*;
public class sample extends JFrame{
    private JLabel item1 = new JLabel("Bob");
    private JLabel item4 = new JLabel("Welcome!");
    private JButton item2 = new JButton("Start New Game");
    private JButton item3 = new JButton("Join a Game");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel(new BorderLayout());
    private JPanel panel4 = new JPanel(new BorderLayout());
    public sample() {
        super("FoilMaker");
        panel1.add(item1);
        add(panel1, BorderLayout.NORTH);

        //如何把button放在中央
        panel2.add(item2);
        panel2.add(item3);
        panel2.setBorder(BorderFactory.createEtchedBorder());
        add(panel2);

        panel3.add(item4);
        add(panel3, BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        sample n = new sample();
        n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        n.setSize(400,600);
        n.setVisible(true);
    }
}