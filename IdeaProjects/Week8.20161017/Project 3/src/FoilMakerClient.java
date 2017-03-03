//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//
///**
// * Created by student on 10/25/16.
// */
//public class FoilMakerClient {
////    JTextField field, PW;
////    JButton Loginbutton, regbutton;
////    private JPanel panel1 = new JPanel();
////    private JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
////    private JPanel panel3=new JPanel(new FlowLayout(FlowLayout.CENTER));
////    private JLabel item1 = new JLabel("FoilMaker");
////    private JLabel item2 = new JLabel("Welcome");
////    JTextField  testField = new JTextField(999);
////    String username, password, msg, RequestUser, item2msg;
////    private JTextArea output;
////    FoilMakerController controller;
////
////
//////    public void FoilMakerClient(FoilMakerClient client) {
//////        this.client = client;
//////    }
////    public void FoilMakerLogin_buttonLogin(ActionEvent e){
////
////        controller.ConnecttoServer();
////        addController(controller);// 보통 client에 들어가는 것들 (연결확인 이런ㄱ
////        JButton click = (JButton) e.getSource();
////        if (click==Loginbutton){
////            msg = "LOGIN";
////            username=field.getText();
////            password=PW.getText();
////            userRequest(msg, username, password);
////            controller.sendMessage(RequestUser);
////            String received1=controller.receiveMessage();//네트워크랑 연결하는 method 수현식 여기다 연결해놓을 것
////            System.out.print(received1);
////            String[] parts = received1.split("--");
////            if (parts[2].equals("INVALIDMESSAGEFPRMAT")){
////                item2.setText("Invalid format");
////            } else if (parts[2].equals("UNKNOWNUSER")){
////                item2.setText("Invalid user");}
////            else if (parts[2].equals("INVALIDUSERPASSWORD")){
////                item2.setText("Invalid Password");}
////            else if (parts[2].equals("USERALREADYLOGGEDIN")){
////                item2.setText("User already logged in");
////            }
////            else if (parts[2].equals("SUCCESS")){
////                item2.setText(username+" logging in");}
////        }
////        if (click==regbutton){
////            msg = "CREATENEWUSER";
////            userRequest(msg, username, password);
////            controller.sendMessage(RequestUser);
////            String received2=controller.receiveMessage();//네트워크랑 연결하는 method 수현식 여기다 연결해놓을 것
////            String[] parts = received2.split("--");
////            if (parts[2].equals("INVALIDMESSAGEFPRMAT")){
////                item2.setText("Invalid format");
////            } else if (parts[2].equals("INVALIDUSERNAME"))
////                item2.setText("Username is empty");
////            else if (parts[2].equals("INVALIDUSERPASSWORD"))
////                item2.setText("Password\tempty");
////            else if (parts[2].equals("USERALREADYEXISTS"))
////                item2.setText("User already exist");
////            else if (parts[2].equals("SUCCESS"))
////                item2.setText(username+" logging in");
////        }
////        FoilMakerModel.getUser();
////    }
////    public void addController(FoilMakerController controller){
////        this.controller = controller;
////    }
////    public void userRequest(String msg, String name, String pw){
////        RequestUser= msg+"--"+name+"--"+pw;
////    }
//
//    public static void main(String[] args) {
//        FoilMakerView es =new FoilMakerView();
//        es.FoilMakerLogin();
//        es.setSize(400,600);
//        es.setVisible(true);
//    }
//    //    FoilMakerView view = new FoilMakerView();
////    view.FoilMakerLogin();
//
//    // 클래스 이름.메소드 부르고
//    //call different views
//    // login --> create choose game
//}
