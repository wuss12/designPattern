package dependenceInversion;

public class Client {
    public static void main(String[] args){
        Mother mother = new Mother();
        mother.narrate(new Book());
    }
}
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
