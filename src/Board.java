import javax.swing.*;
import java.awt.*;

public class Board {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 2000);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(8, 8));
    }
}
