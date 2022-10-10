import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class t7 {
     static class Myframe extends JFrame{
         Myframe() {
             setBounds(300,300,600,600);//设置窗口为画布
             setBackground(Color.white);//设置背景颜色
             setVisible(true);//使窗口可见
             setDefaultCloseOperation(EXIT_ON_CLOSE);//可以退出窗口
             addMouseListener(new MouseAdapter() {//添加鼠标监听器
                 public void mouseClicked(MouseEvent e) {//建立函数
                     new Thread(new Runnable() {//创建线程
                         @Override
                         public void run() {//线程进行时的操作
                           if(e.getClickCount()==2 && e.getButton()==1)//点击两次且为左键点击
                           {
                               repaint();
                           }
                           else if (e.getClickCount()==1){//点击一次的情况
                               try {
                                   Thread.sleep(20);//设置延迟20
                               } catch (InterruptedException ex) {//抓取异常
                                   ex.printStackTrace();
                               }
                               if(e.getButton()==1)//点击左键 画出绿色圆形
                               {
                                   Graphics g = getGraphics();
                                   g.setColor(Color.green);
                                   g.fillOval(e.getX(), e.getY(), 20, 20);
                               }
                                if(e.getButton()==3)//点击右键 画出红色正方形
                               {
                                   Graphics G = getGraphics();
                                   G.setColor(Color.red);
                                   G.fillRect(e.getX(), e.getY(), 20, 20);
                               }
                           }
                         }
                     }).start();//使线程开始

                 }
             });
         }
     }
    public static void main(String[] args) {
        Myframe myframe = new Myframe();//创建Myframe的对象myfream 运行上述类的操作
    }
}
