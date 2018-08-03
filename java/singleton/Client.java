package singleton;

public class Client {
    public static void main(String[] args) {
        SingletonH s = SingletonH.getInstance();
        SingletonH s1 = SingletonH.getInstance();
        System.out.println(s == s1);
    }
}
