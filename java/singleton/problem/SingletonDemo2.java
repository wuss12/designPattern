package singleton.problem;

import java.io.*;

public class SingletonDemo2 implements Serializable {
    private static SingletonDemo2 instance = new SingletonDemo2();
    private SingletonDemo2(){

    }
    public static SingletonDemo2 getInstance(){
        return instance;
    }

    public static void main(String[] args) throws Exception{
        SingletonDemo2 s1 = SingletonDemo2.getInstance();
        SingletonDemo2 s2 = SingletonDemo2.getInstance();
        System.out.println(s1 == s2);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(s1);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempFile"));
        SingletonDemo2 s3 = (SingletonDemo2) ois.readObject();
        ois.close();
        System.out.println(s1 == s3);
    }
}
