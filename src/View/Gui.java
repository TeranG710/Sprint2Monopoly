package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Gui class creates the graphical user interface for the Monopoly game.
 */
public class Gui {

    /**
     * The main method to start the GUI application.
     *
     * @param args Command line arguments.
     *             Team member(s) responsible: Jamell
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Monopoly Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    /**
     * Places the components on the panel.
     *
     * @param panel The panel to place components on.
     *              Team member(s) responsible: Jamell
     */
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(100, 100, 100, 40);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Started!");
            }
        });

        panel.add(startButton);
    }
}