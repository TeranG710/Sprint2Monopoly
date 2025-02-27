package Model;

public class GameBoard {
    
    private static final int NUM_SPACES = 40;
    private Element[] boardElements;

    public GameBoard() {
        boardElements = new Element[NUM_SPACES];
        initializeBoard();
    }

    public Element[] getBoardElements() {
        return boardElements;
    }

    private void initializeBoard() {
        boardElements[0] = new Element("GO", 0);
        boardElements[1] = new Element("Mediterranean Avenue", 1);
        boardElements[2] = new Element("Community Chest", 2);
        boardElements[3] = new Element("Baltic Avenue", 3);
        boardElements[4] = new Element("Income Tax", 4);
        boardElements[5] = new Element("Reading Railroad", 5);
        boardElements[6] = new Element("Oriental Avenue", 6);
        boardElements[7] = new Element("Chance", 7);
        boardElements[8] = new Element("Vermont Avenue", 8);
        boardElements[9] = new Element("Connecticut Avenue", 9);
        boardElements[10] = new Element("Jail / Just Visiting", 10);
        boardElements[11] = new Element("St. Charles Place", 11);
        boardElements[12] = new Element("Electric Company", 12);
        boardElements[13] = new Element("States Avenue", 13);
        boardElements[14] = new Element("Virginia Avenue", 14);
        boardElements[15] = new Element("Pennsylvania Railroad", 15);
        boardElements[16] = new Element("St. James Place", 16);
        boardElements[17] = new Element("Community Chest", 17);
        boardElements[18] = new Element("Tennessee Avenue", 18);
        boardElements[19] = new Element("New York Avenue", 19);
        boardElements[20] = new Element("Free Parking", 20);
        boardElements[21] = new Element("Kentucky Avenue", 21);
        boardElements[22] = new Element("Chance", 22);
        boardElements[23] = new Element("Indiana Avenue", 23);
        boardElements[24] = new Element("Illinois Avenue", 24);
        boardElements[25] = new Element("B. & O. Railroad", 25);
        boardElements[26] = new Element("Atlantic Avenue", 26);
        boardElements[27] = new Element("Ventnor Avenue", 27);
        boardElements[28] = new Element("Water Works", 28);
        boardElements[29] = new Element("Marvin Gardens", 29);
        boardElements[30] = new Element("Go To Jail", 30);
        boardElements[31] = new Element("Pacific Avenue", 31);
        boardElements[32] = new Element("North Carolina Avenue", 32);
        boardElements[33] = new Element("Community Chest", 33);
        boardElements[34] = new Element("Pennsylvania Avenue", 34);
        boardElements[35] = new Element("Short Line", 35);
        boardElements[36] = new Element("Chance", 36);
        boardElements[37] = new Element("Park Place", 37);
        boardElements[38] = new Element("Luxury Tax", 38);
        boardElements[39] = new Element("Boardwalk", 39);
    }


}
