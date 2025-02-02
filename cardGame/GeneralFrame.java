import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralFrame extends JFrame implements completeCheck{
    CardLayout cardLayout;
    CardLayout gameLayout;
    Container container;
    Container gameContainer;
    Menu menu;
    Levels levels;
    Level1 level1;
    Level1 level2;
    Level1 level3;
    Level1 standardGame;

    public GeneralFrame(String title) {
        setTitle(title);
        container = getContentPane();
        cardLayout = new CardLayout();
        container.setLayout(cardLayout);

        menu = new Menu();
        levels = new Levels();
        level1 = new Level1(1,this);
        level2 = new Level1(3,this);
        level3 = new Level1(4,this);
        standardGame = new Level1(2,this);

        container.add(menu, "menu");
        container.add(levels, "levels");
        container.add(level1,"level1");
        container.add(level2,"level2");
        container.add(level3,"level3");
        container.add(standardGame,"standardGame");
        menu.selectLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, "levels");
            }
        });

        levels.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, "menu");
            }
        });

        menu.startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameContainer != null) {
                    container.remove(gameContainer);
                }
                gameContainer = new Container();
                gameLayout = new CardLayout();
                gameContainer.setLayout(gameLayout);
                gameContainer.add(standardGame, "standardGame");
                container.add(gameContainer, "game");
                cardLayout.show(container, "game");
            }
        });
        levels.level1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container,"level1");
            }
        });
        levels.level2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container,"level2");
            }
        });
        levels.level3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container,"level3");
            }
        });

        setSize(500, 500);
        setVisible(true);
    }

    @Override
    public void onCompilation() {
        cardLayout.show(container,"menu");
    }
}
