package Exp02;

import java.io.*;
import java.util.*;

//Inventorys类  表示货物信息
class Inventorys {
    public static Vector<Inventorys> Inventorysarrays=new Vector<Inventorys>();

    String ItemNumber;                    //货物编号
    int Quantity;                         //货物数量
    String Supplier;                      //供应商编号
    String Description;                   //货物描述

    public Inventorys(){
    }

    public Inventorys(String ItemNumber, int Quantity, String Supplier, String Description){//初始化的赋值操作
        this.ItemNumber = ItemNumber;
        this.Quantity = Quantity;
        this.Supplier = Supplier;
        this.Description = Description;
    }
}

//Transactions类  表示事务处理信息
class Transactions {
    //存放四种事务信息的vector数组
    //发货
    public static Vector<Transactions> TransactionsOarrays=new Vector<Transactions>();
    //已发出的货物
    public static Vector<Transactions> TransactionsOarrays2=new Vector<Transactions>();
    //到货
    public static Vector<Transactions> TransactionsRarrays=new Vector<Transactions>();
    //增加货物
    public static Vector<Transactions> TransactionsAarrays=new Vector<Transactions>();
    //删除货物
    public static Vector<Transactions> TransactionsDarrays=new Vector<Transactions>();

    String TransactionType;               //事务类型
    String ItemNumber;                    //货物编号
    int Quantity;                         //货物数量（出货/到货）
    String Custom;                        //客户编号
    String Supplier;                      //供应商编号
    String Description;                   //货物描述

    public Transactions(){
    }

    //以"O"开头的事务类型
    public Transactions(String TransactionType, String ItemNumber, int Quantity, String Custom) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
        this.Quantity = Quantity;
        this.Custom = Custom;
    }

    //以”R“开头的事务类型
    public Transactions(String TransactionType, String ItemNumber, int Quantity) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
        this.Quantity = Quantity;
    }

    //以”A“开头的事务类型
    public Transactions(String TransactionType, String ItemNumber, String Supplier , String Description) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
        this.Supplier = Supplier;
        this.Description = Description;
    }

    //以”D“开头的事务类型
    public Transactions(String TransactionType, String ItemNumber) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
    }
}


