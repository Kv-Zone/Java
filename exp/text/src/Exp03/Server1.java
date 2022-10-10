package Exp03;
import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Server1 {
    public static void main(String args[]) {
        new WindowServer();
    }
}

class WindowServer extends JFrame implements Runnable,ActionListener{//继承
    JButton start,say;
    JTextField inputPort,inputSay;
    JTextArea showtext;
    Socket socket;
    DataOutputStream out=null;
    DataInputStream in=null;
    Thread thread;

    WindowServer(){
        socket=new Socket();
        start=new JButton("Start");
        say=new JButton("Say");
        say.setEnabled(true);
        inputPort=new JTextField(30);//文本框
        inputSay=new JTextField(30);
        showtext=new JTextArea();//文本域
        showtext.setEditable(false);//不可修改

        JPanel jpnorth=new JPanel();
        jpnorth.setBorder(BorderFactory.createTitledBorder("服务端信息: "));
        jpnorth.add(new JLabel("SetPort:"));
        jpnorth.add(inputPort);
        jpnorth.add(start);

        JPanel jpsouth=new JPanel();
        jpsouth.add(new JLabel("输出:"));
        jpsouth.add(inputSay);
        jpsouth.add(say);

        add(jpnorth,BorderLayout.NORTH);
        add(new JScrollPane(showtext),BorderLayout.CENTER);
        add(jpsouth,BorderLayout.SOUTH);

        start.addActionListener(this::actionPerformed);
        say.addActionListener(this);
        thread=new Thread(this);

        setBounds(10,10,600,400);
        setVisible(true);
        setTitle("服务器");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==start) {
            showtext.append("Server starting…\n");

            try {
                if(!socket.isConnected()) {
                    ServerSocket serversocket=new ServerSocket(Integer.parseInt(inputPort.getText()));//服务器套接字等待通过网络进入的请求.创建绑定到指定端口的服务器套接字。
                    socket=serversocket.accept();//受客户端的连接请求,并返回一个套接字。若连接不成功则处于阻塞状态，线程停止
                    showtext.append("Client connected…\n");
                    in=new DataInputStream(socket.getInputStream());//数据输出流
                    out=new DataOutputStream(socket.getOutputStream());//数据输入流
//                    if(!thread.isAlive())
//                        thread=new Thread(this);//创建新线程 run的实现
                    thread.start();
                }
            }
            catch(IOException ee) {
                System.out.println(ee);
                socket=new Socket();
            }
        }
        if(e.getSource()== say) {//点击say时监听
            String S_out=inputSay.getText();//创建String类型来接收传输出去的数据
            showtext.append("我说："+S_out+"\n");//输出自身的数据
            try {
                out.writeUTF(S_out);//向客户端传输数据
            }
            catch(IOException e1) {}
        }
    }

    public void run() {
        String S_in=null;
        while(true) {//持续接收客户端的输入信息
            try {
                S_in=in.readUTF();//接收
                showtext.append("客户:"+S_in+"\n");
            }
            catch(IOException e) {
                showtext.setText("客户1离开");//当先关闭客户端时
                socket=new Socket();
                break;
            }
        }
    }
}