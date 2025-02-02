import javax.swing.*;
import java.awt.*;

public class Levels extends JPanel {
    Image backgroundImg;
    JButton level1;
    JButton level2;
    JButton level3;
    JButton back;

    public Levels() {
        backgroundImg = new ImageIcon("./src/Java Project Assets/background.jpg").getImage();

        setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        level1 = new JButton("Level 1");
        level2 = new JButton("Level 2");
        level3 = new JButton("Level 3");
        back = new JButton("Back");

        level1.setSize(50,20);
        level2.setSize(50,20);
        level3.setSize(50,20);
        back.setSize(50,20);

        panel.add(level1);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(level2);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(level3);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(back);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        add(panel,new GridBagConstraints());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
    }
}
