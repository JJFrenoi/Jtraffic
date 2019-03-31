import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Frame extends JFrame {

    // public JTextField textField = new JTextField(20); // creation 'un champ de
    // saisie
    // public ImageIcon image = new ImageIcon("img/gcars.png");
    // public JButton bouton = new JButton(image);

    public Mainpanel panneau = new Mainpanel();

    public Frame() {

        super("DOUDY LA MECHANTE");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panneau);

        pack();
        setVisible(false);
        setSize(1600, 840);

    }

    public void display() {
        setVisible(true);
    }

}