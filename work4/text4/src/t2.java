public class t2 {
    public static void main(String[] args) throws Exception{
        text1 t1 = new text1();//分别创建计算素数和完数的
        text2 t2 = new text2();
        t1.start();//开始线程
        t1.join();//进入阻塞 先完成t1才能进行下一步线程
        t2.start();
        t2.join();
        System.out.println(t1.getAnswer()*t2.getAnswer());//输出乘积答案


    }

}
class text1 extends Thread{//计算素数之和
    long answer_1;
    public boolean isPrime(int i) throws InterruptedException {
        text1.sleep(2000);
        int end=(int)Math.sqrt(i);//进行开方 简化运算
        if(i<2){
            return false;
        }
        if(i==2){
            return true;
        }
        if(i%2==0){//排除偶数
            return false;
        }
        for(int j=3;j<=end;j+=2){//若可除则不为素数
            if(i%j==0)return false;
        }
        return true;
    }
    public void run(){//运行
        for(int i=1;i<10000;i++){
            try {
                if(isPrime(i)) answer_1+=i;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("素数和为"+answer_1);
    }

    public long getAnswer(){
        return answer_1;
    }
}

class text2 extends Thread{//计算完数
    long answer_2;
    public boolean isPerfect(int i){
        int sum=0;
        if(i%2!=0){//完全数必是偶数
            return false;
        }
        else{
            for(int j=1;j<=i/2;j++){//判断是否可除
                if(i%j==0){
                    sum+=j;
                }
            }
            if(sum==i){
                return true;
            }
            else
                return false;
        }
    }
    public void run(){//运行
        for(int i=1;i<=10000;i++){//循环小于10000 返完数
            if(isPerfect(i)) answer_2+=i;
        }
        System.out.println("完数和为"+answer_2);

    }
    public long getAnswer(){
        return answer_2;
    }

}