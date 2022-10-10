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

    WindowClient(){//���ÿͻ�������Լ��ͻ��˵�Socket��������
        socket=new Socket();
        connect=new JButton("connect");
        say=new JButton("say");
        say.setEnabled(true);//���ÿɵ��
        inputIp=new JTextField(15);//�����ı���
        inputPort=new JTextField(15);
        inputSay=new JTextField(30);
        showtext=new JTextArea();//�ı���
        showtext.setEditable(false);//�����޸��ı�����ʾ������

        JPanel jpnorth=new JPanel();
        jpnorth.setBorder(BorderFactory.createTitledBorder("�ͻ�����Ϣ: "));
        jpnorth.add(new JLabel("Server IP:"));//��������ip
        jpnorth.add(inputIp);
        jpnorth.add(new JLabel("Server Port:"));
        jpnorth.add(inputPort);
        jpnorth.add(connect);

        JPanel jpsouth=new JPanel();
        jpsouth.add(new JLabel("���:"));
        jpsouth.add(inputSay);
        jpsouth.add(say);

        add(jpnorth,BorderLayout.NORTH);
        add(new JScrollPane(showtext),BorderLayout.CENTER);//��������JsrollPane������
        add(jpsouth,BorderLayout.SOUTH);

        connect.addActionListener(this);
        say.addActionListener(this);//��Ӽ�����
        thread=new Thread(this);
        setBounds(100,500,600,400);
        setVisible(true);//��ͼ�ɼ�
        setTitle("�ͻ���");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//���ô��ڹرյİ���
    }

    public void actionPerformed(ActionEvent e) {//����
        if(e.getSource()==connect) {//���connect�������
            try {
                if(!socket.isConnected()) {              //�ж��Ƿ����� ���Ǻ������ж�ʵʱ״̬������ʼ������;�Ͽ���Ȼ��������
                    showtext.append("Connect to server��\n");
                    InetAddress address=InetAddress.getByName(inputIp.getText());//��ʾInternetЭ�飨IP����ַ����
                    InetSocketAddress socketAddress=new InetSocketAddress(address,Integer.parseInt(inputPort.getText()));//��IP��ַ�Ͷ˿ںŴ����׽��ֵ�ַ��
                    socket.connect(socketAddress);//�����׽������ӵ���������
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
            catch(IOException ee) {//�����쳣
                System.out.println(ee);
                socket=new Socket();
            }
        }
        if(e.getSource()==say) {//�����say��ʱ��
            String S_out=inputSay.getText();//����String��������������
            showtext.append("��˵��"+S_out+"\n");//append�����ߵ�ַ���������
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
                showtext.append("��������"+S_in+"\n");
                System.out.println();
            }
            catch(IOException e) {
                showtext.setText("��������Ͽ�"+e);//���ȹرշ���˵�ʱ����
                socket=new Socket();
                break;
            }
        }
    }
}