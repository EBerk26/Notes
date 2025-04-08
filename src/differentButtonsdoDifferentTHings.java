import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class differentButtonsdoDifferentTHings implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton button1;
    JButton button2;
    JTextArea textArea;
    public differentButtonsdoDifferentTHings() {
        frame = new JFrame();
        frame.setSize(900,400);
        panel = new JPanel(new GridLayout(3,1));
        frame.add(panel);
        button1 = new JButton("button1");
        button2 = new JButton("button2");
        textArea = new JTextArea();
        panel.add(textArea);
        panel.add(button1);
        panel.add(button2);

        button1.addActionListener(this);
        button2.addActionListener(this);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new differentButtonsdoDifferentTHings();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button1)){
            textArea.setText(textArea.getText()+"button1");
        }
        if(e.getSource().equals(button2)){
            textArea.setText(textArea.getText()+"button2");
        }

    }
}
