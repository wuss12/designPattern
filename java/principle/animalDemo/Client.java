package principle.animalDemo;

 class Animal {
    public void live(String animal){
        System.out.println(animal+"生活在陆地");
    }
}

public class Client{
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.live("牛");
        animal.live("马");
        animal.live("猪");
    }
}
