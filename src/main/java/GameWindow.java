import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame {

    public static GameWindow gameWindow;
    private static Image backGround;
    private static Image drop;
    private static Image gameOver;
    private static float dropLeft = -100;
    private static float dropTop = 200;
    private static float dropV = 10;
    private static long lastFrameTime;

    public static void main(String[] args) throws IOException {
        backGround = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(906, 478);
        gameWindow.setResizable(false);
        lastFrameTime = System.nanoTime();
        GameField gameField = new GameField();
        gameWindow.add(gameField);
        gameWindow.setVisible(true);
    }

    private static void onRepaint(Graphics g) {
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        dropTop = dropTop + dropV * deltaTime;
        g.drawImage(backGround, 0, 0, null);
        g.drawImage(drop, (int) dropLeft, (int) dropTop, null);
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
