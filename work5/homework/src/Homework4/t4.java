package Homework4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class t4 {
    public static void main(String[] args)throws Exception{
        URL url = new URL("https://baidu.com/default.html");//创建一个url的对象
        URLConnection ur = url.openConnection();  //创建URLConnection对象 收集并读取数据
        BufferedReader in = new BufferedReader(new InputStreamReader(ur.getInputStream()));
        String msg;//逐行读取html
        while ((msg = in.readLine()) != null){
            System.out.println(msg);//输出控制台
        }
    }
}
