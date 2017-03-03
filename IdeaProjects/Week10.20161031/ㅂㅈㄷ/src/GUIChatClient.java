import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUIChatClient extends Frame implements ActionListener, Runnable {
    Button btn_exit; // 종료 버튼
    Button btn_send; // 전송 버튼
    Button btn_connect; // 서버 접속 버튼
    TextArea txt_list; // 채팅 내용 보여주는 부분
    TextField txt_server_ip; // 서버 IP 입력 텍스트필드
    TextField txt_name; // 대화명 입력 텍스트필드
    TextField txt_input; // 채팅 입력 텍스트필드
    Socket client; // 소켓
    BufferedReader br; // 입력
    PrintWriter pw; // 출력
    String server_ip; // 서버의 IP 주소
    final int port = 5005; // 서버의 포트 번호
    CardLayout cl; // 카드 레이아웃 선언

    public GUIChatClient() {
        setTitle("채팅 클라이언트 ver 1.0"); // 제목
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // 자원 반납후 프레임 닫기
            }// windowClosing
        });// X 클릭시 종료
        cl = new CardLayout();
        setLayout(cl); // 셋팅 레이아웃(카드레이아웃으로)
        // 접속 화면 구성
        Panel connect = new Panel(); // 접속 화면(판넬)
        connect.setBackground(Color.LIGHT_GRAY);// 밝은 회색으로 설정
        connect.setLayout(new BorderLayout());
        btn_connect = new Button("서버 접속"); // 서버 접속 버튼 생성
        btn_connect.addActionListener(this); // 버튼에 감지기 붙이기
        txt_server_ip = new TextField("127.0.0.1", 15);// 자신의 IP, 15글자
        txt_name = new TextField("홍길동", 15);// 대화명, 15글자
        Panel connect_sub = new Panel(); // 접속 창의 하위 판넬
        connect_sub.add(new Label("서버 아이피(IP) : "));
        connect_sub.add(txt_server_ip);
        connect_sub.add(new Label("대      화      명  :  "));
        connect_sub.add(txt_name);

        // 채팅 화면(판넬) 구성
        Panel chat = new Panel();
        chat.setLayout(new BorderLayout());// 동,서,남,북,중앙 레이아웃
        Label lblChat = new Label("채팅 접속 화면", Label.CENTER);
        connect.add(lblChat, BorderLayout.NORTH);// 제목 북쪽에 붙인다
        connect.add(connect_sub, BorderLayout.CENTER);// 하위창 중앙에붙인다
        connect.add(btn_connect, BorderLayout.SOUTH);// 버튼 남쪽에 붙인다

        // 채팅창 화면 구성
        txt_list = new TextArea(); // 채팅 내용 보여주는 부분
        txt_input = new TextField("", 25);// 채팅 입력
        btn_exit = new Button("종료"); // 종료 버튼 생성
        btn_send = new Button("전송");
        btn_exit.addActionListener(this); // 종료 버튼에 감지기 붙이기
        btn_send.addActionListener(this); // 채팅 전송 버튼에 감지기 붙이기
        txt_input.addActionListener(this); // 채팅 입력란에 감지기 붙이기
        Panel chat_sub = new Panel(); // 채팅창 하위 패널
        chat_sub.add(txt_input);
        chat_sub.add(btn_send);
        chat_sub.add(btn_exit);
        Label lblChatTitle = new Label("채팅 프로그램 ver 1.0", Label.CENTER);
        chat.add(lblChatTitle, BorderLayout.NORTH);
        chat.add(txt_list, BorderLayout.CENTER);
        chat.add(chat_sub, BorderLayout.SOUTH);
        add(connect, "접속창"); // 카드레이아웃에 접속창 붙이기
        add(chat, "채팅창"); // 카드레이아웃에 채팅창 붙이기
        cl.show(this, "접속창");// 첫 화면은 접속창
        setBounds(250, 250, 300, 300); // 크기 지정
        setVisible(true); // 보이기
    }// 생성자

    public void run() { // 채팅 내용 보여주는 기능 수행
        try {
            client = new Socket(server_ip, port); // 소켓 생성(서버로 접속)
            InputStream is = client.getInputStream(); // 입력
            OutputStream os = client.getOutputStream(); // 출력
            br = new BufferedReader(new InputStreamReader(is));
            pw = new PrintWriter(new OutputStreamWriter(os));
            String msg = txt_name.getText();// 대화명 얻기
            pw.println(msg);// 대화명 전송
            pw.flush(); // 즉시 전송
            txt_input.requestFocus();// 채팅 입력란에 커서를 놓는다.
            while (true) {
                msg = br.readLine(); // 채팅 내용 받기
                txt_list.append(msg + "\n");// 채팅 내용 보여준다.
            }// while
        } catch (Exception e) {
            e.printStackTrace();
        }// catch
    }// run

    public void actionPerformed(ActionEvent e) { // 채팅 내용 보내기
        try {
            Object obj = e.getSource(); // 이벤트가 발생된 소스(위치)얻기
            if (obj == btn_connect) { // 접속 버튼을 누른 경우
                server_ip = txt_server_ip.getText();
                Thread th = new Thread(this);
                th.start(); // 쓰레드 시작 -> run 메소드 실행
                cl.show(this, "채팅창");// 카드레이아웃이 채팅창으로 바꾼다.
            } else if (obj == btn_exit) { // 종료 버튼 클릭시
                System.exit(0); // 프로그램 종료
            } else if (obj == btn_send || obj == txt_input) {
                String msg = txt_input.getText();// 채팅 입력
                pw.println(msg);// 채팅 내용 전송
                pw.flush();// 즉시 전송
                txt_input.setText("");// 채팅 입력란 지우기
                txt_input.requestFocus(); // 채팅 입력란에 커서 놓는다.
            }// else if...
        } catch (Exception ex) {
            ex.printStackTrace();
        }// catch
    }// actionPerformed

    public static void main(String[] args) {
        new GUIChatClient();
    }// main
}// end