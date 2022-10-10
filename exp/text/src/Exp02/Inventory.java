package Exp02;

import java.io.*;
import java.util.*;

//Inventorys��  ��ʾ������Ϣ
class Inventorys {
    public static Vector<Inventorys> Inventorysarrays=new Vector<Inventorys>();

    String ItemNumber;                    //������
    int Quantity;                         //��������
    String Supplier;                      //��Ӧ�̱��
    String Description;                   //��������

    public Inventorys(){
    }

    public Inventorys(String ItemNumber, int Quantity, String Supplier, String Description){//��ʼ���ĸ�ֵ����
        this.ItemNumber = ItemNumber;
        this.Quantity = Quantity;
        this.Supplier = Supplier;
        this.Description = Description;
    }
}

//Transactions��  ��ʾ��������Ϣ
class Transactions {
    //�������������Ϣ��vector����
    //����
    public static Vector<Transactions> TransactionsOarrays=new Vector<Transactions>();
    //�ѷ����Ļ���
    public static Vector<Transactions> TransactionsOarrays2=new Vector<Transactions>();
    //����
    public static Vector<Transactions> TransactionsRarrays=new Vector<Transactions>();
    //���ӻ���
    public static Vector<Transactions> TransactionsAarrays=new Vector<Transactions>();
    //ɾ������
    public static Vector<Transactions> TransactionsDarrays=new Vector<Transactions>();

    String TransactionType;               //��������
    String ItemNumber;                    //������
    int Quantity;                         //��������������/������
    String Custom;                        //�ͻ����
    String Supplier;                      //��Ӧ�̱��
    String Description;                   //��������

    public Transactions(){
    }

    //��"O"��ͷ����������
    public Transactions(String TransactionType, String ItemNumber, int Quantity, String Custom) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
        this.Quantity = Quantity;
        this.Custom = Custom;
    }

    //�ԡ�R����ͷ����������
    public Transactions(String TransactionType, String ItemNumber, int Quantity) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
        this.Quantity = Quantity;
    }

    //�ԡ�A����ͷ����������
    public Transactions(String TransactionType, String ItemNumber, String Supplier , String Description) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
        this.Supplier = Supplier;
        this.Description = Description;
    }

    //�ԡ�D����ͷ����������
    public Transactions(String TransactionType, String ItemNumber) {
        this.TransactionType = TransactionType;
        this.ItemNumber = ItemNumber;
    }
}


