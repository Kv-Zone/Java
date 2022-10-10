package Exp01;
import java.lang.Math;

public class exp01 {
    public static void main(String[] args) {
        Point2D p2d1 = new Point2D(1,2);//两点的初始化
        Point2D p2d2 = new Point2D(2,3);
        p2d1.show();//展示两点的坐标
        p2d2.show();
        p2d2.offset(2,2);
        p2d2.show();
        Point3D p3d1 = new Point3D(p2d1 , 3);//第一种初始化方法
        Point3D p3d2 = new Point3D(3 , 4, 5);//第二种初始化方法
        p3d1.show();
        p3d2.show();
        p3d1.offset(1,1,1);
        distance2D(p2d1 ,p2d2);
        distance3D(p3d1,p3d2);
    }
    public static void distance2D(Point2D p2d1,Point2D p2d2){
        double distance1 ;
        distance1 = Math.pow((p2d1.x-p2d2.x),2)+Math.pow((p2d1.y-p2d2.y),2);
        distance1 = Math.sqrt(distance1);
        System.out.println("p2d1和p2d2的距离为："+distance1);
    }
    public static void distance3D(Point3D p3d1, Point3D p3d2){
        double distance2 ;
        distance2 = Math.pow((p3d1.x-p3d2.x),2)+Math.pow((p3d1.y-p3d2.y),2)+Math.pow((p3d1.z-p3d2.z),2);
        distance2 = Math.sqrt(distance2);
        System.out.println("p3d1和p3d2的距离为："+distance2);
    }
}
