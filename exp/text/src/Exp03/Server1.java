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

class WindowServer extends JFrame implements Runnable,ActionListener{//�̳�
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
        inputPort=new JTextField(30);//�ı���
        inputSay=new JTextField(30);
        showtext=new JTextArea();//�ı���
        showtext.setEditable(false);//�����޸�

        JPanel jpnorth=new JPanel();
        jpnorth.setBorder(BorderFactory.createTitledBorder("�������Ϣ: "));
        jpnorth.add(new JLabel("SetPort:"));
        jpnorth.add(inputPort);
        jpnorth.add(start);

        JPanel jpsouth=new JPanel();
        jpsouth.add(new JLabel("���:"));
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
        setTitle("������");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==start) {
            showtext.append("Server starting��\n");

            try {
                if(!socket.isConnected()) {
                    ServerSocket serversocket=new ServerSocket(Integer.parseInt(inputPort.getText()));//�������׽��ֵȴ�ͨ��������������.�����󶨵�ָ���˿ڵķ������׽��֡�
                    socket=serversocket.accept();//�ܿͻ��˵���������,������һ���׽��֡������Ӳ��ɹ���������״̬���߳�ֹͣ
                    showtext.append("Client connected��\n");
                    in=new DataInputStream(socket.getInputStream());//���������
                    out=new DataOutputStream(socket.getOutputStream());//����������
//                    if(!thread.isAlive())
//                        thread=new Thread(this);//�������߳� run��ʵ��
                    thread.start();
                }
            }
            catch(IOException ee) {
                System.out.println(ee);
                socket=new Socket();
            }
        }
        if(e.getSource()== say) {//���sayʱ����
            String S_out=inputSay.getText();//����String���������մ����ȥ������
            showtext.append("��˵��"+S_out+"\n");//������������
            try {
                out.writeUTF(S_out);//��ͻ��˴�������
            }
            catch(IOException e1) {}
        }
    }

    public void run() {
        String S_in=null;
        while(true) {//�������տͻ��˵�������Ϣ
            try {
                S_in=in.readUTF();//����
                showtext.append("�ͻ�:"+S_in+"\n");
            }
            catch(IOException e) {
                showtext.setText("�ͻ�1�뿪");//���ȹرտͻ���ʱ
                socket=new Socket();
                break;
            }
        }
    }
}