public class Inventory {
    public static void main(String[] args) throws Exception   {
        //�����ļ�����
        File Inventoryfile=new File("D:\\exp\\text\\Inventory.txt");
        File Transactionsfile=new File("D:\\exp\\text\\Transactions.txt");
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        //���ж���Inventory.txt�����ݲ�����Inventorys������
        br1 = new BufferedReader(new FileReader(Inventoryfile));
        String line1=null;
        while((line1=br1.readLine())!=null) {
            //ʹ��split�������ڶ������ı����д�����ŵ�elements1������
            String[] elements1=line1.split("\t");
            //���ù��캯��������ݲ����������Inventoryarrays������
            Inventorys inventorys=new Inventorys(elements1[0],Integer.parseInt(elements1[1]),elements1[2], elements1[3]);
            Inventorys.Inventorysarrays.add(inventorys);
        }
        br1.close();

        //���ж���Transactions.txt�����ݲ�����Transactions������
        br2 = new BufferedReader(new FileReader(Transactionsfile));
        String line2=null;
        while((line2=br2.readLine())!=null) {
            //ʹ��split�������ڶ������ı����д���
            String[] elements2=line2.split("\t");
            //����Transactions���󲢶Բ�ͬ��������ѡ�ò�ͬ�Ĺ��캯����������Ӧ����������
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

        //���������ļ�����
        File file = new File("D:\\exp\\text\\NewInventory.txt");
        BufferedWriter bfw1 = new BufferedWriter(new FileWriter(file));

        File file1 = new File("D:\\exp\\text\\Shipping.txt");
        BufferedWriter bfw2 = new BufferedWriter(new FileWriter(file1));

        File file2 = new File("D:\\exp\\text\\Errors.txt");
        BufferedWriter bfw3 = new BufferedWriter(new FileWriter(file2));

        //ִ�����ӻ���ķ���
        for(int i = 0; i < Transactions.TransactionsAarrays.size(); i++) {
            Transactions a = Transactions.TransactionsAarrays.get(i);
            //��a�����е����ݴ����½��Ķ����в���������
            Inventorys inventorys = new Inventorys(a.ItemNumber, 0, a.Supplier, a.Description);
            Inventorys.Inventorysarrays.add(inventorys);
        }

        //ִ�е�������
        for(int i = 0; i < Transactions.TransactionsRarrays.size(); i++) {
            //��ȡ����ָ���еĻ����ź�����
            String ItemNumber = Transactions.TransactionsRarrays.get(i).ItemNumber;
            int Quantity=Transactions.TransactionsRarrays.get(i).Quantity;
            //�����еĻ����л�ȡ��Ӧ�Ļ�����󲢽��в���
            for(int j = 0; j < Inventorys.Inventorysarrays.size(); j++) {
                if(Inventorys.Inventorysarrays.get(j).ItemNumber.equals(ItemNumber)) {
                    Inventorys.Inventorysarrays.get(j).Quantity += Quantity;
                }
            }
        }

        //�Է���ָ������鰴������Ŀ��С�����������
        for(int i = 0; i < Transactions.TransactionsOarrays.size()-1 ; i++) {
            for(int j = i+1; j < Transactions.TransactionsOarrays.size(); j++) {
                //���ݷ���������ȷ������˳�򲢽��������ڵ�����
                if(Transactions.TransactionsOarrays.get(i).Quantity > Transactions.TransactionsOarrays.get(j).Quantity) {
                    //��������������Ԫ�ص�λ��
                    Collections.swap(Transactions.TransactionsOarrays,i,j);
                }
            }
        }

        //ִ�з�������
        for(int i = 0; i < Transactions.TransactionsOarrays.size(); i++) {
            //��ȡ����ָ���еı�ź���Ŀ
            String ItemNumber = Transactions.TransactionsOarrays.get(i).ItemNumber;
            String Custom = Transactions.TransactionsOarrays.get(i).Custom;
            int  Quantity = Transactions.TransactionsOarrays.get(i).Quantity;

            //��ȡ��Ӧ�Ļ�����󲢽��в���
            for(int j = 0; j < Inventorys.Inventorysarrays.size(); j++) {
                if(Inventorys.Inventorysarrays.get(j).ItemNumber.equals(ItemNumber)) {
                    //ʣ������㹻
                    if(Quantity <= Inventorys.Inventorysarrays.get(j).Quantity) {
                        //ִ�з�������¼
                        //������������
                        Inventorys.Inventorysarrays.get(j).Quantity -= Quantity;
                        //����洢�ѷ�����Ϣ��������
                        //��һ�ݷ�����Ϣ
                        if(Transactions.TransactionsOarrays2.size() == 0){
                            Transactions.TransactionsOarrays2.add(Transactions.TransactionsOarrays.get(i));
                        }
                        else {
                            //�ӵڶ��ݷ�����Ϣ��ʼ����Ҫ��֮ǰ�ķ�����Ϣ���бȽ�
                            for (int k = 0; k < Transactions.TransactionsOarrays2.size(); k++) {
                                //������ͬ�ķ�����Ϣ�����е���
                                if (Transactions.TransactionsOarrays2.get(k).Custom.equals(Custom) &&
                                        Transactions.TransactionsOarrays2.get(k).ItemNumber.equals(ItemNumber)) {

                                    Transactions.TransactionsOarrays2.get(k).Quantity += Quantity;
                                }
                                //��������ͬ�ķ�����Ϣ���򽫴��¶������������
                                else {
                                    Transactions.TransactionsOarrays2.add(Transactions.TransactionsOarrays.get(i));
                                    //����break ��ᵼ��Oarrays�ĳ������� ѭ����������һ��
                                    //breakʮ����Ҫ��������Ϣ�����鳤�����ӣ���ֹ����ѭ���������ݶ�������
                                    break;
                                }
                            }
                        }
                    }
                    //ʣ����ﲻ��
                    else if(Quantity > Inventorys.Inventorysarrays.get(j).Quantity) {
                        //��¼������Ϣ��д���ļ�
                        String str = Transactions.TransactionsOarrays.get(i).Custom + "\t"
                                + Transactions.TransactionsOarrays.get(i).ItemNumber + "\t"
                                + Transactions.TransactionsOarrays.get(i).Quantity;
                        bfw3.write(str);
                        //���б�����һ��д��
                        bfw3.newLine();
                    }
                }
            }

        }

        //�����з�����Ϣ���뵽shipping�ļ���
        for(int m = 0; m < Transactions.TransactionsOarrays2.size(); m++){
            String str = Transactions.TransactionsOarrays2.get(m).Custom + "\t"
                    + Transactions.TransactionsOarrays2.get(m).ItemNumber + "\t"
                    + Transactions.TransactionsOarrays2.get(m).Quantity;

            bfw2.write(str);
            //����
            bfw2.newLine();;
        }

        //ִ��ɾ������
        for(int i = 0 ; i < Transactions.TransactionsDarrays.size(); i++) {
            //��ȡ��ɾ������ı��
            String ItemNumber = Transactions.TransactionsDarrays.get(i).ItemNumber;
            //�����л����в��Ҵ�ɾ������
            for(int j = 0; j < Inventorys.Inventorysarrays.size(); j++) {
                if(Inventorys.Inventorysarrays.get(j).ItemNumber.equals(ItemNumber)) {
                    //������������0����Ҫд��error�ļ�
                    if(Inventorys.Inventorysarrays.get(j).Quantity > 0){
                        String str = String.valueOf("0") + "\t"
                                + Inventorys.Inventorysarrays.get(j).ItemNumber + "\t"
                                + Inventorys.Inventorysarrays.get(j).Quantity;
                        bfw3.write(str);
                        bfw3.newLine();
                    }
                    //�����Ƴ�ԭ����
                    Inventorys.Inventorysarrays.removeElementAt(j);
                }
            }
        }

        //���µĻ�����Ϣ�����ֶδ�С��������
        for(int i = 0; i < Inventorys.Inventorysarrays.size()-1 ; i++) {
            for(int j = i+1; j < Inventorys.Inventorysarrays.size(); j++) {
                //���ݷ���������ȷ������˳�򲢽��������ڵ�����
                if(Integer.parseInt(Inventorys.Inventorysarrays.get(i).ItemNumber) > Integer.parseInt(Inventorys.Inventorysarrays.get(j).ItemNumber)) {
                    //��������������Ԫ�ص�λ��
                    Collections.swap(Inventorys.Inventorysarrays,i,j);
                }
            }
        }

        //���µĻ�����Ϣд�����ļ���
        for(int i = 0; i < Inventorys.Inventorysarrays.size(); i++) {
            //д�����ļ�
            String str=Inventorys.Inventorysarrays.get(i).ItemNumber+"\t"
                    +String.valueOf(Inventorys.Inventorysarrays.get(i).Quantity)+"\t"
                    +Inventorys.Inventorysarrays.get(i).Supplier+"\t"
                    +Inventorys.Inventorysarrays.get(i).Description;
            bfw1.write(str);
            //����
            bfw1.newLine();
        }
        //�ر���
        bfw1.close();
        bfw2.close();
        bfw3.close();
    }
}


