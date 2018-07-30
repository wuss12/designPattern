package liskovSubstitution;

public class Client {
    public static void main(String[] args) {
//        test1();
        test2();
    }
    private static void test1(){
        A a = new A();
        System.out.println("100-40="+a.func1(100,40));
        System.out.println("100-20="+a.func1(100,20));
    }
    private static void test2(){
        B b = new B();
        A a = b;
        System.out.println("100-40="+b.func1(100, 40));
        System.out.println("100-20="+b.func1(100, 20));
        System.out.println("(100-20)*(100+20)="+b.func2(100, 20));

        System.out.println("100-40="+a.func1(100, 40));
        System.out.println("100-20="+a.func1(100, 20));
    }
}
class A{
    public int func1(int a,int b){
        return a-b;
    }
}
class B  extends A{
    public int func1(int a,int b){
        return a + b;
    }
    public int func2(int a,int b)
    {
        return func1(a,b) * super.func1(a,b);
    }
}
