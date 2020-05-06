package rs.stefanlezaic.snakes_maven.gameplay;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();

        obj.setBounds(10, 10, 905, 700);//velicina prozora
        obj.setBackground(Color.DARK_GRAY);//boja pozadine
        obj.setResizable(false);//da ne moze da se prosiri
        obj.setVisible(true);//da bude vidljivo
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//da izlaziNa X
        obj.add(gameplay);
    }
}
