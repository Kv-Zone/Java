package Exp01;
public class Point2D {
    protected int x, y;//����Ԫ��x y ��Ϊ�������������

    Point2D() {
    }


    Point2D(int a, int b) {//��ʼ������
        this.x = a;
        this.y = b;
    }

    public int GetX() {//����ӿڿ��Ի�ȡXֵ
        return x;
    }

    public int GetY() {//����ӿڻ�ȡY��ֵ
        return y;
    }

    void offset(int a, int b) {//ʵ�������Point2D����ƽ��
        this.x += a;
        this.y += b;
    }

    public void show() {
        System.out.println("�õ�����Ϊx:" + x + "y:" + y);
    }
}
