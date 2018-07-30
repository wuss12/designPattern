package principle.animalDemo;
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

