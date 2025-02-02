import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    Image backgroundImg;
    JButton startGame;
    JButton instructions;
    JButton exit;
    JButton selectLevel;
    JLabel title;
    JPanel invisiblePanel;
    public Menu() {
        invisiblePanel = new JPanel();
        invisiblePanel.setLayout(new BoxLayout(invisiblePanel,BoxLayout.Y_AXIS));
        startGame = new JButton("Start Game");
        selectLevel = new JButton("Select Level");
        instructions = new JButton("Instructions");
        exit = new JButton("Exit");
        backgroundImg = new ImageIcon("./src/Java Project Assets/background.jpg").getImage();
        title = new JLabel("Memory Card Game");
        title.setFont(new Font("Arial",Font.ITALIC,28));
        title.setForeground(Color.CYAN);
        startGame.setSize(100,20);
        selectLevel.setSize(100,20);
        instructions.setSize(100,20);
        exit.setSize(100,20);
        invisiblePanel.setLayout(new BoxLayout(invisiblePanel,BoxLayout.Y_AXIS));
        invisiblePanel.setOpaque(false);
        invisiblePanel.add(title);
        invisiblePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        invisiblePanel.add(startGame);
        invisiblePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        invisiblePanel.add(selectLevel);
        invisiblePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        invisiblePanel.add(instructions);
        invisiblePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        invisiblePanel.add(exit);
        setLayout(new GridBagLayout());
        add(invisiblePanel, new GridBagConstraints());

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Instructions:\nThere are 3 levels in game. It gets gradually harder!\nMatch all pairs of cards to win!","Message",JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);

    }

}