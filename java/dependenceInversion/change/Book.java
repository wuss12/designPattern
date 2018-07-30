package dependenceInversion.change;

public class Book implements Reader {
    @Override
    public String getContent() {
        return "从前有座山...";
    }
}
