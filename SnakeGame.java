import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int CELL_SIZE = 20;
    private final LinkedList<Point> snake;
    private Point food;
    private char direction;
    private boolean gameOver;
    private final Timer timer;

    public SnakeGame() {
        snake = new LinkedList<>();
        snake.add(new Point(5, 5));
        direction = 'R';
        gameOver = false;

        // Initialize JFrame
        setTitle("Snake Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: if (direction != 'D') direction = 'U'; break;
                    case KeyEvent.VK_DOWN: if (direction != 'U') direction = 'D'; break;
                    case KeyEvent.VK_LEFT: if (direction != 'R') direction = 'L'; break;
                    case KeyEvent.VK_RIGHT: if (direction != 'L') direction = 'R'; break;
                }
            }
        });

        // Timer for game loop
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    moveSnake();
                    checkCollision();
                    repaint();
                }
            }
        });
        timer.start();
        spawnFood();

        setVisible(true);
    }

    private void moveSnake() {
        Point head = snake.getFirst();
        Point newHead = new Point(head);

        switch (direction) {
            case 'U': newHead.translate(0, -1); break;
            case 'D': newHead.translate(0, 1); break;
            case 'L': newHead.translate(-1, 0); break;
            case 'R': newHead.translate(1, 0); break;
        }

        snake.addFirst(newHead);

        // Check if snake has eaten the food
        if (newHead.equals(food)) {
            spawnFood(); // Spawn new food
        } else {
            snake.removeLast(); // Remove the tail
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(WIDTH / CELL_SIZE);
            y = rand.nextInt(HEIGHT / CELL_SIZE);
            food = new Point(x, y);
        } while (snake.contains(food));
    }

    private void checkCollision() {
        Point head = snake.getFirst();
        // Check if snake collides with the walls or itself
        if (head.x < 0 || head.x >= WIDTH / CELL_SIZE || head.y < 0 || head.y >= HEIGHT / CELL_SIZE || snake.subList(1, snake.size()).contains(head)) {
            gameOver = true;
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Your score: " + (snake.size() - 1));
            System.exit(0);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
        g.setColor(Color.RED);
        g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
