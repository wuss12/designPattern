**设计模式六大原则（3）：依赖倒置原则**

**定义**：高层模块，不应该依赖低层模块，两者都应该依赖其抽象：抽象不应该依赖细节，
细节应该依赖抽象。

**问题由来：** 高层模块类A：负责复杂的业务逻辑，低层模块类B，和类C负责基本的
原子操作。类A依赖类B，如果想要让类A依赖类C，那么只能修改A的代码。修改类A会带来
不必要的风险

**解决方案：** 将类A修改成依赖接口I，而类B和类C分别实现接口I，类A通过接口I
间接的和类B和类C 发生联系，会减少修改类A的几率。

   依赖倒置基于这也一个事实：相对于细节的多变性，抽象的东西要稳定的多。以抽象为
 基础的架构，比以细节为基础的架构要稳定的多。java中抽象值得是抽象类和接口
 
   依赖倒置的核心思想是：面向接口编程。
   我们举个例子进行说明。场景如下：母亲给孩子讲故事，只要给母亲一本书，就可以
 讲故事。  
 class Book{
     public String getContent(){
         return "从前有个山...";
     }
 }
 
 class Mother {
     public void narrate(Book b) {
         System.out.println("妈妈开始讲故事:");
         System.out.println(b.getContent());
     }
 
 }
 public class Client {
     public static void main(String[] args){
         Mother mother = new Mother();
         mother.narrate(new Book());
     }
 }
 
 运行结果：
 妈妈开始讲故事:
 从前有个山...
 
 需求变动，不是给一本书，而是给一份报纸，
 
 class Newspaper{
    public String getContent(){
        System.out.println("卖火柴的小女孩");
    }
 }
 
 这时候Mother 就没法读报纸了，把书换成报纸，就必须修改Mathor类才能读，如果
 换成网页等等，就需要不停的修改Mother类，这显然不是一个好的设计，因为Mother类
 和书本耦合太高。我们引入一个抽象类Reader
 
 public interface Reader {
     public String getContent();
 }


public class Book implements Reader {
    @Override
    public String getContent() {
        return "从前有座山...";
    }
}

public class Newspaper implements Reader {
    @Override
    public String getContent() {
        return "卖火柴的小女孩";
    }
}

public class Mother {
    public void narrate(Reader r){
        System.out.println("妈妈开始讲故事:");
        System.out.println(r.getContent());
    }
}

public class Client {
    public static void main(String[] args) {
        Mother m = new Mother();
        m.narrate(new Book());
        m.narrate(new Newspaper());
    }
}

运行结果：
妈妈开始讲故事:
从前有座山...
妈妈开始讲故事:
卖火柴的小女孩

这样修改以后扩展，不需要修改mother类。遵循依赖倒置的原则，可以降低类之间的耦合
降低修改引入的风险，提高系统的稳定性。

传递依赖关系的有三种方式，上面使用的方式就是 接口传递，还有另外两种传递方式：
构造方法传递 和 setter方法传递

我们变成需要注意以下事项：
1.低层模块尽量都要有抽象类或者接口
2.变量的声明类型尽量是抽象类或者接口
3.使用继承的时候，尽量遵循里式替换原则
   