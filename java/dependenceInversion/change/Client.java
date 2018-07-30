package dependenceInversion.change;

public class Client {
    public static void main(String[] args) {
        Mother m = new Mother();
        m.narrate(new Book());
        m.narrate(new Newspaper());
    }
}
