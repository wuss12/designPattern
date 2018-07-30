设计模式六大原则(1): 单一职责原则

定义：不要存在多余一个导致类变动的原因，通俗的讲一个类 只负责一个原则

问题由来：类T有 P1和P2两个职责，由于需求变动，需要修改P1的职责，修改类T之后
可能造成原来正常执行的P2职责出现了问题

解决方案：遵循单一职责原则，新建两个类，T1 和 T2，T1完成P1的职责，T2 完成P2的职责
当修改T1的时候，不会引起T2发生故障的风险，同理，修改T2 不会引起T1发生故障风险

职责扩散：因为某种原因职责P 被划分为粒度更细的职责P1.P2

比如：类T 只负责一个职责 P，这样设计是符合单一职责的，后来需要把职责P 划分为粒度更细的职责
P1，P2，在这时候如果 遵循 单一职责，只需要把类T 划分为T1和T2 分别负责职责P1和P2，但是在程序
已经写好的情况下，这样修改会浪费时间，而简单的修改类T却让人他负责两个职责确实一个比较不错的选择，
虽然这样做有悖于单一职责原则。（这样做的风险在于职责扩散的不确定性，因为我们不会想到这个职责P，
在未来可能会扩散为P1，P2，P3，P4……Pn。所以记住，在职责扩散到我们无法控制的程度之前，立刻对代码进行重构。）

例子：描述动物生活环境的例子
public class Animal {
    public void live(String animal){
        System.out.println(animal+"生活在陆地");
    }
}


public class Client{
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.live("牛");
        animal.live("羊");
        animal.live("猪");
    }
}

执行结果如下：
牛生活在陆地
马生活在陆地
羊生活在陆地

这时候发现并不是所有的动物都生活在陆地，比如鱼就生活在水中，如果我们遵循单一职责需要把动物类
细分为 陆地生物Terrestrial，和水生动物Aquatic，代码如下：
class Terrestrial{
    public void live(String animal){
        System.out.println(animal+" 生活在陆地");
    }
}
class Aquatic {
    public void live(String animal){
        System.out.println(animal+" 生活在水中");
    }
}
public class ClientChange1 {
    public static void main(String[] args) {
        Terrestrial terrestrial = new Terrestrial();
        terrestrial.live("牛");
        terrestrial.live("马");
        terrestrial.live("羊");

        Aquatic aquatic = new Aquatic();
        aquatic.live("鱼");
    }
}

执行结果：
牛 生活在陆地
马 生活在陆地
羊 生活在陆地
鱼 生活在水中

我们发现这样修改花费很大，不仅需要把原来的类分解成为2个类，还需要修改客户端，而直接
修改animal 类 虽然违背了单一原则，但是，修改花费的代价很小，代码如下：
class Animal {
    public void live(String animal){
        if("鱼".equals(animal)){
            System.out.println(animal+"生活在水中");
        }else {
            System.out.println(animal+"生活在陆地");
        }

    }
}

public class Client{
    public static void main(String[] args) {
        Animal1 animal = new Animal();
        animal.live("牛");
        animal.live("马");
        animal.live("猪");
        animal.live("鱼");
    }
}
我们可以看到这种修改，很简单但是却存在这隐患，如果有一天需要区分生活在淡水中的鱼 和 海水中的鱼
就需要修改 animal 类的live方法，而对原有的方法修改，会对调用“牛”，“马",”羊“带来风险。这种
修改直接在代码层面违背了单一职责，虽然简单，但是隐患很大，还有一种修改方式：
class Animal{
    public void live(String animal){
        System.out.println(animal+"生活在陆地");
    }
     public void live1(String animal){
            System.out.println(animal+"生活在水中");
        }
}

public class Client{
    public static void main(String[] args) {
        Animal1 animal = new Animal();
        animal.live("牛");
        animal.live("马");
        animal.live("猪");
        animal.live1("鱼");
    }
}
我们可以看到，我们没有修改原有的方法，而是新增了一个新的方法，这样虽然也违背了单一职责的原则，但是
在方法层面上是遵循单一职责的。这三种方式各有优缺点，具体如何选择，需要视情况而定。我们可以使用如下原则：
如果代码足够简单可以在代码级别上违背 单一职责，只要方法足够少就可以在方法级别上违背单一职责原则
  例如本文所举的这个例子，它太简单了，它只有一个方法，所以，无论是在代码级别上违反单一职责原则，
  还是在方法级别上违反，都不会造成太大的影响。实际应用中的类都要复杂的多，一旦发生职责扩散而需要修改类时，除非这个类本身非常简单，否则还是遵循单一职责原则的好。
遵循单一职责的优点如下：
1.可以减低类的负责读，一个类只负责一项职责，其逻辑会比负责多个职责的类简单
2.提高类的可读行，提高系统的可维护性
3.变更引起的风险降低，变更是必然的，如果单一职责原则遵守的好，当修改一个功能时，可以显著降低对其他功能的影响