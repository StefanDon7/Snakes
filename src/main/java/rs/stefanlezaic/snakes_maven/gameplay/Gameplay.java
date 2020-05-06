package rs.stefanlezaic.snakes_maven.gameplay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private int lengthofSnake = 3;

    private Timer timer;
    private int delay = 90;
    private ImageIcon snakeimage;

    private int[] enemyXpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 400, 425, 450, 475,
        500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 400, 425, 450, 475, 500, 525,
        550, 575, 600, 625};

    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    private int score = 0;

    private int moves = 0;

    private ImageIcon titleImage;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g) {
        if (moves == 0) {
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;

            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;

        }
        g.setColor(Color.white);// postaviti okvir
        g.drawRect(24, 10, 851, 55);// duzina i sirina margine i duzina i sirina naslova

        titleImage = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/snaketitle.jpg"));// dodajemo ikonicu
        titleImage.paintIcon(this, g, 25, 11);
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);// okvir u kome ce da se krece zmija

        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);// a pozadina ce biti crna

        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores:" + score, 780, 30);

        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length:" + lengthofSnake, 780, 50);

        rightmouth = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/rightmouth.png"));
        rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);

        for (int a = 0; a < lengthofSnake; a++) {
            if (a == 0 && right) {
                rightmouth = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/rightmouth.png"));
                rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a == 0 && left) {
                leftmouth = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/leftmouth.png"));
                leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a == 0 && down) {
                downmouth = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/downmouth.png"));
                downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a == 0 && up) {
                upmouth = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/upmouth.png"));
                upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
            if (a != 0) {
                snakeimage = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/snakeimage.png"));
                snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
            }
        }
        enemyimage = new ImageIcon(getClass().getResource("/rs/stefanlezaic/snakes_maven/icons/enemy.png"));
        if ((enemyXpos[xpos] == snakeXlength[0]) && enemyYpos[ypos] == snakeYlength[0]) {
            lengthofSnake++;
            score++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);

        for (int b = 1; b < lengthofSnake; b++) {
            if (snakeXlength[b] == snakeXlength[0] && snakeYlength[b] == snakeYlength[0]) {
                right = false;
                left = false;
                up = false;
                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("GameOver", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Space to RESTART", 350, 340);
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int r = lengthofSnake - 1; r >= 0; r--) {
                snakeYlength[r + 1] = snakeYlength[r];
            }
            for (int r = lengthofSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeXlength[r] = snakeXlength[r] + 25;
                } else {
                    snakeXlength[r] = snakeXlength[r - 1];
                }
                if (snakeXlength[r] > 850) {
                    snakeXlength[r] = 25;
                }
            }
            repaint();
        }
        if (left) {
            for (int r = lengthofSnake - 1; r >= 0; r--) {
                snakeYlength[r + 1] = snakeYlength[r];
            }
            for (int r = lengthofSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeXlength[r] = snakeXlength[r] - 25;
                } else {
                    snakeXlength[r] = snakeXlength[r - 1];
                }
                if (snakeXlength[r] < 25) {
                    snakeXlength[r] = 850;
                }
            }
            repaint();

        }
        if (up) {
            for (int r = lengthofSnake - 1; r >= 0; r--) {
                snakeXlength[r + 1] = snakeXlength[r];
            }
            for (int r = lengthofSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeYlength[r] = snakeYlength[r] - 25;
                } else {
                    snakeYlength[r] = snakeYlength[r - 1];
                }
                if (snakeYlength[r] < 75) {
                    snakeYlength[r] = 625;
                }
            }
            repaint();
        }
        if (down) {
            for (int r = lengthofSnake - 1; r >= 0; r--) {
                snakeXlength[r + 1] = snakeXlength[r];
            }
            for (int r = lengthofSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeYlength[r] = snakeYlength[r] + 25;
                } else {
                    snakeYlength[r] = snakeYlength[r - 1];
                }
                if (snakeYlength[r] > 625) {
                    snakeYlength[r] = 75;
                }
            }
            repaint();

        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            lengthofSnake = 3;
            repaint();

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            if (!left) {
                right = true;
                up = false;
                down = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            left = true;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }

            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }

            right = false;
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }

            right = false;
            left = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
