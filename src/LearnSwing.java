import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LearnSwing implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton button1;
    private JLabel label;
    private JTextArea textArea;
    private JButton button2;
    int clickcounter = 0;
    Dimension dimension = new Dimension();
    public static void main(String[] args) {
        new LearnSwing();
    }
    LearnSwing() {
        dimension.width = 700;
        dimension.height = 400;
        frame = new JFrame();
        frame.setSize(700,400);
        frame.setMinimumSize(dimension);
        frame.setMaximumSize(dimension);

        panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        frame.add(panel);

        button1 = new JButton("Click me!");
        button1.addActionListener(this);
        panel.add(button1);

        label = new JLabel("<-- This is a button.");
        panel.add(label);

        textArea = new JTextArea("Type here if you want");
        panel.add(textArea);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        clickcounter++;
        if(clickcounter!=1) {
            label.setText("<-- This is a button. It has been clicked " + clickcounter + " times.");
        } else {
            label.setText("<-- This is a button. It has been clicked 1 time.");
        }
    }
}
