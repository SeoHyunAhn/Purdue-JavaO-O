
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUIChatServer extends Frame implements ActionListener {
    Button btnExit;
    TextArea ta;
    Vector vChatList;
    ServerSocket ss;
    Socket sockClient;

    public GUIChatServer() {
        setTitle("GUI 채팅 서버 ver 1.0");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // 자원 반납후 프레임 닫기
            }// windowClosing
        });// X 클릭시 종료
        vChatList = new Vector();
        btnExit = new Button("서버 종료");
        btnExit.addActionListener(this);
        ta = new TextArea();
        add(ta, BorderLayout.CENTER);
        add(btnExit, BorderLayout.SOUTH);
        setBounds(250, 250, 200, 200);
        setVisible(true);//보이기
        chatStart();  //  채팅 메소드 시작
    }// 생성자

    public void chatStart() { // 채팅 시작
        try {
            ss = new ServerSocket(5005);// 포트번호
            while (true) { // 무한 반복
                sockClient = ss.accept(); // 클라이언트 올 때마다 소켓 생성
                ta.append(sockClient.getInetAddress().getHostAddress() + "접속함\n");
                ChatHandle threadChat = new ChatHandle();// 한명씩 처리
                vChatList.add(threadChat);// 리스트에 담는다.
                threadChat.start(); // ChatHandle 쓰레드 시작
            }// while
        } catch (Exception e) {
            e.printStackTrace();
        }// catch
    }// ServerStart

    public void actionPerformed(ActionEvent e) {
        //  서버 종료 버튼 클릭시
        dispose(); // 윈도우 자원 반납후 닫기
    }// actionPerformed

    public static void main(String[] args) {
        new GUIChatServer();
    }// main

    //  클라이언트 채팅을 처리하는 Thread 를 상속받은 내부 클래스
    class ChatHandle extends Thread {
        BufferedReader br = null; // 입력 담당
        PrintWriter pw = null; // 출력 담당

        public ChatHandle() {
            try {
                InputStream is = sockClient.getInputStream();// 입력
                br = new BufferedReader(new InputStreamReader(is));
                OutputStream os = sockClient.getOutputStream();// 출력
                pw = new PrintWriter(new OutputStreamWriter(os));
            } catch (IOException e) {
                e.printStackTrace();
            }// catch
        }// 내부 클래스의 기본 생성자

        public void sendAllClient(String msg) {// 모두에게 채팅 내용 전송
            try {
                int size = vChatList.size();// 채팅 사용자의 수를 얻는다.
                for (int i = 0; i < size; i++) {
                    // 반복문을 돌면서 한 사람씩 채팅 내용을 보낸다.
                    ChatHandle chr = (ChatHandle) vChatList.elementAt(i);
                    chr.pw.println(msg); // 사용자 한 사람의 채팅 내용 전송
                    chr.pw.flush(); // 버퍼의 내용을 즉시 전송하라.
                }// for
            } catch (Exception e) {
                e.printStackTrace();
            }// catch
        }// send_All

        public void run() {
            try {
                String name = br.readLine(); // 사용자 닉네임 얻기
                sendAllClient(name + " 님께서 새로 입장했습니다");
                while (true) { // 무한 반복
                    String msg = br.readLine();// 채팅 내용 받기
                    String str = sockClient.getInetAddress().getHostName();
                    ta.append(msg + "\n");  // 채팅 내용 ta 에  보기
                    if (msg.equals("@@Exit")) {// 사용자가 @@Exit를 입력하면
                        break; // 반복문 종료(채팅 끝)
                    } else {
                        sendAllClient(name + " : " + msg);// 모두에게 채팅내용 전송
                    }// else
                }// while
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                vChatList.remove(this);// 사용자 한 명 지우기
                try {
                    br.close();
                    pw.close();
                    sockClient.close();
                } catch (Exception e) {
                }// catch
            }// finally
        }// run
    }// 내부 클래스 ChatHandle 의 끝
}// end
