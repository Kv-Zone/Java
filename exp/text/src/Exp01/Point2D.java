package Exp01;
public class Point2D {
    protected int x, y;//定义元素x y 作为横坐标和纵坐标

    Point2D() {
    }


    Point2D(int a, int b) {//初始化对象
        this.x = a;
        this.y = b;
    }

    public int GetX() {//对外接口可以获取X值
        return x;
    }

    public int GetY() {//对外接口获取Y的值
        return y;
    }

    void offset(int a, int b) {//实现坐标的Point2D坐标平移
        this.x += a;
        this.y += b;
    }

    public void show() {
        System.out.println("该点坐标为x:" + x + "y:" + y);
    }
}
