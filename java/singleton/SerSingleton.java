package singleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class SerSingleton {
    static class A implements Serializable{
        private String name;
        private static A  instance= new A();
        private A(){
            name="sss";
            System.out.println("A is Crated...");
        }
        private static A getInstance(){
            return instance;
        }
    }

    static class B implements Serializable{
        private String name;
        private static boolean flag = false;
        private static B  instance= new B();
        private B(){
            name="sss";
            if(false == flag){
                flag = !flag;
            }else {
                throw new RuntimeException("单例正在被反射攻击");
            }
            System.out.println("B is Crated...");
        }
        private Object readResolve(){ //阻止生成新的实例，返回当前对象
            return instance;
        }
        private static B getInstance(){
            return instance;
        }
    }

    public static void main(String[] args) throws Exception{
        A a1 = A.getInstance();
        A a2 = A.getInstance();
        System.out.println(a1 == a2);

        Class clazz = A.class;
        Constructor instances = clazz.getDeclaredConstructor(new Class[]{});
        instances.setAccessible(true);
        A a3 = (A)instances.newInstance(new Class[]{});
        System.out.println(a1 == a3);

        String fileName = "D:/aa.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(a1);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        A a4 = (A)ois.readObject();
        System.out.println(a1 == a4);
        System.out.println("------------------");
        B b1 = B.getInstance();
        B b2 = B.getInstance();
        System.out.println(b1 == b2);

        String fileName1 ="D:/test/a.txt";
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(fileName1));
        oos1.writeObject(b1);
        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(fileName1));
        //因为在调用readObject 的过程中会调用该对象的 readResolve 返回的结果，如果和 新创建的对象
        // 不相等(== 判读的) 则使用 readResolve 返回的内容
        B b3 = (B) ois1.readObject();
        System.out.println(b3 == b1);

        Class clazzB = B.class;
        Constructor declaredConstructorB = clazzB.getDeclaredConstructor(null);
        declaredConstructorB.setAccessible(true);
        B b4 = (B) declaredConstructorB.newInstance();
        System.out.println(b1 == b4);
    }
}
