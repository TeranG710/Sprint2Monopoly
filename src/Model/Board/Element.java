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
     * Team member(s) responsible: Deborah
     * */
    public String getName() {
        return name;
    }

    /**
     * Returns position of the element
     * Team member(s) responsible: Deborah
     * */
    public int getPosition() {
        return position;
    }


}
