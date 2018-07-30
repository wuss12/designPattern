package principle.animalDemo;

 class Animal1 {
    public void live(String animal){
        if("鱼".equals(animal)){
            System.out.println(animal+"生活在水中");
        }else {
            System.out.println(animal+"生活在陆地");
        }

    }
}

public class Client1{
    public static void main(String[] args) {
        Animal1 animal = new Animal1();
        animal.live("牛");
        animal.live("马");
        animal.live("猪");
        animal.live("鱼");
    }
}
