package Exp01;
public class Point3D extends Point2D {
    protected int z;//z轴坐标

//    Point3D() {子类无需写无参构造
//    }

    Point3D(int x, int y, int z) {//题设第一种初始化3D的函数
//        this.x = x;
//        this.y = y;
        super(x, y);//调用父类的构造
        this.z = z;
    }

    Point3D(Point2D p1, int z) {//类对象作为传入参数时需要对其进行实例化对象的传入
        super(p1.GetX(), p1.GetY());//调用父类Point2D的两参构造方法进行初始化
        this.z = z;
    }

    void offset(int a, int b, int c) {
        super.offset(a, b);//调用不同名的父类构造函数 平移x y坐标
        this.z += c;
    }

    public void show() {
        System.out.println("x:" + x + "y:" + y + "z:" + z);
    }
}
