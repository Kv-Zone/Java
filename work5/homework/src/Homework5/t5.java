package Homework5 ;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class t5 {//这是服务器端

        public static void main(String[] args) throws Exception {
            //创建服务器端口
            ServerSocket server = new ServerSocket(8888);    //写入端口号端口号

            Socket socket = server.accept(); //创建Socket对象接受输入

            InputStream inps = socket.getInputStream();  //接受从用户端发送来的字节数据内容

            FileOutputStream fs = new FileOutputStream(new File("receive.txt"));//创建文件流进行储存用户端发送给的内容

            byte[] buffer = new byte[1024];   //把输入数据写入到文件当中
            int len;

            while ((len = inps.read(buffer)) != -1){//读取至文本结束
                fs.write(buffer,0,len);
            }
            String msg = new String(buffer);  //byte数组转化为String，然后输出在
            System.out.println(msg.trim());       //用String里面的trim方法，去除数组后面多余的空格

            server.close(); //关闭所使用的流对象
            socket.close();
            inps.close();
            fs.close();

        }
    }

