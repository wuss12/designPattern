package dependenceInversion.change;

public class Newspaper implements Reader {
    @Override
    public String getContent() {
        return "卖火柴的小女孩";
    }
}
