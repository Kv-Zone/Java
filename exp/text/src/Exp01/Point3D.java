package Exp01;
public class Point3D extends Point2D {
    protected int z;//z������

//    Point3D() {��������д�޲ι���
//    }

    Point3D(int x, int y, int z) {//�����һ�ֳ�ʼ��3D�ĺ���
//        this.x = x;
//        this.y = y;
        super(x, y);//���ø���Ĺ���
        this.z = z;
    }

    Point3D(Point2D p1, int z) {//�������Ϊ�������ʱ��Ҫ�������ʵ��������Ĵ���
        super(p1.GetX(), p1.GetY());//���ø���Point2D�����ι��췽�����г�ʼ��
        this.z = z;
    }

    void offset(int a, int b, int c) {
        super.offset(a, b);//���ò�ͬ���ĸ��๹�캯�� ƽ��x y����
        this.z += c;
    }

    public void show() {
        System.out.println("x:" + x + "y:" + y + "z:" + z);
    }
}
