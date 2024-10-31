import javax.swing.*;
import java.awt.*;

public class BorderLayout {
    public static void main(String[] args) {
        new BorderLayout();
    }
    JFrame frame = new JFrame("Border Layout Example");
    BorderLayout(){
        frame.setVisible(true);
        frame.setSize(700,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new java.awt.BorderLayout());
        frame.add(mainPanel);

        JPanel miniPanel = new JPanel(new GridLayout(2,3));
        mainPanel.add(miniPanel, java.awt.BorderLayout.CENTER);

        JButton button[] = new JButton[6];
        for(int x = 1;x<=5;x++){
            button[x] = new JButton("Button "+x);
        }
        JLabel label[] = new JLabel[5];
        for(int x = 1;x<=4;x++){
            label[x] = new JLabel("Label "+x);
            label[x].setHorizontalAlignment(SwingConstants.CENTER);

        }

        mainPanel.add(button[1], java.awt.BorderLayout.NORTH);
        miniPanel.add(button[2]);
        miniPanel.add(label[1]);
        miniPanel.add(button[4]);
        miniPanel.add(label[2]);
        miniPanel.add(button[5]);
        mainPanel.add(button[3], java.awt.BorderLayout.SOUTH);
    }
}
