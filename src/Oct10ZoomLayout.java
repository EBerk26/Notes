import javax.swing.*;
import java.awt.*;

public class Oct10ZoomLayout {
    public static void main(String[] args) {
        new Oct10ZoomLayout();
    }
    public Oct10ZoomLayout() {
        JFrame frame = new JFrame("Zoom - Personal Meeting ID");
        Dimension dimension = new Dimension(376,448);
        frame.setMinimumSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel panel = new JPanel(new GridLayout(6,1));
        frame.add(panel);

        JPanel part2 = new JPanel();
        JLabel part2Heading = new JLabel("Personal Meeting ID");
        part2Heading.setFont(part2Heading.getFont().deriveFont(Font.BOLD, 14));
        part2Heading.setHorizontalTextPosition(SwingConstants.LEFT);
        part2.add(part2Heading);



        JLabel heading = new JLabel("Personal Meeting ID Settings");
        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 20));


        panel.add(heading);
        panel.add(part2);


        frame.setVisible(true);
    }


}
