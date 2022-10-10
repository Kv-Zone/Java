import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Collections;
import java.util.Vector;
public class t5 {
    public static void main(String[] args) throws Exception {
        JFrame myFrame=new JFrame("录入学生信息");//创建Jfream窗口
        myFrame.setBounds(400,400,400,250);//设置窗口的大小
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口可关闭
        myFrame.setLayout(new FlowLayout(FlowLayout.CENTER));//设置流式布局

        JPanel NamePanel=new JPanel();//创建姓名面板
        JLabel NameLabel=new JLabel("name:");//姓名标签label
        JTextField NameText=new JTextField(30);//设置文本框长度
        NamePanel.add(NameLabel);//添加标签和文本框
        NamePanel.add(NameText);

        JPanel IDPanel=new JPanel();//类似姓名的面板创建
        JLabel IDLabel=new JLabel(" id:");
        JTextField IDText=new JTextField(30);
        IDPanel.add(IDLabel);
        IDPanel.add(IDText);

        JPanel MarkPanel=new JPanel();//类似姓名的面板创建
        JLabel MarkLabel=new JLabel(" score :");
        JTextField MarkText=new JTextField(30);
        MarkPanel.add(MarkLabel);
        MarkPanel.add(MarkText);

        JPanel ResultPanel=new JPanel();
        JLabel MaxLabel=new JLabel("max:");
        JTextField MaxText=new JTextField(5);
        JLabel AverageLabel=new JLabel("aver:");
        JTextField AverageText=new JTextField(5);
        JLabel MinLabel=new JLabel("min:");
        JTextField MinText=new JTextField(5);
        ResultPanel.add(MaxLabel);
        ResultPanel.add(MaxText);
        ResultPanel.add(AverageLabel);
        ResultPanel.add(AverageText);
        ResultPanel.add(MinLabel);
        ResultPanel.add(MinText);

        JPanel  ElectPanel=new JPanel();
        JButton AddBtn=new JButton(" addinfo ");//增加学生信息按钮
        JButton ConBtn=new JButton("next");//清空文本框 增加学生信息
        JButton CalcuBtn=new JButton("calculate");//计算分数的平均分和最高分最低分
        ElectPanel.add(AddBtn);
        ElectPanel.add(ConBtn);
        ElectPanel.add(CalcuBtn);

        myFrame.add(NamePanel);//向建立的窗口内加入所有建立的面板
        myFrame.add(IDPanel);
        myFrame.add(MarkPanel);
        myFrame.add(ResultPanel);
        myFrame.add(ElectPanel);
        myFrame.setVisible(true);

        File StudentFile=new File("D:\\work4\\Student.txt");
        File StudentMark=new File("D:\\work4\\StudentMark.txt");
        FileOutputStream Fos=new FileOutputStream(StudentFile);//建立输出流对象fos
        FileInputStream  fis=new FileInputStream(StudentFile);//建立输入流对象fis
        FileOutputStream fos=new FileOutputStream(StudentMark);
        AddBtn.addActionListener(new ActionListener() {//添加学生信息的监听器 达到效果：点击后对文件写入学生的姓名学号
            @Override
            public void actionPerformed(ActionEvent e) {
                try {//捕获异常环绕
                    String Ins=NameText.getText()+" "+IDText.getText()+"#"+MarkText.getText()+"\n";
                    byte[] bytes=new byte[1024];
                    bytes=Ins.getBytes();
                    Fos.write(bytes,0,bytes.length);
                    System.out.println("信息录入成功");
                }catch(IOException e1){
                    System.out.println(e1);

                }

            }
        });

        ConBtn.addActionListener(new ActionListener() {//设置清空文本框的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                NameText.setText("");
                IDText.setText("");
                MarkText.setText("");
            }
        });

        CalcuBtn.addActionListener(new ActionListener() {//设置求和的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedReader bf=new BufferedReader(new InputStreamReader(fis));
                String s;
                Vector<Double> MarkArray=new Vector<>(0);
                Double sum=0.0;
                try {
                    while ((s = bf.readLine() )!= null) {
                        String temp=s.substring(s.indexOf('#')+1);
                        MarkArray.add(Double.parseDouble(temp));
                    }
                    Collections.sort(MarkArray);//.sort排序 排序后最大和最小分别在0 和 len-1的位置上
                    for(int i=0;i<MarkArray.size();i++){//进行求和
                        sum+=MarkArray.get(i);
                    }
                    MaxText.setText(String.valueOf(MarkArray.get((MarkArray.size()-1))));//设置最大
                    AverageText.setText(String.valueOf(sum/MarkArray.size()));
                    MinText.setText(String.valueOf(MarkArray.get(0)));//设置最小
                    String Ins=MaxText.getText()+" "+AverageText.getText()+" "+MinText.getText()+"\n";
                    byte[] b=new byte [1024];
                    b=Ins.getBytes();
                    fos.write(b,0,b.length);
                    System.out.println("成绩录入成功");
                }catch(IOException e1) {

                }

            }
        });

    }

    }