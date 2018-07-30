package dependenceInversion.change;

public class Mother {
    public void narrate(Reader r){
        System.out.println("妈妈开始讲故事:");
        System.out.println(r.getContent());
    }
}