public class Inventory {
    public static void main(String[] args) throws Exception   {
        //创建文件对象
        File Inventoryfile=new File("D:\\exp\\text\\Inventory.txt");
        File Transactionsfile=new File("D:\\exp\\text\\Transactions.txt");
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        //逐行读入Inventory.txt内内容并存入Inventorys对象中
        br1 = new BufferedReader(new FileReader(Inventoryfile));
        String line1=null;
        while((line1=br1.readLine())!=null) {
            //使用split函数对于读到的文本进行处理并存放到elements1数组中
            String[] elements1=line1.split("\t");
            //调用构造函数存放数据并将对象存入Inventoryarrays容器中
            Inventorys inventorys=new Inventorys(elements1[0],Integer.parseInt(elements1[1]),elements1[2], elements1[3]);
            Inventorys.Inventorysarrays.add(inventorys);
        }
        br1.close();

        //逐行读入Transactions.txt内内容并存入Transactions对象中
        br2 = new BufferedReader(new FileReader(Transactionsfile));
        String line2=null;
        while((line2=br2.readLine())!=null) {
            //使用split函数对于读到的文本进行处理
            String[] elements2=line2.split("\t");
            //创建Transactions对象并对不同事务类型选用不同的构造函数并存入相应的数组容器
            if(elements2[0].equals("O")) {
                Transactions TO=new Transactions(elements2[0],elements2[1],Integer.parseInt(elements2[2]),elements2[3]);
                Transactions.TransactionsOarrays.add(TO);
            }
            else if(elements2[0].equals("A")) {
                Transactions TA=new Transactions(elements2[0],elements2[1],elements2[2],elements2[3] );
                Transactions.TransactionsAarrays.add(TA);
            }
            else if(elements2[0].equals("R")) {
                Transactions TR=new Transactions(elements2[0],elements2[1],Integer.parseInt(elements2[2]));
                Transactions.TransactionsRarrays.add(TR);
            }
            else if(elements2[0].equals("D")) {
                Transactions TD=new Transactions(elements2[0],elements2[1]);
                Transactions.TransactionsDarrays.add(TD);
            }
        }
        br2.close();

        //创建三个文件对象
        File file = new File("D:\\exp\\text\\NewInventory.txt");
        BufferedWriter bfw1 = new BufferedWriter(new FileWriter(file));

        File file1 = new File("D:\\exp\\text\\Shipping.txt");
        BufferedWriter bfw2 = new BufferedWriter(new FileWriter(file1));

        File file2 = new File("D:\\exp\\text\\Errors.txt");
        BufferedWriter bfw3 = new BufferedWriter(new FileWriter(file2));

        //执行增加货物的方法
        for(int i = 0; i < Transactions.TransactionsAarrays.size(); i++) {
            Transactions a = Transactions.TransactionsAarrays.get(i);
            //将a对象中的数据存入新建的对象中并存入数组
            Inventorys inventorys = new Inventorys(a.ItemNumber, 0, a.Supplier, a.Description);
            Inventorys.Inventorysarrays.add(inventorys);
        }

        //执行到货方法
        for(int i = 0; i < Transactions.TransactionsRarrays.size(); i++) {
            //获取到货指令中的货物编号和数量
            String ItemNumber = Transactions.TransactionsRarrays.get(i).ItemNumber;
            int Quantity=Transactions.TransactionsRarrays.get(i).Quantity;
            //从已有的货物中获取对应的货物对象并进行操作
            for(int j = 0; j < Inventorys.Inventorysarrays.size(); j++) {
                if(Inventorys.Inventorysarrays.get(j).ItemNumber.equals(ItemNumber)) {
                    Inventorys.Inventorysarrays.get(j).Quantity += Quantity;
                }
            }
        }

        //对发货指令的数组按发货数目从小到大进行排序
        for(int i = 0; i < Transactions.TransactionsOarrays.size()-1 ; i++) {
            for(int j = i+1; j < Transactions.TransactionsOarrays.size(); j++) {
                //根据发货数量，确定发货顺序并交换对象内的数据
                if(Transactions.TransactionsOarrays.get(i).Quantity > Transactions.TransactionsOarrays.get(j).Quantity) {
                    //交换数组中两个元素的位置
                    Collections.swap(Transactions.TransactionsOarrays,i,j);
                }
            }
        }

        //执行发货方法
        for(int i = 0; i < Transactions.TransactionsOarrays.size(); i++) {
            //获取发货指令中的编号和数目
            String ItemNumber = Transactions.TransactionsOarrays.get(i).ItemNumber;
            String Custom = Transactions.TransactionsOarrays.get(i).Custom;
            int  Quantity = Transactions.TransactionsOarrays.get(i).Quantity;

            //获取对应的货物对象并进行操作
            for(int j = 0; j < Inventorys.Inventorysarrays.size(); j++) {
                if(Inventorys.Inventorysarrays.get(j).ItemNumber.equals(ItemNumber)) {
                    //剩余货物足够
                    if(Quantity <= Inventorys.Inventorysarrays.get(j).Quantity) {
                        //执行发货并记录
                        //货物数量减少
                        Inventorys.Inventorysarrays.get(j).Quantity -= Quantity;
                        //存入存储已发货信息的数组中
                        //第一份发货信息
                        if(Transactions.TransactionsOarrays2.size() == 0){
                            Transactions.TransactionsOarrays2.add(Transactions.TransactionsOarrays.get(i));
                        }
                        else {
                            //从第二份发货信息开始都需要与之前的发货信息进行比较
                            for (int k = 0; k < Transactions.TransactionsOarrays2.size(); k++) {
                                //已有相同的发货信息，进行叠加
                                if (Transactions.TransactionsOarrays2.get(k).Custom.equals(Custom) &&
                                        Transactions.TransactionsOarrays2.get(k).ItemNumber.equals(ItemNumber)) {

                                    Transactions.TransactionsOarrays2.get(k).Quantity += Quantity;
                                }
                                //不存在相同的发货信息，则将此新对象存入数组中
                                else {
                                    Transactions.TransactionsOarrays2.add(Transactions.TransactionsOarrays.get(i));
                                    //若不break 则会导致Oarrays的长度增加 循环次数增加一倍
                                    //break十分重要，加入信息后数组长度增加，防止继续循环导致数据二次增加
                                    break;
                                }
                            }
                        }
                    }
                    //剩余货物不足
                    else if(Quantity > Inventorys.Inventorysarrays.get(j).Quantity) {
                        //记录错误信息并写入文件
                        String str = Transactions.TransactionsOarrays.get(i).Custom + "\t"
                                + Transactions.TransactionsOarrays.get(i).ItemNumber + "\t"
                                + Transactions.TransactionsOarrays.get(i).Quantity;
                        bfw3.write(str);
                        //换行便于下一次写入
                        bfw3.newLine();
                    }
                }
            }

        }

        //将所有发货信息存入到shipping文件中
        for(int m = 0; m < Transactions.TransactionsOarrays2.size(); m++){
            String str = Transactions.TransactionsOarrays2.get(m).Custom + "\t"
                    + Transactions.TransactionsOarrays2.get(m).ItemNumber + "\t"
                    + Transactions.TransactionsOarrays2.get(m).Quantity;

            bfw2.write(str);
            //换行
            bfw2.newLine();;
        }

        //执行删除方法
        for(int i = 0 ; i < Transactions.TransactionsDarrays.size(); i++) {
            //获取待删除货物的编号
            String ItemNumber = Transactions.TransactionsDarrays.get(i).ItemNumber;
            //从已有货物中查找待删除货物
            for(int j = 0; j < Inventorys.Inventorysarrays.size(); j++) {
                if(Inventorys.Inventorysarrays.get(j).ItemNumber.equals(ItemNumber)) {
                    //如果库存量大于0，需要写入error文件
                    if(Inventorys.Inventorysarrays.get(j).Quantity > 0){
                        String str = String.valueOf("0") + "\t"
                                + Inventorys.Inventorysarrays.get(j).ItemNumber + "\t"
                                + Inventorys.Inventorysarrays.get(j).Quantity;
                        bfw3.write(str);
                        bfw3.newLine();
                    }
                    //将其移出原数组
                    Inventorys.Inventorysarrays.removeElementAt(j);
                }
            }
        }

        //将新的货物信息根据字段从小到大排序
        for(int i = 0; i < Inventorys.Inventorysarrays.size()-1 ; i++) {
            for(int j = i+1; j < Inventorys.Inventorysarrays.size(); j++) {
                //根据发货数量，确定发货顺序并交换对象内的数据
                if(Integer.parseInt(Inventorys.Inventorysarrays.get(i).ItemNumber) > Integer.parseInt(Inventorys.Inventorysarrays.get(j).ItemNumber)) {
                    //交换数组中两个元素的位置
                    Collections.swap(Inventorys.Inventorysarrays,i,j);
                }
            }
        }

        //将新的货物信息写入新文件中
        for(int i = 0; i < Inventorys.Inventorysarrays.size(); i++) {
            //写入新文件
            String str=Inventorys.Inventorysarrays.get(i).ItemNumber+"\t"
                    +String.valueOf(Inventorys.Inventorysarrays.get(i).Quantity)+"\t"
                    +Inventorys.Inventorysarrays.get(i).Supplier+"\t"
                    +Inventorys.Inventorysarrays.get(i).Description;
            bfw1.write(str);
            //换行
            bfw1.newLine();
        }
        //关闭流
        bfw1.close();
        bfw2.close();
        bfw3.close();
    }
}


