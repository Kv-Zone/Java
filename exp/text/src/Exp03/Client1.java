package Exp03;

import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Client1 {
    public static void main(String args[]) {
        new WindowClient();
    }
}
class WindowClient extends JFrame implements ActionListener,Runnable {
    JButton connect,say;
    JTextField inputIp,inputPort,inputSay;
    JTextArea showtext;
    Socket socket;
    DataInputStream in=null;
    DataOutputStream out=null;
    Thread thread;
//    Thread thread1;

    WindowClient(){//设置客户端面板以及客户端的Socket连接设置
        socket=new Socket();
        connect=new JButton("connect");
        say=new JButton("say");
        say.setEnabled(true);//设置可点击
        inputIp=new JTextField(15);//单行文本框
        inputPort=new JTextField(15);
        inputSay=new JTextField(30);
        showtext=new JTextArea();//文本域
        showtext.setEditable(false);//不可修改文本域显示的内容

        JPanel jpnorth=new JPanel();
        jpnorth.setBorder(BorderFactory.createTitledBorder("客户端信息: "));
        jpnorth.add(new JLabel("Server IP:"));//输入自身ip
        jpnorth.add(inputIp);
        jpnorth.add(new JLabel("Server Port:"));
        jpnorth.add(inputPort);
        jpnorth.add(connect);

        JPanel jpsouth=new JPanel();
        jpsouth.add(new JLabel("输出:"));
        jpsouth.add(inputSay);
        jpsouth.add(say);

        add(jpnorth,BorderLayout.NORTH);
        add(new JScrollPane(showtext),BorderLayout.CENTER);//将面板加入JsrollPane滚动条
        add(jpsouth,BorderLayout.SOUTH);

        connect.addActionListener(this);
        say.addActionListener(this);//添加监听器
        thread=new Thread(this);
        setBounds(100,500,600,400);
        setVisible(true);//框图可见
        setTitle("客户端");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗口关闭的按键
    }

    public void actionPerformed(ActionEvent e) {//监听
        if(e.getSource()==connect) {//点击connect的情况下
            try {
                if(!socket.isConnected()) {              //判断是否连接 但是好像不能判断实时状态，若开始连接中途断开仍然可以运行
                    showtext.append("Connect to server…\n");
                    InetAddress address=InetAddress.getByName(inputIp.getText());//表示Internet协议（IP）地址。类
                    InetSocketAddress socketAddress=new InetSocketAddress(address,Integer.parseInt(inputPort.getText()));//从IP地址和端口号创建套接字地址。
                    socket.connect(socketAddress);//将此套接字连接到服务器。
                    in=new DataInputStream(socket.getInputStream());
                    out=new DataOutputStream(socket.getOutputStream());
//                    if(!thread.isAlive())
//                        thread=new Thread(this);
                    thread.start();
                }
                else{
                    showtext.append("connected\n");
                }

            }
            catch(IOException ee) {//捕获异常
                System.out.println(ee);
                socket=new Socket();
            }
        }
        if(e.getSource()==say) {//当点击say的时候
            String S_out=inputSay.getText();//创建String对象来接收内容
            showtext.append("我说："+S_out+"\n");//append将两者地址相连并输出
            try {
                out.writeUTF(S_out);
            }
            catch(IOException e1) {}
        }
    }

    public void run() {
        String S_in=null;
        while(true) {
            try {
                S_in=in.readUTF();
                showtext.append("服务器："+S_in+"\n");
                System.out.println();
            }
            catch(IOException e) {
                showtext.setText("与服务器断开"+e);//当先关闭服务端的时候则
                socket=new Socket();
                break;
            }
        }
    }
}