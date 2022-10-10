package Homework5;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class t5_1 {//这是用户端
        public static void main(String[] args) throws Exception {
            Socket socket = new Socket(InetAddress.getByName("localhost"),8888); //创建一个Socket对象
            OutputStream os = socket.getOutputStream();//创建流，写东西过去
            FileInputStream fops = new FileInputStream("text.txt");  //改变文件类型，可以传导不同的文件
            //创建读取文件流对象读取文件
            byte[] buffer = new byte[1024]; //读取文件
            int len;
            while ((len = fops.read(buffer)) != -1){//将文件写入的内容全部读取
                //写到服务端口去
                os.write(buffer, 0, len);
            }//文件传输完毕
            socket.close();//关闭所使用的流
            os.close();
            fops.close();
        }

}
