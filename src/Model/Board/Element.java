package Model.Board;

public class Element {
    private String name;
    private int position;

    public Element(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }


}
