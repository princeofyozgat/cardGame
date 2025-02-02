import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Level1 extends JPanel {
    JButton[] cards;
    Icon[] icons;
    int[] valCards;
    boolean[] revealedCard;
    int fIndex = -1;
    int sIndex = -1;
    int attempts;
    int match = 0;
    Icon icon;
    int mode;
    int modeTemp;
    completeCheck listener;
    JLabel levelLabel;
    JLabel attemptsLabel;
    JMenuBar menuBar;
    JPanel finalPanel;
    int score;
    File highScore = new File("./src/highscore.txt");
    public Level1(int mode, completeCheck listener) {
        this.mode = mode;
        this.listener = listener;
        score = 0;
        modeTemp = mode;
        initAttempt();
        setLayout(new BorderLayout());
        menuBar = new JMenuBar();
        JMenu game = new JMenu("Game");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem highscores = new JMenuItem("High Scores");
        game.add(restart);
        game.add(highscores);
        JMenu about = new JMenu("About");
        JMenuItem aboutGame = new JMenuItem("About the Game");
        JMenuItem aboutDeveloper = new JMenuItem("About the Developer");
        about.add(aboutGame);
        about.add(aboutDeveloper);
        JMenu exitMenu = new JMenu("Exit");
        JMenuItem exit = new JMenuItem("Exit");
        exitMenu.add(exit);
        menuBar.add(game);
        menuBar.add(about);
        menuBar.add(exitMenu);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        aboutGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"This game is a Java project.","About",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        aboutDeveloper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Created by Hasan Güzelşemme.","Creator",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        highscores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path fileName = Path.of(".\\src\\highscore.txt");
                String str;
                try {
                    str = Files.readString(fileName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null,str,"High Scores",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onCompilation();
            }
        });
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setBackground(Color.BLUE);
        if (mode == 2) levelLabel = new JLabel("LEVEL " + (modeTemp - 1));
        else if (mode == 3 || mode == 4) levelLabel = new JLabel("LEVEL " + (mode - 1));
        else levelLabel = new JLabel("LEVEL " + mode);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        attemptsLabel = new JLabel("Tries left: " + attempts);
        attemptsLabel.setForeground(Color.WHITE);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());
        finalPanel.add(menuBar,BorderLayout.NORTH);
        topPanel.add(levelLabel);
        topPanel.add(attemptsLabel);
        finalPanel.add(topPanel,BorderLayout.SOUTH);
        add(finalPanel, BorderLayout.NORTH);
        JPanel cardPanel = new JPanel(new GridLayout(4, 4));
        add(cardPanel, BorderLayout.CENTER);

        initializeCards(mode, cardPanel);
        setup();

    }

    private void initAttempt() {
        if (mode == 2) {
            if (modeTemp == 2) attempts = 18;
            else if (modeTemp == 3) attempts = 15;
            else attempts = 12;
        } else {
            if (mode == 1) attempts = 18;
            if (mode == 3) attempts = 15;
            if (mode == 4) attempts = 12;
        }
    }

    private void initializeCards(int mode, JPanel cardPanel) {
        icons = new ImageIcon[8];
        if (modeTemp == 1 || modeTemp == 2) {
            icons[0] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/0.png");
            icons[1] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/1.png");
            icons[2] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/2.png");
            icons[3] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/3.png");
            icons[4] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/4.png");
            icons[5] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/5.png");
            icons[6] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/6.png");
            icons[7] = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/7.png");
            icon = new ImageIcon("./src/Java Project Assets/Level1-InternetAssets/no_image.png");
        } else if (modeTemp == 3) {
            icons[0] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/0.png");
            icons[1] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/1.png");
            icons[2] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/2.png");
            icons[3] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/3.png");
            icons[4] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/4.png");
            icons[5] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/5.png");
            icons[6] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/6.png");
            icons[7] = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/7.png");
            icon = new ImageIcon("./src/Java Project Assets/Level2-CyberSecurityAssets/no_image.png");
        } else if (modeTemp == 4) {
            icons[0] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/0.png");
            icons[1] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/1.png");
            icons[2] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/2.png");
            icons[3] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/3.png");
            icons[4] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/4.png");
            icons[5] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/5.png");
            icons[6] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/6.png");
            icons[7] = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/7.png");
            icon = new ImageIcon("./src/Java Project Assets/Level3-GamingComputerAssets/no_image.png");
        }

        cards = new JButton[16];
        valCards = new int[16];
        revealedCard = new boolean[16];

        for (int i = 0; i < 16; i++) {
            cards[i] = new JButton();
            cards[i].addActionListener(new CardClickListener(i));
            cardPanel.add(cards[i]);
        }

        for (int i = 0; i < 16; i += 2) {
            valCards[i] = i / 2 + 1;
            valCards[i + 1] = i / 2 + 1;
        }

        shuffleCards();
    }

    private void setup() {
        fIndex = -1;
        sIndex = -1;
        match = 0;

        shuffleCards();

        for (int i = 0; i < 16; i++) {
            cards[i].setIcon(icon);
            revealedCard[i] = false;
        }

        revalidate();
        repaint();
    }

    private void shuffleCards() {
        Thread shuffleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                shuffle(valCards);
            }
        });
        shuffleThread.start();
        try {
            shuffleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }



    private void checkMatch() {

        if (fIndex != -1 && sIndex != -1) {
            if (valCards[fIndex] == valCards[sIndex]) {
                match++;
                if(mode == 2){
                    if(modeTemp == 2){
                        score += 5;
                    }
                    else if(modeTemp == 3){
                        score += 4;
                    }
                    else if(modeTemp == 4){
                        score += 3;
                    }
                }
                if (match == 8) {
                    if (mode == 1 || mode == 3 || mode == 4) {
                        JOptionPane.showMessageDialog(this, "Congrats you won!!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                        resetGame();
                        listener.onCompilation();
                    }
                    if (mode == 2) {
                        if (modeTemp == 4) {
                            JOptionPane.showMessageDialog(this, "Congrats you won!!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                            modeTemp = 2;
                            resetGame();
                            String name = JOptionPane.showInputDialog("Enter your username: ");

                            appendStrToFile("highscore.txt",name + " " + score + "\n");
                            Path fileName = Path.of(".\\src\\highscore.txt");
                            String str;
                            try {
                                str = Files.readString(fileName);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            int firstIndex = 0;
                            ArrayList<String> sortingList = new ArrayList<>();
                            for(int i = 0;i < str.length();i++){
                                if(str.charAt(i) == '\n'){
                                    sortingList.add(str.substring(firstIndex,i));
                                    firstIndex = i+1;
                                }
                            }
                            Collections.sort(sortingList,new ScoresComparator());
                            String sortedList = "";
                            if(sortingList.size() <= 10) {
                                for (int i = 0; i < sortingList.size(); i++) {
                                    sortedList += sortingList.get(i) + '\n';
                                }
                            }
                            else{
                                for (int i = 0; i < 10; i++) {
                                    sortedList += sortingList.get(i) + '\n';
                                }
                            }
                            try {
                                FileWriter f2 = new FileWriter(highScore, false);
                                f2.write(sortedList);
                                f2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            listener.onCompilation();
                        } else {
                            modeTemp++;
                            resetGame();
                        }
                    }
                }

                fIndex = -1;
                sIndex = -1;
            } else {
                if(mode == 2){
                    if(modeTemp == 2){
                        score -= 1;
                    }
                    else if(modeTemp == 3){
                        score -= 2;
                    }
                    else if(modeTemp == 4){
                        score -= 3;
                    }
                }

                Timer timer = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cards[fIndex].setIcon(icon);
                        cards[sIndex].setIcon(icon);
                        revealedCard[fIndex] = false;
                        revealedCard[sIndex] = false;
                        fIndex = -1;
                        sIndex = -1;

                        if (modeTemp == 4) {
                            shuffleCards();
                            setup();
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();

                attempts--;
            }

            attemptsLabel.setText("Tries left: " + attempts);

            if (attempts == 0 && match != 8) {
                if (mode == 1 || mode == 3 || mode == 4) {
                    JOptionPane.showMessageDialog(this, "You lost.", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                    resetGame();
                } else {
                    JOptionPane.showMessageDialog(this, "You lost.", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                    modeTemp = 2;
                    resetGame();
                }
            }
        }
        for(int i = 0;i < cards.length;i++){
            cards[i].setEnabled(true);
        }
    }

    private void resetGame() {
        score = 0;
        removeAll();
        initAttempt();
        menuBar = new JMenuBar();
        JMenu game = new JMenu("Game");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem highscores = new JMenuItem("High Scores");
        game.add(restart);
        game.add(highscores);
        JMenu about = new JMenu("About");
        JMenuItem aboutGame = new JMenuItem("About the Game");
        JMenuItem aboutDeveloper = new JMenuItem("About the Developer");
        about.add(aboutGame);
        about.add(aboutDeveloper);
        JMenu exitMenu = new JMenu("Exit");
        JMenuItem exit = new JMenuItem("Exit");
        exitMenu.add(exit);
        menuBar.add(game);
        menuBar.add(about);
        menuBar.add(exitMenu);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        aboutGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"This game is a Java project.","About",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        aboutDeveloper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Created by Hasan Güzelşemme.","Creator",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        highscores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path fileName = Path.of(".\\src\\highscore.txt");
                String str;
                try {
                    str = Files.readString(fileName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null,str,"High Scores",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onCompilation();
            }
        });
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setBackground(Color.BLUE);
        if (mode == 2) levelLabel = new JLabel("LEVEL " + (modeTemp - 1));
        else if (mode == 3 || mode == 4) levelLabel = new JLabel("LEVEL " + (mode - 1));
        else levelLabel = new JLabel("LEVEL " + mode);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        attemptsLabel = new JLabel("Tries left: " + attempts);
        attemptsLabel.setForeground(Color.WHITE);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(levelLabel);
        topPanel.add(attemptsLabel);
        finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());
        finalPanel.add(menuBar,BorderLayout.NORTH);
        finalPanel.add(topPanel,BorderLayout.SOUTH);
        add(finalPanel, BorderLayout.NORTH);
        JPanel cardPanel = new JPanel(new GridLayout(4, 4));
        add(cardPanel, BorderLayout.CENTER);

        if (mode == 2) initializeCards(modeTemp, cardPanel);
        else initializeCards(mode, cardPanel);

        setup();
    }
    private class CardClickListener implements ActionListener {
        private int index;

        public CardClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!revealedCard[index]) {
                revealedCard[index] = true;
                cards[index].setIcon(icons[valCards[index] - 1]);
                if (fIndex == -1) {
                    fIndex = index;
                } else if (sIndex == -1) {
                    sIndex = index;

                    Timer timer = new Timer(500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(int i = 0;i < cards.length;i++){
                                cards[i].setEnabled(false);
                            }
                            checkMatch();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    for(int i = 0;i < cards.length;i++){
                        cards[i].setEnabled(true);
                    }
                }


            }
        }
    }
    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            writer.write(str);
            writer.newLine();

            writer.close();
        }
        catch (IOException e) {
            System.out.println("Exception occurred: " + e);
        }
    }
    private class ScoresComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            int startIndex = 0;
            int i;
            for (i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) >= '0' && o1.charAt(i) <= '9') {
                    startIndex = i;
                }
            }
            int int1 = Integer.parseInt(o1.substring(startIndex, i));
            startIndex = 0;
            for (i = 0; i < o2.length(); i++) {
                if (o2.charAt(i) >= '0' && o2.charAt(i) <= '9') {
                    startIndex = i;
                }
            }
            int int2 = Integer.parseInt(o2.substring(startIndex, i));
            if(int1 > int2) return -1;
            else if(int1 < int2) return 1;
            else return 0;
        }
    }
}

