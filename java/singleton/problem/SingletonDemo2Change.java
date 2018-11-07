package singleton.problem;

import java.io.*;

public class SingletonDemo2Change implements Serializable{
    private static SingletonDemo2Change instance = new SingletonDemo2Change();
    private SingletonDemo2Change(){}
    public static SingletonDemo2Change getInstance(){
        return instance;
    }
    private Object readResolve(){
        return instance;
    }

    public static void main(String[] args) throws Exception{
        SingletonDemo2Change s1 = getInstance();
        SingletonDemo2Change s2 = getInstance();
        System.out.println(s1 == s2);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(s1);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempFile"));
        SingletonDemo2Change s3 = (SingletonDemo2Change) ois.readObject();
        ois.close();
        System.out.println(s1 == s3);
    }
}
