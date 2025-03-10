package Model.Board;

public class Element {
    private String name;
    private int position;

    public Element(String name, int position) {
        this.name = name;
        this.position = position;
    }

    /**
     * Returns name of the element
     * */
    public String getName() {
        return name;
    }

    /**
     * Returns position of the element
     * */
    public int getPosition() {
        return position;
    }


}
