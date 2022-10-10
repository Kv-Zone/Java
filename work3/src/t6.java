import javax.swing.*;
import java.awt.*;

public class t6 {
//类名可不带public 由此 类可和文件名不同
    static class Myframe extends JFrame{
   Myframe(){//进行函数构造
       setBackground(Color.white);//设置窗口的背景颜色
       setBounds(200,200,600,600);//设置窗口的面板大小
       setVisible(true);//使创建的窗口可以在面板中显示 （初始的面板是不能被看到的）
       setDefaultCloseOperation(EXIT_ON_CLOSE);//让创建的窗口可以直接关闭
    }
        public class panel extends JPanel{
            @Override
            public void paint(Graphics g) {//重写paint类 画出骰子的边界和骰子的四个红点
                super.paint(g);
                g.setColor(Color.black);//边界
                g.drawRect(245,245,90,90);
                g.setColor(Color.red);//红点
                g.fillOval(255,255,30,30);
                g.fillOval(295,255,30,30);
                g.fillOval(295,295,30,30);
                g.fillOval(255,295,30,30);
            }
    }
    }
    public static void main(String[] args) {
Myframe myframe = new Myframe();//创建Myframe对象
Myframe.panel panel1 = myframe.new panel();//创建Myframe的类内类 画出骰子
myframe.add(panel1);//将所画的面板加入窗口
    }
}
